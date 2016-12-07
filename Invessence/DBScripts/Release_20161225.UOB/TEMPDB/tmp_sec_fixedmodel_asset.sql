DROP TABLE IF EXISTS `invdb`.`tmp_sec_fixedmodel_asset`;
DROP TABLE IF EXISTS `temp`.`tmp_sec_fixedmodel_asset`;

CREATE TABLE `temp`.`tmp_sec_fixedmodel_asset` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `asset` varchar(40) NOT NULL,
  `assetname` varchar(60) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'A',
  `alloc`  varchar(40) DEFAULT NULL,
  `color` varchar(7) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`asset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
