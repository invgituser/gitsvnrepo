DROP TABLE IF EXISTS `user_basket_access`;

CREATE TABLE `user_basket_access` (
  `advisor` varchar(30) NOT NULL,
  `theme` varchar(30) NOT NULL,
  `status` varchar(1) NOT NULL,
  `displayname` varchar(40) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `primary` varchar(1) NOT NULL DEFAULT 'N',
  `taxable` varchar(1) DEFAULT 'N',
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`advisor`,`theme`,`primary`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
