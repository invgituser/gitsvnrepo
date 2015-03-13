DROP PROCEDURE `sp_update_price_business_date`;

DELIMITER $$
CREATE PROCEDURE `sp_update_price_business_date`()
BEGIN
   DECLARE last_price_date date;
   DECLARE instrPriced   integer;
   DECLARE totalInstr    integer;
   DECLARE yahoo_price_date varchar(20);

   SELECT max(businessdate)
   INTO last_price_date
   FROM sec_daily_info;

   -- Validate
   SELECT COUNT(*) 
   INTO totalInstr
   FROM sec_master
   WHERE sec_master.status='A';
   
   SELECT COUNT(*) 
   INTO instrPriced
   FROM sec_daily_info
   WHERE sec_daily_info.businessdate = last_price_date;

   IF (instrPriced < totalInstr)
	 THEN
		BEGIN
		-- Send alert
			CALL `sp_email_messages_add_mod`(
			 'A' -- <{IN  p_addmodflag      VARCHAR(1)}>
			,'Internal' -- <{IN p_source    varchar(20)}>
			, null  -- <{IN p_messageid bigint(20)}>
			,'no-reply@invessence.com' -- <{IN p_sender varchar(250)}>
			,'pricesupport@invessence.com' -- <{IN p_receiver varchar(250)}> 
			, null -- <{IN p_cc varchar(250)}>
			, null -- <{IN p_bcc varchar(250)}>
			, CONCAT('Warning: Missing ', totalInstr - instrPriced, ' Prices') -- <{IN p_subject varchar(60)}>
			, null -- <{IN p_status tinyint(4)}>
		    , null -- <{IN p_category tinyint(4)}>
			, null -- <{IN p_priority tinyint(4)}>
			, null -- <{IN p_logonid bigint(20)}>
			, null -- <{IN p_sentdate varchar(12)}>
			, CONCAT('Out of ', totalInstr, ' Only loaded, ', instrPriced, ' Prices') -- <{-- MM/DD/YYYY IN p_msg mediumtext}>
			, 'From: sp_update_price_business_date' -- <{IN p_comment varchar(250)}>
			, 'TEXT'  -- p_mimetype, 
			, null  -- p_attachments mediumtext}>);
			);
			DELETE FROM tmp_yahoo_prices;

			INSERT INTO tmp_yahoo_prices
			SELECT * from yahoo_prices;

			UPDATE tmp_yahoo_prices
			SET trade_date = DATE_FORMAT(last_price_date,'%m/%d/%Y');

			INSERT INTO yahoo_prices
			SELECT tmp_yahoo_prices.* 
			from tmp_yahoo_prices
			WHERE CONCAT(ticker,trade_date) not in (SELECT CONCAT(ticker,trade_date)
													FROM yahoo_prices);

		END;
	  else
		BEGIN
		CALL `sp_email_messages_add_mod`(
			 'A' -- <{IN  p_addmodflag      VARCHAR(1)}>
			,'Internal' -- <{IN p_source    varchar(20)}>
			, null  -- <{IN p_messageid bigint(20)}>
			,'no-reply@invessence.com' -- <{IN p_sender varchar(250)}>
			,'pricesupport@invessence.com' -- <{IN p_receiver varchar(250)}> 
			, null -- <{IN p_cc varchar(250)}>
			, null -- <{IN p_bcc varchar(250)}>
			, CONCAT('Info: all ', instrPriced, ' Prices loaded') -- <{IN p_subject varchar(60)}>
			, 0 -- <{IN p_status tinyint(4)}>
		    , 0 -- <{IN p_category tinyint(4)}>
			, 4 -- <{IN p_priority tinyint(4)}>
			, null -- <{IN p_logonid bigint(20)}>
			, null -- <{IN p_sentdate varchar(12)}>
			, CONCAT('Info only.  No action required.') -- <{-- MM/DD/YYYY IN p_msg mediumtext}>
			, 'From sp_update_price_business_date' -- <{IN p_comment varchar(250)}>
			, 'TEXT' -- p_mimetype
			, null -- p_attachments
		    );
		END;
	END IF;


   Update invessence_switch
		set `value` = DATE_FORMAT(last_price_date,'%Y%m%d')
		, lastupdated = now()
   Where name = 'PRICE_DATE';

   
END$$
DELIMITER ;
