DROP PROCEDURE IF EXISTS `sp_upload_IB_Accounts`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_IB_Accounts`(
)
BEGIN

  DECLARE v_clientAccountID, v_name, v_dateOpened, v_dateClosed VARCHAR(80);
  DECLARE done INT DEFAULT FALSE;

  DECLARE t_count INTEGER;
  DECLARE v_acctnum BIGINT(20);
  DECLARE v_firstname, v_lastname, v_email varchar(60);
  DECLARE add_done INT DEFAULT FALSE;

 
  
  DECLARE cur1 CURSOR FOR 
	SELECT `clientAccountID`, 
		   `name`, 
		   `dateOpened`,
		   `dateClosed`
  FROM tmp_IB_Accounts
  WHERE tmp_IB_Accounts.clientAccountID NOT IN (SELECT IB_acctNum FROM IB_Accounts)
  ;
 
  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 
  
  OPEN cur1;
 
  
  the_loop: LOOP
 
    
    FETCH cur1 INTO v_clientAccountID, v_name, v_dateOpened, v_dateClosed;
	
    IF done THEN
      LEAVE the_loop;
    END IF;
	
    SELECT count(*)
	INTO t_count
    FROM vw_pending_user_info
	WHERE name = v_name;

	IF (t_count = 1)
	  THEN
			SELECT acctnum, firstname, lastname, email
			INTO v_acctnum, v_firstname, v_lastname, v_email			
			FROM vw_pending_user_info
			WHERE name = v_name
			LIMIT 1;

			INSERT INTO `IB_Accounts`
			(`acctNum`,
			`IB_acctNum`,
			`email`,
			`lastName`,
			`firstName`,
			`accountStatus`,
			`dateOpened`)
			values (
			 v_acctnum,
			 v_clientAccountID,
			 v_email,
			 v_lastname,
			 v_firstname,
			 'Pending',
			 v_dateOpened
			);
	  ELSE
			INSERT INTO `IB_Accounts`
			(`IB_acctNum`,
			`accountStatus`,
			`dateOpened`)
			values (
			 v_clientAccountID,
			 'Pending',
			 v_dateOpened
			);
	END IF;

  END LOOP the_loop;
 
  CLOSE cur1;

  
  UPDATE `IB_Accounts`
	set dateClosed = DATE_FORMAT(NOW(),'%Y%m%d'),
		`accountStatus` = 'Closed'
  WHERE IB_acctNum in (SELECT clientAccountID FROM tmp_IB_Accounts where LENGTH(TRIM(dateClosed)) > 6)
  ;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_add_IB_Accounts;
