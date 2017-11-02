DROP PROCEDURE IF EXISTS `invdb`.`sel_sec_assetclass_group`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_sec_assetclass_group` (
  IN p_theme VARCHAR(40)
)
BEGIN

	IF (p_theme is null or p_theme = '')
		THEN
			SELECT 
				`sec_assetclass_group`.`theme` AS `theme`,
				`user_basket_access`.`tradeCurrency`,
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
				`sec_assetclass_group`.`endAllocation` AS `endAllocation`
			FROM
				(`sec_assetclass_group`
				JOIN `user_basket_access`)
			WHERE
				`user_basket_access`.`theme` = `sec_assetclass_group`.`theme`
			AND `user_basket_access`.`status` = 'A'
			AND `sec_assetclass_group`.`status` = 'A'
			ORDER BY `sec_assetclass_group`.`theme` , `sec_assetclass_group`.`sortorder`;
		ELSE 
			SELECT 
				`sec_assetclass_group`.`theme` AS `theme`,
                `user_basket_access`.`tradeCurrency`,
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
				`sec_assetclass_group`.`endAllocation` AS `endAllocation`
			FROM
				(`sec_assetclass_group`
				JOIN `user_basket_access`)
			WHERE
				`user_basket_access`.`theme` = `sec_assetclass_group`.`theme`
			AND `user_basket_access`.`status` = 'A'
			AND `sec_assetclass_group`.`status` = 'A'
            AND `user_basket_access`.`theme` = p_theme
			ORDER BY `sec_assetclass_group`.`theme` , `sec_assetclass_group`.`sortorder`;
	END IF;

END $$

DELIMITER ;
