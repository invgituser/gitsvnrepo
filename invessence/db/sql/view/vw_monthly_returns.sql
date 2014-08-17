
/***  Do not use  ****
  Replaced with vw_daily_returns
-------
delimiter $$

DROP VIEW `vw_monthly_returns` $$

CREATE VIEW `vw_monthly_returns` AS
    select 
        `monthly_returns`.`ticker` AS `ticker`,
        DATE_FORMAT(`monthly_returns`.`businessdate`, '%Y%m%d') AS `seqno`,
        `monthly_returns`.`monthly_return` AS `monthly_return`
    from
        `monthly_returns`
    order by `monthly_returns`.`ticker` , `monthly_returns`.`businessdate`
$$
-------
*/