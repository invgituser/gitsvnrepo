DROP PROCEDURE IF EXISTS `invdb`.`sav_notification_advisor`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sav_notification_advisor`(
  `p_messageid` bigint(20),
  `p_status` varchar(1),
  `p_advisorlogonid` bigint(20),
  `p_advisor` varchar(20),
  `p_acctnum` bigint(20),
  `p_noticetype` varchar(1),
  `p_tagid` varchar(20),
  `p_alertdatetime` varchar(20),
  `p_message` varchar(120)
)
BEGIN

		INSERT INTO `advisor_notification`
			(`messageid`,
			`status`,
			`advisorlogonid`,
			`advisor`,
			`acctnum`,
			`noticetype`,
			`tagid`,
			`alertdatetime`,
			`message`,
			`created`,
			`lastupdated`)
		VALUES
			(`p_messageid`,
			`p_status`,
			`p_advisorlogonid`,
			`p_advisor`,
			`p_acctnum`,
			`p_noticetype`,
			`p_tagid`,
			`p_alertdatetime`,
			`p_message`,
			now(),
			null)
		ON duplicate key update  
			`status` = `p_status`,
			`advisorlogonid` = `p_advisorlogonid`,
			`advisor` = `p_advisor`,
			`acctnum` = `p_acctnum`,
			`noticetype` = `p_noticetype`,
			`tagid` = `p_tagid`,
			`alertdatetime` = `p_alertdatetime`,
			`message` = `p_message`,
			`lastupdated` = now()
	;

END$$
DELIMITER ;
