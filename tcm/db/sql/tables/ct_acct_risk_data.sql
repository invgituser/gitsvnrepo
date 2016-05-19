DROP TABLE `acct_risk_data`;

CREATE TABLE `acct_risk_data` (
  `acctnum`  bigint(20)  NOT NULL,
  `atttributename` varchar(45) NOT NULL,
  `charvalue` varchar(60) DEFAULT NULL,
  `numvalue` double(21,8) DEFAULT NULL,
  `datevalue` date DEFAULT NULL,
  PRIMARY KEY (`acctnum`, `atttributename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;
