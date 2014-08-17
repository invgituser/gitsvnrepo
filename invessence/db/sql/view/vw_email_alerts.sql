DROP VIEW IF EXISTS `vw_email_alerts`;

CREATE VIEW `vw_email_alerts` 
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
			   sentdate 
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
			   sentdate 
		from email_alerts
		WHERE status = 0
		AND   sentdate is null
		order by created
;
