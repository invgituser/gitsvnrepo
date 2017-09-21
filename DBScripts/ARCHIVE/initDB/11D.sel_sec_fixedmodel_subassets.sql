DROP PROCEDURE IF EXISTS `sel_sec_fixedmodel_subassets`;

DELIMITER $$
CREATE PROCEDURE `sel_sec_fixedmodel_subassets`()
BEGIN

SELECT `sec_fixedmodel_subasset`.`theme`,
    `sec_fixedmodel_subasset`.`level`,
    `sec_fixedmodel_subasset`.`asset`,
    `sec_fixedmodel_subasset`.`keyname`,
    IFNULL(`sec_master`.`subclass`,`sec_fixedmodel_subasset`.`keydescription`) as `keydescription`,
    `sec_fixedmodel_subasset`.`allocation`,
    `sec_fixedmodel_asset`.`color`,
    IFNULL(`sec_master`.`ticker`,`sec_fixedmodel_subasset`.`keyname`) as `ticker`,
    IFNULL(`sec_master`.`name`,`sec_fixedmodel_subasset`.`keydescription`) as `name`,
    `sec_fixedmodel_subasset`.`sortorder`
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
ORDER BY `sec_fixedmodel_subasset`.`theme`,
		 `sec_fixedmodel`.`sortorder`, 
	     `sec_fixedmodel_asset`.`sortorder`, 
         `sec_fixedmodel_subasset`.`sortorder`
;


END$$
DELIMITER ;
