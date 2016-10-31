DROP PROCEDURE IF EXISTS `sp_addmod_advisor`;

DELIMITER $$
CREATE PROCEDURE `sp_addmod_advisor`(
	IN p_userid    VARCHAR(20),
    IN p_email     VARCHAR(25),
    IN p_lastname  varchar(25),
	IN p_firstname VARCHAR(25),
    IN p_logo      VARCHAR(30),
	IN p_company   VARCHAR(30),
	IN p_state	   VARCHAR(2)
)
BEGIN 

	DECLARE t_logonid BIGINT;

	BEGIN
		SELECT max(logonid)
		INTO t_logonid
		FROM `user_logon`
		WHERE email = p_email;
	END;

   BEGIN
	   IF (t_logonid is NULL) THEN
		   BEGIN
				INSERT INTO `user_logon`
				(
				`userid`,
				`email`,
				`pwd`,
				`logonstatus`,
				`lastname`,
				`firstname`,
				`access`,
				`attempts`,
				`created`)
				VALUES
				(
				p_userid,
				p_email,
				'tmpPwd123',
				'A',
				p_lastname,
				p_firstname,
				'Advisor',
				0,
				now()
				);

			select last_insert_id() into t_logonid;

			INSERT INTO `advisor_info`
			(`logonid`,
			`groupname`,
			`accttype`,
			`lastname`,
			`firstname`,
			`companyname`,
			`state`,
			`emailalt`,
			`logo`,
			`web_theme`,
			`created`
			)
			VALUES
			(t_logonid,
			p_company,
			'Advisor',
			p_lastname,
			p_firstname,
			p_company,
			p_state,
			p_email,
			p_logo,
			'primefaces-advisor',
			now()
			);

			INSERT INTO `role`
			(`logonid`,
			`role`,
			`status`)
			VALUES
			(t_logonid,
			'ADVISOR',
			'A');
		   END;
	   ELSE
		   BEGIN
			 UPDATE  `user_logon`
			 SET
				`userid` = IFNULL(p_userid,`userid`),
				`lastname`= IFNULL(lastname,`lastname`),
				`firstname`= IFNULL(p_firstname,`firstname`),
				`email`	= IFNULL(p_email,`email`),
				`lastupdated` = now()
			 WHERE
				`logonid` = t_logonid;

			UPDATE `advisor_info`
			SET
			`groupname` = IFNULL(p_company,`groupname`),
			`lastname` = IFNULL(lastname,`lastname`),
			`firstname` = IFNULL(p_firstname,`firstname`),
			`companyname` = IFNULL(p_company,`companyname`),
			`state` = IFNULL(p_state,`state`),
			`emailalt` = IFNULL(p_email,`email`),
			`logo` = IFNULL(p_logo,`logo`),
			`lastupdated` = now()
			WHERE `logonid` = t_logonid;
			 
		   END;
	   END IF;
	END;

	IF (t_logonid is null)
		then set t_logonid = 0;
	end if;

END$$
DELIMITER ;
