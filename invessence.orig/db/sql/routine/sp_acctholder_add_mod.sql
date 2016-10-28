delimiter $$

DROP PROCEDURE `sp_acctholder_add_mod`
$$

CREATE PROCEDURE `sp_acctholder_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_acctnum bigint(20),
		IN  p_ss varchar(9),
        IN  p_prefix varchar(25),
        IN  p_firstname varchar(25),
        IN  p_middlename varchar(25),
        IN  p_lastname varchar(25),
        IN  p_suffix varchar(25),
        IN  p_dob datetime,
        IN  p_countryofbirth  varchar(20),
		IN  p_country varchar(20),
		IN  p_dlst varchar(2),
        IN  p_dlnum varchar(60)
)
BEGIN 
 
   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO acct_info
			 (taxid, prefix, firstname, middlename, lastname, suffix, 
					   dob, countryofbirth, country, driverslicensest,
					   driverslicense                  
			 )
		VALUES 
			 ( 
			p_ss,
			p_prefix,
			p_firstname,
			p_middlename,
			p_lastname,
			p_suffix,
			p_dob,
			p_countryofbirth,
			p_country,
			p_dlst,
			p_dlnum
			 ); 
		select LAST_INSERT_ID();
	   END;
   ELSE
	   BEGIN
		 UPDATE  acct_info
		 SET 
			taxid      = p_ss,
			prefix     = p_prefix,
			firstname  = p_firstname,
			middlename = p_middlename,
			lastname   = p_lastname,
			suffix     = p_suffix,
			dob        = p_dob,
			countryofbirth = p_countryofbirth,
			country    = p_country,
			driverslicensest = p_dlst,
			driverslicense = p_dlnum

		 WHERE
			 acctnum = p_acctnum;

		select p_acctnum;
	   END;
   END IF;
END
$$

