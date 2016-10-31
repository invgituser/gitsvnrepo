DROP TABLE IF EXISTS `acct_financial`;
delimiter $$

CREATE TABLE `acct_financial` (
  `acctnum`              bigint(20) NOT NULL,
  `dependent`            tinyint DEFAULT 0,
  `estdDependentExpense` bigint(20) DEFAULT 0,
  -- Main Income 
  `householdwages`       bigint(20) DEFAULT 0,
  `otherincome`          bigint(20) DEFAULT 0,
  `bonusincome`          bigint(20) DEFAULT 0,
  `interestincome`       bigint(20) DEFAULT 0,
  `dividentincome`       bigint(20) DEFAULT 0,
  `rentalIncome`         bigint(20) DEFAULT 0,
  `totalIncome`          bigint(20) DEFAULT 0,
  `totalIncomeAnnulized` bigint(20) DEFAULT 0,
  -- Major Expenses
  `householdPayment`     bigint(20) DEFAULT 0,
  `otherPropertiesPayment`  bigint(20) DEFAULT 0,
  `automobilePayment`    bigint(20) DEFAULT 0,
  `medicalPayment`       bigint(20) DEFAULT 0,
  `federaltaxes`         bigint(20) DEFAULT 0,
  `stateTaxes`           bigint(20) DEFAULT 0,
  `propertyTax`          bigint(20) DEFAULT 0,
  `otherPropertyTax`     bigint(20) DEFAULT 0,
  `homeInsurance`        bigint(20) DEFAULT 0,
  `lifeInsurance`        bigint(20) DEFAULT 0,
  `autoInsurance`        bigint(20) DEFAULT 0,
  `educationPayment`     bigint(20) DEFAULT 0,
  `creditCardPayment`    bigint(20) DEFAULT 0,
  `miscExpenses`         bigint(20) DEFAULT 0,
  `totalExpense`         bigint(20) DEFAULT 0,
  `totalExpenseAnnulized` bigint(20) DEFAULT 0,
  -- Major Asset
  `homeEquity`           bigint(20) DEFAULT 0,
  `autoValue`            bigint(20) DEFAULT 0,
  `moneyMarket`          bigint(20) DEFAULT 0,
  `checkingAcct`         bigint(20) DEFAULT 0,
  `savingAcct`           bigint(20) DEFAULT 0,
  `investment`           bigint(20) DEFAULT 0,
  `equityOtherProperties` bigint(20) DEFAULT 0,
  `retirementInvestement` bigint(20) DEFAULT 0,
  `miscInvestment`       bigint(20) DEFAULT 0,
  `totalAsset`           bigint(20) DEFAULT 0,
  -- Major Liability
  `mortgageLoan`         bigint(20) DEFAULT 0,
  `autoLoan`             bigint(20) DEFAULT 0,
  `educationLoan`        bigint(20) DEFAULT 0,
  `creditCardDebt`       bigint(20) DEFAULT 0,
  `otherPropertiesLoan`  bigint(20) DEFAULT 0,
  `medicalDebt`          bigint(20) DEFAULT 0,
  `otherDebt`            bigint(20) DEFAULT 0,
  `totalDebt`            bigint(20) DEFAULT 0,
  -- Net Worth
  `liquidnetworth`       bigint(20) DEFAULT 0,
  `networth`             bigint(20) DEFAULT 0,
  `created`              timestamp    DEFAULT NOW(),
  `lastupdated`          timestamp  NULL DEFAULT NULL,
  PRIMARY KEY (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

