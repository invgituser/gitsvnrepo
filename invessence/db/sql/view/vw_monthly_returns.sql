DROP VIEW IF EXISTS `vw_monthly_returns`;

CREATE 
VIEW `vw_monthly_returns` AS
    select 
        `invdb`.`sec_daily_info`.`instrumentid` AS `instrumentid`,
        `invdb`.`sec_daily_info`.`ticker` AS `ticker`,
        date_format(`invdb`.`sec_daily_info`.`businessdate`,
                '%Y-%m-%d') AS `date`,
        `invdb`.`sec_daily_info`.`daily_return` AS `daily_return`,
		`invdb`.`sec_daily_info`.`monthly_return` as `monthly_return`,
        `invdb`.`sec_prime_asset_group`.`sortorder` AS `sortorder`
    from
        ((`invdb`.`sec_daily_info`
        join `invdb`.`sec_prime_asset_group`)
        join `invdb`.`inv_monthly_date_table` `mdt`)
    where
        ((`invdb`.`sec_daily_info`.`daily_return` is not null)
            and (`invdb`.`sec_daily_info`.`ticker` = `invdb`.`sec_prime_asset_group`.`ticker`)
            and (`invdb`.`sec_prime_asset_group`.`theme` = 'PRIME-ASSET')
            and (`invdb`.`sec_prime_asset_group`.`status` = 'A')
            and (convert( date_format(`invdb`.`sec_daily_info`.`businessdate`,
                '%Y-%m-%d') using utf8) = `mdt`.`last_businessdate`));
