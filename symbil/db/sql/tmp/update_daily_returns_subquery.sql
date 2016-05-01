select * from daily_returns;

truncate table daily_returns;

CREATE TABLE `daily_returns` (
  `instrumentid` bigint(20) NOT NULL,
  `businessdate` date NOT NULL,
  `ticker` varchar(20) DEFAULT NULL,
  `prev_businessdate` date DEFAULT NULL,
  `prev_close` double DEFAULT NULL,
  `current_close` double DEFAULT NULL,
  `daily_return` double DEFAULT '0',
  PRIMARY KEY (`instrumentid`,`businessdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `invdb`.`daily_returns`
(`instrumentid`,
`businessdate`,
`ticker`,
`prev_businessdate`,
`prev_close`,
`current_close`,
`daily_return`)
select 
`instrumentid`,
`businessdate`,
`ticker`,
`prev_businessdate`,
null,
`close_price`,
null
from sec_daily_info

update daily_returns
set prev_close = (select s1.close_price
				from sec_daily_info s1
				where s1.instrumentid = daily_returns.instrumentid
				and s1.businessdate = daily_returns.prev_businessdate)


update daily_returns
set daily_return = ln (current_close/prev_close);