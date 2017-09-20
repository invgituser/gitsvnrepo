DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_undo_td_submit`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_undo_td_submit`(
	`p_acctnum`	BIGINT
)
BEGIN

	DECLARE `p_logonid`	BIGINT;
    DECLARE `p_sent`	VARCHAR(1);
    
    SELECT max(logonid)
    INTO `p_logonid`
    FROM `invdb`.`user_access_role`
    WHERE `acctnum` = `p_acctnum`
    AND   `role` = 'OWNER'
    ;
    
    SELECT SUBSTRING(`status`,1,1)
    INTO `p_sent`
    FROM `invdb`.`ext_acct_info`
    WHERE `acctnum` = `p_acctnum`
    ;
    
    
    IF (`p_logonid` is not null and `p_sent` is null)
    THEN
		-- Now that is is a valid account and not active account, let's do the work.
        
        -- Update the status, so that we can reprocess the application.
		update `invdb`.`user_trade_profile`
		set `managed` = 'N', `status` = 'N'
		where `acctnum` = `p_acctnum`
        AND `managed` = 'N' and `status` = 'P'
		;

		-- Delete entry from request table so that we can call the docusign process.
		delete
        from `invdb`.`dc_requests_final`
        where `acctnum` = `p_acctnum`;
        
        -- If this is tempoarary account, then set it active so that we can re-access it and resubmit the form.
        -- Only update the Temp account.  If it is already active or Inactive or locked, then do nothing.
        UPDATE `invdb`.`user_logon`
			set `logonstatus` = 'A'
		WHERE `logonid` = `p_logonid`
        AND `logonstatus` = 'T'
        ;

    END IF;

END$$
DELIMITER ;
