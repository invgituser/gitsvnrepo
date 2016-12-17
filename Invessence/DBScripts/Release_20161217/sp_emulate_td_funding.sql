DROP PROCEDURE `testing`.`sp_emulate_td_funding`
DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_td_funding`(
	IN p_acctnum	BIGINT,
    IN p_amount		DOUBLE
)
BEGIN
	DECLARE tFound1 INTEGER;
    DECLARE tFound2	INTEGER;
	DECLARE tStatus	INTEGER;
    DECLARE tClientAccountID VARCHAR(20);
    
    SELECT count(*)
    INTO tFound1
    FROM `invdb`.`ext_acct_info`
    WHERE `ext_acct_info`.`acctnum` = `p_acctnum`
    ;
    
    SELECT count(*)
    INTO tFound2
    FROM `invdb`.`ext_position`
    WHERE `ext_position`.`acctnum` = `p_acctnum`
    ;
    
        IF (IFNULL(tFound1,0) = 0)
		THEN
			SELECT 'This account# NOT YET OPENED' as msg;
		ELSE
        
			SELECT `clientAccountID`
            INTO tClientAccountID
            FROM `invdb`.`ext_acct_info`
            WHERE `ext_acct_info`.`acctnum` = `p_acctnum`
            ;
            
            IF (IFNULL(tClientAccountID,'XXX') != 'XXX')
            THEN
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
					`created`
                    )
				SELECT
					`ext_acct_info`.`acctnum` -- `acctnum`,
					, `ext_acct_info`.`clientAccountID` as clientAccountID -- `clientAccountID`,
					, 'USD' as currencyPrimary -- `currencyPrimary`,
					, '1.0' as fxRateToBase-- `fxRateToBase`,
					, 'Cash' as symbol -- `symbol`,
					, `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE') as reportDate -- `reportDate`,
					, `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE') as purchaseDate -- `purchaseDate`,
					, 'Long' as `side`
					, `p_amount` as `quantity`
					, 1 as costBasisPrice
					, `p_amount` as `costBasisMoney`
					, 1 as markPrice
					, `p_amount` as `positionValue`
					, 0 as pnlUnrealized
					, 'Cash' as `levelOfDetail`
					, now() as created
				FROM `invdb`.`ext_acct_info` as `ext_acct_info`
				WHERE `ext_acct_info`.`acctnum` = `p_acctnum`
				;
                
                call `invdb`.`sp_user_profile_manage`(`p_acctnum`, 'F');
                
				SELECT 'This account# was ACTIVATED and POSITION created' as msg;
			ELSE
				SELECT 'This account# was NOT FOUND to ext_acct_info' as msg;
            END IF;

	END IF;
    
    

END$$
DELIMITER ;
