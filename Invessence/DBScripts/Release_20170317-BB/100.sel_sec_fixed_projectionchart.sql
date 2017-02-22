DROP PROCEDURE IF EXISTS `invdb`.`sel_sec_fixed_projectionChart`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_sec_fixed_projectionChart`()
BEGIN

	SELECT 
		`sfpc`.`theme`,
		`sfpc`.`model`,
		`sfpc`.`year`,
		`sfpc`.`5percent`,
		`sfpc`.`25percent`,
		`sfpc`.`50percent`,
		`sfpc`.`75percent`,
		`sfpc`.`95percent`
	FROM `sec_fixed_projectionchart` `sfpc`
	ORDER BY 1,2,3
	;


END$$
DELIMITER ;
