DROP PROCEDURE `sp_email_messages_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_email_messages_add_mod`(
	IN  p_addmodflag      VARCHAR(1),
	IN p_source    varchar(20),
	IN p_messageid bigint(20),
	IN p_sender varchar(250),
	IN p_receiver varchar(250),
	IN p_cc varchar(250),
	IN p_bcc varchar(250),
	IN p_subject varchar(60),
	IN p_status tinyint(4),
	IN p_category tinyint(4),
	IN p_priority tinyint(4),
	IN p_logonid bigint(20),
	IN p_sentdate varchar(12),  -- MM/DD/YYYY
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
				null,  -- Ignoring the arg!
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
				-- messageid,  (Auto increment)
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
				-- p_messageid,
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
				null,  -- Ignoring the arg!
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
