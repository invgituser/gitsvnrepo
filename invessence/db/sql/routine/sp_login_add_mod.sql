DROP PROCEDURE IF EXISTS sp_login_add_mod;

DELIMITER $$
CREATE PROCEDURE `sp_login_add_mod`(
   IN p_addmod varchar(1),
   INOUT p_logonid bigint(20),
   IN p_userid varchar(60),
   IN p_email varchar(60),
   IN p_pwd varchar(60),
   IN p_logonstatus varchar(1),
   IN p_lastname varchar(25),
   IN p_firstname varchar(25),
   IN p_state varchar(20),
   IN p_emailalt varchar(60),
   IN p_leadsource varchar(25),
   IN p_question1 varchar(60),
   IN p_answer1 varchar(40),
   IN p_question2 varchar(60),
   IN p_answer2 varchar(40),
   IN p_question3 varchar(60),
   IN p_answer3 varchar(40),
   IN p_ip varchar(20),
   IN p_macaddress varchar(20),
   IN p_resetID varchar(8),
   IN p_cookieID varchar(5)
)
BEGIN 

   DECLARE t_logonID bigint(20);
   DECLARE t_status  varchar(2);
   DECLARE t_NumOfAcct INTEGER;

   if (p_addmod is null or p_addmod = 'A') 
   then
   begin
	   select `logonid`, substring(`logonstatus`,1,1)
	   into t_logonID, t_status
	   from user_logon 
	   where userid = p_userid;

	   if (t_logonID is not null)
	   then
			set p_logonid = t_logonID;
	   else
	   begin
			INSERT INTO `user_logon`
			(
			`userid`,
			`email`,
			`pwd`,
			`logonstatus`,
			`lastname`,
			`firstname`,
			`state`,
			`emailalt`,
			`leadsource`,
			`question1`,
			`answer1`,
			`question2`,
			`answer2`,
			`question3`,
			`answer3`,
			`ip`,
			`macaddress`,
			`cookieID`,
			`resetID`,
			`attempts`,
			`created`)
			VALUES
			(
			 p_userid,
			 p_email,
			 IFNULL(p_pwd,"Default123"),
			 IFNULL(p_logonstatus,'T'),
			 p_lastname,
			 p_firstname,
			 p_state,
			 p_emailalt,
			 p_leadsource,
			 p_question1,
			 p_answer1,
			 p_question2,
			 p_answer2,
			 p_question3,
			 p_answer3,
			 p_ip,
			 p_macaddress,
			 p_cookieID,
			 p_resetID,
			 0,
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
			`lastname` = p_lastname,
			`firstname` = p_firstname,
			`state` = p_state,
			`emailalt` = p_emailalt,
			`leadsource` = p_loadsource,
			`question1` = p_question1,
			`answer1` = p_answer1,
			`question2` = p_question2,
			`answer2` = p_answer2,
			`question3` = p_question3,
			`answer3` = p_answer3,
			`ip` = p_ip,
			`macaddress` = p_macaddress,
			`cookieID` = p_cookieID,
			`resetID` = p_resetID,
			`lastupdated` = now()
			WHERE `logonid` = p_logonid;
  end;
  end if;
END$$
DELIMITER ;
