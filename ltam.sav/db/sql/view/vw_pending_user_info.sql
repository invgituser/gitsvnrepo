DROP VIEW IF EXISTS `vw_pending_user_info`;

CREATE VIEW `vw_pending_user_info` AS
    select 
        user_logon.logonid,
        user_logon.email,
        CONCAT(user_logon.firstname,
                ' ',
                user_logon.lastname) as name,
		user_logon.firstname,
		user_logon.lastname,
        user_logon.address,
        user_logon.phone,
        user_access_role.acctnum,
        profile.`acctType` as accttype,
        profile.age,
        profile.horizon,
        profile.riskIndex,
        profile.initialInvestment,
        profile.recurringInvestment,
        profile.longTermGoal,
        profile.stayInvested
    from
        user_logon,
        user_access_role,
        user_trade_profile profile
    where
        user_logon.logonid = user_access_role.logonid
            and user_access_role.acctnum = profile.acctnum
            and user_access_role.acctnum not in (select 
													IB.acctnum
												 from
													IB_Accounts IB
												)
;

