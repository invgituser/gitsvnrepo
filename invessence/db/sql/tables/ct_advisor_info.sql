CREATE TABLE `advisor_info` (
  `logonid` bigint(20) NOT NULL COMMENT 'Unique internal  id for the user.',
  `groupname` varchar(20)  NOT NULL,
  `accttype` varchar(20) NOT NULL COMMENT 'FP - Fiancial Planner, IB - Institution Broker, , Bank',
  `lastname` varchar(25) DEFAULT NULL,
  `firstname` varchar(25) DEFAULT NULL,
  `companyname` varchar(40) DEFAULT NULL,
  `address1` varchar(60) DEFAULT NULL,
  `address2` varchar(60) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `zip`   varchar(10) DEFAULT NULL,
  `emailalt` varchar(60) DEFAULT NULL,
  `logo` varchar(60) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logonid`),
  UNIQUE KEY `UserID_UNIQUE` (`logonid`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;
