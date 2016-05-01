delimiter $$
DROP TABLE `monthly_returns`$$

CREATE TABLE `monthly_returns` (
  `instrumentid` bigint(20) NOT NULL,
  `businessdate` date NOT NULL,
  `ticker` varchar(20),
  `open`         double,
  `close`        double,
  `monthly_return` double,
  PRIMARY KEY (`instrumentid`,`businessdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

