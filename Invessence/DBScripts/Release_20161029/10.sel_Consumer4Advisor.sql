DROP PROCEDURE IF EXISTS `invdb`.`sel_Consumer4Advisor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_Consumer4Advisor`(
	p_logonid BIGINT,
    p_filter  VARCHAR(1),
    p_days    INTEGER    
)
BEGIN

	DECLARE isAdvisor BOOLEAN;
	DECLARE tAdvisor VARCHAR(25);
    DECLARE  tRep     VARCHAR(25);
    
	SELECT advisor, rep
	INTO tAdvisor, tRep
	FROM user_advisor_access
	WHERE logonid = p_logonid
	LIMIT 1;
    
    IF (p_filter is null)
		THEN set p_filter = 'B';
	END IF;
	
	IF (tAdvisor is NOT NULL)
		THEN set isAdvisor = TRUE;  
		ELSE set isAdvisor = FALSE;
	END IF;

	begin
    IF (isAdvisor)
    THEN
		CASE
			WHEN (p_filter = 'A')
			THEN
				SELECT
					user.logonid,
                    advisor_access.`privileges` `advisor_privileges`,
					`profile`.acctnum,
				    `profile`.advisor,
                    `profile`.rep,
				    `profile`.theme,
					`profile`.goal,
					`profile`.portfolioName,
					'Active' as acctstatus,
					user.email as email,
					ext_acct_info.applicantLName  as lastname,
					ext_acct_info.applicantFName  as firstname,
					`profile`.tradePreference,
					`profile`.`acctType` as accttype,
					IFNULL(`profile`.age,30) as age,
					IFNULL(`profile`.horizon,35) as horizon,
					IFNULL(`profile`.riskIndex,0) as riskIndex,
					CAST(IFNULL(`profile`.initialInvestment,0) as SIGNED) as initialInvestment,
					funct_get_actualCapital(`profile`.acctnum) as actualCapital,
					`profile`.keepLiquid as keepLiquid,
					CAST(IFNULL(`profile`.recurringInvestment,0) as SIGNED) as recurringInvestment,
					IFNULL(`profile`.longTermGoal,0) as longTermGoal,
					IFNULL(`profile`.stayInvested,0) as stayInvested,
					DATE_FORMAT(`profile`.created,'%Y-%m-%d') as created
				from
					ext_acct_info,
                    user_trade_profile `profile`,
					user_access_role uar,
					user_logon user,
                    user_advisor_access advisor_access
				WHERE ext_acct_info.acctnum = `profile`.acctnum
                AND   ext_acct_info.status = 'A'
                AND   uar.logonid = user.logonid 
				AND   `profile`.acctnum = uar.acctnum 
				AND    uar.role = 'OWNER'
                AND    advisor_access.logonid = p_logonid
                AND     IFNULL(`profile`.advisor, 'Invessence') like IFNULL(advisor_access.advisor,'%')
                AND   IFNULL(`profile`.rep,'000') like IFNULL(advisor_access.rep,'%')
				;
			WHEN (p_filter = 'P')
			THEN
				select
					user.logonid,
                    advisor_access.`privileges` `advisor_privileges`,
					`profile`.acctnum,
				    `profile`.advisor,
                    `profile`.rep,
				    `profile`.theme,
					`profile`.goal,
					`profile`.portfolioName,
					'Pending' as acctstatus,
					user.email email,
					user.lastname as lastname,
					user.firstname as firstname,
					`profile`.tradePreference,
					`profile`.`acctType` as accttype,
					IFNULL(`profile`.age,30) as age,
					IFNULL(`profile`.horizon,35) as horizon,
					IFNULL(`profile`.riskIndex,0) as riskIndex,
					CAST(IFNULL(`profile`.initialInvestment,0) as SIGNED) as initialInvestment,
					round(funct_get_actualCapital(`profile`.acctnum),0) as actualCapital,
					`profile`.keepLiquid as keepLiquid,
					CAST(IFNULL(`profile`.recurringInvestment,0) as SIGNED) as recurringInvestment,
					IFNULL(`profile`.longTermGoal,0) as longTermGoal,
					IFNULL(`profile`.stayInvested,0) as stayInvested,
					DATE_FORMAT(`profile`.created,'%Y-%m-%d') as created,
					profile.lastUpdated
				from
					user_trade_profile `profile`
                    INNER JOIN user_advisor_access advisor_access
                    LEFT JOIN user_access_role uar
                    ON (`profile`.acctnum = uar.acctnum
                        AND    uar.role = 'OWNER')
					LEFT JOIN user_logon user
                    ON (uar.logonid = user.logonid)
				WHERE `profile`.acctnum NOT IN (SELECT ext.acctnum from ext_acct_info ext
												WHERE ext.status = 'A')
                AND    advisor_access.logonid = p_logonid
                AND     IFNULL(`profile`.advisor, 'Invessence') like IFNULL(advisor_access.advisor,'%')
                AND   IFNULL(`profile`.rep,'000') like IFNULL(advisor_access.rep,'%')
				;
			WHEN (p_filter = 'B')
			THEN
				SELECT
					user.logonid,
                    advisor_access.`privileges` `advisor_privileges`,
					`profile`.acctnum,
				    `profile`.advisor,
                    `profile`.rep,
				    `profile`.theme,
					`profile`.goal,
					`profile`.portfolioName,
					CASE WHEN (ext_acct_info.`status` = 'A') THEN 'Active'
						 ELSE 'Pending' 
					END as acctstatus,
					user.email as email,
					ext_acct_info.applicantLName  as lastname,
					ext_acct_info.applicantFName  as firstname,
					`profile`.tradePreference,
					`profile`.`acctType` as accttype,
					IFNULL(`profile`.age,30) as age,
					IFNULL(`profile`.horizon,35) as horizon,
					IFNULL(`profile`.riskIndex,0) as riskIndex,
					CAST(IFNULL(`profile`.initialInvestment,0) as SIGNED) as initialInvestment,
					round(funct_get_actualCapital(`profile`.acctnum),0) as actualCapital,
					`profile`.keepLiquid as keepLiquid,
					CAST(IFNULL(`profile`.recurringInvestment,0) as SIGNED) as recurringInvestment,
					IFNULL(`profile`.longTermGoal,0) as longTermGoal,
					IFNULL(`profile`.stayInvested,0) as stayInvested,
					DATE_FORMAT(`profile`.created,'%Y-%m-%d') as created
				from user_trade_profile `profile`
                    INNER JOIN user_advisor_access advisor_access
                    LEFT JOIN ext_acct_info
                    ON (ext_acct_info.acctnum = `profile`.acctnum)
                    LEFT JOIN user_access_role uar
                    ON (`profile`.acctnum = uar.acctnum
						AND uar.role = 'OWNER' )
					LEFT JOIN  user_logon user
                    ON (uar.logonid = user.logonid)
				WHERE advisor_access.logonid = p_logonid
                AND     IFNULL(`profile`.advisor, 'Invessence') like IFNULL(advisor_access.advisor,'%')
                AND   IFNULL(`profile`.rep,'000') like IFNULL(advisor_access.rep,'%')
				;
        END CASE;
    END IF;
    end;
END$$
DELIMITER ;
