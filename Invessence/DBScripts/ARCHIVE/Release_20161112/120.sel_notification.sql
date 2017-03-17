DROP PROCEDURE IF EXISTS `invdb`.`sel_notification`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_notification`(
	IN p_logonid BIGINT,
    IN p_messagetype VARCHAR(1),
	IN p_archive VARCHAR(1)
)
BEGIN

DECLARE tAdvisor VARCHAR(20);

	IF (p_archive is null)
		THEN set p_archive = 'N';
	END IF;

	SELECT `advisor`
    INTO tAdvisor
    FROM `user_advisor_info`
    WHERE `user_advisor_info`.`logonid` = p_logonid;
    
    if (tAdvisor is not null)
    then
		CALL `invdb`.`sel_notification_advisor`(p_logonid, p_messagetype, p_archive);
    else
		CALL `invdb`.`sel_notification_user`(p_logonid, p_messagetype, p_archive);
    end if;


END$$
DELIMITER ;
