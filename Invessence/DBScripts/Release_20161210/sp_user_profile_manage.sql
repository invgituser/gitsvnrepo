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
	IF (`tManaged` = 'A')
    THEN
		update user_trade_profile
			set `status` = IFNULL(p_status,'N')
		where acctnum = `p_acctnum`;
    ELSE
		IF (IFNULL(p_status,'N') != 'N')
        THEN
			update user_trade_profile
				set `status` = IFNULL(p_status,'N'),
					 `managed` = 'A'
			where acctnum = `p_acctnum`;
		END IF;
	END IF;
  END;
  
  -- For active, only send one alert
  -- If the last Status was 'N, O, or 'P' else don't send alert.
  -- If we are resetting it to 'A' due to funding or rebalacing, then don't do anything.
  IF (tAdvisor is not null)
	THEN
		  CASE
			WHEN (p_status = 'P') -- Processed (Application submitted, acct is not yet processed by custody
				THEN call `invdb`.`sp_send_advisor_notification`(p_acctnum, tAdvisor, tRep, 'PROCESSED');
			WHEN (p_status = 'O') -- Custody has acked the application as opened
				THEN call `invdb`.`sp_send_advisor_notification`(p_acctnum, tAdvisor, tRep, 'OPENED');
			WHEN (p_status = 'A') -- Custody has acked the application as opened and funded.
				THEN call `invdb`.`sp_send_advisor_notification`(p_acctnum, tAdvisor, tRep, 'ACTIVE');
			WHEN (p_status = 'R') -- User wants to rebalance the trades (Based on new risk assessement.
				THEN call `invdb`.`sp_send_advisor_notification`(p_acctnum, tAdvisor, tRep, 'REBALANCE');
			WHEN (p_status = 'F') -- User wants to fund additional amount.
				THEN call `invdb`.`sp_send_advisor_notification`(p_acctnum, tAdvisor, tRep, 'FUNDED');
			ELSE
				call `invdb`.`sp_send_advisor_notification`(p_acctnum, tAdvisor, tRep, 'NOEVENT');
		  END CASE;
  END IF;
	
END$$
DELIMITER ;
