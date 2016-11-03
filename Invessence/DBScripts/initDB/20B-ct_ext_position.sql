CREATE TABLE `ext_position` (
  `acctnum` bigint(20) NOT NULL,
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `currencyPrimary` varchar(20) DEFAULT 'USD',
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(10) NOT NULL,
  `reportDate` varchar(8) NOT NULL DEFAULT '',
  `side` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `costBasisPrice` double DEFAULT NULL,
  `costBasisMoney` double DEFAULT NULL,
  `markPrice` double DEFAULT NULL,
  `positionValue` double DEFAULT NULL,
  `pnlUnrealized` double DEFAULT NULL,
  `levelOfDetail` varchar(40) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`reportDate`,`symbol`),
  KEY `AK1_ext_position` (`clientAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
