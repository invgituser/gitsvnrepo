DROP PROCEDURE IF EXISTS `sp_upload_td_sec_master`
DELIMITER $$
CREATE PROCEDURE `sp_upload_td_sec_master`()
BEGIN

	INSERT INTO `sec_master`
	(`status`,
	`ticker`,
	`cusip`,
	`isin`,
	`name`,
	`assetclass`,
	`subclass`,
	`type`,
	`style`,
	`expenseRatio`,
	`securityRiskSTD`,
	`yield`
    )
	SELECT 'A' as `status`,
		`tmp`.`symbol`,
		null as `cusip`,
		null as `isin`,
		`tmp`.`description` as `name`,
		IFNULL(`tdmapping`.`assetclass`,'User defined') as assetclass,
		IFNULL(`tdmapping`.`subclass`,'User defined') as subclass,
		 null as `type`,
		 null as `style`,
		null as `expenseRatio`,
		null as `securityRiskSTD`,
		null as `yield`
	FROM `tmp_td_security` `tmp`
		 LEFT JOIN `sec_td_mapping_master` `tdmapping`
         ON (`tmp`.`securityType` = `tdmapping`.`securityType`)
    WHERE `tmp`.`symbol` not in (select `sec`.`ticker` from `sec_master` `sec`)
    ;

    
END$$
DELIMITER ;
