delimiter $$
DROP PROCEDURE `sp_sourceoffund_sel`
$$

CREATE DEFINER=`root`@`%` PROCEDURE `sp_sourceoffund_sel`(
	IN p_acctnum bigint(20)
)
BEGIN
       SELECT bankacctno, bankroutingno
       FROM acct_financial
       WHERE acctnum = p_acctnum;

END
$$