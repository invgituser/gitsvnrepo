DROP PROCEDURE IF EXISTS `sel_notification_user`;

DELIMITER $$
CREATE PROCEDURE `sel_notification_user`(
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
		SELECT `notification`.`messageid`,
			`notification`.`status`,
			`notification`.`logonid`,
			`notification`.`advisor`,
			`notification`.`acctnum`,
			`notification`.`noticetype`,
			`notification`.`tagid`,
			`notification`.`alertdatetime`,
			`notification`.`message`,
            `notification`.`link`,
			`notification`.`created`
		FROM `user_notification` `notification`
		WHERE IFNULL(`notification`.`status`,'N') <> 'N'
		and  `notification`.`alertdatetime` <= now()
		and  `notification`.`alertdatetime` >= DATE_ADD(now(),INTERVAL -45 DAY)
		and  `notification`.`advisorlogonid` = p_logonid
        and  IFNULL(`notification`.`tagid`,'M') = IFNULL(p_messagetype,'M')
		;
	ELSE
		SELECT `notification`.`messageid`,
			`notification`.`status`,
			`notification`.`logonid`,
			`notification`.`advisor`,
			`notification`.`acctnum`,
			`notification`.`noticetype`,
			`notification`.`tagid`,
			`notification`.`alertdatetime`,
			`notification`.`message`,
			`notification`.`link`,
            `notification`.`created`
		FROM `user_notification` `notification`
		WHERE IFNULL(`notification`.`status`,'N') = 'N'
		and `notification`.`alertdatetime` <= now()
		and  `notification`.`advisorlogonid` = p_logonid
        and  IFNULL(`notification`.`tagid`,'M') = IFNULL(p_messagetype,'M')
		;
END IF;


END$$
DELIMITER ;
