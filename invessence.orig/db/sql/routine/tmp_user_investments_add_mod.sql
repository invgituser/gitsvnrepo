delimiter $$
DROP PROCEDURE `tmp_user_investments_add_mod`
$$

CREATE PROCEDURE `tmp_user_investments_add_mod`(
	IN  p_addmodflag      VARCHAR(1),
	INOUT p_logonid bigint(20),
	INOUT p_acctnum bigint(20),
	IN  p_investobjective varchar(10),
	IN  p_name varchar(45),
	IN  p_portfoliostate varchar(2)
)
BEGIN 

   BEGIN
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		set p_logonid = funct_Nextval('LOGON');
		set p_acctnum = funct_Nextval('ACCTNUM');
		INSERT INTO customer_investments
			 (
			logonid,
			acctnum,
			investobjective,
			name,
			portfoliostate,
			created,
			lastupdated
			 )
		VALUES 
			 ( 
			p_logonid,
			p_acctnum,
			p_investobjective,
			p_name,
			IFNULL(p_portfoliostate,'V'),
			now(),
			NULL
			 ) ; 
	   END;
	   ELSE
	   BEGIN
		 UPDATE  customer_investments
		 SET 
			investobjective = p_investobjective,
			name = p_name,
			portfoliostate = p_portfoliostate,
			lastupdated = now()
		 WHERE
			logonid = p_logonid and
			acctnum = p_acctnum;
	   END;
	   END IF;
	   END;

END$$

