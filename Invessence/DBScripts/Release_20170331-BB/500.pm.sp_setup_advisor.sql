DROP PROCEDURE IF EXISTS `testing`.`setup_advisor`;

DELIMITER $$
CREATE PROCEDURE `testing`.`setup_advisor`(
   p_userid			VARCHAR(60)
  ,p_email			VARCHAR(60)
  ,p_firstname		VARCHAR(25)
  ,p_lastname		VARCHAR(25)
  ,p_advisor		VARCHAR(20)
  ,p_rep			VARCHAR(20)
  ,p_companyName	VARCHAR(60)
  ,p_displayName	VARCHAR(60)
  ,p_logo			VARCHAR(60)
  ,p_advisor_css	VARCHAR(30) -- To be used in future
)
BEGIN

	DECLARE tAdvisorFound	INTEGER;
    DECLARE tLogonID		BIGINT(20);
	
    SELECT COUNT(*)
    INTO tLogonID
    FROM `invdb`.`user_logon`
    WHERE `user_logon`.`email` = p_email;
    
    IF (IFNULL(tLogonID,0) = 0)
		THEN
			INSERT INTO `invdb`.`user_logon`
				(`userid`, `pwd`, `logonstatus`, `firstname`,`lastname`,  `email`, `advisor`, `rep`, `emailmsgtype`,`access`,`atstart`)
			VALUES
				(p_userid, 'Default', 'T', p_firstname, p_lastname, p_email, p_advisor, p_rep, 'HTML', 'advisor', 'setting');
                
		    select last_insert_id() into tLogonID;

		ELSE
			UPDATE `invdb`.`user_logon`
				set `userid` = p_userid
                ,	`firstname` = p_firstname
                ,	`lastname` = p_lastname
                ,	`advisor` = p_advisor
                ,	`rep` = p_rep
			WHERE 	`user_logon`.`email` = p_email;
	END IF;
    
    SELECT COUNT(*)
    INTO tAdvisorFound
    FROM `invdb`.`user_advisor_info`
    WHERE `user_advisor_info`.`logonid` = tLogonID;
    
    IF (IFNULL(tAdvisorFound,0) = 0)
		THEN
			INSERT INTO `invdb`.`user_advisor_info`
				(`logonid`,	`advisor`,	`rep`, `accttype`, `companyName`, `displayName`,`logo`,`advisor_css`,`created`)
			VALUES
				(tLogonID, p_advisor, p_rep, 'REP', p_companyName, p_displayName, p_logo, p_advisor_css, now());
		ELSE
			UPDATE `invdb`.`user_advisor_info`
				set `advisor` = p_advisor
                ,	`rep` = p_rep
                ,	`companyName` = p_companyName
                ,	`displayName` = p_displayName
                ,	`logo` = p_logo
			WHERE 	`user_advisor_info`.`logonid` = tLogonID;
	END IF;
    
    SELECT COUNT(*)
    INTO tAdvisorFound
    FROM `invdb`.`user_advisor_access`
    WHERE `user_advisor_access`.`logonid` = tLogonID;
    
    IF (IFNULL(tAdvisorFound,0) = 0)
		THEN
			INSERT INTO `invdb`.`user_advisor_access`
				(`logonid`,	`advisor`,	`rep`, `privileges` ,`created`)
			VALUES
				(tLogonID, p_advisor, p_rep, 'V', now());
		ELSE
			UPDATE `invdb`.`user_advisor_info`
				set `advisor` = p_advisor
                ,	`rep` = p_rep
			WHERE 	`user_advisor_info`.`logonid` = tLogonID;
	END IF;
        
 
    
END$$
DELIMITER ;
