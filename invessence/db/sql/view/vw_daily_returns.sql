
DROP VIEW IF EXISTS `vw_daily_returns`;

delimiter $$

CREATE VIEW `vw_daily_returns` AS
    select 
        `daily_returns`.`ticker` AS `ticker`,
        DATE_FORMAT(`daily_returns`.`businessdate`, '%Y%m%d') AS `seqno`,
        `daily_returns`.`daily_return` AS `daily_return`
    from `daily_returns`
	WHERE `daily_returns`.`daily_return` is not null
    order by 1, 2 desc
$$

