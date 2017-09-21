DROP TABLE IF EXISTS `tmp_sec_fixedmodel`;
CREATE TABLE `tmp_sec_fixedmodel` (
  `theme` varchar(20) NOT NULL,
  `taxable` varchar(1) NOT NULL,
  `level` varchar(20) NOT NULL,
  `sortorder` int(11) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `displayname` varchar(40) NOT NULL,
  `description` varchar(300) NOT NULL,
  `lowRisk` int(11) DEFAULT '-100',
  `highRisk` int(11) DEFAULT '200',
  `expectedreturn` DOUBLE DEFAULT NULL,
  `expectedrisk` DOUBLE DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tmp_sec_fixedmodel_asset`;
CREATE TABLE `tmp_sec_fixedmodel_asset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `assetname` varchar(60) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'A',
  `color` varchar(7) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`asset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tmp_sec_fixedmodel_subasset` ;
CREATE TABLE `tmp_sec_fixedmodel_subasset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `keyname` varchar(30) NOT NULL,
  `keydescription` varchar(60) NOT NULL,
  `status` varchar(1) DEFAULT 'A',
  `allocation` double DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`asset`,`keyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tmp_sec_master`;
CREATE TABLE `tmp_sec_master` (
  `status` 		varchar(1) DEFAULT 'A',
  `ticker` 		varchar(20) NOT NULL,
  `cusip` 		varchar(12) DEFAULT NULL,
  `isin` 		varchar(15) DEFAULT NULL,
  `name` 		varchar(60) DEFAULT NULL,
  `assetclass`	varchar(40) DEFAULT NULL,
  `subclass`	varchar(40) DEFAULT NULL,
  `expenseRatio` double DEFAULT NULL,
  `yield` 		double DEFAULT NULL,
  `securityRiskSTD` double DEFAULT NULL,
  PRIMARY KEY (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
