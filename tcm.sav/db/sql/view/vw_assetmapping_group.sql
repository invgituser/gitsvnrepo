DROP VIEW IF EXISTS `vw_assetmapping_group`;

CREATE 
VIEW `vw_assetmapping_group` AS
SELECT `asset_mapping_group`.`groupname`,
    `asset_mapping_group`.`classname` as `assetclass`,
    `asset_mapping_group`.`parent`,
    `asset_mapping_group`.`description`,
    `asset_mapping_group`.`assetLevel`,
    `asset_mapping_group`.`sortOrder`,
    `asset_mapping_group`.`indexFund`,
    `asset_mapping_group`.`lowerBound`,
    `asset_mapping_group`.`upperBound`,
    `asset_mapping_group`.`color`,
    `asset_mapping_group`.`averageReturn`,
    `asset_mapping_group`.`riskAdjustment`,
    `asset_mapping_group`.`endAllocation`
FROM `asset_mapping_group`
where
	(`asset_mapping_group`.`assetLevel` = 1)
order by `asset_mapping_group`.`groupname`,
		`asset_mapping_group`.`assetLevel`,
		`asset_mapping_group`.`sortorder`;
