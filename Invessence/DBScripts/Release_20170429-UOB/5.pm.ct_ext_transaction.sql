DROP TABLE IF EXISTS `ext_transaction`;

CREATE TABLE `ext_transaction` (
  `acctnum` bigint(20) DEFAULT NULL,
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `ticker` varchar(10) NOT NULL DEFAULT '',
  `confirmNumber` varchar(12) NOT NULL DEFAULT '',
  `transactionSource` varchar(30) NOT NULL DEFAULT '',
  `transactionType` varchar(50) NOT NULL DEFAULT '',
  `transactionStatus` varchar(15) NOT NULL DEFAULT '',
  `controlNumber` varchar(60) DEFAULT NULL,
  `quantity` varchar(15) DEFAULT NULL,
  `price` varchar(15) DEFAULT NULL,
  `netAmount` varchar(15) DEFAULT NULL,
  `commission` varchar(15) DEFAULT NULL,
  `otherFees` varchar(15) DEFAULT NULL,
  `tradeDate` varchar(10) NOT NULL,
  `settDate` varchar(10) DEFAULT NULL,
  `voidDate` varchar(10) DEFAULT NULL,
  `comment` varchar(120) DEFAULT NULL,
  `currencyPrimary` varchar(3) DEFAULT 'USD',
  `fxRateToBase`	DOUBLE DEFAULT 1.0,
  `created` datetime NOT NULL,
  PRIMARY KEY (`clientAccountID`,`ticker`,`transactionType`,`tradeDate`,`confirmNumber`),
  KEY `AK1_ext_transaction` (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
