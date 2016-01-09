DROP PROCEDURE IF EXISTS `sp_upload_Cash_Info`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_Cash_Info`(
)
BEGIN

  #declare variable

  DECLARE v_clientAccountID, v_accountAlias, v_currencyPrimary, v_fromDate, v_toDate  VARCHAR(20);
  DECLARE v_startingCash, v_endingCash Double;
  DECLARE done INT DEFAULT FALSE;
 
  #declare cursor
  DECLARE cur1 CURSOR FOR 
	SELECT clientAccountID,
			substring(accountAlias,1,20) as accountAlias,
			currencyPrimary,
			fromDate,
			toDate,
			startingCash,
			endingCash
  FROM tmp_IB_Cash_Info
  ;
 
  #declare handle 
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 
  #open cursor
  OPEN cur1;
 
  #starts the loop
  the_loop: LOOP
 
    #get the values of each column into our variables
    FETCH cur1 INTO v_clientAccountID, v_accountAlias, v_currencyPrimary, v_fromDate, v_toDate,
					v_startingCash, v_endingCash;
	
    IF done THEN
      LEAVE the_loop;
    END IF;
	
	call sp_Cash_Position_addmod('A', v_clientAccountID, v_accountAlias, 'USD', v_fromDate, v_toDate,
					v_startingCash, v_endingCash);

  END LOOP the_loop;
 
  CLOSE cur1;

END$$
DELIMITER ;
