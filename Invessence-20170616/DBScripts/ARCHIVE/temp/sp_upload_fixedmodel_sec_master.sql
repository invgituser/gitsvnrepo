DROP PROCEDURE IF EXISTS `temp`.`sp_upload_fixedmodel_sec_master`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_fixedmodel_sec_master`()
BEGIN

    UPDATE `invdb`.`sec_master`, `temp`.`tmp_sec_master`
		set 
			`sec_master`.`status` = `tmp_sec_master`.`status`,
			`sec_master`.`cusip` = `tmp_sec_master`.`cusip`,
			`sec_master`.`isin` = `tmp_sec_master`.`isin`,
			`sec_master`.`name` = `tmp_sec_master`.`name`,
			`sec_master`.`assetclass` = `tmp_sec_master`.`assetclass`,
			`sec_master`.`subclass` = `tmp_sec_master`.`subclass`,
			`sec_master`.`expenseRatio` = `tmp_sec_master`.`expenseRatio`,
			`sec_master`.`yield` = `tmp_sec_master`.`yield`,
			`sec_master`.`securityRiskSTD` = `tmp_sec_master`.`securityRiskSTD`
	WHERE `sec_master`.`ticker` = `tmp_sec_master`.`ticker`
    ;
	

	INSERT INTO `invdb`.`sec_master`
	(`sec_master`.`status`,
    `sec_master`.`ticker`,
    `sec_master`.`cusip`,
    `sec_master`.`isin`,
    `sec_master`.`name`,
    `sec_master`.`assetclass`,
    `sec_master`.`subclass`,
    `sec_master`.`expenseRatio`,
    `sec_master`.`yield`,
    `sec_master`.`securityRiskSTD`
    )
	SELECT `tmp_sec_master`.`status`,
		`tmp_sec_master`.`ticker`,
		`tmp_sec_master`.`cusip`,
		`tmp_sec_master`.`isin`,
		`tmp_sec_master`.`name`,
		`tmp_sec_master`.`assetclass`,
		`tmp_sec_master`.`subclass`,
		`tmp_sec_master`.`expenseRatio`,
		`tmp_sec_master`.`yield`,
		`tmp_sec_master`.`securityRiskSTD`
	FROM `temp`.`tmp_sec_master`
    WHERE `tmp_sec_master`.`ticker` not in 
		  (select `sec_master`.`ticker` from `invdb`.`sec_master`)
    ;
    


    
END$$
DELIMITER ;
