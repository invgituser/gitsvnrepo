CREATE OR REPLACE
VIEW `invdb`.`vw_monthly_returns` AS
    SELECT 
        null AS `instrumentid`,
        `rbsa_daily`.`ticker` AS `ticker`,
        DATE_FORMAT(`rbsa_daily`.`businessdate`,
                '%Y-%m-%d') AS `date`,
        `rbsa_daily`.`daily_return` AS `daily_return`,
        `rbsa_daily`.`monthly_return` AS `monthly_return`,
        `sec_prime_asset_group`.`sortorder` AS `sortorder`
    FROM
        ((`rbsa`.`rbsa_daily`
        JOIN `sec_prime_asset_group`)
        JOIN `inv_monthly_date_table` `mdt`)
    WHERE
        ((`rbsa_daily`.`daily_return` IS NOT NULL)
            AND (`rbsa_daily`.`ticker` = `sec_prime_asset_group`.`ticker`)
            AND (`sec_prime_asset_group`.`theme` = 'PRIME-ASSET')
            AND (`sec_prime_asset_group`.`status` = 'A')
            AND (CONVERT( DATE_FORMAT(`rbsa_daily`.`businessdate`,
                '%Y-%m-%d') USING UTF8) = `mdt`.`last_businessdate`));
