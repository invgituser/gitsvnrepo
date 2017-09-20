USE `invdb`;
DROP procedure IF EXISTS `sav_notification_advisor`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `sav_notification_advisor`(
  `p_messageid` bigint(20),
  `p_status` varchar(1),
  `p_advisorlogonid` bigint(20),
  `p_advisor` varchar(20),
  `p_acctnum` bigint(20),
  `p_noticetype` varchar(1),
  `p_tagid` varchar(20),
  `p_alertdatetime` varchar(30),
  `p_message` varchar(2000),
  `p_link`    varchar(120)
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
            `link`,
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
            `p_link`,
			now(),
			null)
		ON duplicate key update  
			`status` = `p_status`,
			`lastupdated` = now()
	;

END$$

DELIMITER ;

