DROP PROCEDURE IF EXISTS `sp_login_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_login_add_mod`(
   IN p_addmod varchar(1),
   INOUT p_logonid bigint(20),
   IN p_acctnum bigint(20),
   IN p_userid varchar(60),
   IN p_email varchar(60),
   IN p_pwd varchar(60),
   IN p_logonstatus varchar(1),
   IN p_lastname varchar(25),
   IN p_firstname varchar(25),
   IN p_stateRegistered varchar(20),
   IN p_emailalt varchar(60),
   IN p_question1 varchar(60),
   IN p_answer1 varchar(40),
   IN p_question2 varchar(60),
   IN p_answer2 varchar(40),
   IN p_question3 varchar(60),
   IN p_answer3 varchar(40),
   IN p_ip varchar(20),
   IN p_resetID varchar(8),
   IN p_emailmsgtype varchar(45),
   IN p_leadsource varchar(25),
   IN p_cid varchar(3),
   IN p_advisor varchar(20),
   IN p_rep int,
   IN p_access varchar(20)
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
			set p_logonid = -1;
	   else
		   begin
				INSERT INTO `user_logon`
				(
				`userid`,
				`pwd`,
				`logonstatus`,
				`firstname`,
				`lastname`,
				`email`,
				`emailalt`,
				`stateRegistered`,
				`leadSource`,
				`cid`,
				`advisor`,
				`rep`,
				`question1`,
				`answer1`,
				`question2`,
				`answer2`,
				`question3`,
				`answer3`,
				`ip`,
				`resetID`,
				`emailmsgtype`,
				`access`,
				`created`,
				`lastupdated`)
				VALUES (
					p_userid,
					IFNULL(p_pwd,"Default123"),
					IFNULL(p_logonstatus,'T'),
					p_firstname,
					p_lastname,
					p_email,
					p_emailalt,
					p_stateRegistered,
					p_leadSource,
					p_cid,
					p_advisor,
					p_rep,
					p_question1,
					p_answer1,
					p_question2,
					p_answer2,
					p_question3,
					p_answer3,
					p_ip,
					p_resetID,
					p_emailmsgtype,
					IFNULL(p_access,'User'),
					now(),
					now()
				);

				select last_insert_id() into p_logonid;
                set t_logonID = p_logonid;

		   end;
	   END IF;
           
       IF (IFNULL(t_logonID,0) <= 0)
       THEN
			-- Add to exception, if logon was already added or it had errors.
			INSERT INTO `user_logon_exception`
				(`logonid`, `acctnum`, `userid`, `pwd`, `logonstatus`, `lastname`, `firstname`, `email`, `created`, `lastupdated`)
			VALUES (t_logonID, p_acctnum, p_userid, p_pwd, p_logonstatus, p_lastname. p_firstname, p_email, now(), null);
       ELSE
			call `sp_login_access_add_mod`( p_logonid, p_acctnum, 'OWNER', 'W');
	   END IF;
   end;
  end if;
END$$
DELIMITER ;
