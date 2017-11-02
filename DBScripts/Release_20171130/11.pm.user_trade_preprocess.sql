DROP TABLE IF EXISTS `invdb`.`rebalance_trade`;
DROP TABLE IF EXISTS `invdb`.`user_trade_preprocess`;

CREATE TABLE `invdb`.`user_trade_preprocess` (
  `advisor` varchar(20) NOT NULL,
  `clientAccountID` varchar(20) NOT NULL,
  `acctnum` bigint(20) NOT NULL,
  `processed` varchar(1) DEFAULT NULL,
  `tradeDate` varchar(8) DEFAULT NULL,
  `tradeCurrency` varchar(3) DEFAULT 'USD',
  `ticker` varchar(25) DEFAULT NULL,
  `assetclass` varchar(40) DEFAULT NULL,
  `subclass` varchar(40) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `curQty` double DEFAULT NULL,
  `curPrice` double DEFAULT NULL,
  `curValue` double DEFAULT NULL,
  `holdingTicker` varchar(25) DEFAULT NULL,
  `holdingQty` int(11) DEFAULT NULL,
  `holdingPrice` double DEFAULT NULL,
  `holdingValue` double DEFAULT NULL,
  `newQty` int(11) DEFAULT NULL,
  `newValue` double DEFAULT NULL,
  `tradetype` varchar(20) DEFAULT NULL,
  `exchangeRate` Double DEFAULT 1.0,
  `settleCurrency` varchar(3) DEFAULT 'USD',
  `settleQty` double DEFAULT NULL,
  `settlePrice` double DEFAULT NULL,
  `settleValue` double DEFAULT NULL,
  `reason` varchar(40) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  KEY `advisor` (`advisor`,`clientAccountID`,`tradeDate`, `ticker`,`tradeCurrency`),
  KEY `acctnum` (`acctnum`,`processed`, `tradeDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
