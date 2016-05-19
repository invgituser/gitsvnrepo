delimiter $$
DROP PROCEDURE `sp_contactinfo_add_mod`
$$

CREATE PROCEDURE `sp_contactinfo_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_acctnum bigint(20),
		IN  p_ss varchar(9),
        IN  p_phone  varchar(14),
		IN  p_phonetype varchar(1),
        IN  p_altphone  varchar(14),
		IN  p_altphonetype varchar(1),
        IN  p_email varchar(60),
        IN  p_altemail varchar(60),
		IN  p_address1  varchar(80),
		IN  p_address2  varchar(80),
        IN  p_city      varchar(25),
        IN  p_state     varchar(20),
        IN  p_zipcode       varchar(9),
		IN  p_altaddress1  varchar(80),
		IN  p_altaddress2  varchar(80),
        IN  p_altcity      varchar(25),
        IN  p_altstate     varchar(20),
        IN  p_altzipcode       varchar(9)
)
BEGIN 
 
   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO acct_info
			 (acctnum, phone, phonetype, phonealt, phonetypealt,
					   email, emailalt, address, address2, city, state, zip,
					   addressalt, address2alt, cityalt, statealt, zipalt
			 )
		VALUES 
			 ( 
			p_acctnum,
			p_phone,
			p_phonetype,
			p_altphone,
			p_altphonetype,
			p_email,
			p_altemail,
			p_address1,
			p_address2,
			p_city,
			p_state,
			p_zipcode,
			p_altaddress1,
			p_altaddress2,
			p_altcity,
			p_altstate,
			p_altzipcode
			 ); 
	   END;
   ELSE
	   BEGIN
		 UPDATE  acct_info
		 SET 
			phone      = p_phone,
			phonetype  = p_phonetype,
			phonealt   = p_altphone,
			phonetypealt = p_altphonetype,
			email      = p_email,
			emailalt   = p_altemail,
			address    = p_address1,
			address2   = p_address2,
			city       = p_city,
			state      = p_state,
			zip        = p_zipcode,
			addressalt  = p_altaddress1,
			address2alt = p_altaddress2,
			cityalt     = p_altcity,
			statealt    = p_altstate,
			zipalt      = p_altzipcode

		 WHERE
			 acctnum = p_acctnum;
	   END;
   END IF;
END
$$

