DROP VIEW IF EXISTS `vw_email_alerts`;

<<<<<<< .mineCREATE 
VIEW `vw_email_alerts` AS
    select 
        'User' AS `source`,
        `email_messages`.`messageid` AS `messageid`,
        `email_messages`.`sender` AS `sender`,
        `email_messages`.`receiver` AS `receiver`,
        `email_messages`.`cc` AS `cc`,
        `email_messages`.`bcc` AS `bcc`,
        `email_messages`.`subject` AS `subject`,
        `email_messages`.`msg` AS `msg`,
        `email_messages`.`status` AS `status`,
        `email_messages`.`category` AS `category`,
        `email_messages`.`priority` AS `priority`,
        `email_messages`.`created` AS `created`,
        `email_messages`.`lastupdated` AS `lastupdated`,
        `email_messages`.`sentdate` AS `sentdate`,
        `email_messages`.`mimetype` AS `mimetype`,
        `email_messages`.`attachments` AS `attachments`
    from
        `email_messages`
    where
        ((`email_messages`.`status` = 0)
            and isnull(`email_messages`.`sentdate`)) 
    union all select 
        'Internal' AS `source`,
        `email_alerts`.`messageid` AS `messageid`,
        `email_alerts`.`sender` AS `sender`,
        `email_alerts`.`receiver` AS `receiver`,
        `email_alerts`.`cc` AS `cc`,
        `email_alerts`.`bcc` AS `bcc`,
        `email_alerts`.`subject` AS `subject`,
        `email_alerts`.`msg` AS `msg`,
        `email_alerts`.`status` AS `status`,
        `email_alerts`.`category` AS `category`,
        `email_alerts`.`priority` AS `priority`,
        `email_alerts`.`created` AS `created`,
        `email_alerts`.`lastupdated` AS `lastupdated`,
        `email_alerts`.`sentdate` AS `sentdate`,
        `email_alerts`.`mimetype` AS `mimetype`,
        `email_alerts`.`attachments` AS `attachments`
    from
        `email_alerts`
    where
        ((`email_alerts`.`status` = 0)
            and isnull(`email_alerts`.`sentdate`))
    order by `created`;
=======CREATE VIEW `vw_email_alerts` 
AS
        select 'User' as source,
			   messageid, 
			   sender, 
			   receiver, 
			   cc, 
			   bcc, 
			   subject, 
			   msg, 
			   status, 
         category,
			   priority, 
			   created, 
			   lastupdated,
			   sentdate,
			   mimetype,
			   attachments
		from email_messages
		WHERE status = 0
		AND   sentdate is null
		union all
        select 'Internal' as source,
			   messageid, 
			   sender, 
			   receiver, 
			   cc, 
			   bcc, 
			   subject, 
			   msg, 
			   status, 
         category,
			   priority, 
			   created, 
			   lastupdated,
			   sentdate,
			   mimetype,
			   attachments
		from email_alerts
		WHERE status = 0
		AND   sentdate is null
		order by created
;
>>>>>>> .theirs