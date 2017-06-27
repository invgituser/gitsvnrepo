DROP TABLE IF EXISTS `temp`.`tmp_sec_fixed_performancechart`;
DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `sortorder` int(11) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `displayname` varchar(40) NOT NULL,
  `description` varchar(600) NOT NULL,
  `lowRisk` int(11) DEFAULT '-100',
  `highRisk` int(11) DEFAULT '200',
  `expectedreturn` double DEFAULT NULL,
  `expectedrisk` double DEFAULT NULL,
  `taxable` varchar(1) NOT NULL,
  PRIMARY KEY (`theme`,`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel_asset`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel_asset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `assetname` varchar(60) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'A',
  `alloc` varchar(40) DEFAULT NULL,
  `color` varchar(7) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  PRIMARY KEY (`theme`,`level`,`asset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel_subasset`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel_subasset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `keyname` varchar(30) NOT NULL,
  `keydescription` varchar(60) NOT NULL,
  `status` varchar(1) DEFAULT 'A',
  `allocation` double DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  PRIMARY KEY (`theme`,`level`,`asset`,`keyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel_projection`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel_projection` (
  `theme` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `5percent` double DEFAULT NULL,
  `25percent` double DEFAULT NULL,
  `50percent` double DEFAULT NULL,
  `75percent` double DEFAULT NULL,
  `95percent` double DEFAULT NULL,
  PRIMARY KEY (`theme`,`model`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel_performance`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel_performance` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `5percent` double DEFAULT NULL,
  `25percent` double DEFAULT NULL,
  `50percent` double DEFAULT NULL,
  `75percent` double DEFAULT NULL,
  `95percent` double DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `temp`.`tmp_sec_master`;

CREATE TABLE `temp`.`tmp_sec_master` (
  `status` varchar(1) DEFAULT 'A' COMMENT 'A - Active, I - Inactive',
  `ticker` varchar(20) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `assetclass` varchar(40) DEFAULT NULL COMMENT 'Equities, Bonds, Commodities, etc.',
  `subclass` varchar(40) DEFAULT NULL COMMENT 'LargeCap, SmallCap, etc.',
  `exchange` varchar(10) DEFAULT 'NYSE',
  `base_currency` varchar(3) DEFAULT 'USD',
  PRIMARY KEY (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



