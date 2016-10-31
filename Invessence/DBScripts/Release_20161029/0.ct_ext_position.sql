DROP TABLE IF EXISTS `invdb`.`ext_position` ;

CREATE TABLE `invdb`.`ext_position` (
  `acctnum` bigint(20) NOT NULL,
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `currencyPrimary` varchar(20) DEFAULT 'USD',
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(12) NOT NULL,
  `reportDate` varchar(8) NOT NULL DEFAULT '',
  `purchaseDate` varchar(8) DEFAULT NULL,
  `side` varchar(6) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `costBasisPrice` double DEFAULT NULL,
  `costBasisMoney` double DEFAULT NULL,
  `markPrice` double DEFAULT NULL,
  `positionValue` double DEFAULT NULL,
  `pnlUnrealized` double DEFAULT NULL,
  `levelOfDetail` varchar(40) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`, `reportDate`,`symbol`, `purchaseDate`,`costBasisPrice`),
  KEY `AK1_ext_position` (`acctnum`, `reportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
