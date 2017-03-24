DROP PROCEDURE IF EXISTS `invdb`.`sel_notification_advisor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_notification_advisor`(
	IN p_logonid BIGINT,
    IN p_messagetype VARCHAR(1),
	IN p_archive VARCHAR(1)
)
BEGIN


IF (p_archive is null)
	THEN set p_archive = 'N';
END IF;


IF (p_archive <> 'N')
	THEN
	select output.* from (
	SELECT `advisor_notification`.`messageid`,
			`advisor_notification`.`status`,
			`advisor_notification`.`advisorlogonid`,
			`advisor_notification`.`advisor`,
			`advisor_notification`.`rep`,
			`advisor_notification`.`acctnum`,
			`advisor_notification`.`noticetype`,
			`advisor_notification`.`tagid`,
			`advisor_notification`.`alertdatetime`,
			`advisor_notification`.`message`,
			`advisor_notification`.`link`,
			`advisor_notification`.`created`
		FROM `advisor_notification`
        WHERE `advisor_notification`.`advisorlogonid` = p_logonid
		AND IFNULL(`advisor_notification`.`status`,'N') <> 'N'
		and  `advisor_notification`.`alertdatetime` <= now()
		and  `advisor_notification`.`alertdatetime` >= DATE_ADD(now(),INTERVAL -45 DAY)
        and  IFNULL(`advisor_notification`.`tagid`,'M') like concat(IFNULL(p_messagetype,'M'),'%')
        UNION 
		SELECT `advisor_notification`.`messageid`,
			`advisor_notification`.`status`,
			`advisor_notification`.`advisorlogonid`,
			`advisor_notification`.`advisor`,
			`advisor_notification`.`rep`,
			`advisor_notification`.`acctnum`,
			`advisor_notification`.`noticetype`,
			`advisor_notification`.`tagid`,
			`advisor_notification`.`alertdatetime`,
			`advisor_notification`.`message`,
			`advisor_notification`.`link`,
			`advisor_notification`.`created`
		FROM `advisor_notification`, `user_advisor_access` 
		WHERE `user_advisor_access`.`logonid` = p_logonid
        AND `advisor_notification`.`advisor` like IFNULL(`user_advisor_access`.`advisor`,'NONE')
        AND IFNULL(`advisor_notification`.`rep`,'HOUSE') like IFNULL(`user_advisor_access`.`rep`,'%')
		AND IFNULL(`advisor_notification`.`status`,'N') <> 'N'
		and  `advisor_notification`.`alertdatetime` <= now()
		and  `advisor_notification`.`alertdatetime` >= DATE_ADD(now(),INTERVAL -45 DAY)
        and  `advisor_notification`.`advisorlogonid` = 0
        and  IFNULL(`advisor_notification`.`tagid`,'M') like concat(IFNULL(p_messagetype,'M'),'%')
        )output order by output.created desc
		;
	ELSE
    select output.* from (
		SELECT `advisor_notification`.`messageid`,
			`advisor_notification`.`status`,
			`advisor_notification`.`advisorlogonid`,
			`advisor_notification`.`advisor`,
			`advisor_notification`.`rep`,
			`advisor_notification`.`acctnum`,
			`advisor_notification`.`noticetype`,
			`advisor_notification`.`tagid`,
			`advisor_notification`.`alertdatetime`,
			`advisor_notification`.`message`,
			`advisor_notification`.`link`,
			`advisor_notification`.`created`
		FROM `advisor_notification`
		WHERE `advisor_notification`.`advisorlogonid` = p_logonid
        AND  IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and `advisor_notification`.`alertdatetime` <= now()
        and  IFNULL(`advisor_notification`.`tagid`,'M') like concat(IFNULL(p_messagetype,'M'),'%')
        UNION
		SELECT `advisor_notification`.`messageid`,
			`advisor_notification`.`status`,
			`advisor_notification`.`advisorlogonid`,
			`advisor_notification`.`advisor`,
			`advisor_notification`.`rep`,
			`advisor_notification`.`acctnum`,
			`advisor_notification`.`noticetype`,
			`advisor_notification`.`tagid`,
			`advisor_notification`.`alertdatetime`,
			`advisor_notification`.`message`,
			`advisor_notification`.`link`,
			`advisor_notification`.`created`
		FROM `advisor_notification`, `user_advisor_access` 
		WHERE `user_advisor_access`.`logonid` = p_logonid
        AND `advisor_notification`.`advisor` like IFNULL(`user_advisor_access`.`advisor`,'NONE')
        AND IFNULL(`advisor_notification`.`rep`,'HOUSE') like IFNULL(`user_advisor_access`.`rep`,'%')
		AND IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and `advisor_notification`.`alertdatetime` <= now()
        and  `advisor_notification`.`advisorlogonid` = 0
        and  IFNULL(`advisor_notification`.`tagid`,'M') like concat(IFNULL(p_messagetype,'M'),'%')
        ) output order by output.created desc
		;
END IF;


END$$
DELIMITER ;
