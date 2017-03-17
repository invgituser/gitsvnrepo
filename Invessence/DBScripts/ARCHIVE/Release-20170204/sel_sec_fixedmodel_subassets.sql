DROP PROCEDURE IF EXISTS `invdb`.`sel_sec_fixedmodel_subassets`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_sec_fixedmodel_subassets`()
BEGIN

SELECT `sec_fixedmodel_subasset`.`theme`,
    `sec_fixedmodel_subasset`.`level`,
    `sec_fixedmodel_subasset`.`asset`,
    `sec_fixedmodel_subasset`.`keyname`,
    `sec_fixedmodel_asset`.`assetname`,
    IFNULL(`sec_fixedmodel_subasset`.`keydescription`,`sec_master`.`subclass`) as keydescription,
    `sec_fixedmodel_subasset`.`allocation`,
    `sec_fixedmodel_asset`.`color`,
    `sec_master`.`ticker`,
    `sec_master`.`name`
FROM `sec_fixedmodel_subasset`
	 INNER JOIN `sec_fixedmodel_asset`
     ON ((((     `sec_fixedmodel_asset`.`theme` = `sec_fixedmodel_subasset`.`theme` )
		AND   `sec_fixedmodel_asset`.`level` = `sec_fixedmodel_subasset`.`level`)
		AND	  `sec_fixedmodel_asset`.`asset` = `sec_fixedmodel_subasset`.`asset`))
	 INNER JOIN `sec_fixedmodel`
     ON ((     `sec_fixedmodel_subasset`.`theme` = `sec_fixedmodel`.`theme`)
     AND   `sec_fixedmodel_subasset`.`level` = `sec_fixedmodel`.`level`)
	 LEFT JOIN `sec_master`
     ON (`sec_master`.`ticker` = `sec_fixedmodel_subasset`.`keyname`)
WHERE `sec_fixedmodel_subasset`.`status` = 'A'
ORDER BY `sec_fixedmodel_subasset`.`theme`, `sec_fixedmodel`.`sortorder`, 
	`sec_fixedmodel_asset`.`sortorder`, `sec_fixedmodel_subasset`.`sortorder`
;


END$$
DELIMITER ;
