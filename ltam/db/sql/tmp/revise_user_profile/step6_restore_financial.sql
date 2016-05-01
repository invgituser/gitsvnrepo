INSERT INTO `invdb`.`acct_financial`
(`acctnum`,
`dependent`,

`householdwages`,
`totalIncome`,
`totalIncomeAnnulized`,

`householdPayment`,
`automobilePayment`,
`lifeInsurance`,
`autoInsurance`,
`totalExpense`,
`totalExpenseAnnulized`,

`moneyMarket`,
`savingAcct`,
`investment`,
`totalAsset`,

`creditCardDebt`,
`otherDebt`,
`medicalDebt`,
`totalDebt`,

`liquidnetworth`,
`networth`,
`created`,
`lastupdated`)
SELECT `save_user_trade_profile`.`acctnum`,
    `save_user_trade_profile`.`dependent`,

    `save_user_trade_profile`.`currentincome`,
    `save_user_trade_profile`.`currentincome`,
    `save_user_trade_profile`.`currentincome`,

    `save_user_trade_profile`.`mortgage`,
    `save_user_trade_profile`.`autoloans`,
    `save_user_trade_profile`.`lifeInsurance`,
    `save_user_trade_profile`.`autoInsurance`,
    mortgage + autoloans + medical + lifeInsurance + autoInsurance,
    mortgage + autoloans + medical + lifeInsurance + autoInsurance,

    `save_user_trade_profile`.`moneymarket`,
    `save_user_trade_profile`.`otherSavings`,
    `save_user_trade_profile`.`mutualfund`,
    moneymarket + otherSavings + mutualfund,

    `save_user_trade_profile`.`creditCardDebt`,
    `save_user_trade_profile`.`otherdebts`,
    `save_user_trade_profile`.`medical`,
	creditCardDebt + otherdebts,

    0,
    0,
    `save_user_trade_profile`.`created`,
    `save_user_trade_profile`.`lastUpdated`
FROM `invdb`.`save_user_trade_profile`;
