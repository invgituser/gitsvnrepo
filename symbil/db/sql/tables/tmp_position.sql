DROP TABLE IF EXISTS `tmp_position`;

CREATE TABLE `tmp_position` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `accountAlias` varchar(15) DEFAULT NULL,
  `currencyPrimary` varchar(3) DEFAULT NULL,
  `assetClass` varchar(10) DEFAULT NULL,
  `fxRateToBase` double DEFAULT NULL,
  `symbol` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(60) DEFAULT NULL,
  `reportDate` varchar(8) NOT NULL DEFAULT '',
  `side` varchar(6) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `costBasisPrice` double DEFAULT NULL,
  `CostBasisMoney` double DEFAULT NULL,
  `markPrice` double DEFAULT NULL,
  `positionValue` double DEFAULT NULL,
  `fifoPnlUnrealized` double DEFAULT NULL,
  `LevelOfDetail` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`, `reportDate`, `symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;