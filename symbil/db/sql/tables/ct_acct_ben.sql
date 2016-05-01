delimiter $$

CREATE TABLE `acct_ben` (
  `acctnum` bigint(20) NOT NULL,
  `benseqnum` tinyint(4) NOT NULL,
  `firstname` varchar(25) DEFAULT NULL,
  `lastname` varchar(25) DEFAULT NULL,
  `ssn` varchar(9) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `percent` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`benseqnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$
