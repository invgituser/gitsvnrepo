DROP PROCEDURE IF EXISTS `sel_AdvisorAcctProfile`;

DELIMITER $$
CREATE PROCEDURE `sel_AdvisorAcctProfile`(
	p_acctnum BIGINT
)
BEGIN

	begin
				select
					user.logonid,
					`profile`.acctnum,
				    `profile`.advisor,
				    `profile`.theme,
					`profile`.goal,
					'Pending' as acctstatus,
					user.email as email,
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
					user_trade_profile `profile`,
					user_access_role uar,
					user_logon user
				WHERE uar.logonid = user.logonid 
				AND   `profile`.acctnum = uar.acctnum 
				AND    uar.role = 'OWNER' 
				AND   `profile`.acctnum = p_acctnum
				;
    end;
END$$
DELIMITER ;
