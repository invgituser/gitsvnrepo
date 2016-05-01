DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
	`clientAccountID` VARCHAR(8),
	`accountAlias`    VARCHAR(20),
	`currencyPrimary` VARCHAR(20),
	`assetClass`	  VARCHAR(10),
	`fxRateToBase`	  DOUBLE,
	`symbol`		  VARCHAR(10),
	`description`	  VARCHAR(60),
	`reportDate`	  VARCHAR(8),
	`side`			  VARCHAR(6),
	`quantity`		  INTEGER,
	`costBasisPrice`  DOUBLE,
	`costBasisMoney`  DOUBLE,
	`markPrice`		  DOUBLE,
	`positionValue`   DOUBLE,
	`fifoPnlUnrealized` DOUBLE,
	`levelOfDetail`	  VARCHAR(10),
  PRIMARY KEY (`ClientAccountID`,`Symbol`,`ReportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
