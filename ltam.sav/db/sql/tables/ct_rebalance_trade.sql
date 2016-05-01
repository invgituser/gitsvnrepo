DROP TABLE IF EXISTS `rebalance_trade`;

CREATE TABLE `rebalance_trade` (
   clientAccountID VARCHAR(20),
   acctnum LONG,
   processed VARCHAR(1),
   ticker VARCHAR(25),
   curQty INTEGER,
   curPrice  DOUBLE,
   curValue  DOUBLE,
   holdingTicker VARCHAR(25),
   holdingQty INTEGER,
   holdingPrice DOUBLE,
   holdingValue DOUBLE,
   holdingWeight DOUBLE,
   holdingCostBasis DOUBLE,
   allocTicker VARCHAR(25),
   allocQty INTEGER,
   allocPrice DOUBLE,
   allocValue DOUBLE,
   allocWeight DOUBLE,
   created	DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
