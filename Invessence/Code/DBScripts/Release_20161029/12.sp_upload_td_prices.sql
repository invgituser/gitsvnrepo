DROP PROCEDURE IF EXISTS `sp_upload_td_prices`
DELIMITER $$
CREATE PROCEDURE `sp_upload_td_prices`()
BEGIN

	-- Update only closing price for items that we already updated.
	UPDATE `sec_daily_info`, `tmp_td_price` `tmp`
		set `sec_daily_info`.`close_price` = `tmp`.`price`
	WHERE `sec_daily_info`.`businessdate` = `funct_strdate2inv_date`(`tmp`.`priceDate`, '%m/%d/%Y')
    AND  `sec_daily_info`.`ticker` = `tmp`.`symbolCusip`;
    
	-- Mark all updated on tmp table as done.
	UPDATE `tmp_td_price` `tmp`, `sec_daily_info`
		set `tmp`.`securityType` = 'XX'
	WHERE `sec_daily_info`.`businessdate` = `funct_strdate2inv_date`(`tmp`.`priceDate`, '%m/%d/%Y')
    AND  `sec_daily_info`.`ticker` = `tmp`.`symbolCusip`;
    

	-- Insert prices that we did not collect from external source.
	INSERT INTO `sec_daily_info`
	(`instrumentid`,
	`ticker`,
	`businessdate`,
	`open_price`,
	`close_price`,
	`high_price`,
	`low_price`,
	`adjusted_price`,
	`prev_businessdate`,
	`prev_close_price`,
	`daily_return`,
	`volume`,
	`monthly_return`)
	SELECT `sec_master`.`instrumentid`,
		`tmp`.`symbolCusip`,
		`funct_strdate2inv_date`(`tmp`.`priceDate`, '%m/%d/%Y') as businessdate,
		`tmp`.`price` as `open_price`,
		`tmp`.`price` as `close_price`,
		`tmp`.`price` as `high_price`,
		`tmp`.`price` as `low_price`,
		`tmp`.`price` as `adjusted_price`,
		null as `prev_businessdate`,
		null as `prev_close_price`,
        null as `daily_return`,
        null as `volume`,
        null as `monthly_return`
	FROM `tmp_td_price` `tmp`
		 INNER JOIN `sec_master` `sec_master`
         ON (`tmp`.`symbolCusip` = `sec_master`.`ticker`)
	WHERE `tmp`.`securityType` not in ( 'XX' );

    
END$$
DELIMITER ;
