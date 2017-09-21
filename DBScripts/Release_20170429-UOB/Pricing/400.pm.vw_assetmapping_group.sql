CREATE OR REPLACE
VIEW `invdb`.`vw_assetmapping_group` AS
    SELECT 
        `sec_assetclass_group`.`theme` AS `theme`,
        `sec_assetclass_group`.`status` AS `status`,
        `sec_assetclass_group`.`assetclass` AS `assetclass`,
        `sec_assetclass_group`.`displayName` AS `displayName`,
        `sec_assetclass_group`.`ticker` AS `ticker`,
        `sec_assetclass_group`.`sortorder` AS `sortorder`,
        `sec_assetclass_group`.`lowerBound` AS `lowerBound`,
        `sec_assetclass_group`.`upperBound` AS `upperBound`,
        `sec_assetclass_group`.`color` AS `color`,
        `sec_assetclass_group`.`averageReturn` AS `averageReturn`,
        `sec_assetclass_group`.`riskAdjustment` AS `riskAdjustment`,
        `sec_assetclass_group`.`endAllocation` AS `endAllocation`,
        `sec_assetclass_group`.`created` AS `created`,
        `sec_assetclass_group`.`lastupdated` AS `lastupdated`
    FROM
        `invdb`.`sec_assetclass_group`,
        `invdb`.`user_basket_access`
	WHERE `user_basket_access`.`theme` = `sec_assetclass_group`.`theme`
    AND   `user_basket_access`.`status` IN ('A')
    AND   `sec_assetclass_group`.`status` IN ('A')
    ORDER BY `sec_assetclass_group`.`theme` , `sec_assetclass_group`.`sortorder`;
