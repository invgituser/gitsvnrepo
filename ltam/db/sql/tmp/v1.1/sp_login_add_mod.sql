DROP procedure if exists sp_login_add_mod;
DELIMITER $$

CREATE PROCEDURE `sp_login_add_mod`(
   IN p_addmod varchar(1),
   INOUT p_logonid bigint(20),
   IN p_userid varchar(60),
   IN p_email varchar(60),
   IN p_pwd varchar(60),
   IN p_logonstatus varchar(1),
   IN p_prefix varchar(25),
   IN p_lastname varchar(25),
   IN p_firstname varchar(25),
   IN p_middlename varchar(25),
   IN p_suffix varchar(25),
   IN p_address varchar(80),
   IN p_address2 varchar(80),
   IN p_address3 varchar(80),
   IN p_address4 varchar(80),
   IN p_city varchar(25),
   IN p_state varchar(20),
   IN p_zip varchar(9),
   IN p_emailalt varchar(60),
   IN p_phone varchar(14),
   IN p_phonetype varchar(1),
   IN p_phonealt varchar(14),
   IN p_phonetypealt varchar(1),
   IN p_leadsource varchar(25),
   IN p_question1 varchar(60),
   IN p_answer1 varchar(40),
   IN p_question2 varchar(60),
   IN p_answer2 varchar(40),
   IN p_question3 varchar(60),
   IN p_answer3 varchar(40),
   IN p_ip varchar(20),
   IN p_resetID varchar(8),
   IN p_cookieID varchar(5),
   IN p_macaddress varchar(20),
   IN p_attempts integer
)
BEGIN 

   if (p_addmod is null or p_addmod = 'A') 
   then
   begin
	   if exists( select 1 from user_logon where userid = p_userid) 
	   then
			set p_logonid = -1;
	   else
	   begin
			INSERT INTO `user_logon`
			(
			`userid`,
			`email`,
			`pwd`,
			`logonstatus`,
			`prefix`,
			`lastname`,
			`firstname`,
			`middlename`,
			`suffix`,
			`address`,
			`address2`,
			`address3`,
			`address4`,
			`city`,
			`state`,
			`zip`,
			`emailalt`,
			`phone`,
			`phonetype`,
			`phonealt`,
			`phonetypealt`,
			`leadsource`,
			`question1`,
			`answer1`,
			`question2`,
			`answer2`,
			`question3`,
			`answer3`,
			`ip`,
			`cookieID`,
			`resetID`,
			`macaddress`,
			`attempts`,
			`created`)
			VALUES
			(
			 p_userid,
			 p_email,
			 IFNULL(p_pwd,"Default123"),
			 IFNULL(p_logonstatus,'T'),
			 p_prefix,
			 p_lastname,
			 p_firstname,
			 p_middlename,
			 p_suffix,
			 p_address,
			 p_address2,
			 p_address3,
			 p_address4,
			 p_city,
			 p_state,
			 p_zip,
			 p_emailalt,
			 p_phone,
			 p_phonetype,
			 p_phonealt,
			 p_phonetypealt,
			 p_leadsource,
			 p_question1,
			 p_answer1,
			 p_question2,
			 p_answer2,
			 p_question3,
			 p_answer3,
			 p_ip,
			 p_cookieID,
			 p_resetID,
			 p_macaddress,
			 p_attempts,
			 now()
			);

			select last_insert_id() into p_logonid;

	   end;
       END IF;
   end;
   else
   begin
			UPDATE `user_logon`
			SET
			`userid` = p_userid,
			`email` = p_email,
			`pwd` = p_pwd,
			`logonstatus` = p_logonstatus,
			`prefix` = p_prefix,
			`lastname` = p_lastname,
			`firstname` = p_firstname,
			`middlename` = p_middlename,
			`suffix` = p_suffix,
			`address` = p_address,
			`address2` = p_address2,
			`address3` = p_address3,
			`address4` = p_address4,
			`city` = p_city,
			`state` = p_state,
			`zip` = p_zip,
			`emailalt` = p_emailalt,
			`phone` = p_phone,
			`phonetype` = p_phonetype,
			`phonealt` = p_phonealt,
			`phonetypealt` = p_phonetypealt,
			`leadsource` = p_loadsource,
			`question1` = p_question1,
			`answer1` = p_answer1,
			`question2` = p_question2,
			`answer2` = p_answer2,
			`question3` = p_question3,
			`answer3` = p_answer3,
			`ip` = p_ip,
			`cookieID` = p_cookieID,
			`resetID` = p_resetID,
			`macaddress` = p_macaddress,
			`attempts` = p_attempts,
			`lastupdated` = now()
			WHERE `logonid` = p_logonid;
  end;
  end if;
END
$$
DELIMITER ;