DROP PROCEDURE IF EXISTS `sp_add_survey_visitor`;

DELIMITER $$
CREATE PROCEDURE `sp_add_survey_visitor`(
	  p_email			varchar(60),
	  p_lastname		varchar(30),
	  p_firstname		varchar(30),
	  p_leadsource		varchar(15),
	  p_passwrd			varchar(15),
	  p_followup		varchar(1),
	  p_surveylink		varchar(150),
	  p_emailMimeType	varchar(4)
)
BEGIN
	DECLARE tCount	Integer;

	SELECT count(*)
	INTO tCount
	FROM survey_visitors
	WHERE email = p_email;

	IF (p_email is not null)
		then
		IF (IFNULL(tCount,0) = 0)
			THEN
				INSERT INTO `survey_visitors`
				(`email`,
				`lastname`,
				`firstname`,
				`leadsource`,
				`passwrd`,
				`followup`,
				`surveylink`,
				`emailMimeType`,
				`attempts`,
				`created`,
				`lastupdated`)
				VALUES
				(p_email,
				p_lastname,
				p_firstname,
				p_leadsource,
				p_passwrd,
				IFNULL(p_followup,'Y'),
				p_surveylink,
				IFNULL(p_emailMimeType,'HTML'),
				1,
				now(),
				null);
			else
				UPDATE `survey_visitors`
					set `attempts` = IFNULL(`attempts`,0) + 1
				where
					`email` = p_email;
		END IF;
	end if;
 
END$$
DELIMITER ;
