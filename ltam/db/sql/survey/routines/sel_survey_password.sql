DROP PROCEDURE IF EXISTS `sel_survey_password`;

DELIMITER $$
CREATE PROCEDURE `sel_survey_password`(
	  p_source			varchar(60)
)
BEGIN
	SELECT `survey_passwords`.`source`,
		`survey_passwords`.`passwordID`,
		`survey_passwords`.`passwrd`,
		`survey_passwords`.`created`,
		`survey_passwords`.`lastupdated`
	FROM `survey_passwords`;
END$$
DELIMITER ;
