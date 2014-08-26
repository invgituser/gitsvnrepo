
CREATE TABLE `user_subclass_exclusion` (
  `acctnum` bigint(20) NOT NULL,
  `assetclass` varchar(20) NOT NULL,
  `subasset` varchar(20) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`assetclass`,`subasset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
