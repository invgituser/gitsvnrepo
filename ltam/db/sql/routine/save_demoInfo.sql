DROP PROCEDURE IF EXISTS `save_demoInfo`;

DELIMITER $$
CREATE PROCEDURE `save_demoInfo`(
	INOUT p_demoID BIGINT(20),
    IN p_email     VARCHAR(25),
    IN p_lastname  varchar(25),
	IN p_firstname VARCHAR(25),
    IN p_info      VARCHAR(25)
)
BEGIN 

	DECLARE t_found INTEGER;

	BEGIN
		SELECT max(demoID)
		INTO p_demoID
		FROM `demo_users_info`
		WHERE email = p_email;
	END;
   BEGIN
	   IF (p_demoID is NULL) THEN
		   BEGIN
				INSERT INTO `demo_users_info`
				(
				`email`,
				`lastname`,
				`firstname`,
				`info`,
				`attempts`,
				`created`)
				VALUES
				(
				p_email,
				p_lastname,
				p_firstname,
				p_info,
				1,
				now()
				);

			select last_insert_id() into p_demoID;
		   END;
	   ELSE
		   BEGIN
			 UPDATE  `demo_users_info`
			 SET
				`lastname` = IFNULL(p_lastname,`lastname`),
				`firstname`= IFNULL(p_firstname,`firstname`),
				`attempts` = `attempts` + 1,
				`lastupdated` = now()
			 WHERE
				`email` = p_email;
		   END;
	   END IF;
	END;

	IF (p_demoID is null)
		then set p_demoID = 0;
	end if;

END$$
DELIMITER ;
