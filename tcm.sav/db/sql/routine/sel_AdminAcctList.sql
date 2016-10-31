DROP PROCEDURE `sel_AdminAcctList`;

DELIMITER $$
CREATE PROCEDURE `sel_AdminAcctList`(
	IN p_role    VARCHAR(20)
)
BEGIN

	begin
		select
			user.logonid,
			profile.acctnum,
			profile.advisor,
			profile.theme,
			IB.IB_acctnum,
			CASE WHEN (IB.accountStatus is null) THEN 'Pending'
				 WHEN (upper(IB.accountStatus) = 'ACTIVE') THEN 'Active'
				 WHEN (upper(IB.accountStatus) = 'FUNDED') THEN 'Active'
				 WHEN (IB.accountStatus is not null) then 'Pending'
				 ELSE 'Pending'
			END as activeStatus,
			IB.accountStatus,
			IFNULL(IB.email, user.email) as email,
			IFNULL(IB.lastname, user.lastname) as lastname,
			IFNULL(IB.firstname, user.firstname) as firstname,
			profile.tradePreference,
			`profile`.`goal` as goal,
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
			user_trade_profile profile
			LEFT JOIN `IB_Accounts` as IB
			ON (profile.acctnum = IB.acctnum)
			LEFT JOIN `nav_daily` nav
				ON (IB.IB_acctnum = nav.clientAccountID
				AND nav.reportDate = funct_get_switch('BROKER_BDATE'))
		WHERE user.logonid = uar.logonid
		AND   uar.acctnum = profile.acctnum
		AND   UPPER(IFNULL(profile.advisor,'Invessence')) = IFNULL(p_role,'Invessence')
		AND   uar.role = 'OWNER'
		;
    end;
END$$
DELIMITER ;
