-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_client_empinfo_sel`(
	IN p_logonid bigint(20)
)
BEGIN

 SELECT acctnum,
		empstatus,
		employer,
		natureofbusiness,
		occupation,
		address,
		address2,
		city,
		state,
		country,
		zip
       FROM client_emp_info
       WHERE logonid = p_logonid;

END