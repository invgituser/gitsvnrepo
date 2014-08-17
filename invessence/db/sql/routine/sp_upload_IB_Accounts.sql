DROP PROCEDURE IF EXISTS `sp_upload_IB_Accounts`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_IB_Accounts`(
)
BEGIN

  #declare variable

  DECLARE v_clientAccountID, v_name, v_dateOpened, v_dateClosed VARCHAR(80);
  DECLARE done INT DEFAULT FALSE;
 
  #declare cursor
  DECLARE cur1 CURSOR FOR 
	SELECT `clientAccountID`, 
		   `name`, 
		   `dateOpened`,
		   `dateClosed`
  FROM tmp_IB_Accounts
  WHERE tmp_IB_Accounts.clientAccountID NOT IN (SELECT IB_acctNum FROM IB_Accounts)
  ;
 
  #declare handle 
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 
  #open cursor
  OPEN cur1;
 
  #starts the loop
  the_loop: LOOP
 
    #get the values of each column into our variables
    FETCH cur1 INTO v_clientAccountID, v_name, v_dateOpened, v_dateClosed;

	SELECT v_clientAccountID, v_name, v_dateOpened, v_dateClosed;
	
    IF done THEN
      LEAVE the_loop;
    END IF;
	
	call sp_add_IB_Accounts(v_clientAccountID, v_name, v_dateOpened, v_dateClosed);

  END LOOP the_loop;
 
  CLOSE cur1;

  # Mark all those that we already have a match.
  UPDATE tmp_IB_Accounts
	SET `status` = 'X'
  WHERE clientAccountID in (SELECT IB_acctNum FROM IB_Accounts);

  # OK, now process the all closed accounts.
  UPDATE `IB_Accounts`
	set dateClosed = DATE_FORMAT(NOW(),'%Y%m%d'),
		`accountStatus` = 'Closed'
  WHERE IB_acctNum in (SELECT clientAccountID FROM tmp_IB_Accounts where LENGTH(TRIM(dateClosed)) > 6)
  ;

END$$
DELIMITER ;
