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
    IN  p_mortgageEquity  BIGINT(20),
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
				p_monthlywages, -- p_householdwages
				p_otherincome,
				p_mortgagePayment, -- p_householdPayment
				p_otherExpense, -- p_miscExpenses
				p_mortgageEquity, -- p_homeEquity
				p_moneymarket,
				p_investment,
				p_otherSavings, -- p_miscInvestment
				p_mortgageLoan,
				p_autoLoan,
				p_medical, -- p_medicalDebt
				p_otherDebt
			);

	END;


END$$
DELIMITER ;
