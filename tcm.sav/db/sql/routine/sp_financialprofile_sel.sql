delimiter $$
DROP PROCEDURE `sp_financialprofile_sel`
$$

CREATE PROCEDURE `sp_financialprofile_sel`(
	IN p_acctnum bigint(20)
)
BEGIN 
       SELECT maritalstatus, noofdependents, annualincome, networth,
           liquidnetworth, yearsexp
       FROM acct_financial
       WHERE acctnum = p_acctnum;

END
$$

