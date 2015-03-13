delimiter $$

DROP PROCEDURE `sp_register_add_mod`
$$

CREATE PROCEDURE `sp_register_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_userid varchar(60),
        IN  p_prefix varchar(25),
        IN  p_firstname varchar(25),
        IN  p_middlename varchar(25),
        IN  p_lastname varchar(25),
        IN  p_suffix varchar(25),
        IN  p_leadsource varchar(25),
        IN  p_phone  varchar(14),
		IN  p_phonetype varchar(1),
        IN  p_altphone  varchar(14),
		IN  p_altphonetype varchar(1),
        IN  p_altemail varchar(60),
		IN  p_address1  varchar(80),
		IN  p_address2  varchar(80),
        IN  p_city      varchar(25),
        IN  p_state     varchar(20),
        IN  p_zipcode       varchar(9),
        IN  p_emailmsgtype       varchar(45)
)
BEGIN 
       -- leadsource: How did you find us?: text ; 
       -- phonetype: 'H','W','M'
 
   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
   BEGIN
    INSERT INTO user_logon
         (logonid, prefix, firstname, middlename, lastname, suffix, leadsource , phone, phonetype, phonealt, phonetypealt,
                   emailalt, address, address2, city, state, zip
         )
    VALUES 
         ( 
		p_userid,
        p_prefix,
        p_firstname,
        p_middlename,
        p_lastname,
        p_suffix,
        p_leadsource,
        p_phone,
		p_phonetype,
        p_altphone,
		p_altphonetype,
        p_altemail,
		p_address1,
		p_address2,
        p_city,
        p_state,
        p_zipcode,
		p_emailmsgtype
         ) ; 
   END;
   ELSE
   BEGIN
     UPDATE  user_logon
     SET 
 		prefix     = p_prefix,
	    firstname  = p_firstname,
        middlename = p_middlename,
        lastname   = p_lastname,
        suffix     = p_suffix,
        leadsource = p_leadsource,
        phone      = p_phone,
		phonetype  = p_phonetype,
        phonealt   = p_altphone,
		phonetypealt= p_altphonetype,
        emailalt   = p_altemail,
		address    = p_address1,
		address2   = p_address2,
        city       = p_city,
        state      = p_state,
        zip        = p_zipcode,
        emailmsgtype  = p_emailmsgtype
     WHERE
	     logonid = p_userid;
   END;
   END IF;
END$$

