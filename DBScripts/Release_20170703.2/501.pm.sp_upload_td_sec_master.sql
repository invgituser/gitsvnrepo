DROP PROCEDURE IF EXISTS `temp`.`sp_upload_td_sec_master`;


DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_td_sec_master`()
BEGIN

	SET SQL_SAFE_UPDATES = 0;

	INSERT INTO `invdb`.`sec_master`
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
	`securityRiskSTD`
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
		null as `securityRiskSTD`
	FROM `temp`.`tmp_td_security` `tmp`
		 LEFT JOIN `temp`.`sec_td_mapping_master` `tdmapping`
         ON (`tmp`.`securityType` = `tdmapping`.`securityType`)
    WHERE `tmp`.`symbol` not in (select `sec`.`ticker` from `invdb`.`sec_master` `sec`)
    ;

	SET SQL_SAFE_UPDATES = 0;

END$$
DELIMITER ;
