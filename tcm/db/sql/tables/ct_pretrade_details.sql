DROP TABLE IF EXISTS `pretrade_details`;

CREATE TABLE `pretrade_details` (
  `acctnum` bigint(20) NOT NULL,
  `clientAccountID` varchar(20) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `tradedate` varchar(10) NOT NULL,
  `ticker` varchar(20) NOT NULL,
  `accttype` varchar(40) DEFAULT NULL,
  `currentqty` int(11) DEFAULT 0,
  `costBasisPrice`   double DEFAULT 0,
  `costBasisMoney` double DEFAULT 0,
  `currentValue` double DEFAULT 0,
  `pnl` double DEFAULT 0,
  `newqty` int(11) DEFAULT 0,
  `newValue` double DEFAULT 0,
  `tradeqty` int(11) DEFAULT 0,
  `adjustedQty` int(11) DEFAULT NULL,
  `tradeprice`    double DEFAULT 0,
  `tradeValue` double DEFAULT 0,
  `priceperShare` double DEFAULT 0,
  `realizedPnL` double DEFAULT 0,
  `tradedPnL` double DEFAULT 0,
  `runningCashBal` double DEFAULT 0,
  `percentAllocated` double DEFAULT 0,
  `sortOrder` int(11) DEFAULT 99,
  PRIMARY KEY (`acctnum`,`ticker`,`tradedate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
