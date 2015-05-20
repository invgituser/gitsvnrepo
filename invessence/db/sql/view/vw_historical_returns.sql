DROP VIEW IF EXISTS `vw_historical_returns`;
 
CREATE 
VIEW `vw_historical_returns` AS
    select 
        `sec_daily_info`.`ticker` AS `indexFund`,
        concat(substring(businessdate,1,4),substring(businessdate,6,2),substring(businessdate,9,2)) AS `seqno`,
        `sec_daily_info`.`daily_return` AS `monthly_return`
    from
        `sec_daily_info`
    where
        (`sec_daily_info`.`ticker` in ('SPY' , 'IEF', 'IAU', 'BIL', 'VEU'))
    order by 1 , 2 desc;
