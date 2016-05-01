-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_clientinfo_mod`(
 		IN  p_acctnum bigint(20),
		IN  p_loginid     bigint(20),
        IN  p_dob datetime,
        IN  p_maritalstatus  varchar(45),
		IN  p_dependents varchar(10),
		IN  p_gender varchar(10),
        IN  p_citizenship varchar(45),
        IN  p_ssn varchar(20)
)
BEGIN
 		 UPDATE  client_info
		 SET
			dob = p_dob,
			maritalstatus = p_maritalstatus,
			dependents = p_dependents,
			gender = p_gender,
			citizenship = p_citizenship,
			ssn = p_ssn
		 WHERE
			 acctnum = p_acctnum;
		select p_acctnum;
END