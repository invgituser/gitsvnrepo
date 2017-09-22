USE `invdb`;
DROP procedure IF EXISTS `sel_notification_advisor`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `sel_notification_advisor`(
	IN p_logonid BIGINT,
    IN p_messagetype VARCHAR(1),
	IN p_archive VARCHAR(1),
    IN p_advisor VARCHAR(20),
    IN p_rep VARCHAR(20)
)
BEGIN

	DECLARE tRep 	 VARCHAR(25);
	DECLARE tAdvisor VARCHAR(25);
	DECLARE vAdvisor VARCHAR(25);

		BEGIN

		select advisor into vAdvisor  from user_logon where logonid=p_logonid;
		if(vAdvisor='DEMO') then
			set tAdvisor = IFNULL(p_advisor,'');
			set tRep = IFNULL(p_rep,'');
		else
			SELECT rep, advisor
			INTO tRep, tAdvisor
			FROM user_advisor_access
			WHERE logonid = p_logonid
			LIMIT 1;
		end if;
		END;
        

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
		FROM `advisor_notification`
        WHERE 
            IFNULL(`advisor_notification`.`advisor`,'NONE') like tAdvisor
			AND IFNULL(`advisor_notification`.`rep`,'') like tRep
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
		FROM `advisor_notification`
        WHERE 
            IFNULL(`advisor_notification`.`advisor`,'NONE') like tAdvisor
			AND IFNULL(`advisor_notification`.`rep`,'') like tRep
		AND IFNULL(`advisor_notification`.`status`,'N') = 'N'
		and `advisor_notification`.`alertdatetime` <= now()
        and  `advisor_notification`.`advisorlogonid` = 0
        and  IFNULL(`advisor_notification`.`tagid`,'M') like concat(IFNULL(p_messagetype,'M'),'%')
        ) output order by output.created desc
		;
END IF;


END$$

DELIMITER ;

