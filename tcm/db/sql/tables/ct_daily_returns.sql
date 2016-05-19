delimiter $$
DROP TABLE IF EXISTS `daily_returns`$$

CREATE TABLE `daily_returns` (
  `instrumentid`      bigint(20) NOT NULL,
  `businessdate`      date NOT NULL,
  `ticker`            varchar(20),
  `prev_businessdate` date,
  `prev_close`        double,
  `current_close`     double,
  `daily_return`    double DEFAULT 0,
  PRIMARY KEY (`instrumentid`,`businessdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

