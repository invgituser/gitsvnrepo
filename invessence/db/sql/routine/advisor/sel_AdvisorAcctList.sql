DROP PROCEDURE IF Exists `sel_AdvisorAcctList`;

DELIMITER $$
CREATE PROCEDURE `sel_AdvisorAcctList`(
	p_filter BIGINT
)
BEGIN

	begin

		select
			user.logonid,
			profile.acctnum,
			IB.IB_acctnum,
			CASE WHEN (IB.IB_acctnum is null) then 'Pending'
				 ELSE 'Active'
			END as acctstatus,
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
			profile.created,
			nav.stock,
			(nav.interestAccrual + nav.dividentAccrual) as accrual,
			profile.lastUpdated
		from
			user_logon user,
			user_access_role uar,
			user_trade_profile profile
			LEFT JOIN `IB_Accounts` IB
				ON (profile.acctnum = IB.acctnum)
			LEFT JOIN `nav_daily` nav
			    ON (IB.IB_acctnum = nav.clientAccountID
				AND nav.reportDate = funct_get_switch('BROKER_BDATE'))
	    WHERE user.logonid = uar.logonid
		AND   uar.acctnum = profile.acctnum
		AND   user.logonstatus = 'A'

		AND   user.logonid = p_filter
		;

    end;
END$$
DELIMITER ;
