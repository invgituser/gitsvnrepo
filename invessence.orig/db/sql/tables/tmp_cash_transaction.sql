DROP TABLE IF EXISTS `tmp_cash_transaction`;

CREATE TABLE `tmp_cash_transaction` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `currencyPrimary` varchar(3) DEFAULT NULL,
  `assetClass` varchar(10) DEFAULT NULL,
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(80) NULL DEFAULT '',
  `reportDate` varchar(10) NOT NULL DEFAULT '',
  `amount` Double DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `tradeID` varchar(20) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  PRIMARY KEY(`clientAccountID`,`type`,`reportDate`,`tradeID`,`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
