DROP PROCEDURE IF EXISTS `sel_ClientProfileData2`;

DELIMITER $$
CREATE PROCEDURE `sel_ClientProfileData2`(
	IN p_logonid BIGINT,
	IN p_acctnum BIGINT
)
BEGIN

	DECLARE filterType VARCHAR(1);
	DECLARE tAdvisorType VARCHAR(25);

	IF (p_acctnum is not null)
		THEN SET filterType = 'S';  -- Single account
	ELSE
		IF (p_logonid is null)
			THEN set filterType = 'X';  -- System Admin account
		ELSE
			SELECT MIN(groupname)
			INTO tAdvisorType
			FROM advisor_info
			WHERE logonid = p_logonid;
			IF (tAdvisorType is NOT NULL)
				THEN set filterType = 'A';  -- Advisor process
				ELSE set filterType = 'O';  -- Owner
			END IF;
		END IF;
	END IF;
		
	IF (filterType = 'S')
	THEN
		select 
		user_logon.logonid,
		profile.acctnum,
		user_logon.email,
		user_logon.userid,
		profile.advisor,
		profile.theme,
		user_logon.lastname,
		user_logon.firstname,
		user_logon.state,
		IB.IB_acctnum as clientAccountID,
		CASE WHEN (IB.accountStatus is null) THEN 'Pending'
			 WHEN (upper(IB.accountStatus) = 'ACTIVE') THEN 'Active'
			 ELSE 'Pending'
		END as acctStatus,
		profile.tradePreference,
		IFNULL(profile.goal,'Retirement') as goal,
		IFNULL(profile.`acctType`,'IRA') as accttype,
		IFNULL(profile.age,30) as age,
		IFNULL(profile.horizon,30) as horizon,
		round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
		IFNULL(profile.riskIndex,0) as riskIndex,
		CAST(IFNULL(profile.initialInvestment,5000) as SIGNED) as initialInvestment,
		round(funct_get_actualCapital(profile.acctnum),0) as actualCapital,
		IFNULL(profile.keepLiquid,0) as keepLiquid,
		IFNULL(profile.recurringInvestment,0) as recurringInvestment,
		IFNULL(profile.longTermGoal,0) as longTermGoal,
		IFNULL(profile.stayInvested,0) as stayInvested,
		IFNULL(`acct_financial`.`dependent`,0) as dependent,
		IFNULL(IB.dateOpened,DATE_FORMAT(profile.created,'%Y%m%d')) as dateOpened,

				IFNULL(`acct_financial`.`householdwages`,0) as householdwages,
				IFNULL(`acct_financial`.`householdPayment`,0) as householdPayment,
				IFNULL(`acct_financial`.`miscExpenses`,0) as miscExpenses,
				IFNULL(`acct_financial`.`moneyMarket`,0) as moneyMarket,
				IFNULL(`acct_financial`.`investment`,0) as investment,
				IFNULL(`acct_financial`.`otherDebt`,0) as otherDebt,

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
			left join `IB_Accounts` IB
			On (IB.acctnum = profile.acctnum)
		where
			user_logon.logonid = user_access_role.logonid
		and user_access_role.acctnum = profile.acctnum
		and user_access_role.role = 'OWNER'
		and profile.acctnum = p_acctnum
		order by profile.created desc
		;
	ELSE
	IF (filterType = 'A')
		THEN
			select 
			user_logon.logonid,
			profile.acctnum,
			user_logon.email,
			user_logon.userid,
			profile.advisor,
			profile.theme,
			user_logon.lastname,
			user_logon.firstname,
			user_logon.state,
			IB.IB_acctnum as clientAccountID,
			CASE WHEN (IB.accountStatus is null) THEN 'Pending'
				 WHEN (upper(IB.accountStatus) = 'ACTIVE') THEN 'Active'
				 ELSE 'Pending'
			END as acctStatus,
			profile.tradePreference,
			IFNULL(profile.goal,'Retirement') as goal,
			IFNULL(profile.`acctType`,'IRA') as accttype,
			IFNULL(profile.age,30) as age,
			IFNULL(profile.horizon,30) as horizon,
			round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
			IFNULL(profile.riskIndex,0) as riskIndex,
			CAST(IFNULL(profile.initialInvestment,5000) as SIGNED) as initialInvestment,
			round(funct_get_actualCapital(profile.acctnum),0) as actualCapital,
			IFNULL(profile.keepLiquid,0) as keepLiquid,
			IFNULL(profile.recurringInvestment,0) as recurringInvestment,
			IFNULL(profile.longTermGoal,0) as longTermGoal,
			IFNULL(profile.stayInvested,0) as stayInvested,
			IFNULL(`acct_financial`.`dependent`,0) as dependent,
			IFNULL(IB.dateOpened,DATE_FORMAT(profile.created,'%Y%m%d')) as dateOpened,

				IFNULL(`acct_financial`.`householdwages`,0) as householdwages,
				IFNULL(`acct_financial`.`householdPayment`,0) as householdPayment,
				IFNULL(`acct_financial`.`miscExpenses`,0) as miscExpenses,
				IFNULL(`acct_financial`.`moneyMarket`,0) as moneyMarket,
				IFNULL(`acct_financial`.`investment`,0) as investment,
				IFNULL(`acct_financial`.`otherDebt`,0) as otherDebt,

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
				left join `IB_Accounts` IB
				On (IB.acctnum = profile.acctnum)
			where
				user_logon.logonid = user_access_role.logonid
			and user_access_role.acctnum = profile.acctnum
			and user_access_role.role = 'OWNER'
			and IFNULL(profile.advisor,'Invessence') = IFNULL(tAdvisorType,'Invessence')
			order by profile.created desc
			;
		ELSE 
			IF (filterType = 'X')
			THEN
				select 
				user_logon.logonid,
				profile.acctnum,
				user_logon.email,
				user_logon.userid,
				profile.advisor,
				profile.theme,
				user_logon.lastname,
				user_logon.firstname,
				user_logon.state,
				IB.IB_acctnum as clientAccountID,
				CASE WHEN (IB.accountStatus is null) THEN 'Pending'
					 WHEN (upper(IB.accountStatus) = 'ACTIVE') THEN 'Active'
					 ELSE 'Pending'
				END as acctStatus,
				profile.tradePreference,
				IFNULL(profile.goal,'Retirement') as goal,
				IFNULL(profile.`acctType`,'IRA') as accttype,
				IFNULL(profile.age,30) as age,
				IFNULL(profile.horizon,30) as horizon,
				round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
				IFNULL(profile.riskIndex,0) as riskIndex,
				CAST(IFNULL(profile.initialInvestment,5000) as SIGNED) as initialInvestment,
				round(funct_get_actualCapital(profile.acctnum),0) as actualCapital,
				IFNULL(profile.keepLiquid,0) as keepLiquid,
				IFNULL(profile.recurringInvestment,0) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
				IFNULL(`acct_financial`.`dependent`,0) as dependent,
				IFNULL(IB.dateOpened,DATE_FORMAT(profile.created,'%Y%m%d')) as dateOpened,

				IFNULL(`acct_financial`.`householdwages`,0) as householdwages,
				IFNULL(`acct_financial`.`householdPayment`,0) as householdPayment,
				IFNULL(`acct_financial`.`miscExpenses`,0) as miscExpenses,
				IFNULL(`acct_financial`.`moneyMarket`,0) as moneyMarket,
				IFNULL(`acct_financial`.`investment`,0) as investment,
				IFNULL(`acct_financial`.`otherDebt`,0) as otherDebt,

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
					left join `IB_Accounts` IB
					On (IB.acctnum = profile.acctnum)
				where
					user_logon.logonid = user_access_role.logonid
				and user_access_role.acctnum = profile.acctnum
				and user_access_role.role = 'OWNER'
				order by profile.created desc
				;
			ELSE
				select 
				user_logon.logonid,
				profile.acctnum,
				user_logon.email,
				user_logon.userid,
				profile.advisor,
				profile.theme,
				user_logon.lastname,
				user_logon.firstname,
				user_logon.state,
				IB.IB_acctnum as clientAccountID,
				CASE WHEN (IB.accountStatus is null) THEN 'Pending'
					 WHEN (upper(IB.accountStatus) = 'ACTIVE') THEN 'Active'
					 ELSE 'Pending'
				END as acctStatus,
				profile.tradePreference,
				IFNULL(profile.goal,'Retirement') as goal,
				IFNULL(profile.`acctType`,'IRA') as accttype,
				IFNULL(profile.age,30) as age,
				IFNULL(profile.horizon,30) as horizon,
				round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
				IFNULL(profile.riskIndex,0) as riskIndex,
				CAST(IFNULL(profile.initialInvestment,5000) as SIGNED) as initialInvestment,
				round(funct_get_actualCapital(profile.acctnum),0) as actualCapital,
				IFNULL(profile.keepLiquid,0) as keepLiquid,
				IFNULL(profile.recurringInvestment,0) as recurringInvestment,
				IFNULL(profile.longTermGoal,0) as longTermGoal,
				IFNULL(profile.stayInvested,0) as stayInvested,
				IFNULL(`acct_financial`.`dependent`,0) as dependent,
				IFNULL(IB.dateOpened,DATE_FORMAT(profile.created,'%Y%m%d')) as dateOpened,

				IFNULL(`acct_financial`.`householdwages`,0) as householdwages,
				IFNULL(`acct_financial`.`householdPayment`,0) as householdPayment,
				IFNULL(`acct_financial`.`miscExpenses`,0) as miscExpenses,
				IFNULL(`acct_financial`.`moneyMarket`,0) as moneyMarket,
				IFNULL(`acct_financial`.`investment`,0) as investment,
				IFNULL(`acct_financial`.`otherDebt`,0) as otherDebt,

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
					left join `IB_Accounts` IB
					On (IB.acctnum = profile.acctnum)
				where
					user_logon.logonid = user_access_role.logonid
				and user_access_role.acctnum = profile.acctnum
				and user_access_role.role = 'OWNER'
				and user_logon.logonid = p_logonid
				order by profile.created desc
				;
			END IF;
		END IF;
	END IF;

END$$
DELIMITER ;
