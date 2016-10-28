delimiter $$

DROP PROCEDURE `sp_asset_alloc_sel`
$$

CREATE PROCEDURE `sp_asset_alloc_sel`(
	IN p_acctnum          bigint(20)
)
BEGIN 

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
WHERE `asset_alloc`.`acctnum` = p_acctnum;

END
$$

