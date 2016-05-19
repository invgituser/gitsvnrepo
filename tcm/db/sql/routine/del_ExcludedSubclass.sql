DROP PROCEDURE IF EXISTS `del_ExcludedSubclass`;

DELIMITER $$
CREATE PROCEDURE `del_ExcludedSubclass`(
	IN p_acctnum          bigint(20)
)
BEGIN

DELETE
FROM `user_exclude_subclass`
WHERE `user_exclude_subclass`.`acctnum` = p_acctnum;

END$$
DELIMITER ;