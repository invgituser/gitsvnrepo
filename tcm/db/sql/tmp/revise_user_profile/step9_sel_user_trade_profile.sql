DROP PROCEDURE IF EXISTS `sel_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `sel_user_trade_profile`
(
	IN	p_acctnum	bigint(20)
)
BEGIN
	SELECT `user_trade_profile`.`acctnum`,
		`user_trade_profile`.`goal`,
		`user_trade_profile`.`acctType`,
		`user_trade_profile`.`age`,
		`user_trade_profile`.`horizon`,
		`user_trade_profile`.`initialInvestment`,
		`user_trade_profile`.`recurringInvestment`,
		`user_trade_profile`.`experience`,
		`user_trade_profile`.`longTermGoal`,
		`user_trade_profile`.`stayInvested`,
		`user_trade_profile`.`charitablegoals`,
		`user_trade_profile`.`dependent`,
		`user_trade_profile`.`riskIndex`,
		`acct_financial`.`dependent`,
		`acct_financial`.`estdDependentExpense`,
		`acct_financial`.`householdwages`,
		`acct_financial`.`otherincome`,
		`acct_financial`.`bonusincome`,
		`acct_financial`.`interestincome`,
		`acct_financial`.`dividentincome`,
		`acct_financial`.`rentalIncome`,
		`acct_financial`.`totalIncome`,
		`acct_financial`.`totalIncomeAnnulized`,
		`acct_financial`.`householdPayment`,
		`acct_financial`.`otherPropertiesPayment`,
		`acct_financial`.`automobilePayment`,
		`acct_financial`.`medicalPayment`,
		`acct_financial`.`federaltaxes`,
		`acct_financial`.`stateTaxes`,
		`acct_financial`.`propertyTax`,
		`acct_financial`.`otherPropertyTax`,
		`acct_financial`.`homeInsurance`,
		`acct_financial`.`lifeInsurance`,
		`acct_financial`.`autoInsurance`,
		`acct_financial`.`educationPayment`,
		`acct_financial`.`creditCardPayment`,
		`acct_financial`.`miscExpenses`,
		`acct_financial`.`totalExpense`,
		`acct_financial`.`totalExpenseAnnulized`,
		`acct_financial`.`homeEquity`,
		`acct_financial`.`autoValue`,
		`acct_financial`.`moneyMarket`,
		`acct_financial`.`checkingAcct`,
		`acct_financial`.`savingAcct`,
		`acct_financial`.`investment`,
		`acct_financial`.`equityOtherProperties`,
		`acct_financial`.`retirementInvestement`,
		`acct_financial`.`miscInvestment`,
		`acct_financial`.`totalAsset`,
		`acct_financial`.`mortgageLoan`,
		`acct_financial`.`autoLoan`,
		`acct_financial`.`educationLoan`,
		`acct_financial`.`creditCardDebt`,
		`acct_financial`.`otherPropertiesLoan`,
		`acct_financial`.`medicalDebt`,
		`acct_financial`.`otherDebt`,
		`acct_financial`.`totalDebt`,
		`acct_financial`.`liquidnetworth`,
		`acct_financial`.`networth`,
		`user_trade_profile`.`created`,
		`user_trade_profile`.`lastUpdated`
	FROM `user_trade_profile`
	LEFT JOIN `acct_financial`
	ON `user_trade_profile`.`acctnum` = `acct_financial`.`acctnum`
	WHERE `user_trade_profile`.`acctnum` = p_acctnum
	;
END
$$