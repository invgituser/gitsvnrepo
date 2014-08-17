CREATE TABLE `IB_Accounts` (
  `acctnum` bigint(20) NOT NULL,
  `IB_acctnum` varchar(8) NOT NULL,
  `email` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `accountStatus` varchar(20) NOT NULL,
  `dateOpened` varchar(10) DEFAULT NULL,
  `dateClosed` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
