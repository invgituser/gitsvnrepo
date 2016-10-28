DROP TABLE IF EXISTS `user_reports`;

CREATE TABLE `user_reports` (
  `acctnum` BIGINT(20) NOT NULL,
  `reportDate` varchar(8) NOT NULL,
  `reportName` varchar(20) NOT NULL,
  `filename`   varchar(255) NOT NULL,
  `created`    date,
  PRIMARY KEY (`acctnum`, `reportDate`, `reportName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
