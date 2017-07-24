DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_asset`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel_asset`()
BEGIN

    DECLARE tLevel VARCHAR(10);

	DELETE FROM `invdb`.`sec_fixedmodel_asset`
    WHERE concat(theme,level,asset) in (select concat(tmp.theme,tmp.level,tmp.asset) from `temp`.`tmp_sec_fixedmodel_asset` `tmp`);
    
	UPDATE `invdb`.`sec_fixedmodel_asset`
		set status = 'I', updated = now()
	WHERE `sec_fixedmodel_asset`.`status` = 'A'
    AND   `sec_fixedmodel_asset`.`theme` in (select tmp.theme from `temp`.`tmp_sec_fixedmodel_asset` `tmp`);

	INSERT INTO `invdb`.`sec_fixedmodel_asset`
	(`theme`,
	`level`,
	`asset`,
	`assetname`,
	`status`,
	`allocation`,
	`color`,
	`sortorder`,
	`created`,
	`updated`)
	SELECT `tmp`.`theme`,
		`tmp`.`level`,
		`tmp`.`asset`,
		IFNULL(`tmp`.`assetname`,`tmp`.`asset`),
		`tmp`.`status`,
		0,
		`tmp`.`color`,
		`tmp`.`sortorder`,
		now(),
		null
	FROM `temp`.`tmp_sec_fixedmodel_asset` `tmp`;
    
END$$
DELIMITER ;
