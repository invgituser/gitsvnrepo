delimiter $$

DROP PROCEDURE `sp_register_sel`
$$

CREATE PROCEDURE `sp_register_sel`(
	IN p_logonid bigint(20)
)
BEGIN 
       -- leadsource: How did you find us?: text ; 
       -- phone1type,phone2type: 'H','W','M'
       SELECT prefix, firstname, middlename, lastname, suffix, leadsource , phone, phonetype, phonealt, phonetypealt,
                   emailalt, address, address2, city, state, zip
       FROM user_logon
       WHERE logonid = p_logonid;

END
$$

