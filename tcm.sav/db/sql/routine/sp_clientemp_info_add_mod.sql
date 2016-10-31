-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_clientemp_info_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_acctnum bigint(20),
		IN  p_logonid	bigint(20),
        IN  p_empstatus varchar(45),
        IN  p_employer varchar(80),
        IN  p_natureofbusiness varchar(45),
        IN  p_occupation varchar(45),
		IN  p_address varchar(80),
		IN  p_address2 varchar(30),
		IN  p_city varchar(25),
		IN  p_state varchar(20),
		IN  p_country varchar(20),
		IN  p_zip varchar(9)
)
BEGIN

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO client_emp_info
			 (acctnum, logonid, empstatus, employer, natureofbusiness, occupation,
						address,address2,city,state,country,zip
			 )
		VALUES
			 (
			p_acctnum,
			p_logonid,
			p_empstatus,
			p_employer,
			p_natureofbusiness,
			p_occupation,
			p_address,
			p_address2,
			p_city,
			p_state,
			p_country,
			p_zip
		);
	   END;
   ELSE
	   BEGIN
		 UPDATE  client_emp_info
		 SET
			logonid     = p_logonid,
			empstatus  = p_empstatus,
			employer = p_employer,
			natureofbusiness   = p_natureofbusiness,
			occupation     = p_occupation,
			address =	p_address,
			address2 = p_address2,
			city = p_city,
			state = p_state,
			country = p_country,
			zip = p_zip
			WHERE
			 acctnum = p_acctnum;
		select p_acctnum;
	   END;
   END IF;
END