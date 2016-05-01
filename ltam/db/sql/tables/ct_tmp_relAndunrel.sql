DROP TABLE IF EXISTS `tmp_relAndunrel`;

CREATE TABLE `tmp_relAndunrel` (
			clientAccountID	VARCHAR(20) NOT NULL
		,	symbol	VARCHAR(20) NOT NULL
		,	reportDate	VARCHAR(8) NOT NULL
		,	realizedShortTermProfit	Double DEFAULT NULL
		,	realizedShortTermLoss	Double DEFAULT NULL
		,	realizedLongTermProfit	Double DEFAULT NULL
		,	realizedLongTermLoss	Double DEFAULT NULL
		,	totalRealizedPnl	Double DEFAULT NULL
		,	unrealizedProfit	Double DEFAULT NULL
		,	unrealizedLoss	Double DEFAULT NULL
		,	unrealizedSTProfit	Double DEFAULT NULL
		,	unrealizedSTLoss	Double DEFAULT NULL
		,	unrealizedLTProfit	Double DEFAULT NULL
		,	unrealizedLTLoss	Double DEFAULT NULL
		,	totalUnrealizedPnl	Double DEFAULT NULL
		,	totalFifoPnl	Double DEFAULT NULL
 , PRIMARY KEY (`clientAccountID`,`symbol`,`reportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
