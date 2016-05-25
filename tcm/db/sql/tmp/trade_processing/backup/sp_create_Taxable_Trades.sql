DROP PROCEDURE IF Exists `sp_create_Taxable_Trades`;

DELIMITER $$
CREATE PROCEDURE `sp_create_Taxable_Trades`(
	p_acctnum BIGINT(20),
	p_customerType VARCHAR(40))
BEGIN
	DECLARE vToday varchar(10);
	DECLARE vCount varchar(10);
	DECLARE vBusinessDate varchar(10);
	DECLARE vCustomerType varchar(40);

	set vToday = now();

	IF (p_customerType is null) THEN
			begin
				SELECT customerType
				INTO vCustomerType
				FROM `IB_Accounts`
				WHERE acctnum = p_acctnum;
			end;
		else
			set vCustomerType = p_customerType;
	END IF;


	IF (instr(vCustomerType,'IRA') = 0)
	then 
		begin
			-- Get business Date.
			select value  
			into vBusinessDate
			from invessence_switch where name = 'BROKER_BDATE';

			SELECT count(*)
			INTO vCount
			FROM virtual_portfolio
			WHERE acctnum = p_acctnum
			AND status = 'A';

			IF (IFNULL(vCount,0) > 0)
				THEN
					begin
						DROP temporary table if exists `tmpInvNonIRATrades`;

						CREATE TEMPORARY TABLE IF NOT EXISTS `tmpInvNonIRATrades` AS (
						SELECT `virtual_portfolio`.`acctnum`,
							`IB_Accounts`.`IB_acctnum` as clientAccountID,
							`virtual_portfolio`.`ticker`,
							`virtual_portfolio`.`qty`,
							SUM(IFNULL(`position`.`quantity`,0)) as posQty,
							(`virtual_portfolio`.`qty` - SUM(IFNULL(`position`.`quantity`,0))) as newQty,
							`virtual_portfolio`.`price`,
							SUM(IFNULL(`position`.`fifoPnlUnrealized`,0)) as pnl
						FROM `virtual_portfolio`
						LEFT JOIN `IB_Accounts`
						ON (`virtual_portfolio`.`acctnum` = `IB_Accounts`.`acctnum`)
						LEFT JOIN `position`
						ON (`position`.`clientAccountID` = `IB_Accounts`.`IB_acctnum`
						AND `virtual_portfolio`.`ticker` = `position`.`symbol`
						AND `position`.`reportDate` = vBusinessDate)
						WHERE `virtual_portfolio`.`acctnum` = p_acctnum
						GROUP BY `virtual_portfolio`.`acctnum`,
							`virtual_portfolio`.`ticker`,
							`virtual_portfolio`.`qty`,
							`virtual_portfolio`.`price`
						);

						DELETE FROM user_trade_log
						WHERE acctnum = p_acctnum
						AND action = 'P';

							INSERT INTO `user_trade_log`
							(`acctnum`,
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
								now(),
								`ticker`,
								'P',
								`sectype`,
								`exchange`,
								'USD',
								`timeinforce`,
								`newQty`,
								CASE WHEN (upper(ticker) = 'CASH')
											THEN 1.00
									 ELSE `price`
								END as price,
								`price`,
								CASE WHEN (upper(ticker) = 'CASH')
											THEN `newQty`
									 ELSE `newQty` * `price`
								END as investmentamount,
								`tradeID`,
								`ordertype`,
								`confirmationID`,
								`created`,
								`lastupdated`
							FROM tmpInvTrades
							WHERE acctnum = p_acctnum;
					end;
			END IF;
		end;
	end if;
END$$
DELIMITER ;
