DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_prime_asset_group`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_prime_asset_group`(
	  IN p_logon			BIGINT(20)
	, IN p_new_theme		VARCHAR(50)
    , IN p_current_theme	VARCHAR(50)
	)
BEGIN

	DECLARE tAccess	VARCHAR(20);
    
	DELETE FROM `invdb`.`sec_prime_asset_group`
    WHERE concat(theme) in (p_new_theme);
    
	SELECT `access`
    INTO tAccess
    from `invdb`.`user_logon`
    where logonid = tAccess;
    
    -- If the individual is Admin then they can replace all data.
    If (lower(IFNULL(tAccess,'XXX')) = 'admin')
		THEN
			INSERT INTO `invdb`.`sec_prime_asset_group`
			(`theme`,
			`assetclass`,
			`primeassetclass`,
			`ticker`,
			`status`,
			`sortorder`,
			`color`,
			`lowerBound`,
			`upperBound`,
			`expectedReturn`,
			`risk`,
			`yield`,
			`created`,
			`lastUpdated`)
			SELECT
				p_new_theme,
				`tmp`.`assetclass`,
				`tmp`.`primeassetclass`,
				`tmp`.`ticker`,
				`tmp`.`status`,
				`tmp`.`sortorder`,
				`tmp`.`color`,
				`tmp`.`lowerBound`,
				`tmp`.`upperBound`,
				`tmp`.`expectedReturn`,
				`tmp`.`risk`,
				`tmp`.`yield`,
				`tmp`.`created`,
				now()        
            FROM `temp`.`tmp_sec_prime_asset_group` as `tmp`
            WHERE `tmp`.`theme` = p_new_theme;
		ELSE
			-- Else allow only limited access.
			INSERT INTO `invdb`.`sec_prime_asset_group`
			(`theme`,
			`assetclass`,
			`primeassetclass`,
			`ticker`,
			`status`,
			`sortorder`,
			`color`,
			`lowerBound`,
			`upperBound`,
			`expectedReturn`,
			`risk`,
			`yield`,
			`created`,
			`lastUpdated`)
			SELECT
				p_new_theme,
				`sec_prime_asset_group`.`assetclass`,
				`sec_prime_asset_group`.`primeassetclass`,
				`sec_prime_asset_group`.`ticker`,
				`sec_prime_asset_group`.`status`,
				`sec_prime_asset_group`.`sortorder`,
				`sec_prime_asset_group`.`color`,
				`sec_prime_asset_group`.`lowerBound`,
				`sec_prime_asset_group`.`upperBound`,
				`temp`.`expectedReturn`,
				`temp`.`risk`,
				`temp`.`yield`,
				`temp`.`created`,
				now()        
            FROM `temp`.`tmp_sec_prime_asset_group` as `tmp`
            INNER JOIN `invdb`.`sec_prime_asset_group`
            ON (`sec_prime_asset_group`.`theme` = p_current_theme
			AND  `sec_prime_asset_group`.`assetclass` = `tmp`.`assetclass`
			AND  `sec_prime_asset_group`.`primeassetclass` = `tmp`.`primeassetclass`
            AND  `sec_prime_asset_group`.`ticker` = `tmp`.`ticker`
            )
            WHERE `tmp`.`theme` = p_new_theme;
            
	END IF;

	-- Make sure that both numbers match.
	UPDATE `invdb`.`sec_assetclass_group`, `temp`.`tmp_sec_prime_asset_group` 
		set `tmp`.`averageReturn` = `tmp_sec_prime_asset_group`.`expectedReturn`,
			`tmp`.`riskAdjustment` = `tmp_sec_prime_asset_group`.`risk`
	WHERE `sec_assetclass_group`.`ticker` = `tmp_sec_prime_asset_group`.`ticker`
	AND   `sec_assetclass_group`.`theme` = `tmp_sec_prime_asset_group`.`theme`
	AND   `sec_assetclass_group`.`theme` = p_new_theme;

    
END$$
DELIMITER ;
