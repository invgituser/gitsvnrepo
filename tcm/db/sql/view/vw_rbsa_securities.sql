CREATE 
VIEW `vw_rbsa_securities` AS
    select 
        ifnull(`sec_theme`.`theme`, 'Unused') AS `theme`,
        `sec_master`.`status` AS `status`,
        `sec_master`.`ticker` AS `ticker`,
        `sec_master`.`cusip` AS `cusip`,
        `sec_master`.`isin` AS `isin`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`assetclass` AS `assetclass`,
        `sec_rbsa`.`primeassetclass` AS `primeassetclass`,
        `sec_master`.`subclass` AS `subclass`,
        `sec_master`.`expenseRatio` AS `expenseRatio`,
        `sec_master`.`nontaxableReturn` AS `nontaxableReturn`,
        `sec_master`.`adv3months` AS `adv3months`,
        `sec_master`.`aum` AS `aum`,
        `sec_master`.`beta` AS `beta`,
        `sec_master`.`securityRiskSTD` AS `securityRiskSTD`,
        `sec_master`.`yield` AS `yield`,
        (case
            when (ucase(`sec_master`.`ticker`) = ucase('Cash')) then 1.00
            else ifnull(`sd`.`close_price`, 0.00)
        end) AS `price`,
        ifnull(`sec_rbsa`.`weight`, 1.0) AS `rbsaweight`,
        0 AS `sortorder`
    from
        (((`sec_theme`
        join `sec_master`)
        join `sec_rbsa`)
        join `sec_daily_info` `sd`)
    where
        ((`sec_theme`.`ticker` = `sec_master`.`ticker`)
            and (`sec_master`.`ticker` = `sec_rbsa`.`ticker`)
            and (`sec_master`.`ticker` = `sd`.`ticker`)
            and (date_format(`sd`.`businessdate`, '%Y%m%d') = funct_get_switch('PRICE_DATE'))
            and (`sec_theme`.`status` = 'A')
            and (`sec_master`.`status` = 'A'));
