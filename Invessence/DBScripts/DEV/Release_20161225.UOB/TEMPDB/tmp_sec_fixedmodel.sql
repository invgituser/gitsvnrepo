DROP TABLE IF EXISTS `invdb`.`tmp_sec_fixedmodel`;

DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `sortorder` int(11) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `displayname` varchar(40) NOT NULL,
  `description` varchar(300) NOT NULL,
  `lowRisk` int(11) DEFAULT '-100',
  `highRisk` int(11) DEFAULT '200',
  `expectedreturn` double DEFAULT NULL,
  `expectedrisk` double DEFAULT NULL,
  `taxable` varchar(1) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
