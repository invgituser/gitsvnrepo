DROP PROCEDURE IF EXISTS `sp_acct_financial_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_acct_financial_add_mod`(
	IN  p_addmodflag VARCHAR(1),
	IN	p_acctnum	bigint(20)	,
	IN	p_dependent	tinyint(4)	,
	IN	p_householdwages	bigint(20)	,
	IN	p_otherincome	bigint(20)	,
	IN	p_householdPayment	bigint(20)	,
	IN  p_miscExpenses   bigint(20),
	IN	p_homeEquity	bigint(20)	,
	IN	p_moneyMarket	bigint(20)	,
	IN	p_investment	bigint(20)	,
	IN	p_miscInvestment	bigint(20)	,
	IN	p_mortgageLoan	bigint(20)	,
	IN	p_autoLoan	bigint(20)	,
	IN	p_medicalDebt	bigint(20)	,
	IN	p_otherDebt	bigint(20)
)
BEGIN
   BEGIN
	DECLARE p_totalIncome bigint(20);
	DECLARE p_totalIncomeAnnulized bigint(20);
	DECLARE p_totalExpense bigint(20);
	DECLARE p_totalExpenseAnnulized bigint(20);
    DECLARE p_totalAsset   bigint(20);
	DECLARE p_totalDebt bigint(20);
	DECLARE p_liquidNetWorth bigint(20);
	DECLARE p_networth bigint(20);
    DECLARE t_dataFound int;

	DECLARE p_estdDependentExpense	bigint(20)	;
	DECLARE 	p_bonusincome	bigint(20)	;
	DECLARE 	p_interestincome	bigint(20)	;
	DECLARE 	p_dividentincome	bigint(20)	;
	DECLARE 	p_rentalIncome	bigint(20)	;
	DECLARE 	p_otherPropertiesPayment	bigint(20)	;
	DECLARE 	p_automobilePayment	bigint(20)	;
	DECLARE 	p_medicalPayment	bigint(20)	;
	DECLARE 	p_federaltaxes	bigint(20)	;
	DECLARE 	p_stateTaxes	bigint(20)	;
	DECLARE 	p_propertyTax	bigint(20)	;
	DECLARE 	p_otherPropertyTax	bigint(20)	;
	DECLARE 	p_homeInsurance	bigint(20)	;
	DECLARE 	p_lifeInsurance	bigint(20)	;
	DECLARE 	p_autoInsurance	bigint(20)	;
	DECLARE 	p_educationPayment	bigint(20)	;
	DECLARE 	p_creditCardPayment	bigint(20)	;
	DECLARE 	p_autoValue	bigint(20)	;
	DECLARE 	p_checkingAcct	bigint(20)	;
	DECLARE     p_savingAcct    bigint(20) ;
	DECLARE 	p_equityOtherProperties	bigint(20)	;
	DECLARE 	p_retirementInvestement	bigint(20)	;
	DECLARE 	p_educationLoan	bigint(20)	;
	DECLARE 	p_creditCardDebt	bigint(20)	;
	DECLARE 	p_otherPropertiesLoan	bigint(20);


	set p_totalIncome = IFNULL(p_householdwages,0) +
						IFNULL(p_otherincome,0) +
						IFNULL(p_bonusincome,0) +
						IFNULL(p_interestincome,0) +
						IFNULL(p_dividentincome,0) +
						IFNULL(p_rentalIncome,0);
	set p_totalIncomeAnnulized = p_totalIncome * 12;

	set p_totalExpense = IFNULL(p_householdPayment,0) +
						IFNULL(p_otherPropertiesPayment,0) +
						IFNULL(p_automobilePayment,0) +
						IFNULL(p_medicalPayment,0) +
						IFNULL(p_federaltaxes,0) +
						IFNULL(p_stateTaxes,0) +
						IFNULL(p_propertyTax,0) +
						IFNULL(p_otherPropertyTax,0) +
						IFNULL(p_homeInsurance,0) +
						IFNULL(p_lifeInsurance,0) +
						IFNULL(p_autoInsurance,0) +
						IFNULL(p_educationPayment,0) +
						IFNULL(p_creditCardPayment,0) +
						IFNULL(p_miscExpenses,0);
	set p_totalExpenseAnnulized = p_totalExpense * 12;

	set p_totalAsset = IFNULL(p_homeEquity,0) +
						IFNULL(p_autoValue,0) +
						IFNULL(p_moneyMarket,0) +
						IFNULL(p_checkingAcct,0) +
						IFNULL(p_savingAcct,0) +
						IFNULL(p_investment,0) +
						IFNULL(p_equityOtherProperties,0) +
						IFNULL(p_retirementInvestement,0) +
						IFNULL(p_miscInvestment,0);
   
	set p_totalDebt = IFNULL(p_mortgageLoan,0) +
						IFNULL(p_autoLoan,0) +
						IFNULL(p_educationLoan,0) +
						IFNULL(p_creditCardDebt,0) +
						IFNULL(p_otherPropertiesLoan,0) +
						IFNULL(p_medicalDebt,0) +
						IFNULL(p_otherDebt,0);


	set p_liquidNetWorth = 0;
	set p_networth = (p_totalIncomeAnnulized - p_totalExpenseAnnulized) +
				   (p_totalAsset + p_totalDebt);


    -- Right now, we have to ignore addmodflag.  Because we adding records from goals pages.	
	SELECT count(*) 
	INTO  t_dataFound
	FROM `acct_financial`
	WHERE acctnum = p_acctnum;


	   IF ( IFNULL(t_dataFound,0) = 0 ) THEN
		   BEGIN
				INSERT INTO `acct_financial`
				(
				`acctnum`,
				`dependent`,
				`estdDependentExpense`,
				`householdwages`,
				`otherincome`,
				`bonusincome`,
				`interestincome`,
				`dividentincome`,
				`rentalIncome`,
				`totalIncome`,
				`totalIncomeAnnulized`,
				`householdPayment`,
				`otherPropertiesPayment`,
				`automobilePayment`,
				`medicalPayment`,
				`federaltaxes`,
				`stateTaxes`,
				`propertyTax`,
				`otherPropertyTax`,
				`homeInsurance`,
				`lifeInsurance`,
				`autoInsurance`,
				`educationPayment`,
				`creditCardPayment`,
				`miscExpenses`,
				`totalExpense`,
				`totalExpenseAnnulized`,
				`homeEquity`,
				`autoValue`,
				`moneyMarket`,
				`checkingAcct`,
				`savingAcct`,
				`investment`,
				`equityOtherProperties`,
				`retirementInvestement`,
				`miscInvestment`,
				`totalAsset`,
				`mortgageLoan`,
				`autoLoan`,
				`educationLoan`,
				`creditCardDebt`,
				`otherPropertiesLoan`,
				`medicalDebt`,
				`otherDebt`,
				`totalDebt`,
				`liquidnetworth`,
				`networth`,
				`created`
				)
				VALUES (
					p_acctnum,
					p_dependent,
					p_estdDependentExpense,
					p_householdwages,
					p_otherincome,
					p_bonusincome,
					p_interestincome,
					p_dividentincome,
					p_rentalIncome,
					p_totalIncome,
					p_totalIncomeAnnulized,
					p_householdPayment,
					p_otherPropertiesPayment,
					p_automobilePayment,
					p_medicalPayment,
					p_federaltaxes,
					p_stateTaxes,
					p_propertyTax,
					p_otherPropertyTax,
					p_homeInsurance,
					p_lifeInsurance,
					p_autoInsurance,
					p_educationPayment,
					p_creditCardPayment,
					p_miscExpenses,
					p_totalExpense,
					p_totalExpenseAnnulized,
					p_homeEquity,
					p_autoValue,
					p_moneyMarket,
					p_checkingAcct,
					p_savingAcct,
					p_investment,
					p_equityOtherProperties,
					p_retirementInvestement,
					p_miscInvestment,
					p_totalAsset,
					p_mortgageLoan,
					p_autoLoan,
					p_educationLoan,
					p_creditCardDebt,
					p_otherPropertiesLoan,
					p_medicalDebt,
					p_otherDebt,
					p_totalDebt,
					p_liquidnetworth,
					p_networth,
					now()
				);
		   END;
	   else
		   begin
				Update `acct_financial`
				set
					`dependent`	 =	p_dependent	,
					`estdDependentExpense`	 =	p_estdDependentExpense	,
					`householdwages`	 =	p_householdwages	,
					`otherincome`	 =	p_otherincome	,
					`bonusincome`	 =	p_bonusincome	,
					`interestincome`	 =	p_interestincome	,
					`dividentincome`	 =	p_dividentincome	,
					`rentalIncome`	 =	p_rentalIncome	,
					`totalIncome`	 =	p_totalIncome	,
					`totalIncomeAnnulized`	 =	p_totalIncomeAnnulized	,
					`householdPayment`	 =	p_householdPayment	,
					`otherPropertiesPayment`	 =	p_otherPropertiesPayment	,
					`automobilePayment`	 =	p_automobilePayment	,
					`medicalPayment`	 =	p_medicalPayment	,
					`federaltaxes`	 =	p_federaltaxes	,
					`stateTaxes`	 =	p_stateTaxes	,
					`propertyTax`	 =	p_propertyTax	,
					`otherPropertyTax`	 =	p_otherPropertyTax	,
					`homeInsurance`	 =	p_homeInsurance	,
					`lifeInsurance`	 =	p_lifeInsurance	,
					`autoInsurance`	 =	p_autoInsurance	,
					`educationPayment`	 =	p_educationPayment	,
					`creditCardPayment`	 =	p_creditCardPayment	,
					`totalExpense`	 =	p_totalExpense	,
					`miscExpenses`   =  p_miscExpenses  ,
					`totalExpenseAnnulized`	 =	p_totalExpenseAnnulized	,
					`homeEquity`	 =	p_homeEquity	,
					`autoValue`	 =	p_autoValue	,
					`moneyMarket`	 =	p_moneyMarket	,
					`checkingAcct`	 =	p_checkingAcct	,
					`savingAcct`	 =	p_savingAcct	,
					`investment`	 =	p_investment	,
					`equityOtherProperties`	 =	p_equityOtherProperties	,
					`retirementInvestement`	 =	p_retirementInvestement	,
					`miscInvestment`	 =	p_miscInvestment	,
					`totalAsset`	 =	p_totalAsset	,
					`mortgageLoan`	 =	p_mortgageLoan	,
					`autoLoan`	 =	p_autoLoan	,
					`educationLoan`	 =	p_educationLoan	,
					`creditCardDebt`	 =	p_creditCardDebt	,
					`otherPropertiesLoan`	 =	p_otherPropertiesLoan	,
					`medicalDebt` = p_medicalDebt,
					`otherDebt`	 =	p_otherDebt	,
					`totalDebt`	 =	p_totalDebt	,
					`liquidnetworth`	 =	p_liquidnetworth	,
					`networth`	 =	p_networth	,
					`lastupdated`	 =	now()
				where acctnum = p_acctnum;
		   END;
	   END IF;
    END;
END
$$

