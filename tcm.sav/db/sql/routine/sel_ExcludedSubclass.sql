DROP PROCEDURE IF EXISTS `sel_ExcludedSubclass`;

DELIMITER $$
CREATE PROCEDURE `sel_ExcludedSubclass`(
	IN p_acctnum          bigint(20)
)
BEGIN

SELECT 
	`user_exclude_subclass`.`acctnum`,
    `user_exclude_subclass`.`assetclass`,
    `user_exclude_subclass`.`subclass`,
    `user_exclude_subclass`.`created`,
    `user_exclude_subclass`.`lastUpdated`
FROM `user_exclude_subclass`
WHERE `user_exclude_subclass`.`acctnum` = p_acctnum;

END$$
DELIMITER ;