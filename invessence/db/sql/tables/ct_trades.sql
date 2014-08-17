DROP TABLE IF EXISTS `trades`;

CREATE TABLE `trades` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `currencyPrimary` varchar(3) DEFAULT NULL,
  `assetClass` varchar(10) DEFAULT NULL,
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(80) DEFAULT '',
  `tradeID` varchar(20) NOT NULL DEFAULT '',
  `reportDate` varchar(10) NOT NULL DEFAULT '',
  `quantity` int(11) DEFAULT NULL,
  `tradeprice` double DEFAULT NULL,
  `proceed` double DEFAULT NULL,
  `ibcommission` double DEFAULT NULL,
  `levelofDetail` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`,`symbol`,`reportDate`,`tradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
