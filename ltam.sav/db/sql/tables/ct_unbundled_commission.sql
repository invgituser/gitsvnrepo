DROP TABLE IF EXISTS `unbundld_commission`;

CREATE TABLE `unbundld_commission` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `currencyPrimary` varchar(3) DEFAULT NULL,
  `assetClass` varchar(10) DEFAULT NULL,
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(80) NULL DEFAULT '',
  `reportDate` varchar(10) NOT NULL DEFAULT '',
  `reportTime` varchar(10) DEFAULT NULL,
  `exchange` varchar(20) DEFAULT NULL,
  `buy_sell` varchar(8) DEFAULT NULL,
  `quantity` Integer DEFAULT NULL,
  `price` Double DEFAULT NULL,
  `tradeID` varchar(20) DEFAULT NULL,
  `totalcommission` Double DEFAULT NULL,
  `brokerExecutionCharge` Double DEFAULT NULL,
  `brokerClearingCharge` Double DEFAULT NULL,
  `thirdPartyExecutionCharge` Double DEFAULT NULL,
  `thirdPartyRegulatoryCharge` Double DEFAULT NULL,
  `other` Double DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`, `symbol`, `reportDate`, `reportTime`, `tradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

