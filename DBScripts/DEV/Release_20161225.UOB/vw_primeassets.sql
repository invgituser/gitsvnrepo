CREATE or REPLACE
VIEW `vw_primeassets` AS
    SELECT 
		IFNULL(`sec_prime`.`theme`, 'Invessence') AS `theme`,
		`sec_prime`.`assetclass`AS `assetclass`,
		`sec_prime`.`ticker` AS `ticker`,
		`sec_prime`.`status` AS `status`,
		IFNULL(`sec_prime`.`lowerBound`,0) AS `lowerBound`,
		IFNULL(`sec_prime`.`upperBound`,1) AS `upperBound`,
		`sec_prime`.`expectedReturn` AS `expectedReturn`,
		`sec_prime`.`risk` AS `securityRiskSTD`,
		`sec_prime`.`yield` AS `yield`,
		IFNULL(`sec_prime`.`sortorder`, 999999) AS `sortorder`
    FROM `sec_prime_asset_group` `sec_prime`
    WHERE `sec_prime`.`status` = 'A'
    ORDER BY IFNULL(`sec_prime`.`theme`, 'Invessence'), IFNULL(`sec_prime`.`sortorder`, 999999);
