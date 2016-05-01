CREATE TABLE `user_basket_access` (
  `groupname` varchar(30) NOT NULL,
  `theme` varchar(30) NOT NULL,
  `status` varchar(1) NOT NULL,
  `basket` varchar(30) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `created` date DEFAULT NULL,
  `lastupdated` date DEFAULT NULL,
  PRIMARY KEY (`groupname`,`theme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
