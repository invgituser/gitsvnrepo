DROP TABLE IF EXISTS `rebalance_trade`;

CREATE TABLE `rebalance_trade` (
  `advisor` varchar(20) NOT NULL,
  `clientAccountID` varchar(20) NOT NULL,
  `acctnum` bigint(20) NOT NULL,
  `processed` varchar(1) DEFAULT NULL,
  `ticker` varchar(25) DEFAULT NULL,
  `assetclass` varchar(40) DEFAULT NULL,
  `subclass` varchar(40) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `curQty` int(11) DEFAULT NULL,
  `curPrice` double DEFAULT NULL,
  `curValue` double DEFAULT NULL,
  `holdingTicker` varchar(25) DEFAULT NULL,
  `holdingQty` int(11) DEFAULT NULL,
  `holdingPrice` double DEFAULT NULL,
  `holdingValue` double DEFAULT NULL,
  `newQty` int(11) DEFAULT NULL,
  `newValue` double DEFAULT NULL,
  `tradetype` varchar(20) DEFAULT NULL,
  `reason` varchar(40) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  KEY `advisor` (`advisor`,`clientAccountID`,`ticker`),
  KEY `acctnum` (`acctnum`,`processed`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
