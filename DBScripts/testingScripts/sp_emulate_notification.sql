DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_notification`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_notification`(
  `p_advisor` 				varchar(20),
  `p_messageType` 			varchar(20),
  `p_includeAdvisor` 		varchar(1),
  `p_advisorsubject` 		varchar(30),
  `p_includeAdvisorEmail` 	varchar(1),
  `p_emailAdvisorSubject` 	varchar(30),
  `p_emailAdvisorRecepient` varchar(30),
  `p_includeUser` 			varchar(1),
  `p_userSubject` 			varchar(30),
  `p_includeUserEmail` 		varchar(1),
  `p_emailUserSubject` 		varchar(30)
)
BEGIN

	call `invdb`.`sav_notification_message_lookup`(
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
	  `p_emailUserSubject`
    );

END$$
DELIMITER ;
