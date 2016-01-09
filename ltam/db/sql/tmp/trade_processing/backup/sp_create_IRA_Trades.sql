DROP PROCEDURE IF Exists `sp_create_IRA_Trades`;

DELIMITER $$
CREATE PROCEDURE `sp_create_IRA_Trades`(
	p_acctnum BIGINT(20),
	p_customerType VARCHAR(40))
BEGIN
	DECLARE vToday varchar(10);
	DECLARE vCount varchar(10);
	DECLARE vBusinessDate varchar(10);
	DECLARE vCustomerType varchar(40);

	set vToday = DATE_FORMAT(now(),'%Y%m%d');

	IF (p_customerType is null) THEN
			begin
				SELECT `acctType`
				INTO vCustomerType
				FROM `user_trade_profile`
				WHERE acctnum = p_acctnum;
			end;
		else
			set vCustomerType = p_customerType;
	END IF;


	IF (instr(vCustomerType,'IRA') > 0)
	then 
		begin
			-- Get business Date.
			set   vBusinessDate = funct_get_switch('BROKER_BDATE');

			SELECT count(*)
			INTO vCount
			FROM `virtual_portfolio`
			WHERE `acctnum` = p_acctnum
			AND `active` = 'A';

			IF (IFNULL(vCount,0) > 0)
				THEN
					begin
						DELETE FROM tmp_pretrade_details
						WHERE acctnum = p_acctnum;

						INSERT INTO `invdb`.`tmp_pretrade_details`
						(`acctnum`,
						`clientAccountID`,
						`name`,
						`tradedate`,
						`ticker`,
						`accttype`,
						`posqty`,
						`newqty`,
						`tradeqty`,
						`pnl`,
						`priceperShare`,
						`gainloss`,
						`adjustedQty`,
						`tradeprice`,
						`posamount`,
						`newamount`,
						`tradeamount`)
						SELECT `virtual_portfolio`.`acctnum`, -- Col A
							`IB_Accounts`.`IB_acctnum`,  -- Col B
							concat(`IB_Accounts`.firstname," ",`IB_Accounts`.lastname) as name,  -- Col C
							vToday as tradedate, -- Col D
							`virtual_portfolio`.`ticker`,  -- Col E
							vCustomerType as accttype,  -- Col F
							SUM(IFNULL(`position`.`quantity`,0)) as posQty,  -- Col G
							SUM(`virtual_portfolio`.`qty`) as newqty,  -- Col H
							(SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))) as tradeQty,  -- Col I (H - G)
							SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) as pnl, -- Col J
							CASE WHEN (SUM(IFNULL(`position`.`quantity`,0)) = 0) THEN 0
								 ELSE SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) / SUM(IFNULL(`position`.`quantity`,0))
							END as priceperShare, -- Col K (J/G - pnl/position)
							CASE WHEN (SUM(IFNULL(`position`.`quantity`,0)) = 0) THEN 0
								 ELSE CASE WHEN ((SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))) < 0)
												THEN (-1 * (SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) / SUM(IFNULL(`position`.`quantity`,0))) *
															(SUM(`virtual_portfolio`.`qty`) - SUM(IFNULL(`position`.`quantity`,0))))
												ELSE 0
									   END
							END as gainloss, -- Col L (-1*K*I)
							null, -- Col M (adjustedQty)
							`virtual_portfolio`.`tradeprice` as tradeprice, -- Col N
							SUM(IFNULL(`position`.`positionValue`,0)) as posamount, -- Col O
							SUM(`virtual_portfolio`.`investmentvalue`) as newamount, -- Col P
							SUM(`virtual_portfolio`.`investmentvalue`) - SUM(IFNULL(`position`.`positionValue`,0)) as tradeamount -- Col Q
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
						INSERT INTO `tmp_pretrade_details`
						(`acctnum`,
						`clientAccountID`,
						`name`,
						`tradedate`,
						`ticker`,
						`accttype`,
						`posqty`,
						`newqty`,
						`tradeqty`,
						`pnl`,
						`priceperShare`,
						`gainloss`,
						`adjustedQty`,
						`tradeprice`,
						`posamount`,
						`newamount`,
						`tradeamount`)
							SELECT `IB_Accounts`.`acctnum`, -- Col A
							`IB_Accounts`.`IB_acctnum`, -- Col B
							concat(`IB_Accounts`.firstname," ",`IB_Accounts`.lastname) as name, -- Col C
							vToday as tradedate,  -- Col D
							`position`.`symbol` as ticker,  -- Col E
							vCustomerType as accttype, -- Col F
							SUM(IFNULL(`position`.`quantity`,0)) as posQty, -- Col G
							0 as newqty, -- Col H
							0 as tradeQty, -- Col I
							SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) as pnl, -- Col J
							0 as priceperShare, -- Col K
							0 as gainloss, -- Col L
							0 as adjustedQty, -- Col M
							MAX(`position`.`markprice`) as tradeprice, -- Col N
							SUM(IFNULL(`position`.`positionValue`,0)) as posamount, -- Col O
							0.0 as newamount, -- Col P
							0.0 as tradeamount -- Col Q
						FROM `position`,
						     `IB_Accounts`
						WHERE `IB_Accounts`.`acctnum` = p_acctnum
					    AND   `IB_Accounts`.IB_acctnum = `position`.`clientAccountID`
						AND `position`.reportDate = funct_get_switch('BROKER_BDATE')
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

						DELETE FROM user_trade_log
						WHERE acctnum = p_acctnum
						AND tradedate in (select `tradedate` from tmp_pretrade_details tpd 
											where tpd.acctnum = user_trade_log.acctnum and tpd.ticker = user_trade_log.ticker);

							INSERT INTO `user_trade_log`
							(`acctnum`,
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
								`tradeQty` as qty,
								CASE WHEN (upper(ticker) = 'CASH')
											THEN 1.00
									 ELSE `tradeprice`
								END as price,
								CASE WHEN (upper(ticker) = 'CASH')
											THEN `tradeQty`
									 ELSE `tradeQty` * `tradeprice`
								END as investmentamount,
								concat(`ticker`,DATE_FORMAT(now(),'%Y%m%d')) `tradeID`,
								'LMT' as ordertype,
								null as confirmationID,
								now() as created,
								null as lastupdated
							FROM tmp_pretrade_details
							WHERE acctnum = p_acctnum;
					end;
			END IF;
		end;
	end if;
END$$
DELIMITER ;
