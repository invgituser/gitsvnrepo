DROP TABLE `tmp_realized_unrealized`;

CREATE TABLE `tmp_realized_unrealized` (
  `clientAccountID`		varchar(20) NOT NULL,
  `Symbol`				varchar(20) NOT NULL,
  `reportDate`			varchar(10),
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
   PRIMARY KEY (`clientAccountID`, `Symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
