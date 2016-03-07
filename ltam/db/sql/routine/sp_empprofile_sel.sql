delimiter $$

DROP PROCEDURE `sp_empprofile_sel`
$$

CREATE PROCEDURE `sp_empprofile_sel`(
	IN p_acctnum bigint(20)
)
BEGIN 
       SELECT occupation, empstatus, dateemployed, employername, employeraddress,
           secempyn, secfirmrelation, secempfirm, secempname
       FROM acct_financial
       WHERE acctnum = p_acctnum;

END
$$

