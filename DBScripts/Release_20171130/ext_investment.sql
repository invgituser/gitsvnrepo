DROP TABLE IF EXISTS `invdb`.`ext_investment`;

CREATE TABLE `invdb`.`ext_investment` (
  `acctnum` bigint(20) DEFAULT NULL,
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `ticker` varchar(20) NOT NULL DEFAULT 'Cash',
  `investmentDate` varchar(10) NOT NULL,
  `investmentCurrency` varchar(3) DEFAULT 'USD',
  `netAmount` double DEFAULT NULL,
  `comment` varchar(120) DEFAULT NULL,
  `fxRateToBase` double DEFAULT '1',
  `baseCurrency` varchar(3) DEFAULT 'USD',
  `convertedNetAmount` double DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`clientAccountID`,`investmentDate`, `ticker`),
  KEY `AK1_ext_transaction` (`acctnum`, `investmentDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
