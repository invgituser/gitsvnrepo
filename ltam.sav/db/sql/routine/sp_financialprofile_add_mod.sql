delimiter $$

DROP PROCEDURE `sp_financialprofile_add_mod`
$$

CREATE PROCEDURE `sp_financialprofile_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_acctnum bigint(20),
		IN  p_maritalstatus varchar(20),
        IN  p_noofdependents tinyint(4),
		IN  p_annualincome bigint(20),
        IN  p_networth bigint(20),
		IN  p_liquidnetworth bigint(20),
        IN  p_yearsexp tinyint(4)
)
BEGIN 
 
   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
   BEGIN
    INSERT INTO acct_financial
         (acctnum, maritalstatus, noofdependents, annualincome, networth,
           liquidnetworth, yearsexp       
          )
    VALUES 
         ( 
		p_acctnum, p_maritalstatus, p_noofdependents, p_annualincome,
		p_networth, p_liquidnetworth, p_yearsexp
         ); 
   END;
   ELSE
   BEGIN
     UPDATE  acct_financial
     SET 
         maritalstatus = p_maritalstatus,
         noofdependents = p_noofdependents,
         annualincome = p_annualincome,
         networth  = p_networth,
         liquidnetworth = p_liquidnetworth,
         yearsexp = p_yearsexp
     WHERE
	     acctnum = p_acctnum;
   END;
   END IF;
END
$$

