DROP PROCEDURE IF EXISTS `sel_sec_fixedmodel_assets`;

DELIMITER $$
CREATE PROCEDURE `sel_sec_fixedmodel_assets`(
)
BEGIN

SELECT
	`sec_fixedmodel_asset`.`theme`,
    `sec_fixedmodel_asset`.`level`,
    `sec_fixedmodel_asset`.`asset`,
    `sec_fixedmodel_asset`.`assetname`,
    `sec_fixedmodel_asset`.`color`,
    `sec_fixedmodel_asset`.`allocation`,
    `sec_fixedmodel_asset`.`sortorder`
FROM `sec_fixedmodel_asset`,
	 `sec_fixedmodel`
WHERE `sec_fixedmodel_asset`.`status` = 'A'
AND   `sec_fixedmodel_asset`.`theme` = `sec_fixedmodel`.`theme`
AND   `sec_fixedmodel_asset`.`level` = `sec_fixedmodel`.`level`
ORDER BY `sec_fixedmodel_asset`.`theme`, `sec_fixedmodel`.`sortorder`, `sec_fixedmodel_asset`.`sortorder`
;

END$$
DELIMITER ;
