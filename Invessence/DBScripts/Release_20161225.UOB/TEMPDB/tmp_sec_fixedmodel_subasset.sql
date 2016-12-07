DROP TABLE IF EXISTS `invdb`.`tmp_sec_fixedmodel_subasset`;
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
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`asset`,`keyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
