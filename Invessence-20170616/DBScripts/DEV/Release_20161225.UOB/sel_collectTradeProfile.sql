DROP PROCEDURE IF EXISTS `sel_collectTradeProfile`;

DELIMITER $$
CREATE PROCEDURE `sel_collectTradeProfile`(
	p_filter varchar(1)
)
BEGIN
	begin
		call sp_addClients2Trade();
	end;

	begin
		select 
			profile.acctnum,
			ext_acct_info.clientAccountID,
			ext_acct_info.applicantLName lastname,
			ext_acct_info.applicantFName firstname,
            null as accountCapabilities,
			profile.tradePreference,
            profile.advisor,
			profile.theme,
			IFNULL(profile.goal,0) as goal,
			profile.`acctType` as accttype,
			IFNULL(profile.age,30) as age,
			IFNULL(profile.horizon,0) as horizon,
			round(datediff(now(),IFNULL(profile.created,now()))/364,0) as yearnum,
			IFNULL(profile.riskIndex,0) as riskIndex,
			CAST(IFNULL(profile.initialInvestment,0) as SIGNED) as initialInvestment,
			round(funct_get_actualCapital(profile.acctnum),0) as actualCapital,
			IFNULL(profile.keepLiquid,0) as keepLiquid,
			CAST(IFNULL(profile.recurringInvestment,0) as SIGNED) as recurringInvestment,
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
			ctt.processStatus,
			ctt.`lastTraded`,
			ctt.`reason` as reason,
			ctt.`assetAllocationOffset`,
			ctt.`position`,
			ctt.`assetclass`,
			ctt.`currentAllocation`,
			ctt.`requiredAllocation`,
			profile.created,
			profile.lastUpdated
		from
			user_trade_profile `profile`,
			`clients_to_trade` ctt,
			`ext_acct_info`
			LEFT JOIN `acct_financial`
			ON (ext_acct_info.acctnum = `acct_financial`.acctnum)
			LEFT JOIN `user_risk_questions`
			ON (ext_acct_info.acctnum = `user_risk_questions`.acctnum)
		where
			`profile`.acctnum = ctt.acctnum
		and `profile`.tradePreference in ('A')
		and ctt.processStatus = 'P'
		and `profile`.acctnum = ext_acct_info.acctnum
		;

    end;
END$$
DELIMITER ;
