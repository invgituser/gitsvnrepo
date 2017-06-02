DROP PROCEDURE IF EXISTS `invdb`.`sp_acct_financial_add_mod`;
DROP PROCEDURE IF EXISTS `invdb`.`save_user_financial_data`;


DELIMITER $$
CREATE PROCEDURE `invdb`.`save_user_financial_data`
(
	IN	`p_acctnum`	BIGINT(20)	,
	IN	`p_dependent`	integer	,
    
	IN	`p_estdDependentExpense`	BIGINT	,
	IN	`p_householdwages`	BIGINT	,
	IN	`p_otherincome`	BIGINT	,
	IN	`p_bonusincome`	BIGINT	,
	IN	`p_interestincome`	BIGINT	,
	IN	`p_dividentincome`	BIGINT	,
	IN	`p_rentalIncome`	BIGINT	,
	IN	`p_totalIncome`	BIGINT	,
	IN	`p_totalIncomeAnnulized`	BIGINT	,
    
	IN	`p_householdPayment`	BIGINT	,
	IN	`p_otherPropertiesPayment`	BIGINT	,
	IN	`p_automobilePayment`	BIGINT	,
	IN	`p_medicalPayment`	BIGINT	,
    
	IN	`p_federaltaxes`	BIGINT	,
	IN	`p_stateTaxes`	BIGINT	,
	IN	`p_propertyTax`	BIGINT	,
	IN	`p_otherPropertyTax`	BIGINT	,
    
	IN	`p_homeInsurance`	BIGINT	,
	IN	`p_lifeInsurance`	BIGINT	,
	IN	`p_autoInsurance`	BIGINT	,
    
	IN	`p_educationPayment`	BIGINT	,
	IN	`p_creditCardPayment`	BIGINT	,
	IN	`p_miscExpenses`	BIGINT	,
	IN	`p_totalExpense`	BIGINT	,
	IN	`p_totalExpenseAnnulized`	BIGINT	,
    
	IN	`p_homeEquity`	BIGINT	,
	IN	`p_autoValue`	BIGINT	,
	IN	`p_moneyMarket`	BIGINT	,
	IN	`p_checkingAcct`	BIGINT	,
	IN	`p_savingAcct`	BIGINT	,
	IN	`p_investment`	BIGINT	,
	IN	`p_equityOtherProperties`	BIGINT	,
	IN	`p_retirementInvestement`	BIGINT	,
	IN	`p_miscInvestment`	BIGINT	,
	IN	`p_totalAsset`	BIGINT	,
    
	IN	`p_mortgageLoan`	BIGINT	,
	IN	`p_autoLoan`	BIGINT	,
	IN	`p_educationLoan`	BIGINT	,
	IN	`p_creditCardDebt`	BIGINT	,
	IN	`p_otherPropertiesLoan`	BIGINT	,
	IN	`p_medicalDebt`	BIGINT	,
	IN	`p_otherDebt`	BIGINT	,
	IN	`p_totalDebt`	BIGINT	,
    
	IN	`p_liquidnetworth`	BIGINT	,
	IN	`p_networth`	BIGINT

)
BEGIN 

	DECLARE tCount INTEGER;
   BEGIN
       
	   BEGIN
		 UPDATE  `user_trade_profile`
		 SET 
			`dependent`	 =	p_dependent	,
			`lastupdated` = now()
		 WHERE
			`acctnum` = p_acctnum;
	   END;

				IF (`p_totalIncome` is null)
					THEN
						set p_totalIncome = IFNULL(p_householdwages,0) +
									IFNULL(p_otherincome,0) +
									IFNULL(p_bonusincome,0) +
									IFNULL(p_interestincome,0) +
									IFNULL(p_dividentincome,0) +
									IFNULL(p_rentalIncome,0);
				END IF;
				
				IF (`p_totalIncomeAnnulized` is null)
					THEN
						set p_totalIncomeAnnulized = p_totalIncome * 12;
				END IF;
				
				IF (`p_totalExpense` is null)
					THEN
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
				END IF;
                IF (`p_totalExpenseAnnulized` is null)
					THEN
						set p_totalExpenseAnnulized = p_totalExpense * 12;
				END IF;

				IF (p_totalAsset is null)
					THEN
						set p_totalAsset = IFNULL(p_homeEquity,0) +
								IFNULL(p_autoValue,0) +
								IFNULL(p_moneyMarket,0) +
								IFNULL(p_checkingAcct,0) +
								IFNULL(p_savingAcct,0) +
								IFNULL(p_investment,0) +
								IFNULL(p_equityOtherProperties,0) +
								IFNULL(p_retirementInvestement,0) +
								IFNULL(p_miscInvestment,0);
				END IF;
   
				IF (p_totalDebt is null)
					THEN
						set p_totalDebt = IFNULL(p_mortgageLoan,0) +
								IFNULL(p_autoLoan,0) +
								IFNULL(p_educationLoan,0) +
								IFNULL(p_creditCardDebt,0) +
								IFNULL(p_otherPropertiesLoan,0) +
								IFNULL(p_medicalDebt,0) +
								IFNULL(p_otherDebt,0);
				END IF;

				IF (p_liquidnetworth is null)
					THEN
						set p_liquidNetWorth = IFNULL(p_moneyMarket,0) +
								   IFNULL(p_checkingAcct,0) +
								   IFNULL(p_savingAcct,0);
				END IF;

				if (`p_networth` is null)
					THEN
						set p_networth = (p_totalIncomeAnnulized - p_totalExpenseAnnulized) +
						   (p_totalAsset + p_totalDebt);
				END IF;

             INSERT INTO `invdb`.`acct_financial`
				(`acctnum`,
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
				`created`,
				`lastupdated`)
			VALUES
			(`p_acctnum`,
			`p_dependent`,
			`p_estdDependentExpense`,
			`p_householdwages`,
			`p_otherincome`,
			`p_bonusincome`,
			`p_interestincome`,
			`p_dividentincome`,
			`p_rentalIncome`,
			`p_totalIncome`,
			`p_totalIncomeAnnulized`,
			`p_householdPayment`,
			`p_otherPropertiesPayment`,
			`p_automobilePayment`,
			`p_medicalPayment`,
			`p_federaltaxes`,
			`p_stateTaxes`,
			`p_propertyTax`,
			`p_otherPropertyTax`,
			`p_homeInsurance`,
			`p_lifeInsurance`,
			`p_autoInsurance`,
			`p_educationPayment`,
			`p_creditCardPayment`,
			`p_miscExpenses`,
			`p_totalExpense`,
			`p_totalExpenseAnnulized`,
			`p_homeEquity`,
			`p_autoValue`,
			`p_moneyMarket`,
			`p_checkingAcct`,
			`p_savingAcct`,
			`p_investment`,
			`p_equityOtherProperties`,
			`p_retirementInvestement`,
			`p_miscInvestment`,
			`p_totalAsset`,
			`p_mortgageLoan`,
			`p_autoLoan`,
			`p_educationLoan`,
			`p_creditCardDebt`,
			`p_otherPropertiesLoan`,
			`p_medicalDebt`,
			`p_otherDebt`,
			`p_totalDebt`,
			`p_liquidnetworth`,
			`p_networth`,
			now(),
			null)
            ON duplicate key update
				`dependent`				=	`p_dependent`,
				`estdDependentExpense`	=	`p_estdDependentExpense`,
				`householdwages`		=	`p_householdwages`,
				`otherincome`			=	`p_otherincome`,
				`bonusincome`			=	`p_bonusincome`,
				`interestincome`		=	`p_interestincome`,
				`dividentincome`		=	`p_dividentincome`,
				`rentalIncome`			=	`p_rentalIncome`,
				`totalIncome`			=	`p_totalIncome`,
				`totalIncomeAnnulized`	=	`p_totalIncomeAnnulized`,
				`householdPayment`		=	`p_householdPayment`,
				`otherPropertiesPayment`=	`p_otherPropertiesPayment`,
				`automobilePayment`		=	`p_automobilePayment`,
				`medicalPayment`		=	`p_medicalPayment`,
				`federaltaxes`			=	`p_federaltaxes`,
				`stateTaxes`			=	`p_stateTaxes`,
				`propertyTax`			=	`p_propertyTax`,
				`otherPropertyTax`		=	`p_otherPropertyTax`,
				`homeInsurance`			=	`p_homeInsurance`,
				`lifeInsurance`			=	`p_lifeInsurance`,
				`autoInsurance`			=	`p_autoInsurance`,
				`educationPayment`		=	`p_educationPayment`,
				`creditCardPayment`		=	`p_creditCardPayment`,
				`miscExpenses`			=	`p_miscExpenses`,
				`totalExpense`			=	`p_totalExpense`,
				`totalExpenseAnnulized`	=	`p_totalExpenseAnnulized`,
				`homeEquity`			=	`p_homeEquity`,
				`autoValue`				=	`p_autoValue`,
				`moneyMarket`			=	`p_moneyMarket`,
				`checkingAcct`			=	`p_checkingAcct`,
				`savingAcct`			=	`p_savingAcct`,
				`investment`			=	`p_investment`,
				`equityOtherProperties`	=	`p_equityOtherProperties`,
				`retirementInvestement`	=	`p_retirementInvestement`,
				`miscInvestment`		=	`p_miscInvestment`,
				`totalAsset`			=	`p_totalAsset`,
				`mortgageLoan`			=	`p_mortgageLoan`,
				`autoLoan`				=	`p_autoLoan`,
				`educationLoan`			=	`p_educationLoan`,
				`creditCardDebt`		=	`p_creditCardDebt`,
				`otherPropertiesLoan`	=	`p_otherPropertiesLoan`,
				`medicalDebt`			=	`p_medicalDebt`,
				`otherDebt`				=	`p_otherDebt`,
				`totalDebt`				=	`p_totalDebt`,
				`liquidnetworth`		=	`p_liquidnetworth`,
				`networth`				=	`p_networth`,
				`lastupdated`			=	now()
            ;

	END;


END$$
DELIMITER ;
