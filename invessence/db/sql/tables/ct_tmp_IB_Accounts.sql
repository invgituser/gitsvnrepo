DROP TABLE IF EXISTS tmp_IB_Accounts;

CREATE TABLE `tmp_IB_Accounts` (
  `clientAccountID` varchar(15) NOT NULL,
  `accountAlias` varchar(20) DEFAULT NULL,
  `currencyPrimary` varchar(5) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `accountType` varchar(40) DEFAULT NULL,
  `customerType` varchar(40) DEFAULT NULL,
  `accountCapabilities` varchar(40) DEFAULT NULL,
  `tradingPermissions` varchar(80) DEFAULT NULL,
  `dateOpened` varchar(12) DEFAULT NULL,
  `dateClosed` varchar(40) DEFAULT NULL,
  `street` varchar(80) DEFAULT NULL,
  `street2` varchar(80) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `country` varchar(15) DEFAULT NULL,
  `postalCode` varchar(12) DEFAULT NULL,
  `emailPrimary` varchar(40) DEFAULT NULL,
  `emailSecondary` varchar(40) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `altPhoneNo` varchar(20) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'P',
  PRIMARY KEY (`clientAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
