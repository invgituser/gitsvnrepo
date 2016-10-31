DROP PROCEDURE IF EXISTS `sel_survey_visitor`;

DELIMITER $$
CREATE PROCEDURE `sel_survey_visitor`(
	  p_email			varchar(60)
)
BEGIN
			SELECT 
			`email`,
			`lastname`,
			`firstname`,
			`leadsource`,
			`passwrd`,
			`followup`,
			`surveylink`,
			`emailMimeType`,
			DATE_FORMAT(`created`,'%Y-%m-%d %H:%i:%S') as created,
			DATE_FORMAT(`lastupdated`,'%Y-%m-%d %H:%i:%S') as lastupdated
			FROM `survey_visitors`
			WHERE CASE WHEN (p_email is null)
						THEN 'ALL'
					    ELSE `email`
				  END = IFNULL(p_email,'ALL');
END$$
DELIMITER ;
