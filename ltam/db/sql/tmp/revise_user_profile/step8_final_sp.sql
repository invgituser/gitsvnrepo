DROP PROCEDURE IF EXISTS `web_page2_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `web_page2_user_trade_profile`(
	IN  p_addmodflag VARCHAR(1),
    IN  p_logonid    BIGINT(20),
	INOUT	p_acctnum	bigint(20),
	IN	p_dependent	integer,

    IN  p_monthlywages BIGINT(20),
    IN  p_otherIncome  BIGINT(20),

    IN  p_mortgagePayment  BIGINT(20),
    IN  p_otherExpense  BIGINT(20),

    IN  p_moneymarket  BIGINT(20),
    IN  p_investment  BIGINT(20),
    IN  p_mortgateEquity  BIGINT(20),
    IN  p_otherSavings  BIGINT(20),
 
    IN  p_autoLoan  BIGINT(20),
    IN  p_medical  BIGINT(20),
    IN  p_mortgageLoan  BIGINT(20),
    IN  p_otherDebt  BIGINT(20)
)
BEGIN 

   BEGIN
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
		   BEGIN

			INSERT INTO `user_trade_profile` (
				`dependent`,
				`created`
			)
			VALUES (
				p_dependent	,
				now()
			);

			select last_insert_id() into p_acctnum;
			call sp_user_access_add_mod('A', p_logonid, p_acctnum, NULL, NULL, NULL);
		   END;
	   ELSE
		   BEGIN
			 UPDATE  `user_trade_profile`
			 SET 
				`dependent`	 =	p_dependent	,
				`lastupdated` = now()
			 WHERE
				`acctnum` = p_acctnum;
		   END;
	   END IF;

		call sp_acct_financial_add_mod (
				p_addmodflag,
				p_acctnum,
				p_dependent,
				0, -- p_estdDependentExpense
				p_monthlywages, -- p_householdwages
				p_otherincome,
				0,  -- p_bonusincome
				0, -- p_interestincome
				0, -- accessible
				0, -- p_rentalIncome
				p_mortgagePayment, -- p_householdPayment
				0, -- p_otherPropertiesPayment
				0, -- p_automobilePayment
				p_medical, -- p_medicalPayment
				0, -- p_federaltaxes
				0, -- p_stateTaxes
				0, -- p_propertyTax
				0, -- p_otherPropertyTax
				0, -- p_homeInsurance
				0, -- p_lifeInsurance
				0, -- p_autoInsurance
				0, -- p_educationPayment
				0, -- p_creditCardPayment
				p_otherExpense, -- p_miscExpenses
				p_mortgateEquity, -- p_homeEquity
				0, -- p_autoValue
				p_moneymarket,
				0, -- p_checkingAcct
				0, -- p_savingAcct
				p_investment,
				0,  -- p_equityOtherProperties
				0, -- p_retirementInvestement
				p_otherSavings, -- p_miscInvestment
				p_mortgageLoan,
				p_autoLoan,
				0, -- p_educationLoan
				0, -- p_creditCardDebt
				0, -- p_otherPropertiesLoan
				p_otherDebt
			);

	END;


END$$
DELIMITER ;
