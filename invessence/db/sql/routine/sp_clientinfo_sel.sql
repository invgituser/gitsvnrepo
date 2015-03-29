-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_clientinfo_sel`(
	IN p_logonid bigint(20)
)
BEGIN

 SELECT acctnum,
        prefix,
		lastname,
		middlename,
		firstname,
		suffix,
		address,
		address2,
		city,
		state,
		country,
		zip,
		addressalt,
		address2alt,
		cityalt,
		statealt,
		countryalt,
		zipalt,
		dob,
		maritalstatus,
		dependents,
		gender,
        citizenship,
		ssn
       FROM client_info
       WHERE logonid = p_logonid;

END