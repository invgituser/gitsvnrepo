DROP PROCEDURE IF EXISTS `invdb`.`sp_user_profile_manage`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_user_profile_manage`(
	IN p_acctnum   BIGINT(20),
    IN p_status   VARCHAR(1)
)
BEGIN

  DECLARE tAdvisor, tRep VARCHAR(30);
  DECLARE tLastStatus VARCHAR(1);
  DECLARE tManaged	  VARCHAR(1);

	SELECT 
		`advisor`,
        `rep`,
        `status`,
        `managed`
	INTO tAdvisor, tRep, tLastStatus, tManaged
	FROM `user_trade_profile`
    WHERE acctnum = p_acctnum;
    
  BEGIN
  
	-- For tLastStatus is same, then ignore this whole thing.
	IF (IFNULL(tLastStatus,'XX') != p_status)
    THEN
    
		-- If we are resetting it to 'A' due to funding or rebalacing, then don't send another alert.
		IF (tManaged = 'A' and IFNULL(p_status,'V') = 'A')
        THEN
			IF (tAdvisor is not null)
			THEN
				 call `invdb`.`sp_send_advisor_notification`(
					p_acctnum, 
					tAdvisor, 
					tRep,
					CASE 
						WHEN (p_status = 'P') THEN 'PROCESSED'
						WHEN (p_status = 'O') THEN 'OPENED'
						WHEN (p_status = 'A') THEN 'ACTIVE'
						WHEN (p_status = 'R') THEN 'REBALANCE'
						WHEN (p_status = 'F') THEN 'FUNDED'
						WHEN (p_status = 'V') THEN 'VISITOR'
						WHEN (p_status = 'N') THEN 'NEWCLIENT'
						WHEN (p_status = 'C') THEN 'CLOSED'
						ELSE 'NOEVENT'
					END
				 );
					
			END IF;
       END IF;
        
        
		IF (IFNULL(p_status,'V') = 'A')
        THEN
			update user_trade_profile
				set `status` = p_status,
					 `managed` = 'A'
			where acctnum = `p_acctnum`;
        ELSE
			update user_trade_profile
				set `status` = IFNULL(p_status,'V')
			where acctnum = `p_acctnum`;
        END IF;

     END IF;

  END;
  
END$$
DELIMITER ;
