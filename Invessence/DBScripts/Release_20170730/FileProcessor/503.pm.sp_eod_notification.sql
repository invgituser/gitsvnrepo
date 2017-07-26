DROP PROCEDURE IF EXISTS `invdb`.`sp_eod_notification`;


DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_eod_notification`(
)
BEGIN

  DECLARE tacctnum, tfirstname, tlastname varchar(60);
  DECLARE tWhichOne VARCHAR(1);
  DECLARE updt_done Boolean DEFAULT FALSE;
  DECLARE tFound INTEGER;
  DECLARE today  DATETIME;

  DECLARE cur1 CURSOR FOR 
	SELECT DISTINCT
			'O' as whichone
		  , `ext_acct_info`.`acctnum`
		  , upper(`ext_acct_info`.`applicantFName`) as firstname
		  , upper(`ext_acct_info`.`applicantLName`) as lastname
  FROM  `invdb`.`ext_acct_info`
  WHERE `ext_acct_info`.`status` = 'O'
  AND   `ext_acct_info`.`created` > DATE_FORMAT(now(),'%Y-%m-%d')
  UNION
	SELECT DISTINCT
			'F' as whichone
		  , `ext_acct_info`.`acctnum`
		  , upper(`ext_acct_info`.`applicantFName`) as firstname
		  , upper(`ext_acct_info`.`applicantLName`) as lastname
  FROM  `invdb`.`ext_acct_info`
  WHERE `ext_acct_info`.`status` = 'O'
  AND   `ext_acct_info`.`created` > DATE_FORMAT(now(),'%Y-%m-%d')
  AND   `ext_acct_info`.`clientAccountID` in (select `ext_nav_daily`.`clientAccountID` from  `invdb`.`ext_nav_daily`)
  ;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET updt_done = TRUE;
  
  BEGIN
       OPEN cur1;
	   the_loop: LOOP
 
	   FETCH cur1 INTO tWhichOne, tacctnum, tfirstname, tlastname;
	
      IF updt_done THEN
        LEAVE the_loop;
      END IF;
      
      IF (tWhichOne = 'O')
      THEN
		CALL `invdb`.`sp_user_profile_manage`(tacctnum, 'O');
      END IF;
      
      IF (tWhichOne = 'F')
      THEN
		CALL `invdb`.`sp_user_profile_manage`(tacctnum, 'F');
      END IF;

	END LOOP the_loop;
    CLOSE cur1;

  END;
END$$
DELIMITER ;
