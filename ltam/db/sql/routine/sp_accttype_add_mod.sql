delimiter $$

DROP PROCEDURE `sp_accttype_add_mod`
$$

CREATE PROCEDURE `sp_accttype_add_mod`(
        IN  p_addmodflag VARCHAR(1),
		IN  p_loginid     bigint(20),
		IN  p_acctnum     bigint(20),
        IN  p_accttype   VARCHAR(25),
		OUT o_acctnum     bigint(20)
)
BEGIN
   BEGIN
	   IF (p_acctnum is NULL)
		 THEN set p_addmodflag = 'A';
	   END IF;

	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
			INSERT INTO acct_info (acctnum, accttype) VALUES (null, p_accttype);
			set o_acctnum = LAST_INSERT_ID();

		   /* Create login to account relationship */
			call sp_user_access_add_mod('A', p_loginid, o_acctnum, NULL, NULL, NULL);
       END;
       ELSE
	   BEGIN
		   UPDATE acct_info
			SET accttype = p_accttype
		   WHERE acctnum = p_acctnum;

			set o_acctnum = p_acctnum;
       END;
    END IF;
  END;
    
END
$$

