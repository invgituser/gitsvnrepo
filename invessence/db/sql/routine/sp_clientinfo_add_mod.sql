-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_clientinfo_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_acctnum bigint(20),
		IN  p_logonid	bigint(20),
        IN  p_prefix varchar(25),
        IN  p_lastname varchar(45),
        IN  p_middlename varchar(25),
        IN  p_firstname varchar(45),
        IN  p_suffix varchar(25),
		IN  p_address varchar(80),
		IN  p_address2 varchar(30),
		IN  p_city varchar(25),
		IN  p_state varchar(20),
		IN  p_country varchar(20),
		IN  p_zip varchar(9),
		IN  p_addressalt varchar(80),
		IN  p_address2alt varchar(30),
		IN  p_cityalt varchar(25),
		IN  p_statealt varchar(20),
		IN  p_countryalt varchar(20),
		IN  p_zipalt varchar(9),
        IN  p_dob datetime,
        IN  p_maritalstatus  varchar(45),
		IN  p_dependents varchar(10),
		IN  p_gender varchar(10),
        IN  p_citizenship varchar(45),
        IN  p_ssn varchar(20)
)
BEGIN

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO client_info
			 (acctnum, logonid, prefix, lastname, middlename, firstname, suffix,
						address,address2,city,state,country,zip,addressalt,
						address2alt,cityalt,statealt,countryalt,zipalt,
						dob, maritalstatus, dependents, gender,citizenship,
						ssn
			 )
		VALUES
			 (
			p_acctnum,
			p_logonid,
			p_prefix,
			p_lastname,
			p_middlename,
			p_firstname,
			p_suffix,
			p_address,
			p_address2,
			p_city,
			p_state,
			p_country,
			p_zip,
			p_addressalt,
			p_address2alt,
			p_cityalt,
			p_statealt,
			p_countryalt,
			p_zipalt,
			p_dob,
			p_maritalstatus,
			p_dependents,
			p_gender,
			p_citizenship,
			p_ssn
			 );
		select LAST_INSERT_ID();
	   END;
   ELSE
	   BEGIN
		 UPDATE  client_info
		 SET

			prefix     = p_prefix,
			lastname  = p_lastname,
			middlename = p_middlename,
			firstname   = p_firstname,
			suffix     = p_suffix,
			address =	p_address,
			address2 = p_address2,
			city = p_city,
			state = p_state,
			country = p_country,
			zip = p_zip,
			addressalt = p_addressalt,
			address2alt = p_address2alt,
			cityalt = p_cityalt,
			statealt = p_statealt,
			countryalt = p_countryalt,
			zipalt = p_zipalt
		 WHERE
			 acctnum = p_acctnum;
		select p_acctnum;
	   END;
   END IF;
END