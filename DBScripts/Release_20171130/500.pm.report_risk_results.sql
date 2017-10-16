DROP PROCEDURE IF EXISTS `invdb`.`report_risk_results`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`report_risk_results`(
	  in p_logonid 	BIGINT(20)
    , in p_days		INTEGER
    , in p_filter	VARCHAR(1)
    , IN p_advisor	VARCHAR(20)
    , IN p_rep		VARCHAR(20)
    )
BEGIN

	DECLARE tAdvisor VARCHAR(25);
	DECLARE vAdvisor VARCHAR(25);
	DECLARE  tRep     VARCHAR(25);

	
	SELECT advisor, rep
	INTO tAdvisor, tRep
	FROM user_advisor_access
	WHERE logonid = p_logonid
	LIMIT 1;

	select advisor 
	into vAdvisor  
	from user_logon where logonid=p_logonid;
	
	if(vAdvisor='DEMO') then
		 set tAdvisor = IFNULL(p_advisor,'');
		 set tRep = IFNULL(p_rep,'');
	end if;
    
    if (p_days is null)
    then
		set p_days = -5;
	else
		set p_days = -1 * p_days;
    end if;
    
    if (p_filter is null)
    then
		set p_filter = 'A';
	end if;


	IF (p_filter = 'A')
    then
		SELECT 
			`user_risk_questions`.`acctnum`,
			`user_trade_profile`.`clientAccountID`,
			concat(`user_trade_profile`.`firstname`,' ',`user_trade_profile`.`lastname`) as `name`,
			`user_logon`.`email`,
			`user_risk_questions`.`investmentgoal`,
			`user_risk_questions`.`age`,
			`user_risk_questions`.`retireage`,
			`user_risk_questions`.`retired`,
			`user_risk_questions`.`horizon`,
			`user_risk_questions`.`ans1`,
			`user_risk_questions`.`ans2`,
			`user_risk_questions`.`ans3`,
			`user_risk_questions`.`ans4`,
			`user_risk_questions`.`ans5`,
			`user_risk_questions`.`ans6`,
			`user_risk_questions`.`ans7`,
			`user_risk_questions`.`ans8`,
			`user_risk_questions`.`ans9`,
			`user_risk_questions`.`ans10`,
			`user_risk_questions`.`ans11`,
			`user_risk_questions`.`ans12`,
			`user_risk_questions`.`ans13`,
			`user_risk_questions`.`ans14`,
			`user_risk_questions`.`ans15`,
			`user_risk_questions`.`formula`,
			`user_risk_questions`.`risk1`,
			`user_risk_questions`.`risk2`,
			`user_risk_questions`.`risk3`,
			`user_risk_questions`.`risk4`,
			`user_risk_questions`.`risk5`,
			`user_risk_questions`.`risk6`,
			`user_risk_questions`.`risk7`,
			`user_risk_questions`.`risk8`,
			`user_risk_questions`.`risk9`,
			`user_risk_questions`.`risk10`,
			`user_risk_questions`.`risk11`,
			`user_risk_questions`.`risk12`,
			`user_risk_questions`.`risk13`,
			`user_risk_questions`.`risk14`,
			`user_risk_questions`.`risk15`,
            `user_risk_questions`.`standardRisk`,
            `user_risk_questions`.`overrideRisk`,
			`user_risk_questions`.`totalrisk`
		FROM `invdb`.`user_risk_questions`
        INNER JOIN `invdb`.`ext_acct_info`
        ON (`ext_acct_info`.`acctnum` = `user_risk_questions`.`acctnum`)
		INNER JOIN `invdb`.`user_trade_profile`
		ON (`user_trade_profile`.`acctnum` = `user_risk_questions`.`acctnum`)
		INNER JOIN `invdb`.`user_access_role`
		ON (`user_trade_profile`.`acctnum` = `user_access_role`.`acctnum`
		AND `user_access_role`.`role` = 'OWNER')
		INNER JOIN `invdb`.`user_logon`
		ON (`user_access_role`.`logonid` = `user_logon`.`logonid`)
		WHERE `user_trade_profile`.`advisor` like tAdvisor
		AND   `user_trade_profile`.`rep` like tRep
        AND   IFNULL(`ext_acct_info`.`created`,now()) >= DATE_ADD(now(),INTERVAL p_days DAY)
		;
	ELSE 
		SELECT 
			`user_risk_questions`.`acctnum`,
			`user_trade_profile`.`clientAccountID`,
			concat(`user_trade_profile`.`firstname`,' ',`user_trade_profile`.`lastname`) as `name`,
			`user_logon`.`email`,
			`user_risk_questions`.`investmentgoal`,
			`user_risk_questions`.`age`,
			`user_risk_questions`.`retireage`,
			`user_risk_questions`.`retired`,
			`user_risk_questions`.`horizon`,
			`user_risk_questions`.`ans1`,
			`user_risk_questions`.`ans2`,
			`user_risk_questions`.`ans3`,
			`user_risk_questions`.`ans4`,
			`user_risk_questions`.`ans5`,
			`user_risk_questions`.`ans6`,
			`user_risk_questions`.`ans7`,
			`user_risk_questions`.`ans8`,
			`user_risk_questions`.`ans9`,
			`user_risk_questions`.`ans10`,
			`user_risk_questions`.`ans11`,
			`user_risk_questions`.`ans12`,
			`user_risk_questions`.`ans13`,
			`user_risk_questions`.`ans14`,
			`user_risk_questions`.`ans15`,
			`user_risk_questions`.`formula`,
			`user_risk_questions`.`risk1`,
			`user_risk_questions`.`risk2`,
			`user_risk_questions`.`risk3`,
			`user_risk_questions`.`risk4`,
			`user_risk_questions`.`risk5`,
			`user_risk_questions`.`risk6`,
			`user_risk_questions`.`risk7`,
			`user_risk_questions`.`risk8`,
			`user_risk_questions`.`risk9`,
			`user_risk_questions`.`risk10`,
			`user_risk_questions`.`risk11`,
			`user_risk_questions`.`risk12`,
			`user_risk_questions`.`risk13`,
			`user_risk_questions`.`risk14`,
			`user_risk_questions`.`risk15`,
            `user_risk_questions`.`standardRisk`,
            `user_risk_questions`.`overrideRisk`,
			`user_risk_questions`.`totalrisk`
		FROM `invdb`.`user_risk_questions`
		INNER JOIN `invdb`.`user_trade_profile`
		ON (`user_trade_profile`.`acctnum` = `user_risk_questions`.`acctnum`)
		INNER JOIN `invdb`.`user_access_role`
		ON (`user_trade_profile`.`acctnum` = `user_access_role`.`acctnum`
		AND `user_access_role`.`role` = 'OWNER')
		INNER JOIN `invdb`.`user_logon`
		ON (`user_access_role`.`logonid` = `user_logon`.`logonid`)
		WHERE `user_trade_profile`.`advisor` like tAdvisor
		AND   `user_trade_profile`.`rep` like tRep
        AND   IFNULL(`user_trade_profile`.`created`,now()) >= DATE_ADD(now(),INTERVAL p_days DAY)
        AND   `user_trade_profile`.`managed` not in ('A')
		;
    END IF;
    



END$$
DELIMITER ;
