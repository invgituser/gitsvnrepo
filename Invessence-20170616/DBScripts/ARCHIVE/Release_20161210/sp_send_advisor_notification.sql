DROP PROCEDURE IF EXISTS `invdb`.`sp_send_advisor_notification`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_send_advisor_notification`(
	IN p_acctnum		BIGINT,
    IN p_advisor		VARCHAR(20),
    IN p_rep			VARCHAR(20),
    IN p_messagetype 	VARCHAR(20)
    )
BEGIN

  DECLARE tFound INTEGER;
  DECLARE tName VARCHAR(80);
  DECLARE tAlertTime VARCHAR(30);
  DECLARE tMessage VARCHAR(1000);
  
  DECLARE 		
		tincludeAdvisor,
		tadvisorsubject,
		tincludeAdvisorEmail,
		temailAdvisorSubject,
		temailAdvisorRecepient,
		tincludeUser,
		tuserSubject,
		tincludeUserEmail,
		temailUserSubject    VARCHAR(60);
        
	DECLARE
		ttheme,
		tfirstname,
		tlastname,
		tportfolioName,
		tgoal,
		tacctType,
		tclientAccountID VARCHAR(60);

	DECLARE tinvested DOUBLE;

    
	set tAlertTime = DATE_FORMAT(now(),'%Y-%m-%d %T');
    
    SELECT COUNT(*)
    INTO tFound
    FROM `notification_message_lookup`
    WHERE `notification_message_lookup`.`messageType` = upper(p_messagetype)
    ;
    
    IF (tFound > 0)
    THEN
		SELECT 
			`notification_message_lookup`.`includeAdvisor`,
			IFNULL(`notification_message_lookup`.`advisorsubject`,'Notice'),
			`notification_message_lookup`.`includeAdvisorEmail`,
			IFNULL(`notification_message_lookup`.`emailAdvisorSubject`,'Email'),
			IFNULL(`notification_message_lookup`.`emailAdvisorRecepient`,`user_advisor_mapping`.`operationsEmail`),
			`notification_message_lookup`.`includeUser`,
			IFNULL(`notification_message_lookup`.`userSubject`,'Notice'),
			`notification_message_lookup`.`includeUserEmail`,
			IFNULL(`notification_message_lookup`.`emailUserSubject`,'Email')
		INTO
			tincludeAdvisor,
			tadvisorsubject,
			tincludeAdvisorEmail,
			temailAdvisorSubject,
			temailAdvisorRecepient,
			tincludeUser,
			tuserSubject,
			tincludeUserEmail,
			temailUserSubject    
		FROM `notification_message_lookup`
			INNER JOIN `user_advisor_mapping`
			ON (`notification_message_lookup`.`advisor` = `user_advisor_mapping`.`advisor`)
		WHERE `notification_message_lookup`.`advisor` = upper(p_advisor)
		AND   `notification_message_lookup`.`messageType` = upper(p_messagetype)
		LIMIT 1;
	  
		SELECT 
			IFNULL(`user_trade_profile`.`theme`,'Invessence'),
			IFNULL(`ext_acct_info`.`applicantFName`, `user_trade_profile`.`firstname`),
			IFNULL(`ext_acct_info`.`applicantLName`, `user_trade_profile`.`lastname`),
			IFNULL(`user_trade_profile`.`portfolioName`,'Portfolio'),
			IFNULL(`user_trade_profile`.`goal`,'No goal'),
			IFNULL(`ext_acct_info`.`accountType`,IFNULL(`user_trade_profile`.`acctType`,'Unassigned')),
			`invdb`.`funct_get_actualCapital`(`user_trade_profile`.`acctnum`),
			IFNULL(`ext_acct_info`.`clientAccountID`,`user_trade_profile`.`acctnum`)
		INTO
			ttheme,
			tfirstname,
			tlastname,
			tportfolioName,
			tgoal,
			tacctType,
			tinvested,
			tclientAccountID
		FROM `user_trade_profile`
			 LEFT JOIN `ext_acct_info`
			 ON (`user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`)
		WHERE `user_trade_profile`.`acctnum` = p_acctnum
		LIMIT 1;
        
--         SELECT 
-- 			p_acctnum,
-- 			p_advisor,
-- 			p_rep,
-- 			p_messagetype
-- 		;
-- 		
--         SELECT 
-- 			tincludeAdvisor,
-- 			tadvisorsubject,
-- 			tincludeAdvisorEmail,
-- 			temailAdvisorSubject,
-- 			temailAdvisorRecepient,
-- 			tincludeUser,
-- 			tuserSubject,
-- 			tincludeUserEmail,
-- 			temailUserSubject;
--             
-- 		SELECT 
-- 			ttheme,
-- 			tfirstname,
-- 			tlastname,
-- 			tportfolioName,
-- 			tgoal,
-- 			tacctType,
-- 			tinvested,
-- 			tclientAccountID;
 
		set tName = concat(tfirstname,' ',tlastname);
        set tName = IFNULL(tName,'Unassigned');
		IF (tincludeAdvisor is NOT NULL)
		THEN
			set tMessage = null;
			IF (p_messageType = 'PROCESSED')
			THEN
				SET tMessage=concat('<strong>',tadvisorsubject,', Account#:','Processing','</strong>'
									,'<table>'
									,'<tr><td>Name</td><td>Strategy</td><td>Goal</td></tr>'
									,'<tr><td>',tName,'</td><td>',tPortfolioName,'</td><td>',tGoal,'</td></tr>'
									,'</table>');
-- 				SELECT 'Processed', tMessage;
                
			END IF;
			IF (p_messageType = 'OPENED')
			THEN
				SET tMessage=concat('<strong>',tadvisorsubject,', Account#:',tclientAccountID,'</strong>'
									,'<table>'
									,'<tr><td>Name</td><td>Account Type</td></tr>'
									,'<tr><td>',tName,'</td><td>',tacctType,'</td></tr>'
									,'</table>');
-- 				SELECT 'OPENED', tMessage;
			END IF;
			IF (p_messageType = 'FUNDED')
			THEN
				set tInvested = Round(tInvested,2);
				SET tMessage=concat('<strong>',tadvisorsubject,', Account#:',tclientAccountID,'</strong>'
									,'<table>'
									,'<tr><td>Name</td><td>Funded</td></tr>'
									,'<tr><td>',tName,'</td><td>$',tInvested,'</td></tr>'
									,'</table>');
-- 				SELECT 'FUNDED', tMessage;
			END IF;
			IF (p_messageType = 'ACTIVE')
			THEN
				set tInvested = Round(tInvested,2);
				SET tMessage=concat('<strong>',tadvisorsubject,', Account#:',tclientAccountID,'</strong>'
									,'<table>'
									,'<tr><td>Account</td><td>Strategy</td><td>Goal</td><td>Amount</td></tr>'
									,'<tr><td>',tacctType,'</td><td>',tPortfolioName,'</td><td>',tGoal,'</td><td>',tInvested,'</td></tr>'
									,'</table>');
				SELECT 'ACTIVE', tMessage;
			END IF;
			IF (p_messageType = 'REBALANCE')
			THEN
				SET tMessage=concat('<strong>',tadvisorsubject,', Account#:',tclientAccountID,'</strong>'
									,'<table>'
									,'<tr><td>Name</td><td>Strategy</td><td>Goal</td></tr>'
									,'<tr><td>',tName,'</td><td>',tPortfolioName,'</td><td>',tGoal,'</td></tr>'
									,'</table>');
-- 				SELECT 'REBALANCE', tMessage;
			END IF;
			
-- 			SELECT 'Notification:', tMessage;
            
			IF (tMessage is not NULL)
			THEN
				CALL `invdb`.`sav_notification_advisor`(
				  null, -- `p_messageid` bigint(20),
				  'N', -- `p_status` varchar(1), (N=New, A=Archive)
				  0, -- `p_advisorlogonid` bigint(20), (Not assigned to specific advisor)
				  p_advisor,
				  tclientAccountID, -- `p_acctnum` bigint(20),
				  'H', -- `p_noticetype` varchar(1), (H=High)
				  'Message', -- `p_tagid` varchar(20), (M = Message)
				  tAlertTime, -- `p_alertdatetime` varchar(20),
				  tMessage, -- `p_message` varchar(120)
				  null -- p_link
				);
			END IF;
		END IF;
		
		
		IF (tincludeAdvisorEmail is NOT NULL)
		THEN
			set tMessage = null;
			IF (p_messageType = 'PROCESSED')
			THEN
				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Name: ', tName
									, ' \n\t Account#: Processing' 
									, ' \n\t Account Type: ', tacctType 
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
									);
			END IF;
			IF (p_messageType = 'OPENED')
			THEN
				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Name: ', tName
									, ' \n\t Account#: ', tclientAccountID
									, ' \n\t Account Type: ', tacctType
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
									);
			END IF;
			IF (p_messageType = 'FUNDED')
			THEN
				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Name: ', tName
									, ' \n\t Account#: ', tclientAccountID
									, ' \n\t Account Type: ', tacctType
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
									);
			END IF;
			IF (p_messageType = 'ACTIVE')
			THEN
				set tInvested = Round(tInvested,2);
				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Name: ', tName
									, ' \n\t Account#: ', tclientAccountID
									, ' \n\t Account Type: ', tacctType
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
									, ' \n\t Amount: ', tInvested
									);
			END IF;
			
            IF (p_messageType = 'REBALANCE')
            THEN
				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Name: ', tName
									, ' \n\t Account#: ', tclientAccountID
									, ' \n\t Account Type: ', tacctType
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
									);
            END IF;
            
-- 			SELECT 'Email', tMessage;

			IF (tMessage is not NULL)
			THEN
				CALL `invdb`.`sp_email_messages_add_mod`(
					  'A' -- <p_addmodflag      VARCHAR(1)}>, 
					, 'User' -- <{IN p_source    varchar(20)}>, 
					, null -- <{IN p_messageid bigint(20)}>, 
					, 'no-reply@invessence.com' -- <{IN p_sender varchar(250)}>, 
					, temailAdvisorRecepient --         <{IN p_receiver varchar(250)}>, 
					, null --         <{IN p_cc varchar(250)}>, 
					, null --         <{IN p_bcc varchar(250)}>, 
					, temailAdvisorSubject --         <{IN p_subject varchar(60)}>, 
					, 0 --         <{IN p_status tinyint(4)}>, 
					, 0 --         <{IN p_category tinyint(4)}>, 
					, 0 --         <{IN p_priority tinyint(4)}>, 
					, null --         <{IN p_logonid bigint(20)}>, 
					, null --         <{IN p_sentdate varchar(12)}>, 
					, tMessage --         <{IN p_msg mediumtext}>, 
					, null --         <{IN p_comment varchar(250)}>, 
					, 'TEXT' --         <{IN p_mimetype varchar(250)}>, 
					, null --         <{IN p_attachments mediumtext}>
				 );
			END IF;
		END IF;
    END IF;
  
    
END$$
DELIMITER ;
