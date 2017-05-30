DROP TABLE IF EXISTS `invdb`.`investment_model`;

CREATE TABLE `invdb`.`investment_model` (
  `modeltype` VARCHAR(20),
  `name`	  VARCHAR(20),
  `template`  VARCHAR(80),
  PRIMARY KEY (`modeltype`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('PREDEFINED', 'MODEL', 'sec_fixedmodel');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('PREDEFINED', 'ASSET', 'sec_fixedmodel_asset');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('PREDEFINED', 'SUBASSET', 'sec_fixedmodel_subasset');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('PREDEFINED', 'PROJECTION', 'sec_fixed_projectionchart');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('PREDEFINED', 'PERFORMANCE', 'sec_fixed_performancechart');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('OPTIMIZED', 'ASSET', 'sec_assetclass_group');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('OPTIMIZED', 'PRIMEASSET', 'sec_prime_asset_group');
INSERT INTO `invdb`.`investment_model` (`modeltype`, `name`, `template`) VALUES ('OPTIMIZED', 'RBSA', 'sec_rbsa');


DROP PROCEDURE IF EXISTS `invdb`.`sel_investmentmodel_template`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_investmentmodel_template`(
	IN p_modeltype VARCHAR(20)
)
BEGIN

SELECT
  `modeltype`,
  `name`,
  `template`
FROM `invdb`.`investment_model`
WHERE `investment_model`.`modeltype` = p_modeltype
;
	

END$$
DELIMITER ;



