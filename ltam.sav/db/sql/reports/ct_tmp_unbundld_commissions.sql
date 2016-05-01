DROP TABLE IF EXISTS `tmp_unbundld_commissions`;

CREATE TABLE `tmp_unbundld_commissions` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `currencyPrimary` varchar(3) DEFAULT NULL,
  `assetClass` varchar(10) DEFAULT NULL,
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(80) DEFAULT '',
  `reportDate` varchar(20) NOT NULL DEFAULT '',
  `exchange` varchar(20) DEFAULT NULL,
  `buy_sell` varchar(8) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `tradeID` varchar(20) NOT NULL DEFAULT '',
  `totalcommission` double DEFAULT NULL,
  `brokerExecutionCharge` double DEFAULT NULL,
  `brokerClearingCharge` double DEFAULT NULL,
  `thirdPartyExecutionCharge` double DEFAULT NULL,
  `thirdPartyRegulatoryCharge` double DEFAULT NULL,
  `other` double DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`,`symbol`,`reportDate`,`tradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
