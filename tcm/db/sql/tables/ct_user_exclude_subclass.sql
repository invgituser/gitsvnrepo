CREATE TABLE `user_exclude_subclass` (
  `acctnum` 	bigint(20) NOT NULL,
  `assetclass`	varchar(30) DEFAULT NULL,
  `subclass`	varchar(30) DEFAULT NULL,
  `created` 	date DEFAULT NULL,
  `lastUpdated` date DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`assetclass`,`subclass`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;
