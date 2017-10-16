DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_assetclass_group`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_assetclass_group`(
	  IN p_logon			BIGINT(20)
	, IN p_new_theme		VARCHAR(50)
    , IN p_current_theme	VARCHAR(50)
	)
BEGIN

	DECLARE tAccess	VARCHAR(20);
    
	DELETE FROM `invdb`.`sec_assetclass_group`
    WHERE concat(theme) in (p_new_theme);
    
	SELECT `access`
    INTO tAccess
    from `invdb`.`user_logon`
    where logonid = tAccess;
    
    -- If the individual is Admin then they can replace all data.
    If (lower(IFNULL(tAccess,'XXX')) = 'admin')
		THEN
			INSERT INTO `invdb`.`sec_assetclass_group`
			(`theme`,
			`status`,
			`assetclass`,
			`displayName`,
			`ticker`,
			`sortorder`,
			`lowerBound`,
			`upperBound`,
			`color`,
			`averageReturn`,
			`riskAdjustment`,
			`endAllocation`,
			`created`,
			`lastupdated`)
			SELECT 
            p_new_theme,
			`tmp`.`status`,
			`tmp`.`assetclass`,
			`tmp`.`displayName`,
			`tmp`.`ticker`,
			`tmp`.`sortorder`,
			`tmp`.`lowerBound`,
			`tmp`.`upperBound`,
			`tmp`.`color`,
			`tmp`.`averageReturn`,
			`tmp`.`riskAdjustment`,
			`tmp`.`endAllocation`,
			`tmp`.`created`,
			now()
            FROM `temp`.`tmp_sec_assetclass_group` as `tmp`
            WHERE `tmp`.`theme` = p_new_theme;
		ELSE
			-- Else allow only limited access.
			INSERT INTO `invdb`.`sec_assetclass_group`
			(`theme`,
			`status`,
			`assetclass`,
			`displayName`,
			`ticker`,
			`sortorder`,
			`lowerBound`,
			`upperBound`,
			`color`,
			`averageReturn`,
			`riskAdjustment`,
			`endAllocation`,
			`created`,
			`lastupdated`)
			SELECT 
            p_new_theme,
			`sec_assetclass_group`.`status`,
			`sec_assetclass_group`.`assetclass`,
			`sec_assetclass_group`.`displayName`,
			`sec_assetclass_group`.`ticker`,
			`sec_assetclass_group`.`sortorder`,
			`sec_assetclass_group`.`lowerBound`,
			`sec_assetclass_group`.`upperBound`,
			`sec_assetclass_group`.`color`,
			`tmp`.`averageReturn`,
			`tmp`.`riskAdjustment`,
			`tmp`.`endAllocation`,
			`sec_assetclass_group`.`created`,
			now()
            FROM `temp`.`tmp_sec_assetclass_group` as `tmp`
            INNER JOIN `invdb`.`sec_assetclass_group`
            ON (`sec_assetclass_group`.`theme` = p_current_theme
			AND  `sec_assetclass_group`.`assetclass` = `tmp`.`assetclass`
            AND  `sec_assetclass_group`.`ticker` = `tmp`.`ticker`
            )
            WHERE `tmp`.`theme` = p_new_theme;
	END IF;
    
END$$
DELIMITER ;
