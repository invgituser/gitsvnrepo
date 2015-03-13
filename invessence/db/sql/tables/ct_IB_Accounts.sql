DROP TABLE `IB_Accounts`;

CREATE TABLE `IB_Accounts` (
  `acctnum` bigint(20) DEFAULT NULL,
  `IB_acctnum` varchar(8) NOT NULL,
  `accountName` varchar(60) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `accountStatus` varchar(20) DEFAULT NULL,
  `dateOpened` varchar(10) DEFAULT NULL,
  `dateClosed` varchar(10) DEFAULT NULL,
  `customerType` varchar(40) DEFAULT NULL,
  `accountCapabilities` varchar(40) DEFAULT NULL,
  `residentState` varchar(2) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`IB_acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

