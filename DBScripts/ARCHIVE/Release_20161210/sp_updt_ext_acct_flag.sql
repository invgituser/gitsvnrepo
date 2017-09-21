DROP PROCEDURE IF EXISTS `temp`.`sp_updt_ext_acct_flag`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_updt_ext_acct_flag`()
BEGIN

  DECLARE done INT DEFAULT FALSE;
  DECLARE tAcctNum VARCHAR(20);
  DECLARE tLastManaged VARCHAR(1);
  DECLARE tTotal DOUBLE;
  
  DECLARE cur1 CURSOR FOR 
  SELECT distinct
		 `ext_acct_info`.`acctnum`,
         `ext_nav`.`total`
  FROM `invdb`.`ext_acct_info`, `invdb`.`ext_nav`, `invdb`.`user_trade_profile` 
  WHERE `ext_acct_info`.`status` not in ('A')
  AND `ext_acct_info`.`acctnum` = `user_trade_profile`.`acctnum`
  AND `ext_acct_info`.`clientAccountID` = `ext_nav`.`clientAccountID`
  AND `ext_nav`.`reportDate` = (select max(reportDate) from `invdb`.`ext_nav`)
  AND `ext_nav`.`total` > 0
  ;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
  OPEN cur1;
 
the_loop: LOOP
    
    FETCH cur1 INTO tAcctNum, tTotal;
	
    IF done THEN
      LEAVE the_loop;
    END IF;

	CALL `invdb`.`sp_user_profile_manage`(tAcctNum, 'A');

  END LOOP the_loop;
 
  CLOSE cur1;
END$$
DELIMITER ;
