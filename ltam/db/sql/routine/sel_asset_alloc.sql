DROP PROCEDURE IF EXISTS `sel_asset_alloc`;

DELIMITER $$
CREATE PROCEDURE `sel_asset_alloc`(
	IN p_acctnum          bigint(20)
)
BEGIN
	DECLARE t_lastYear integer;
	DECLARE t_assetModel VARCHAR(1);

	begin
		SELECT MAX(`asset_alloc`.`assetyear`)
		INTO t_lastYear
		FROM `asset_alloc`
		WHERE `asset_alloc`.`acctnum` = p_acctnum;
	end;

	begin
		SELECT MAX(`asset_alloc`.`allocationmodel`)
		INTO t_assetModel
		FROM `asset_alloc`
		WHERE `asset_alloc`.`acctnum` = p_acctnum
		AND   `asset_alloc`.`assetyear` = IFNULL(t_lastYear,0)
		;
	end;

		

SELECT
	`asset_alloc`.`acctnum`,
	`asset_alloc`.`assetclass`,
	`asset_alloc`.`themecode`,
	`asset_alloc`.`allocationmodel`,
	`asset_alloc`.`assetyear`,
	`asset_alloc`.`active`,
	`asset_alloc`.`sortnum`,
	`asset_alloc`.`weight`,
	`asset_alloc`.`created`,
	`asset_alloc`.`lastupdated`
FROM `asset_alloc`
WHERE `asset_alloc`.`acctnum` = p_acctnum
AND `asset_alloc`.`assetyear` = IFNULL(t_lastYear,0)
AND `asset_alloc`.`allocationmodel` = IFNULL(t_assetModel,'D');

END$$
DELIMITER ;