DROP TABLE IF EXISTS `sec_fixedmodel`;
CREATE TABLE `sec_fixedmodel` (
  `theme` varchar(20) NOT NULL,
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


DROP TABLE IF EXISTS `sec_fixedmodel_asset`;
CREATE TABLE `sec_fixedmodel_asset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `assetname` varchar(60) NOT NULL,
  `status` varchar(1) DEFAULT 'A',
  `allocation` double DEFAULT NULL,
  `color` varchar(7) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`asset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sec_fixedmodel_subasset` ;
CREATE TABLE `sec_fixedmodel_subasset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `keyname` varchar(40) NOT NULL,
  `keydescription` varchar(60) NOT NULL,
  `status` varchar(1) DEFAULT 'A',
  `allocation` double DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`asset`,`keyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
