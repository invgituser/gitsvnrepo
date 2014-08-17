DROP TABLE `user_logon`;

CREATE TABLE `user_logon` (
  `logonid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique internal  id for the user.',
  `userid` varchar(60) NOT NULL,
  `email` varchar(60) NOT NULL,
  `pwd` varchar(60) NOT NULL,
  `logonstatus` varchar(1) NOT NULL DEFAULT 'T',
  `prefix` varchar(25) DEFAULT NULL,
  `lastname` varchar(25) DEFAULT NULL,
  `firstname` varchar(25) DEFAULT NULL,
  `middlename` varchar(25) DEFAULT NULL,
  `suffix` varchar(25) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `address2` varchar(80) DEFAULT NULL,
  `address3` varchar(80) DEFAULT NULL,
  `address4` varchar(80) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `zip` varchar(9) DEFAULT NULL,
  `emailalt` varchar(60) DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  `phonetype` varchar(1) DEFAULT NULL,
  `phonealt` varchar(14) DEFAULT NULL,
  `phonetypealt` varchar(1) DEFAULT NULL,
  `leadsource` varchar(25) DEFAULT NULL,
  `question1` varchar(60) DEFAULT NULL,
  `answer1` varchar(60) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logonid`),
  UNIQUE KEY `UserID_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8
;
