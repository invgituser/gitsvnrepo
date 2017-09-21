DROP PROCEDURE IF EXISTS `invdb`.`sel_notificationInfo_advisor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_notificationInfo_advisor`(
	IN p_logonid BIGINT
)
BEGIN

		SELECT 'AdvisorMessage' as src,
			count(*) as value
		FROM `advisor_notification`, `user_advisor_access` 
		WHERE `user_advisor_access`.`logonid` = `p_logonid`
        AND `advisor_notification`.`advisor` like IFNULL(`user_advisor_access`.`advisor`,'NONE')
        AND IFNULL(`advisor_notification`.`rep`,'HOUSE') like IFNULL(`user_advisor_access`.`rep`,'%')
        AND IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and  `advisor_notification`.`alertdatetime` <= now()
		and  IFNULL(`advisor_notification`.`tagid`,'M') like 'M%' 
		UNION
		SELECT 'Task' as src,
			count(*) as value
		FROM `advisor_notification`, `user_advisor_access` 
		WHERE `user_advisor_access`.`logonid` = `p_logonid`
        AND `advisor_notification`.`advisor` like IFNULL(`user_advisor_access`.`advisor`,'NONE')
        AND IFNULL(`advisor_notification`.`rep`,'HOUSE') like IFNULL(`user_advisor_access`.`rep`,'%')
		AND IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and  `advisor_notification`.`alertdatetime` <= now()
		and  IFNULL(`advisor_notification`.`tagid`,'M') like 'T%'
		;
END$$
DELIMITER ;
