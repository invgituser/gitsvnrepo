DROP TABLE `realized_unrealized`;

CREATE TABLE `realized_unrealized` (
  `clientAccountID`		varchar(20) NOT NULL,
  `Symbol`				varchar(20) NOT NULL,
  `reportDate`			varchar(10) NOT NULL,
  `RealizedSTProfit` 	double,
  `RealizedSTLoss`	 	double,
  `RealizedLTProfit` 	double,
  `RealizedLTLoss`	 	double,
  `TotalRealizedPnl` 	double,
  `UnrealizedProfit` 	double,
  `UnrealizedLoss`		double,
  `UnrealizedSTProfit`	double,
  `UnrealizedSTLoss`	double,
  `UnrealizedLTProfit`	double,
  `UnrealizedLTLoss`	double,
  `TotalUnrealizedPnl`	double,
  `TotalFifoPnl`		double,
   PRIMARY KEY (`clientAccountID`, `Symbol`,`reportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
