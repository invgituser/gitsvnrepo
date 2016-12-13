DROP PROCEDURE IF EXISTS `invdb`.`sp_archive_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_archive_user_trade_profile`(
	  IN p_acctnum	BIGINT
    , IN p_advisor	VARCHAR(20)
)
BEGIN

	DECLARE tAcctnum	BIGINT;
	DECLARE updt_done Boolean DEFAULT FALSE;
    
	DECLARE cur1 CURSOR FOR 
			SELECT `user_trade_profile`.`acctnum`
            FROM   `user_trade_profile`
                   LEFT JOIN `user_advisor_mapping`
                   ON (`user_advisor_mapping`.`advisor` = IFNULL(`user_trade_profile`.`advisor`,'Invessence'))
			WHERE `user_trade_profile`.`acctnum` not in (select `ext_acct_info`.`acctnum` from `ext_acct_info` where `ext_acct_info`.`acctnum`  is not null )
            AND   IFNULL(`user_trade_profile`.`status`,'V') in ('V','N','C')
            AND   `invdb`.`get_business_date`(now(),(IFNULL(`user_advisor_mapping`.`archiveUnopenedApps`,6000)*-1)) < `user_trade_profile`.`created`
           ;

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET updt_done = TRUE;
	
	-- If the account number is not null then move to audir this specific account.
	IF (p_acctnum is not null)
	THEN 
		BEGIN
			SELECT `user_trade_profile`.`acctnum`
            INTO   `tAcctnum`
            FROM   `user_trade_profile`
                   LEFT JOIN `user_advisor_mapping`
                   ON (`user_advisor_mapping`.`advisor` = IFNULL(`user_trade_profile`.`advisor`,'Invessence'))
			WHERE `user_trade_profile`.`acctnum` = `p_acctnum`
            AND   IFNULL(`user_trade_profile`.`status`,'V') in ('V','N','C')
            AND   `invdb`.`get_business_date`(now(),IFNULL(`user_advisor_mapping`.`archiveUnopenedApps`,6000)) < `user_trade_profile`.`created`
            ;
            
            IF (tAcctnum is not null)
            THEN
				CALL `invdb`.`save_user_trade_profile_audit`(`tAcctnum`);
                CALL `invdb`.`save_user_risk_questions_audit`(`tAcctnum`);
            END IF;
        END;
	ELSE
		-- Else, If the advisor is not null then move group of them to audit file.
		if (p_advisor is not null)
        THEN
        
			-- Find associated account number from DB.
			OPEN cur1;

			the_loop: LOOP


			FETCH cur1 INTO tacctnum;

			IF updt_done THEN
				LEAVE the_loop;
			END IF;
            
			END LOOP the_loop;
            
				CALL `invdb`.`save_user_trade_profile_audit`(`tAcctnum`);
                CALL `invdb`.`save_user_risk_questions_audit`(`tAcctnum`);
    
			CLOSE cur1;

        END IF;
	END IF;
    
END$$
DELIMITER ;
