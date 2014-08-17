delimiter $$
DROP PROCEDURE `sp_login_sel`
$$

CREATE PROCEDURE `sp_login_sel`(
	IN p_loginid bigint(20)
)
BEGIN 
       SELECT pwd
       FROM user_logon
       WHERE logonid = p_loginid;

END$$

