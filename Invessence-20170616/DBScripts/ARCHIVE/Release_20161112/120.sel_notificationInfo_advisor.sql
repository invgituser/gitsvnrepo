DROP PROCEDURE IF EXISTS `invdb`.`sel_notificationInfo_advisor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_notificationInfo_advisor`(
	IN p_logonid BIGINT
)
BEGIN
		SELECT 'AdvisorMessage' as src,
			count(*) as value
		FROM `advisor_notification`, `user_logon` 
		WHERE IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and  `advisor_notification`.`alertdatetime` <= now()
        and  `user_logon`.`logonid` = p_logonid
		and  `advisor_notification`.`advisor` = `user_logon`.`advisor`
		and  IFNULL(`advisor_notification`.`tagid`,'M') like 'M%'
		UNION
		SELECT 'Task' as src,
			count(*) as value
		FROM `advisor_notification`, `user_logon`
		WHERE IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and  `advisor_notification`.`alertdatetime` <= now()
        and  `user_logon`.`logonid` = p_logonid
		and  `advisor_notification`.`advisor` = `user_logon`.`advisor`
		and  IFNULL(`advisor_notification`.`tagid`,'M') like 'T%'
		UNION
		SELECT 'UserMessage' as src,
			count(*) as value
		FROM `user_notification`, `user_logon`
		WHERE IFNULL(`user_notification`.`status`,'N') = 'N'
		and  `user_notification`.`alertdatetime` <= now()
        and  `user_logon`.`logonid` = p_logonid
		and  IFNULL(`user_notification`.`tagid`,'M') like 'M%';

END$$
DELIMITER ;
