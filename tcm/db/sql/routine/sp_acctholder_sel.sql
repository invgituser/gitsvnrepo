delimiter $$

DROP PROCEDURE `sp_acctholder_sel`
$$

CREATE PROCEDURE `sp_acctholder_sel`(
	IN p_acctnum bigint(20)
)
BEGIN 

 SELECT taxid, 
		prefix, 
		firstname, 
		middlename, 
		lastname, 
		suffix, 
		dob, 
		countryofbirth, 
		country, 
        driverslicensest, 
		driverslicense
       FROM acct_info
       WHERE acctnum = p_acctnum;

END
$$

