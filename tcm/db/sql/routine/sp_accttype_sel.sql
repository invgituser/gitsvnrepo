delimiter $$

DROP PROCEDURE `sp_accttype_sel`
$$

CREATE PROCEDURE `sp_accttype_sel`(
	IN p_acctnum bigint(20)
)
BEGIN 
 SELECT acctnum,
		accttype
 FROM acct_info
 WHERE acctnum = p_acctnum;

END
$$

