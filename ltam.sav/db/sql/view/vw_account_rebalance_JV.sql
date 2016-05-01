DROP VIEW IF EXISTS `vw_account_rebalance_JV`;

CREATE 
VIEW `vw_account_rebalance_JV` AS
    select 
		user_logon.logonid,
		user_logon.email,
		profile.acctnum as `acctnum`,
        `IB_Accounts`.`IB_acctnum` AS `IB_acctnum`,
        concat(`IB_Accounts`.`firstName`,
                ' ',
                `IB_Accounts`.`lastName`) AS `name`,
        profile.`goal` AS `goal`,
        profile.`acctType` AS `taxType`,
        profile.`age` AS `age`,
        profile.`horizon` AS `horizon`,
        profile.`riskIndex` AS `riskIndex`,
		IFNULL(profile.longTermGoal,0) as longTermGoal,
        profile.`stayInvested` AS `stayInvested`,
        `nav_daily`.`total` AS `totalCapital`,
		IFNULL(`acct_financial`.`dependent`,0) as dependent,
		IFNULL(`acct_financial`.`totalIncomeAnnulized`,0) as totalIncomeAnnulized,
		IFNULL(`acct_financial`.`totalExpenseAnnulized`,0) as totalExpenseAnnulized,
		IFNULL(`acct_financial`.`totalAsset`,0) as totalAsset,
		IFNULL(`acct_financial`.`totalDebt`,0) as totalDebt,
		IFNULL(`acct_financial`.`liquidnetworth`,0) as liquidnetworth,
		IFNULL(`acct_financial`.`networth`,0) as networth,
        profile.`created` AS `dateCreated`,
		profile.lastUpdated
    from
		user_logon,
		user_access_role,
		user_trade_profile profile
		left join `acct_financial`
			On ( profile.acctnum = `acct_financial`.acctnum)
        left join `IB_Accounts` 
			ON ((profile.`acctnum` = `IB_Accounts`.`acctnum`))
        left join `nav_daily` 
			ON ((convert( `IB_Accounts`.`IB_acctnum` using utf8) = `nav_daily`.`clientAccountID`)
				and (`nav_daily`.`reportDate` = (select value from invessence_switch where name = 'BROKER_BDATE'))
				and (`nav_daily`.`total` > 0.0))
	where
		user_logon.logonid = user_access_role.logonid
	and user_access_role.acctnum = profile.acctnum
	and user_access_role.acctnum in (SELECT IB.acctnum from IB_Accounts IB
												 WHERE IB.accountStatus in ('Active'));
