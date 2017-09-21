USE `invdb`;
CREATE TABLE `trade_process_identifier` (
  `acctnum` bigint(20) NOT NULL,
  `tradeStatus` varchar(1) DEFAULT NULL COMMENT 'N = New\nA = Allocation\nD = DateBreak\nV = View',
  `processStatus` varchar(1) DEFAULT NULL COMMENT 'N : Not Processed\nY : User Selected\nR : ReBalance\nP : Processed\nS : Sent',
  `reason` varchar(100) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `file_process_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product` varchar(45) NOT NULL,
  `mode` varchar(45) NOT NULL,
  `process` varchar(45) NOT NULL,
  `processId` varchar(45) NOT NULL,
  `fileName` varchar(100) NOT NULL,
  `status` varchar(45) NOT NULL,
  `remarks` varchar(500) NOT NULL,
  `executionTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



USE `invdb`;
DROP procedure IF EXISTS `file_process_auditrial`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `file_process_auditrial`(
in p_id	bigint(20),
in p_product	varchar(45),
in p_mode	varchar(45),
in p_process	varchar(45),
in p_processId	varchar(45),
in p_fileName	varchar(100),
in p_status	varchar(45),
in p_remarks	varchar(500),
in p_executionTime	datetime,
in p_opt varchar(20),
out op_msgCode int(3),out op_msg varchar(20))
BEGIN

	Insert into invdb.file_process_audit(
product,
mode,
process,
processId,
fileName,
status,
remarks,
executionTime)
    value(p_product,
p_mode,
p_process,
p_processId,
p_fileName,
p_status,
p_remarks,
now());

    
	SELECT 'MSG', max(id) INTO op_msg , op_msgCode from invdb.file_process_audit;

END$$

DELIMITER ;




USE `invdb`;
DROP procedure IF EXISTS `sp_email_messages_add_mod`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `sp_email_messages_add_mod`(
	IN  p_addmodflag      VARCHAR(1),
	IN p_source    varchar(20),
	IN p_messageid bigint(20),
	IN p_sender varchar(250),
	IN p_receiver varchar(250),
	IN p_cc varchar(250),
	IN p_bcc varchar(250),
	IN p_subject varchar(250),
	IN p_status tinyint(4),
	IN p_category tinyint(4),
	IN p_priority tinyint(4),
	IN p_logonid bigint(20),
	IN p_sentdate varchar(12),
	IN p_msg mediumtext,
	IN p_comment varchar(250),
	IN p_mimetype varchar(250),
	IN p_attachments mediumtext
)
BEGIN

   IF (p_source is not null and p_source = "Internal") THEN
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
		   BEGIN
			INSERT INTO email_alerts
				 (
				sender,
				receiver,
				cc,
				bcc,
				`subject`,
				msg,
				`status`,
				category,
				priority,
				logonid,
				sentdate,
				`comment`,
				created,
				lastupdated,
				mimetype,
				attachments
				 )
			VALUES
				 (
				p_sender,
				p_receiver,
				p_cc,
				p_bcc,
				IFNULL(p_subject,'Invessence: email'),
				p_msg,
				p_status,
				p_category,
				p_priority,
				p_logonid,
				null,
				p_comment,
				now(),
				NULL,
				p_mimetype,
				p_attachments
				) ;
		   END;
	   ELSE
		   BEGIN
			 UPDATE  email_alerts
			 SET
			`subject` = IFNULL(p_subject,'Invessence: email'),
			sender = p_sender,
			receiver = p_receiver,
			cc = p_cc,
			bcc = p_bcc,
			msg = p_msg,
			`status` = p_status,
			category = p_category,
			priority = p_priority,
			logonid = p_logonid,
			sentdate = STR_TO_DATE(p_sentdate,'%m/%d/%Y'),
			`comment` = p_comment,
			lastupdated = now(),
			mimetype = p_mimetype,
			attachments = p_attachments
		 WHERE
			messageid = p_messageid
			AND lastupdated is null;
		   END;
	   END IF;
   ELSE
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
		   BEGIN
			INSERT INTO email_messages
				 (

				sender,
				receiver,
				cc,
				bcc,
				`subject`,
				msg,
				`status`,
				category,
				priority,
				logonid,
				sentdate,
				`comment`,
				created,
				lastupdated,
				mimetype,
				attachments
				 )
			VALUES
				 (

				p_sender,
				p_receiver,
				p_cc,
				p_bcc,
				IFNULL(p_subject,'Invessence: email'),
				p_msg,
				p_status,
				p_category,
				p_priority,
				p_logonid,
				null,
				p_comment,
				now(),
				NULL,
				p_mimetype,
				p_attachments
				 ) ;
		   END;
	   ELSE
		   BEGIN
			 UPDATE  email_messages
			 SET
			`subject` = IFNULL(p_subject,'Invessence: email'),
			sender = p_sender,
			receiver = p_receiver,
			cc = p_cc,
			bcc = p_bcc,
			msg = p_msg,
			`status` = p_status,
			category = p_category,
			priority = p_priority,
			logonid = p_logonid,
			sentdate = STR_TO_DATE(p_sentdate,'%m/%d/%Y'),
			`comment` = p_comment,
			lastupdated = now(),
			mimetype = p_mimetype,
			attachments = p_attachments
		 WHERE
			messageid = p_messageid
			AND lastupdated is null;
		   END;
	  END IF;
   END IF;
END$$

DELIMITER ;



