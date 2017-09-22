USE `invdb`;
DROP procedure IF EXISTS `sel_notificationInfo_advisor`;

DELIMITER $$
USE `invdb`$$
CREATE  PROCEDURE `sel_notificationInfo_advisor`(
	IN p_logonid BIGINT,
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
        
		SELECT 'AdvisorMessage' as src,
			count(*) as value
			FROM `advisor_notification` 
			WHERE 
            IFNULL(`advisor_notification`.`advisor`,'NONE') like tAdvisor
			AND IFNULL(`advisor_notification`.`rep`,'') like tRep
			AND IFNULL(`advisor_notification`.`status`,'N') = 'N'
			and  `advisor_notification`.`alertdatetime` <= now()
			and  IFNULL(`advisor_notification`.`tagid`,'M') like 'M%' 
		UNION
			SELECT 'Task' as src,
				count(*) as value
			FROM `advisor_notification`
            WHERE 
            IFNULL(`advisor_notification`.`advisor`,'NONE') like tAdvisor
			AND IFNULL(`advisor_notification`.`rep`,'') like tRep
			AND IFNULL(`advisor_notification`.`status`,'N') = 'N'
			and  `advisor_notification`.`alertdatetime` <= now()
			and  IFNULL(`advisor_notification`.`tagid`,'M') like 'T%'
			;
END$$

DELIMITER ;

