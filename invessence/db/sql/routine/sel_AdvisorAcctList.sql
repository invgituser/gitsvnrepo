DROP PROCEDURE IF EXISTS sel_AdvisorAcctList;

DELIMITER $$
CREATE PROCEDURE `sel_AdvisorAcctList`(
	p_logonid BIGINT,
	p_filter  VARCHAR(20)
)
BEGIN

	begin

		IF (upper(p_filter) = 'ACTIVE' OR upper(p_filter) = 'ALL')
		then
				select
					user.logonid,
					profile.acctnum,
					IB.IB_acctnum,
					'Active' as acctstatus,
					IFNULL(IB.email, user.email) as email,
					IFNULL(IB.lastname, user.lastname) as lastname,
					IFNULL(IB.firstname, user.firstname) as firstname,
					profile.tradePreference,
					profile.`acctType` as accttype,
					IFNULL(profile.age,30) as age,
					IFNULL(profile.horizon,0) as horizon,
					IFNULL(profile.riskIndex,0) as riskIndex,
					CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
					round(funct_get_actualCapital(profile.acctnum),0) as actualCapital,
					profile.keepLiquid as keepLiquid,
					CAST(IFNULL(profile.recurringInvestment,0) as SIGNED) as recurringInvestment,
					IFNULL(profile.longTermGoal,0) as longTermGoal,
					IFNULL(profile.stayInvested,0) as stayInvested,
					DATE_FORMAT(profile.created,'%Y-%m-%d') as created,
					nav.stock,
					(nav.interestAccrual + nav.dividentAccrual) as accrual,
					profile.lastUpdated
				from
					user_logon user,
					user_access_role uar,
					user_trade_profile profile,
					`IB_Accounts` IB
					LEFT JOIN `nav_daily` nav
						ON (IB.IB_acctnum = nav.clientAccountID
						AND nav.reportDate = funct_get_switch('BROKER_BDATE'))
				WHERE profile.acctnum = IB.acctnum
				AND   user.logonid = uar.logonid
				AND   uar.acctnum = profile.acctnum
				AND   uar.acctnum in (select uar2.acctnum from user_access_role uar2 where logonid = p_logonid and role = 'ADVISOR')
				AND   user.logonid <> p_logonid
				;
		end if;

		IF (upper(p_filter) = 'PENDING' OR upper(p_filter) = 'ALL')
		then
			select
				user.logonid,
				profile.acctnum,
				'',
				'Pending' as acctstatus,
				user.email as email,
				user.lastname as lastname,
				user.firstname as firstname,
				profile.tradePreference,
				profile.`acctType` as accttype,
				IFNULL(profile.age,30) as age,
				IFNULL(profile.horizon,0) as horizon,
				IFNULL(profile.riskIndex,0) as riskIndex,
				CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
				0 as actualCapital,
				profile.keepLiquid as keepLiquid,
				CAST(IFNULL(profile.recurringInvestment,0) as SIGNED) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
			    DATE_FORMAT(profile.created,'%Y-%m-%d') as created,
				0 as stock,
				0 as accrual,
				profile.lastUpdated
			from
				user_logon user,
				user_access_role uar,
				user_trade_profile profile
			WHERE user.logonid = uar.logonid
			AND   uar.acctnum = profile.acctnum
			AND   uar.acctnum in (select uar2.acctnum from user_access_role uar2 where logonid = p_logonid and role = 'ADVISOR')
			AND   user.logonid <> p_logonid
			AND   profile.acctnum not in (select IB_acctnum from `IB_Accounts`)
			;

		end if;
    end;
END$$
DELIMITER ;
