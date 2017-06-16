DROP PROCEDURE IF EXISTS `invdb`.`sav_notification_message_lookup`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sav_notification_message_lookup`(
  `p_advisor` 				varchar(20),
  `p_messageType` 			varchar(20),
  `p_includeAdvisor` 		varchar(1),
  `p_advisorsubject` 		varchar(30),
  `p_includeAdvisorEmail` 	varchar(1),
  `p_emailAdvisorSubject` 	varchar(30),
  `p_emailAdvisorRecepient` 	varchar(30),
  `p_includeUser` 			varchar(1),
  `p_userSubject` 			varchar(30),
  `p_includeUserEmail` 		varchar(1),
  `p_emailUserSubject` 		varchar(30)
)
BEGIN
	INSERT INTO `invdb`.`notification_message_lookup`
	(`advisor`,
	`messageType`,
	`includeAdvisor`,
	`advisorsubject`,
	`includeAdvisorEmail`,
	`emailAdvisorSubject`,
	`emailAdvisorRecepient`,
	`includeUser`,
	`userSubject`,
	`includeUserEmail`,
	`emailUserSubject`,
	`created`)
	VALUES
	(
	  `p_advisor`,
	  `p_messageType`,
	  `p_includeAdvisor`,
	  `p_advisorsubject`,
	  `p_includeAdvisorEmail`,
	  `p_emailAdvisorSubject`,
	  `p_emailAdvisorRecepient`,
	  `p_includeUser`,
	  `p_userSubject`,
	  `p_includeUserEmail`,
	  `p_emailUserSubject`,
      now()
    )
    ON duplicate key update
		`includeAdvisor` = `p_includeAdvisor`,
		`advisorsubject` = `p_advisorsubject`,
		`includeAdvisorEmail` = `p_includeAdvisorEmail`,
		`emailAdvisorSubject`= `p_emailAdvisorSubject`,
		`emailAdvisorRecepient`= `p_emailAdvisorRecepient`,
		`includeUser` = `p_includeUser`,
		`userSubject` = `p_userSubject`,
		`includeUserEmail` = `p_includeUserEmail`,
		`emailUserSubject` = `p_emailUserSubject`,
        `updated` = now()
	;

END$$
DELIMITER ;
