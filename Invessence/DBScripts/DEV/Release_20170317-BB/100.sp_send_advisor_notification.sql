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
  DECLARE tCompanyName VARCHAR(60);
  DECLARE tRepDisplayName VARCHAR(60);
  DECLARE tMessage VARCHAR(2000);
  
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
			`notification_message_lookup`.`advisorsubject`,
			`notification_message_lookup`.`includeAdvisorEmail`,
			`notification_message_lookup`.`emailAdvisorSubject`,
			`notification_message_lookup`.`emailAdvisorRecepient`,
			`notification_message_lookup`.`includeUser`,
			`notification_message_lookup`.`userSubject`,
			`notification_message_lookup`.`includeUserEmail`,
			`notification_message_lookup`.`emailUserSubject`
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
		WHERE `notification_message_lookup`.`advisor` = upper(p_advisor)
		AND   `notification_message_lookup`.`messageType` = upper(p_messagetype)
		LIMIT 1;
	  
		SELECT 
			`user_trade_profile`.`theme`,
			IFNULL(`ext_acct_info`.`applicantFName`, `user_trade_profile`.`firstname`),
			IFNULL(`ext_acct_info`.`applicantLName`, `user_trade_profile`.`lastname`),
			`user_trade_profile`.`portfolioName`,
			`user_trade_profile`.`goal`,
			IFNULL(`ext_acct_info`.`accountType`,`user_trade_profile`.`accttype`),
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
        
        SELECT
			companyName, displayName
		INTO tCompanyName, tRepDisplayName 
		FROM `invdb`.`user_advisor_info` 
        WHERE `advisor` = p_advisor
        AND   `rep`     = p_rep;
        
        IF (tCompanyName is NULL and tRepDisplayName is NULL)
        THEN
			set tCompanyName = FUNCT_WEB_INFO_BY_ADVISOR(p_advisor, 'WEB.COMPANYNAME');
            set tRepDisplayName = 'House Account';
        END IF;
		
		set tName = concat(tfirstname,' ',tlastname);
		IF (tincludeAdvisor is NOT NULL)
		THEN
			set tMessage = null;
			IF (p_messageType = 'PROCESSED')
			THEN
				SET tMessage=concat('<div class="msgAdvStatus">
							<span>',tadvisorsubject,'</span>
							</div>
							<div class="msgAdvAcctName">
							<div class="Container40">
							<div class="ContentTitleDash">Name</div>
							<div class="ContentForTitleDash">',tFirstname,' ',tLastName,'</div>
							</div><div class="Container40">
							<div class="ContentTitleDash">Account</div>
							<div class="ContentForTitleDash">',tclientAccountID,'</div>
							</div></div>
							<table class="messageContentAdvNotification">
							<tr><td>
							<div class="ContentTitleDash">Strategy</div>
							<div class="ContentForTitleDash Fs14">',tPortfolioName,'</div>
							</td><td>
							<div class="ContentTitleDash">Goal</div>
							<div class="ContentForTitleDash Fs14">',tGoal,'</div>
							</td><td>
							<div class="ContentTitleDash">Account Type</div>
							<div class="ContentForTitleDash Fs14">',tacctType,'</div>
							</td></tr>
							</table>');

			END IF;
			IF (p_messageType = 'OPENED')
			THEN
					SET tMessage=concat('<div class="msgAdvStatus">
							<span>',tadvisorsubject,'</span>
							</div>
							<div class="msgAdvAcctName">
							<div class="Container40">
							<div class="ContentTitleDash">Name</div>
							<div class="ContentForTitleDash">',tFirstname,' ',tLastName,'</div>
							</div><div class="Container40">
							<div class="ContentTitleDash">Account</div>
							<div class="ContentForTitleDash">',tclientAccountID,'</div>
							</div></div>
							<table class="messageContentAdvNotification">
							<tr><td>
							<div class="ContentTitleDash">Strategy</div>
							<div class="ContentForTitleDash Fs14">',tPortfolioName,'</div>
							</td><td>
							<div class="ContentTitleDash">Goal</div>
							<div class="ContentForTitleDash Fs14">',tGoal,'</div>
							</td><td>
							<div class="ContentTitleDash">Account Type</div>
							<div class="ContentForTitleDash Fs14">',tacctType,'</div>
							</td></tr>
							</table>');

			END IF;
			IF (p_messageType = 'FUNDED')
			THEN
				set tInvested = Round(tInvested,2);
					SET tMessage=concat('<div class="msgAdvStatus">
							<span>',tadvisorsubject,'</span>
							<div class="msgAdvAcctName">
							<div class="Container40">
							<div class="ContentTitleDash">Name</div>
							<div class="ContentForTitleDash">',tFirstname,' ',tLastName,'</div>
							</div><div class="Container40">
							<div class="ContentTitleDash">Account</div>
							<div class="ContentForTitleDash">',tclientAccountID,'</div>
							</div></div>
							<table class="messageContentAdvNotification">
							<tr><td>
							<div class="ContentTitleDash">Strategy</div>
							<div class="ContentForTitleDash Fs14">',tPortfolioName,'</div>
							</td><td>
							<div class="ContentTitleDash">Goal</div>
							<div class="ContentForTitleDash Fs14">',tGoal,'</div>
							</td><td>
							<div class="ContentTitleDash">Account Type</div>
							<div class="ContentForTitleDash Fs14">',tacctType,'</div>
							</td><td>
							<div class="ContentTitleDash">Invested</div>
							<div class="ContentForTitleDash Fs14">',tInvested,'</div>
							</td></tr>
							</table>');

			END IF;
			IF (p_messageType = 'ACTIVE')
			THEN
				set tInvested = Round(tInvested,2);
					SET tMessage=concat('<div class="msgAdvStatus">
							<span>',tadvisorsubject,'</span>
							</div>
							<div class="msgAdvAcctName">
							<div class="Container40">
							<div class="ContentTitleDash">Name</div>
							<div class="ContentForTitleDash">',tFirstname,' ',tLastName,'</div>
							</div><div class="Container40">
							<div class="ContentTitleDash">Account</div>
							<div class="ContentForTitleDash">',tclientAccountID,'</div>
							</div></div>
							<table class="messageContentAdvNotification">
							<tr><td>
							<div class="ContentTitleDash">Strategy</div>
							<div class="ContentForTitleDash Fs14">',tPortfolioName,'</div>
							</td><td>
							<div class="ContentTitleDash">Goal</div>
							<div class="ContentForTitleDash Fs14">',tGoal,'</div>
							</td><td>
							<div class="ContentTitleDash">Account Type</div>
							<div class="ContentForTitleDash Fs14">',tacctType,'</div>
							</td><td>
							<div class="ContentTitleDash">Invested</div>
							<div class="ContentForTitleDash Fs14">',tInvested,'</div>
							</td></tr>
							</table>');
			END IF;			
			IF (p_messageType = 'REBALANCE')
			THEN
					SET tMessage=concat('<div class="msgAdvStatus">
							<span>',tadvisorsubject,'</span>
							<div class="msgAdvAcctName">
							<div class="Container40">
							<div class="ContentTitleDash">Name</div>
							<div class="ContentForTitleDash">',tFirstname,' ',tLastName,'</div>
							</div><div class="Container40">
							<div class="ContentTitleDash">Account</div>
							<div class="ContentForTitleDash">',tclientAccountID,'</div>
							</div></div>
							<table class="messageContentAdvNotification">
							<tr><td>
							<div class="ContentTitleDash">Strategy</div>
							<div class="ContentForTitleDash Fs14">',tPortfolioName,'</div>
							</td><td>
							<div class="ContentTitleDash">Goal</div>
							<div class="ContentForTitleDash Fs14">',tGoal,'</div>
							</td><td>
							<div class="ContentTitleDash">Account Type</div>
							<div class="ContentForTitleDash Fs14">',tacctType,'</div>
							</td></tr>
							</table>');
			END IF;
			IF (p_messageType = 'CHNGADDRS')
 			THEN
					SET tMessage=concat('<div class="msgAdvStatus">
							<span>',tadvisorsubject,'</span>
							<div class="msgAdvAcctName">
							<div class="Container40">
							<div class="ContentTitleDash">Name</div>
							<div class="ContentForTitleDash">',tFirstname,' ',tLastName,'</div>
							</div><div class="Container40">
							<div class="ContentTitleDash">Account</div>
							<div class="ContentForTitleDash">',tclientAccountID,'</div>
							</div></div>');
 			END IF;

 			IF (tMessage is not NULL)
 			THEN
 				CALL invdb.sav_notification_advisor(
 				  null, -- p_messageid bigint(20),
 				  'N', -- p_status varchar(1), (N=New, A=Archive)
 				  0, -- p_advisorlogonid bigint(20), (Not assigned to specific advisor)
 				  p_advisor,
                  p_rep,
 				  p_acctnum, -- p_acctnum  tclientAccountID bigint(20),
 				  'H', -- p_noticetype varchar(1), (H=High)
 				  'Message', -- p_tagid varchar(20), (M = Message)
 				  tAlertTime, -- p_alertdatetime varchar(20),
 				  tMessage, -- p_message varchar(120)
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
									, ' \n\t Company: ', tCompanyName
									, ' \n\t Rep: ', tRepDisplayName
                                    , ' \n\t Account#: ', tclientAccountID
 									, ' \n\t Name: ', tName
 									, ' \n\t Account Type: ', tacctType
 									, ' \n\t Strategy: ', tPortfolioName
 									, ' \n\t Goal: ', tGoal
 									);
 			END IF;
 			IF (p_messageType = 'OPENED')
 			THEN
 				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Company: ', tCompanyName
									, ' \n\t Rep: ', tRepDisplayName
 									, ' \n\t Account#: ', tclientAccountID
 									, ' \n\t Name: ', tName
 									, ' \n\t Account Type: ', tacctType
 									, ' \n\t Strategy: ', tPortfolioName
 									, ' \n\t Goal: ', tGoal
 									);
 			END IF;
 			IF (p_messageType = 'FUNDED')
 			THEN
 				set tInvested = Round(tInvested,2);
 				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Company: ', tCompanyName
									, ' \n\t Rep: ', tRepDisplayName
 									, ' \n\t Account#: ', tclientAccountID
 									, ' \n\t Name: ', tName
 									, ' \n\t Account Type: ', tacctType
 									, ' \n\t Strategy: ', tPortfolioName
 									, ' \n\t Goal: ', tGoal
 									, ' \n\t Funded: ', tInvested
 									);
 			END IF;
 			IF (p_messageType = 'ACTIVE')
 			THEN
			IF (p_messageType = 'REBALANCE')
			THEN
				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Company: ', tCompanyName
									, ' \n\t Rep: ', tRepDisplayName
									, ' \n\t Account#: ', tclientAccountID
									, ' \n\t Name: ', tName
									, ' \n\t Account Type: ', tacctType
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
							);
			END IF;
			set tInvested = Round(tInvested,2);
			SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Company: ', tCompanyName
									, ' \n\t Rep: ', tRepDisplayName
									, ' \n\t Account#: ', tclientAccountID
									, ' \n\t Name: ', tName
									, ' \n\t Account Type: ', tacctType
									, ' \n\t Strategy: ', tPortfolioName
									, ' \n\t Goal: ', tGoal
									, ' \n\t Amount: ', tInvested
						);
			END IF;
 			IF (p_messageType = 'CHNGADDRS')
 			THEN
 				SET tMessage=concat(temailAdvisorSubject, '\n'
									, ' \n\t Company: ', tCompanyName
									, ' \n\t Rep: ', tRepDisplayName
 									, ' \n\t Account#: ', tclientAccountID
 									, ' \n\t Name: ', tName
 									, ' \n\t Account Type: ', tacctType
 									, ' \n\t Strategy: ', tPortfolioName
 									);
 			END IF;

 			IF (tMessage is not NULL)
 			THEN
 				CALL invdb.sp_email_messages_add_mod(
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
