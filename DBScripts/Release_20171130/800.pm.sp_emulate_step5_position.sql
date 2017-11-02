DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_step5_position`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_step5_position`(
  IN p_acctnum BIGINT
)
BEGIN
	DECLARE tStatus VARCHAR(1);
    DECLARE tClientAccountID VARCHAR(20);
    DECLARE tCash Double;
    DECLARE tTotal Double;
    DECLARE tReportDate VARCHAR(10);
    DECLARE tTradeCurrency VARCHAR(3);

    SELECT `eai`.`status`, `eai`.`clientAccountID`, IFNULL(`profile`.`tradeCurrency`,'USD')
    INTO tStatus, tClientAccountID, tTradeCurrency
    FROM invdb.ext_acct_info eai, `invdb`.`user_trade_profile` `profile`
    WHERE eai.acctnum = `profile`.`acctnum`
	AND   eai.acctnum = p_acctnum;

	set tReportDate = `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE');
    IF (IFNULL(tStatus,'Z') != 'A')
    THEN
      SELECT 'This account# NOT YET Active' AS msg;
    ELSE
	  select SUM(`cash`), sum(`total`)
      INTO tCash, tTotal
      from invdb.ext_nav 
      where clientAccountID = tClientAccountID
      and reportDate = tReportDate;
      
	IF (tCash = 0.0)
		THEN
			SELECT 'This account# NOT YET Funded' AS msg;
		ELSE
			IF (tCash < tTotal)
            THEN
				SELECT 'This account# has already been allocated' AS msg;
           ELSE
           
				DELETE FROM invdb.ext_position 
                where clientAccountID = tClientAccountID
                AND reportDate = tReportDate;
 
				 INSERT INTO `invdb`.`ext_position`
					(`acctnum`,
					`clientAccountID`,
					`tradeCurrency`,
					`fxRateToBase`,
					`symbol`,
					`reportDate`,
					`purchaseDate`,
					`side`,
					`quantity`,
					`costBasisPrice`,
					`costBasisMoney`,
					`markPrice`,
					`positionValue`,
					`pnlUnrealized`,
					`levelOfDetail`,
					`settleCurrency`,
					`settleQty`,
					`settlePrice`,
					`settleMoney`,
					`settleMarkPrice`,
					`settlePnL`,
					`created`)
				SELECT 
					`virtual_portfolio`.`acctnum`,
                    tClientAccountID,
                    `virtual_portfolio`.`tradeCurrency`,
                    `virtual_portfolio`.`exchangeRate`,
					`virtual_portfolio`.`ticker`,
                    tReportDate,
                    tReportDate,
                    'Long',
					`virtual_portfolio`.`qty`,
					(`tTotal` * `virtual_portfolio`.`weight`) / `virtual_portfolio`.`qty`,
                    (`tTotal` * `virtual_portfolio`.`weight`),
					(`tTotal` * `virtual_portfolio`.`weight`) / `virtual_portfolio`.`qty`,
                    (`tTotal` * `virtual_portfolio`.`weight`),
					0.0,
					'Emulated',
                    `virtual_portfolio`.`settleCurrency`,
					IFNULL(`virtual_portfolio`.`settleQty`, `virtual_portfolio`.`qty`),
					(`tTotal` * `virtual_portfolio`.`exchangeRate` * `virtual_portfolio`.`weight`) / IFNULL(`virtual_portfolio`.`settleQty`, `virtual_portfolio`.`qty`),
                    (`tTotal` * `virtual_portfolio`.`exchangeRate` * `virtual_portfolio`.`weight`),
					(`tTotal` * `virtual_portfolio`.`exchangeRate` * `virtual_portfolio`.`weight`) / IFNULL(`virtual_portfolio`.`settleQty`, `virtual_portfolio`.`qty`),
                    0.0,				
					now()
				FROM `invdb`.`virtual_portfolio`
                WHERE acctnum = p_acctnum
				;
                
                SELECT costBasisMoney
                INTO tCash
                FROM invdb.ext_position
                WHERE clientAccountID = tClientAccountID
                AND   upper(symbol) = 'CASH'
                AND reportDate = tReportDate;
                
                UPDATE invdb.ext_nav
                set cash = tCash,
					stock = total - tCash
                where clientAccountID = tClientAccountID
                AND reportDate = tReportDate;
				
				SELECT 'Success: This account# has been allocated' AS msg;
				

            END IF;
	END IF;
        

    END IF;


  END$$
DELIMITER ;
