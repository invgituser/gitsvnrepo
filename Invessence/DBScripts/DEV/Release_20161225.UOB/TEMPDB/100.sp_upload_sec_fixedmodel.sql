DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_sec_fixedmodel`;
DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel`;
DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel`()
BEGIN

	DELETE FROM `invdb`.`sec_fixedmodel`
    WHERE theme in (select theme from `temp`.`tmp_sec_fixedmodel`);

	INSERT INTO `invdb`.`sec_fixedmodel`
	(
	`theme`,
	`level`,
	`sortorder`,
	`status`,
	`displayname`,
	`description`,
	`lowRisk`,
	`highRisk`,
	`expectedreturn`,
	`expectedrisk`,
	`created`,
	`updated`
    )
    SELECT `tmp`.`theme`,
    `tmp`.`level`,
    `tmp`.`sortorder`,
    `tmp`.`status`,
    `tmp`.`displayname`,
    `tmp`.`description`,
    `tmp`.`lowRisk`,
    `tmp`.`highRisk`,
    `tmp`.`expectedreturn`,
    `tmp`.`expectedrisk`,
    now(),
    null
	FROM `temp`.`tmp_sec_fixedmodel` `tmp`;

    
END$$
DELIMITER ;
