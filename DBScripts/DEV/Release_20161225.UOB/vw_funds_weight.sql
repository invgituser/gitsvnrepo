CREATE or replace
VIEW `vw_funds_weights` AS
    SELECT 
        `sec_rbsa`.`ticker` AS `ticker`,
        `sec_prime_asset_group`.`ticker` AS `indexfund`,
        `sec_prime_asset_group`.`theme` AS `theme`,
        `sec_prime_asset_group`.`assetclass` AS `assetclass`,
        `sec_prime_asset_group`.`ticker` AS `primeassetclass`,
        `sec_prime_asset_group`.`sortorder` AS `sortorder`,
        `sec_prime_asset_group`.`lowerBound` AS `lowerBound`,
        `sec_prime_asset_group`.`upperBound` AS `upperBound`,
        `sec_prime_asset_group`.`expectedReturn` AS `expectedReturn`,
        `sec_rbsa`.`weight` AS `weight`
    FROM
        ((`sec_prime_asset_group`
        JOIN `sec_rbsa`))
    WHERE
        ((`sec_prime_asset_group`.`theme` = `sec_rbsa`.`theme`)
            AND (`sec_prime_asset_group`.`ticker` = `sec_rbsa`.`primeasset_ticker`)
            AND (`sec_prime_asset_group`.`status` = 'A'));
