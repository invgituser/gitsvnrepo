delimiter $$

DROP PROCEDURE `sp_acct_sel`
$$

CREATE PROCEDURE `sp_acct_sel`(
	IN p_logonid varchar(20),
    IN p_acctnum bigint(20)
)
BEGIN 

  IF ( p_acctnum is NULL) THEN
	  BEGIN
		 SELECT ai.acctnum, ai.accttype  /* ,uar.functionid, uar.role, uar.privilege */
		 FROM  user_access_role AS uar, acct_info AS ai
		 WHERE uar.logonid = p_logonid and 
			   uar.acctnum = ai.acctnum;
	  END;
  ELSE
	  BEGIN
		 SELECT ai.acctnum, ai.accttype  /* ,uar.functionid, uar.role, uar.privilege */
		 FROM acct_info AS ai  /* ,user_access_role AS uar */
		 WHERE ai.acctnum = p_acctnum;  /* and uar.acctnum = ai.acctnum */
	  END;
  END IF;
END
$$

