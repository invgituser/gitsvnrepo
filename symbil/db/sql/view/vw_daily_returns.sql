DROP VIEW IF EXISTS `vw_daily_returns`;

CREATE 
VIEW `vw_daily_returns` AS
    select 
        `sec_daily_info`.`instrumentid` AS `instrumentid`,
        `sec_daily_info`.`ticker` AS `ticker`,
        date_format(`sec_daily_info`.`businessdate`,
                '%Y%m%d') AS `seqno`,
        `sec_daily_info`.`daily_return` AS `daily_return`,
        `sec_daily_info`.`close_price` AS `close_price`,
        `sec_daily_info`.`prev_close_price` AS `prev_close_price`,
        `sec_daily_info`.`volume` AS `volume`
    from
        `sec_daily_info`
    where
        (`sec_daily_info`.`daily_return` is not null)
    order by `sec_daily_info`.`instrumentid` , date_format(`sec_daily_info`.`businessdate`, '%Y%m%d') desc;
