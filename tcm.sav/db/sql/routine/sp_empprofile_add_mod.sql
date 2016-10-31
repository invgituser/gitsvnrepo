delimiter $$

DROP PROCEDURE `sp_empprofile_add_mod`
$$

CREATE PROCEDURE `sp_empprofile_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_acctnum bigint(20),
		IN  p_occupation varchar(80),
		IN  p_empstatus varchar(20),
        IN  p_dateemployed timestamp,
		IN  p_employername  varchar(20),
        IN  p_employeraddress varchar(127),
		IN  p_secempyn varchar(1),
        IN  p_secfirmrelation varchar(25),
        IN  p_secempfirm varchar(25),
        IN  p_secempname varchar(80)
)
BEGIN 
 
   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO acct_financial
			 (acctnum, occupation, empstatus, dateemployed, employername, employeraddress,
			   secempyn, secfirmrelation, secempfirm, secempname
			  )
		VALUES 
			 ( 
			p_acctnum, p_occupation, p_empstatus, p_dateemployed, p_employername, p_employeraddress,
			 p_secempyn, p_secfirmrelation, p_secempfirm, p_secempname
			 ); 
		END;
   ELSE
	   BEGIN
		 UPDATE  acct_financial
		 SET 
			 occupation = p_occupation,
			 empstatus = p_empstatus,
			 dateemployed = p_dateemployed,
			 employername = p_employername,
			 employeraddress = p_employeraddress,
			 secempyn  = p_secempyn,
			 secfirmrelation = p_secfirmrelation,
			 secempfirm = p_secempfirm,
			 secempname = p_secempname
		 WHERE
			 acctnum = p_acctnum;
		END;
   END IF;
END
$$

