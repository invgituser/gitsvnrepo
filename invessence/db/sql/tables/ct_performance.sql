DROP TABLE  `performance_daily_info`;
CREATE TABLE `performance_daily_info` (
  `clientAccountID`	varchar(20) NOT NULL,
  `reportDate`		varchar(8) NOT NULL,
  `nav`				double DEFAULT '0',
  `divident`		double DEFAULT '0',
  `deposit`			double DEFAULT '0',
  `withdrawl`		double DEFAULT '0',
  `unrealized`		double DEFAULT '0',
  `realized`		double DEFAULT '0',
  `totalFee`		double DEFAULT '0',
  `performance`		double DEFAULT '0',
  `created`			date,
  `lastUpdated`		date,
  PRIMARY KEY (`clientAccountID`,`reportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE  `performance_mtd_info`;
CREATE TABLE `performance_mtd_info` (
  `clientAccountID`	varchar(20) NOT NULL,
  `fromDate`		varchar(8) NOT NULL,
  `toDate`			varchar(8) NOT NULL,
  `nav`				double DEFAULT '0',
  `divident`		double DEFAULT '0',
  `deposit`			double DEFAULT '0',
  `withdrawl`		double DEFAULT '0',
  `unrealized`		double DEFAULT '0',
  `realized`		double DEFAULT '0',
  `totalFee`		double DEFAULT '0',
  `performance`		double DEFAULT '0',
  `created`			date,
  `lastUpdated`		date,
  PRIMARY KEY (`clientAccountID`,`fromDate`, `toDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE  `performance_ytd_info`;
CREATE TABLE `performance_ytd_info` (
  `clientAccountID`	varchar(20) NOT NULL,
  `fromDate`		varchar(8) NOT NULL,
  `toDate`			varchar(8) NOT NULL,
  `nav`				double DEFAULT '0',
  `divident`		double DEFAULT '0',
  `deposit`			double DEFAULT '0',
  `withdrawl`		double DEFAULT '0',
  `unrealized`		double DEFAULT '0',
  `realized`		double DEFAULT '0',
  `totalFee`		double DEFAULT '0',
  `performance`		double DEFAULT '0',
  `created`			date,
  `lastUpdated`		date,
  PRIMARY KEY (`clientAccountID`,`fromDate`, `toDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
