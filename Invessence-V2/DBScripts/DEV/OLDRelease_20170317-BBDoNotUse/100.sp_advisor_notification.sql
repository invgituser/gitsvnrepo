DROP PROCEDURE IF EXISTS `invdb`.`sp_advisor_notification`;

DELIMITER $$
CREATE PROCEDURE `invdb.``sp_advisor_notification`(
  p_messageid		BIGINT(20),
  p_status			VARCHAR(1),
  p_advisorlogonid	BIGINT(20),
  p_advisor 		VARCHAR(20),
  p_rep 			VARCHAR(20),
  p_acctnum 		BIGINT(20),
  p_noticetype 		varchar(1),
  p_tagid 			VARCHAR(20),
  p_alertdatetime 	VARCHAR(30),
  p_message	 		VARCHAR(120)
)
BEGIN
	DECLARE t_alertdatetime datetime;

	IF (p_advisor is null and p_advisorlogonid is not null )
    THEN
		SELECT groupname 
        into p_advisor
        FROM advisor_info
        WHERE logonid = p_advisorlogonid;
    END IF;
    
    IF (p_alertdatetime is null)
    THEN
		set t_alertdatetime = now();
	ELSE
		set t_alertdatetime = str_to_date(p_alertdatetime,'%Y/%m/%d %H:%i');
    END IF;
    
    SELECT t_alertdatetime;
    
	IF (p_messageid is null)
		THEN
			INSERT INTO `invdb`.`advisor_notification`
			(
				`status`,
				`advisorlogonid`,
				`advisor`,
                `rep`,
				`acctnum`,
				`noticetype`,
				`tagid`,
				`alertdatetime`,
				`message`,
				`created`,
				`lastupdated`
			)
			VALUES
			(
				p_status,
				p_advisorlogonid,
				p_advisor,
                p_rep,
				IFNULL(p_acctnum,0),
				IFNULL(p_noticetype,'I'),
				p_tagid,
				IFNULL(t_alertdatetime,now()),
				p_message,
				now(),
				null
			);
		ELSE
			UPDATE `invdb`.`advisor_notification`
				set `status` = p_status,
				    `lastupdated` = now()
			WHERE `messageid` = p_messageid
			;
	END IF;

END$$
DELIMITER ;
