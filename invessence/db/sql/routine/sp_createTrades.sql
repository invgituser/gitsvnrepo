DROP PROCEDURE IF EXISTS `sp_createTrades`;

DELIMITER $$
CREATE PROCEDURE `sp_createTrades`(
	p_acctnum BIGINT(20))
BEGIN
	DECLARE vCustomerType varchar(40);
	DECLARE vToday varchar(10);
	DECLARE vCount varchar(10);
	DECLARE vBusinessDate varchar(10);

	begin

		set vBusinessDate = funct_get_switch('BROKER_BDATE');

		SELECT `acctType`
		INTO vCustomerType
		FROM `user_trade_profile`
		WHERE acctnum = p_acctnum;

		SELECT count(*)
		INTO vCount
		FROM `virtual_portfolio`
		WHERE `acctnum` = p_acctnum
		AND `active` = 'A';

		IF (IFNULL(vCount,0) > 0)
			THEN
				begin
					DELETE FROM pretrade_details
					WHERE acctnum = p_acctnum;

				INSERT INTO `invdb`.`pretrade_details`
					(`acctnum`,
					`clientAccountID`,
					`name`,
					`tradedate`,
					`ticker`,
					`accttype`,
					`currentqty`,
					`costBasisPrice`,
					`costBasisMoney`,
					`currentValue`,
					`pnl`,
					`newqty`,
					`newValue`,
					`tradeqty`,
					`tradeprice`,
					`tradeValue`,
					`priceperShare`,
					`realizedPnL`,
					`percentAllocated`
					)
					SELECT `virtual_portfolio`.`acctnum` as acctnum,
						`IB_Accounts`.`IB_acctnum` as clientAccountID, 
						concat(`IB_Accounts`.firstname," ",`IB_Accounts`.lastname) as name,
						vBusinessDate as tradedate,
						`virtual_portfolio`.`ticker` as ticker,
						vCustomerType as accttype,
						SUM(IFNULL(`position`.`quantity`,0)) as currentqty,
						SUM(IFNULL(`position`.`costBasisPrice`,0)) as costBasisPrice,
						SUM(IFNULL(`position`.`costBasisMoney`,0)) as costBasisMoney,
						SUM(IFNULL(`position`.`positionValue`,0)) as currentValue,
						SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) as pnl,
						SUM(`virtual_portfolio`.`qty`) as newqty,
						SUM(`virtual_portfolio`.`investmentvalue`) as newValue,
						(SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))) as tradeQty,
						`virtual_portfolio`.`tradeprice` as tradeprice,
						`virtual_portfolio`.`tradeprice` * (SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))) as tradeValue,
						CASE WHEN (SUM(IFNULL(`position`.`quantity`,0)) = 0) THEN 0
							 ELSE SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) / SUM(IFNULL(`position`.`quantity`,0))
						END as priceperShare,
						CASE WHEN (SUM(IFNULL(`position`.`quantity`,0)) = 0) THEN 0
							 ELSE CASE WHEN ((SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))) < 0)
											THEN (-1 * (SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) / SUM(IFNULL(`position`.`quantity`,0))) *
														(SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))))
											ELSE 0
								   END
						END as realizedPnL,
						SUM(`virtual_portfolio`.`investmentvalue`)/funct_get_actualCapital(`virtual_portfolio`.`acctnum`) as percentAllocated
					FROM `virtual_portfolio`
					LEFT JOIN `IB_Accounts`
					ON (`virtual_portfolio`.`acctnum` = `IB_Accounts`.`acctnum`)
					LEFT JOIN `position`
					ON (`position`.`clientAccountID` = `IB_Accounts`.`IB_acctnum`
					AND `virtual_portfolio`.`ticker` = `position`.`symbol`
					AND `position`.`reportDate` = vBusinessDate)
					WHERE `virtual_portfolio`.`acctnum` = p_acctnum
					GROUP BY `virtual_portfolio`.`acctnum`,
						`virtual_portfolio`.`ticker`
					;

					-- Insert other positions that are not being traded.
					INSERT INTO `pretrade_details`
					(`acctnum`,
					`clientAccountID`,
					`name`,
					`tradedate`,
					`ticker`,
					`accttype`,
					`currentqty`,
					`costBasisPrice`,
					`costBasisMoney`,
					`currentValue`,
					`pnl`,
					`newqty`,
					`newValue`,
					`tradeqty`,
					`tradeprice`,
					`tradeValue`,
					`priceperShare`,
					`realizedPnL`,
					`percentAllocated`
					)
					SELECT `IB_Accounts`.`acctnum`,
						`IB_Accounts`.`IB_acctnum`,
						concat(`IB_Accounts`.firstname," ",`IB_Accounts`.lastname) as name,
						vBusinessDate as tradedate,
						`position`.`symbol` as ticker,
						vCustomerType as accttype,
						SUM(IFNULL(`position`.`quantity`,0)) as currentqty,
						SUM(IFNULL(`position`.`costBasisPrice`,0)) as costBasisPrice,
						SUM(IFNULL(`position`.`costBasisMoney`,0)) as costBasisMoney,
						SUM(IFNULL(`position`.`positionValue`,0)) as currentValue,
						SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) as pnl,
						0 as newqty,
						0 as newValue,
						0 as tradeQty, 
						MAX(`position`.`markprice`) as tradeprice,
						0 as tradeValue,
						0 as priceperShare,
						0 as realizedPnL,
						0.0 as percentAllocated
					FROM `position`,
						 `IB_Accounts`
					WHERE `IB_Accounts`.`acctnum` = p_acctnum
					AND   `IB_Accounts`.IB_acctnum = `position`.`clientAccountID`
					AND `position`.reportDate = vBusinessDate
					AND  not Exists
							(SELECT * 
							 FROM `virtual_portfolio`
							 WHERE `virtual_portfolio`.`acctnum` = `IB_Accounts`.`acctnum`
							 AND `virtual_portfolio`.`ticker` = `position`.`symbol`)
					group by 
						`IB_Accounts`.`acctnum`,
						`IB_Accounts`.`IB_acctnum`,
						`position`.`symbol`
					;

					commit;

					call sp_create_Trades_adjustments(p_acctnum, vCustomerType);

					DELETE FROM user_trade_log
					WHERE acctnum = p_acctnum
					AND `tradeStatus` in ('P', 'T');

						INSERT INTO `user_trade_log`
						(`acctnum`,
						`clientAccountID`,
						`tradeStatus`,
						`tradedate`,
						`ticker`,
						`action`,
						`sectype`,
						`exchange`,
						`currency`,
						`timeinforce`,
						`qty`,
						`tradeprice`,
						`investmentamount`,
						`tradeID`,
						`ordertype`,
						`confirmationID`,
						`created`,
						`lastupdated`)
						SELECT 
							`acctnum`,
							`clientAccountID`,
							'P' as tradeStatus,
							`tradedate`,
							`ticker` as ticker,
							case when (`tradeQty` >= 0) THEN 'BUY'
								 else 'SELL'
							end as action,
							'STK' as sectype,
							'ANY' as exchange,
							'USD' as currency,
							'DAY' as timeinforce,
							IFNULL(`adjustedQty`,`tradeQty`) as qty,
							CASE WHEN (upper(ticker) = 'CASH')
										THEN 1.00
								 ELSE `tradeprice`
							END as price,
							CASE WHEN (upper(ticker) = 'CASH')
										THEN IFNULL(`adjustedQty`,`tradeQty`)
								 ELSE IFNULL(`adjustedQty`,`tradeQty`) * `tradeprice`
							END as investmentamount,
							concat(`ticker`,`tradedate`) as `tradeID`,
							'LMT' as ordertype,
							null as confirmationID,
							now() as created,
							null as lastupdated
						FROM pretrade_details
						WHERE `acctnum` = p_acctnum;

				end;
		END IF;
	end;

END$$
DELIMITER ;
