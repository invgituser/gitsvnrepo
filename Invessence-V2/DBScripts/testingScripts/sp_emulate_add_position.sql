DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_add_position`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_add_position`(
  IN p_acctnum BIGINT
)
BEGIN
    DECLARE tFound1 INTEGER;
    DECLARE tFound2 INTEGER;
    DECLARE tStatus INTEGER;
    DECLARE tClientAccountID VARCHAR(20);

    SELECT count(*)
    INTO tFound1
    FROM `invdb`.`ext_acct_info`
    WHERE `ext_acct_info`.`acctnum` = `p_acctnum`
    AND   `ext_acct_info`.`status` in ('A');

    IF (IFNULL(tFound1, 0) = 0)
    THEN
      SELECT 'This account# NOT YET OPENED or not YET ACTIVE' AS msg;
    ELSE
      SELECT `clientAccountID`
      INTO tClientAccountID
      FROM `invdb`.`ext_acct_info`
      WHERE `ext_acct_info`.`acctnum` = `p_acctnum`;

      IF (IFNULL(tClientAccountID, 'XXX') != 'XXX')
      THEN
      
		  SELECT count(*)
		  INTO tFound2
		  FROM `invdb`.`virtual_portfolio`
		  WHERE `virtual_portfolio`.`acctnum` = `p_acctnum`;
			
		  IF (IFNULL(tFound1, 0) > 0)
          THEN
          
			DELETE FROM `invdb`.`ext_position`
			WHERE `clientAccountID` = tClientAccountID
			AND   `reportDate` = `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE');
            
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
                    IFNULL(`sec_master`.`base_currency`,'USD'),
                    null,
                    `sec_master`.`ticker`,
                    `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE'),
                    `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE'),
                    'Long',
                    `virtual_portfolio`.`qty`,
                    `virtual_portfolio`.`tradeprice`,
                    `virtual_portfolio`.`investmentvalue`,
                    `virtual_portfolio`.`tradeprice`,
                    `virtual_portfolio`.`investmentvalue`,
                    0.0, -- `pnlUnrealized` 
                    'Manual',
                    now()
				FROM `invdb`.`virtual_portfolio`, `invdb`.`sec_master`
                WHERE `virtual_portfolio`.`ticker` = `sec_master`.`ticker`
                AND  `virtual_portfolio`.`acctnum` = `p_acctnum`;
			ELSE 
				SELECT 'Sample POSITION is missing' AS msg;
			END IF;
		SELECT 'The POSITION has been added' AS msg;
      ELSE
        SELECT 'This account# was NOT FOUND to ext_acct_info' AS msg;
      END IF;

    END IF;


  END$$
DELIMITER ;
