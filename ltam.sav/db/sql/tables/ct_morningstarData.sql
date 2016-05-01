DROP TABLE `morningstarData`;

CREATE TABLE `morningstarData` (
  `ticker` 				varchar(6) NOT NULL,
  `name` 				varchar(60) DEFAULT NULL,
  `standardDeviation` 	double DEFAULT NULL,
  `expenseRatio` 		double DEFAULT NULL,
  `beta3Year` 			double DEFAULT NULL,
  `dividendYield` 		double DEFAULT NULL,
  `duration` 			double DEFAULT NULL,
  `effectiveMaturity` 	double DEFAULT NULL,
  `totalReturnMonth` 	double DEFAULT NULL,
  `totalReturnYTD` 		double DEFAULT NULL,
  `total Return12Month` double DEFAULT NULL,
  `totalReturn10Year` 	double DEFAULT NULL,
  `taxCostRatio10Year` 	double DEFAULT NULL,
  `fundSizeTotalAssets` double DEFAULT NULL,
  `averageDailyVolume` 	double DEFAULT NULL,
  `turnoverRatio` 		double DEFAULT NULL,
  `creditQuality` 		varchar(20) DEFAULT NULL,
  `equityStyleBox` 		varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
