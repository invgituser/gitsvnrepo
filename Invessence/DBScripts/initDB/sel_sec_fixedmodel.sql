DROP PROCEDURE IF EXISTS `sel_sec_fixedmodel`;

DELIMITER $$
CREATE PROCEDURE `sel_sec_fixedmodel`(
)
BEGIN

SELECT
	`sec_fixedmodel`.`theme`,
    `sec_fixedmodel`.`level`,
    `sec_fixedmodel`.`sortorder`,
    `sec_fixedmodel`.`status`,
    `sec_fixedmodel`.`displayname`,
    `sec_fixedmodel`.`description`,
    `sec_fixedmodel`.`lowRisk`,
    `sec_fixedmodel`.`highRisk`,
    `sec_fixedmodel`.`expectedreturn`,
    `sec_fixedmodel`.`expectedrisk`
FROM `sec_fixedmodel`
WHERE `sec_fixedmodel`.`status` = 'A'
ORDER BY 
	`sec_fixedmodel`.`theme`, `sec_fixedmodel`.`sortorder`;

END$$
DELIMITER ;
