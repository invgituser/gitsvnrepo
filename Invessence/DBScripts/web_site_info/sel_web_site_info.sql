DROP PROCEDURE IF EXISTS `invdb`.`sel_web_site_info`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_web_site_info`(
	IN p_url 		VARCHAR(60)
)
BEGIN

	SELECT 
		`web_site_info`.`url`,
		`web_site_info`.`name`,
		`web_site_info`.`status`,
		`web_site_info`.`value`,
		`web_site_info`.`encrFlag`
	FROM `invdb`.`web_site_info`
    WHERE `web_site_info`.`url` = `p_url`;


END$$
DELIMITER ;
