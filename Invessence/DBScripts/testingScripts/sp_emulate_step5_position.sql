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

    SELECT `status`, `clientAccountID`
    INTO tStatus, tClientAccountID
    FROM invdb.ext_acct_info eai
    WHERE eai.acctnum = p_acctnum;

	set tReportDate = `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE');
    IF (IFNULL(tStatus,'Z') != 'A')
    THEN
      SELECT 'This account# NOT YET Active' AS msg;
    ELSE
	  select `cash`, `total`
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
				`currencyPrimary`,
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
				`created`)
				SELECT 
					`virtual_portfolio`.`acctnum`,
                    tClientAccountID,
                    'USD',
                    1.0,
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
					now()
				FROM `invdb`.`virtual_portfolio`
                WHERE acctnum = p_acctnum
				;
                
                SELECT costBasisMoney
                INTO tCash
                FROM invdb.ext_position
                WHERE clientAccountID = tClientAccountID
                AND   upper(symbol) = 'Cash'
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
