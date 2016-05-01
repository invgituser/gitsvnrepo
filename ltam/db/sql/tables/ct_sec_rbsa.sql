CREATE TABLE `sec_rbsa` (
  `ticker` varchar(20) NOT NULL,
  `primeassetclass` varchar(30) NOT NULL,
  `weight` double DEFAULT NULL,
  `created` date DEFAULT NULL,
  PRIMARY KEY (`ticker`,`primeassetclass`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
