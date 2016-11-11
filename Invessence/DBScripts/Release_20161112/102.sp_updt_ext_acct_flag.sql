DROP PROCEDURE IF EXISTS `temp`.`sp_updt_ext_acct_flag`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_updt_ext_acct_flag`()
BEGIN

  DECLARE done INT DEFAULT FALSE;
  DECLARE tClientID, tAcctNum, tTotal VARCHAR(20);
  DECLARE tAdvisor, tAlertTime, tPortfolioName, tGoal VARCHAR(30);
  DECLARE tMessage VARCHAR(320);
  DECLARE tReceiver VARCHAR(60);
  
  DECLARE cur1 CURSOR FOR 
  SELECT distinct
		 `ext_acct_info`.`clientAccountID`, 
		 `ext_acct_info`.`acctnum`,
         `ext_nav`.`total`,
         `user_trade_profile`.`advisor`,
         `user_trade_profile`.`portfolioName`,
         `user_trade_profile`.`goal`
  FROM `invdb`.`ext_acct_info`, `invdb`.`ext_nav`, `invdb`.`user_trade_profile`
  WHERE `ext_acct_info`.`status` in ('O', 'P', 'X')
  AND `ext_acct_info`.`acctnum` = `user_trade_profile`.`acctnum`
  AND `ext_acct_info`.`clientAccountID` = `ext_nav`.`clientAccountID`
  AND `ext_nav`.`reportDate` = `invdb`.`funct_get_switch`('BROKER_BDATE')
  AND `ext_nav`.`total` > 0
  ;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  set tAlertTime = DATE_FORMAT(now(),'%Y-%m-%d %T');
  
  SELECT value 
  into tReceiver
  FROM service.web_site_info
  WHERE name = 'SUPPORT.EMAIL'
  AND mode = 'PROD'
  LIMIT 1;
  
  -- set tReceiver = 'prashant@invessence.com';
  
  OPEN cur1;
 
  
  the_loop: LOOP
 
    
    FETCH cur1 INTO tClientID, tAcctNum, tTotal, tAdvisor, tPortfolioName, tGoal;
	
    IF done THEN
      LEAVE the_loop;
    END IF;

		SET tMessage=concat('New Account opened and funded, Account#:',tClientID,',Funded:',tTotal,',Strategy:',tPortfolioName,',Goal:',tGoal);
        -- select tClientID, tAcctNum, tTotal, tAdvisor, tPortfolioName, tGoal, tMessage;
		CALL `invdb`.`sav_notification_advisor`(
		  null, -- `p_messageid` bigint(20),
		  'N', -- `p_status` varchar(1), (N=New, A=Archive)
		  0, -- `p_advisorlogonid` bigint(20), (Not assigned to specific advisor)
		  tAdvisor,
		  tAcctNum, -- `p_acctnum` bigint(20),
		  'H', -- `p_noticetype` varchar(1), (H=High)
		  'Message', -- `p_tagid` varchar(20), (M = Message)
		  tAlertTime, -- `p_alertdatetime` varchar(20),
		  tMessage -- `p_message` varchar(120)
		);

        -- select tClientID, tAcctNum, tTotal, tAdvisor, tPortfolioName, tGoal, tMessage;
        CALL `invdb`.`sp_email_messages_add_mod`(
			  'A' -- <p_addmodflag      VARCHAR(1)}>, 
			, 'User' -- <{IN p_source    varchar(20)}>, 
			, null -- <{IN p_messageid bigint(20)}>, 
			, 'no-reply@invessence.com' -- <{IN p_sender varchar(250)}>, 
			, tReceiver --         <{IN p_receiver varchar(250)}>, 
			, null --         <{IN p_cc varchar(250)}>, 
			, null --         <{IN p_bcc varchar(250)}>, 
			, 'New account opened and funded' --         <{IN p_subject varchar(60)}>, 
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


	-- select tClientID, tAcctNum, tTotal, tAdvisor, tPortfolioName, tGoal, tMessage;
    -- Update all accounts to Active where we have nav.
    UPDATE `invdb`.`ext_acct_info`
		set `ext_acct_info`.`status` = 'A',
			`ext_acct_info`.`lastUpdated` = now()
	WHERE `ext_acct_info`.`clientAccountID` = tClientID
    ;


  END LOOP the_loop;
 
  CLOSE cur1;
  

        
END$$
DELIMITER ;
