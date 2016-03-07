DROP VIEW IF exists `vw_historical_returns`;

CREATE 
VIEW `vw_historical_returns` AS
    select 
        `daily_returns`.`ticker` AS `indexFund`,
        date_format(`daily_returns`.`businessdate`, '%Y%m%d') AS `seqno`,
        `daily_returns`.`daily_return` AS `monthly_return`
    from
        `daily_returns`
    where
        (`daily_returns`.`ticker` in ('SPY' , 'IEF', 'IAU', 'BIL', 'VEU'))
    order by 1 , 2 desc;
