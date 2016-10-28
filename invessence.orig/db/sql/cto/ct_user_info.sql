DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `logonid` bigint(20) NOT NULL,
  `prefix` varchar(25) DEFAULT NULL,
  `lastname` varchar(25) DEFAULT NULL,
  `firstname` varchar(25) DEFAULT NULL,
  `middlename` varchar(25) DEFAULT NULL,
  `suffix` varchar(25) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `address2` varchar(30) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `zip` varchar(9) DEFAULT NULL,
  `primaryPhone` varchar(14) DEFAULT NULL,
  `secondaryPhone` varchar(14) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `maritalStatus` varchar(1) DEFAULT NULL,
  `dateofBirth` date default null,
  `numDependent` Integer default null,
  `socialSecurity` varchar(11) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  `acctstate` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`logonid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
