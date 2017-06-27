DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_sec_master`;
DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_sec_fixedmodel_sec_master`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel_sec_master`()
BEGIN

	UPDATE `invdb`.`sec_master`, `temp`.`tmp_sec_master`
		set
			`sec_master`.`status` = `tmp_sec_master`.`status`,
            `sec_master`.`securityStatus` = CASE WHEN (`tmp_sec_master`.`status` = 'A') THEN 'A'
												 ELSE 'I'
										    END,
			`sec_master`.`name` = `tmp_sec_master`.`name`,
			`sec_master`.`assetclass` = `tmp_sec_master`.`assetclass`,
			`sec_master`.`subclass` = `tmp_sec_master`.`subclass`,
            `sec_master`.`exchange` = `tmp_sec_master`.`exchange`,
            `sec_master`.`base_currency` = `tmp_sec_master`.`base_currency`
	WHERE `sec_master`.`ticker` = `tmp_sec_master`.`ticker`
    ;

		INSERT INTO `invdb`.`sec_master`
		(`status`,
		`securityStatus`,
		`ticker`,
		`cusip`,
		`isin`,
		`ric`,
		`name`,
		`assetclass`,
		`subclass`,
		`type`,
		`style`,
		`expenseRatio`,
		`inception`,
		`issuer`,
		`securityRiskSTD`,
		`rbsaFlag`,
		`exchange`,
		`base_currency`)
	SELECT 
		`status`,
        CASE WHEN (`tmp_sec_master`.`status` = 'A') THEN 'A'
												 ELSE 'I'
										    END as `securityStatus`,
		`ticker`,
		null as `cusip`,
		null as `isin`,
		null as `ric`,
		`name`,
		`assetclass`,
		`subclass`,
        null as `type`,
        null as `style`,
		null as `expenseRatio`,
		null as `inception`,
        null as `issuer`,
        null as `securityRiskSTD`,
        null as `rbsaFlag`,
		`exchange`,
		`base_currency`
    FROM `temp`.`tmp_sec_master`
    WHERE `tmp_sec_master`.`ticker` not in (select `sec_master`.`ticker` from `invdb`.`sec_master`)
    ;

    
END$$
DELIMITER ;
