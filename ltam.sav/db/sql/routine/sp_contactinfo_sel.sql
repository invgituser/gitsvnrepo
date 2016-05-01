delimiter $$
DROP PROCEDURE `sp_contactinfo_sel`
$$

CREATE PROCEDURE `sp_contactinfo_sel`(
	IN p_acctnum bigint(20)
)
BEGIN 
       SELECT phone, phonetype, phonealt, phonetypealt, email,
                   emailalt, address, address2, city, state, zip,
                   addressalt, address2alt, cityalt, statealt, zipalt
       FROM acct_info
       WHERE acctnum = p_acctnum;

END$$

