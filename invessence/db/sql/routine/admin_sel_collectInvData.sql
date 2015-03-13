DROP PROCEDURE IF EXISTS `admin_sel_collectInvData`;
DELIMITER $$
CREATE PROCEDURE `admin_sel_collectInvData`(
	IN p_filter	VARCHAR(20)
)
BEGIN
	IF (p_filter is null)
		THEN set p_filter = "ALL";
		ELSE set p_filter = upper(p_filter);
	END IF;

	IF (p_filter = "ALL")
		then
			select 
				user_logon.logonid,
				user_logon.email,
				user_logon.firstname,
				user_logon.lastname,
				user_logon.state,
				profile.acctnum,
			profile.advisor,
			profile.theme,
			profile.tradePreference,
			IFNULL(profile.goal,0) as goal,
			profile.`acctType` as accttype,
			IFNULL(profile.age,30) as age,
			IFNULL(profile.horizon,0) as horizon,
			round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
			IFNULL(profile.riskIndex,0) as riskIndex,
			CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
			IFNULL(profile.keepLiquid,0) as keepLiquid,
				IFNULL(profile.recurringInvestment,0) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
			IFNULL(`acct_financial`.`dependent`,0) as dependent,
			IFNULL(`acct_financial`.`totalIncomeAnnulized`,0) as totalIncomeAnnulized,
			IFNULL(`acct_financial`.`totalExpenseAnnulized`,0) as totalExpenseAnnulized,
			IFNULL(`acct_financial`.`totalAsset`,0) as totalAsset,
			IFNULL(`acct_financial`.`totalDebt`,0) as totalDebt,
			IFNULL(`acct_financial`.`liquidnetworth`,0) as liquidnetworth,
			IFNULL(`acct_financial`.`networth`,0) as networth,
			IFNULL(`user_risk_questions`.`ans1`,0) AS ans1,
			IFNULL(`user_risk_questions`.`ans2`,0) AS ans2,
			IFNULL(`user_risk_questions`.`ans3`,0) AS ans3,
			IFNULL(`user_risk_questions`.`ans4`,0) AS ans4,
			IFNULL(`user_risk_questions`.`ans5`,0) AS ans5,
			IFNULL(`user_risk_questions`.`ans6`,0) AS ans6,
			IFNULL(`user_risk_questions`.`ans7`,0) AS ans7,
			IFNULL(`user_risk_questions`.`ans8`,0) AS ans8,
			IFNULL(`user_risk_questions`.`ans9`,0) AS ans9,
			IFNULL(`user_risk_questions`.`ans10`,0) AS ans10,
			IFNULL(`user_risk_questions`.`ans11`,0) AS ans11,
			IFNULL(`user_risk_questions`.`ans12`,0) AS ans12,
			IFNULL(`user_risk_questions`.`ans13`,0) AS ans13,
			IFNULL(`user_risk_questions`.`ans14`,0) AS ans14,
			IFNULL(`user_risk_questions`.`ans15`,0) AS ans15,
			DATE_FORMAT(profile.created,'%Y-%m-%d') as created
			--	profile.lastUpdated
			from
				user_logon,
				user_access_role,
				user_trade_profile profile
				left join `acct_financial`
				On ( profile.acctnum = `acct_financial`.acctnum)
				left join `user_risk_questions`
				On (profile.acctnum = `user_risk_questions`.acctnum)
			where
				user_logon.logonid = user_access_role.logonid
			and user_access_role.acctnum = profile.acctnum
			and user_access_role.role = 'OWNER';
	end if;
	if (p_filter = "NEW")
		then
			select 
				user_logon.logonid,
				user_logon.email,
				user_logon.firstname,
				user_logon.lastname,
				user_logon.state,
				profile.acctnum,
			profile.advisor,
			profile.theme,
			profile.tradePreference,
			IFNULL(profile.goal,0) as goal,
			profile.`acctType` as accttype,
			IFNULL(profile.age,30) as age,
			IFNULL(profile.horizon,0) as horizon,
			round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
			IFNULL(profile.riskIndex,0) as riskIndex,
			CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
			IFNULL(profile.keepLiquid,0) as keepLiquid,
				IFNULL(profile.recurringInvestment,0) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
			IFNULL(`acct_financial`.`dependent`,0) as dependent,
			IFNULL(`acct_financial`.`totalIncomeAnnulized`,0) as totalIncomeAnnulized,
			IFNULL(`acct_financial`.`totalExpenseAnnulized`,0) as totalExpenseAnnulized,
			IFNULL(`acct_financial`.`totalAsset`,0) as totalAsset,
			IFNULL(`acct_financial`.`totalDebt`,0) as totalDebt,
			IFNULL(`acct_financial`.`liquidnetworth`,0) as liquidnetworth,
			IFNULL(`acct_financial`.`networth`,0) as networth,
			IFNULL(`user_risk_questions`.`ans1`,0) AS ans1,
			IFNULL(`user_risk_questions`.`ans2`,0) AS ans2,
			IFNULL(`user_risk_questions`.`ans3`,0) AS ans3,
			IFNULL(`user_risk_questions`.`ans4`,0) AS ans4,
			IFNULL(`user_risk_questions`.`ans5`,0) AS ans5,
			IFNULL(`user_risk_questions`.`ans6`,0) AS ans6,
			IFNULL(`user_risk_questions`.`ans7`,0) AS ans7,
			IFNULL(`user_risk_questions`.`ans8`,0) AS ans8,
			IFNULL(`user_risk_questions`.`ans9`,0) AS ans9,
			IFNULL(`user_risk_questions`.`ans10`,0) AS ans10,
			IFNULL(`user_risk_questions`.`ans11`,0) AS ans11,
			IFNULL(`user_risk_questions`.`ans12`,0) AS ans12,
			IFNULL(`user_risk_questions`.`ans13`,0) AS ans13,
			IFNULL(`user_risk_questions`.`ans14`,0) AS ans14,
			IFNULL(`user_risk_questions`.`ans15`,0) AS ans15,
			DATE_FORMAT(profile.created,'%Y-%m-%d') as created
			--	profile.lastUpdated
			from
				user_logon,
				user_access_role,
				user_trade_profile profile
				left join `acct_financial`
				On ( profile.acctnum = `acct_financial`.acctnum)
				left join `user_risk_questions`
				On (profile.acctnum = `user_risk_questions`.acctnum)
			where
				user_logon.logonid = user_access_role.logonid
			and user_access_role.acctnum = profile.acctnum
			and user_access_role.role = 'OWNER'
			and user_access_role.acctnum not in (select IB.acctnum
									 from IB_Accounts IB 
									 where IB.acctnum is not null);
	end if;
	if (p_filter = "TRADED" or p_filter = "ACTIVE")
		then
			select 
				user_logon.logonid,
				user_logon.email,
				user_logon.firstname,
				user_logon.lastname,
				user_logon.state,
				profile.acctnum,
			profile.advisor,
			profile.theme,
			profile.tradePreference,
			IFNULL(profile.goal,0) as goal,
			profile.`acctType` as accttype,
			IFNULL(profile.age,30) as age,
			IFNULL(profile.horizon,0) as horizon,
			round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
			IFNULL(profile.riskIndex,0) as riskIndex,
			CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
			IFNULL(profile.keepLiquid,0) as keepLiquid,
				IFNULL(profile.recurringInvestment,0) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
			IFNULL(`acct_financial`.`dependent`,0) as dependent,
			IFNULL(`acct_financial`.`totalIncomeAnnulized`,0) as totalIncomeAnnulized,
			IFNULL(`acct_financial`.`totalExpenseAnnulized`,0) as totalExpenseAnnulized,
			IFNULL(`acct_financial`.`totalAsset`,0) as totalAsset,
			IFNULL(`acct_financial`.`totalDebt`,0) as totalDebt,
			IFNULL(`acct_financial`.`liquidnetworth`,0) as liquidnetworth,
			IFNULL(`acct_financial`.`networth`,0) as networth,
			IFNULL(`user_risk_questions`.`ans1`,0) AS ans1,
			IFNULL(`user_risk_questions`.`ans2`,0) AS ans2,
			IFNULL(`user_risk_questions`.`ans3`,0) AS ans3,
			IFNULL(`user_risk_questions`.`ans4`,0) AS ans4,
			IFNULL(`user_risk_questions`.`ans5`,0) AS ans5,
			IFNULL(`user_risk_questions`.`ans6`,0) AS ans6,
			IFNULL(`user_risk_questions`.`ans7`,0) AS ans7,
			IFNULL(`user_risk_questions`.`ans8`,0) AS ans8,
			IFNULL(`user_risk_questions`.`ans9`,0) AS ans9,
			IFNULL(`user_risk_questions`.`ans10`,0) AS ans10,
			IFNULL(`user_risk_questions`.`ans11`,0) AS ans11,
			IFNULL(`user_risk_questions`.`ans12`,0) AS ans12,
			IFNULL(`user_risk_questions`.`ans13`,0) AS ans13,
			IFNULL(`user_risk_questions`.`ans14`,0) AS ans14,
			IFNULL(`user_risk_questions`.`ans15`,0) AS ans15,
			DATE_FORMAT(profile.created,'%Y-%m-%d') as created
			--	profile.lastUpdated
			from
				user_logon,
				user_access_role,
				user_trade_profile profile
				left join `acct_financial`
				On ( profile.acctnum = `acct_financial`.acctnum)
				left join `user_risk_questions`
				On (profile.acctnum = `user_risk_questions`.acctnum)
			where
				user_logon.logonid = user_access_role.logonid
			and user_access_role.acctnum = profile.acctnum
			and user_access_role.role = 'OWNER'
            and user_access_role.acctnum in (SELECT IB.acctnum from IB_Accounts IB
											WHERE IB.acctnum is not null);
	end if;
	if (p_filter = "FUNDED")
		then
			select 
				user_logon.logonid,
				user_logon.email,
				user_logon.firstname,
				user_logon.lastname,
				user_logon.state,
				profile.acctnum,
			profile.advisor,
			profile.theme,
			profile.tradePreference,
			IFNULL(profile.goal,0) as goal,
			profile.`acctType` as accttype,
			IFNULL(profile.age,30) as age,
			IFNULL(profile.horizon,0) as horizon,
			round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
			IFNULL(profile.riskIndex,0) as riskIndex,
			CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
			IFNULL(profile.keepLiquid,0) as keepLiquid,
				IFNULL(profile.recurringInvestment,0) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
			IFNULL(`acct_financial`.`dependent`,0) as dependent,
			IFNULL(`acct_financial`.`totalIncomeAnnulized`,0) as totalIncomeAnnulized,
			IFNULL(`acct_financial`.`totalExpenseAnnulized`,0) as totalExpenseAnnulized,
			IFNULL(`acct_financial`.`totalAsset`,0) as totalAsset,
			IFNULL(`acct_financial`.`totalDebt`,0) as totalDebt,
			IFNULL(`acct_financial`.`liquidnetworth`,0) as liquidnetworth,
			IFNULL(`acct_financial`.`networth`,0) as networth,
			IFNULL(`user_risk_questions`.`ans1`,0) AS ans1,
			IFNULL(`user_risk_questions`.`ans2`,0) AS ans2,
			IFNULL(`user_risk_questions`.`ans3`,0) AS ans3,
			IFNULL(`user_risk_questions`.`ans4`,0) AS ans4,
			IFNULL(`user_risk_questions`.`ans5`,0) AS ans5,
			IFNULL(`user_risk_questions`.`ans6`,0) AS ans6,
			IFNULL(`user_risk_questions`.`ans7`,0) AS ans7,
			IFNULL(`user_risk_questions`.`ans8`,0) AS ans8,
			IFNULL(`user_risk_questions`.`ans9`,0) AS ans9,
			IFNULL(`user_risk_questions`.`ans10`,0) AS ans10,
			IFNULL(`user_risk_questions`.`ans11`,0) AS ans11,
			IFNULL(`user_risk_questions`.`ans12`,0) AS ans12,
			IFNULL(`user_risk_questions`.`ans13`,0) AS ans13,
			IFNULL(`user_risk_questions`.`ans14`,0) AS ans14,
			IFNULL(`user_risk_questions`.`ans15`,0) AS ans15,
			DATE_FORMAT(profile.created,'%Y-%m-%d') as created
			--	profile.lastUpdated
			from
				user_logon,
				user_access_role,
				user_trade_profile profile
				left join `acct_financial`
				On ( profile.acctnum = `acct_financial`.acctnum)
				left join `user_risk_questions`
				On (profile.acctnum = `user_risk_questions`.acctnum)
			where
				user_logon.logonid = user_access_role.logonid
			and user_access_role.acctnum = profile.acctnum
			and user_access_role.role = 'OWNER'
            and user_access_role.acctnum in (SELECT IB.acctnum from IB_Accounts IB
												 WHERE upper(IB.accountStatus) in ('FUNDED'));
	end if;

END$$
DELIMITER ;
