DROP PROCEDURE IF EXISTS `invdb`.`sel_sec_primeasset_group`;

DELIMITER $$

CREATE PROCEDURE `invdb`.`sel_sec_primeasset_group` (
	IN p_theme VARCHAR(40)
    )
BEGIN

	IF (p_theme is null or p_theme = '')
		THEN
            SELECT 
				IFNULL(`sec_prime`.`theme`, 'Invessence') AS `theme`,
                `user_basket_access`.`tradeCurrency`,
				`sec_prime`.`assetclass` AS `assetclass`,
				`sec_prime`.`ticker` AS `ticker`,
				`sec_prime`.`status` AS `status`,
				IFNULL(`sec_prime`.`lowerBound`, 0) AS `lowerBound`,
				IFNULL(`sec_prime`.`upperBound`, 1) AS `upperBound`,
				NULL AS `expenseRatio`,
				`sec_prime`.`expectedReturn` AS `expectedReturn`,
				NULL AS `adv3months`,
				NULL AS `aum`,
				NULL AS `beta`,
				NULL AS `securityRiskSTD`,
				NULL AS `yield`,
				IFNULL(`sec_prime`.`sortorder`, 999999) AS `sortorder`
			FROM
				(`sec_prime_asset_group` `sec_prime`
				JOIN `user_basket_access`)
			WHERE
				((`user_basket_access`.`theme` = `sec_prime`.`theme`)
					AND (`user_basket_access`.`status` = 'A')
					AND (`sec_prime`.`status` = 'A'))
			ORDER BY `sec_prime`.`theme` , IFNULL(`sec_prime`.`sortorder`, 999999);

		ELSE
			SELECT 
				IFNULL(`sec_prime`.`theme`, 'Invessence') AS `theme`,
                `user_basket_access`.`tradeCurrency`,
				`sec_prime`.`assetclass` AS `assetclass`,
				`sec_prime`.`ticker` AS `ticker`,
				`sec_prime`.`status` AS `status`,
				IFNULL(`sec_prime`.`lowerBound`, 0) AS `lowerBound`,
				IFNULL(`sec_prime`.`upperBound`, 1) AS `upperBound`,
				NULL AS `expenseRatio`,
				`sec_prime`.`expectedReturn` AS `expectedReturn`,
				NULL AS `adv3months`,
				NULL AS `aum`,
				NULL AS `beta`,
				NULL AS `securityRiskSTD`,
				NULL AS `yield`,
				IFNULL(`sec_prime`.`sortorder`, 999999) AS `sortorder`
			FROM
				(`sec_prime_asset_group` `sec_prime`
				JOIN `user_basket_access`)
			WHERE `user_basket_access`.`theme` = `sec_prime`.`theme`
			AND	`user_basket_access`.`status` = 'A'
			AND `sec_prime`.`status` = 'A'
            AND `user_basket_access`.`theme` = p_theme
			ORDER BY `sec_prime`.`theme` , IFNULL(`sec_prime`.`sortorder`, 999999);

	END IF;

END$$
DELIMITER ;

