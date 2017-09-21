DROP TABLE IF EXISTS `user_logon_exception`;

CREATE TABLE `user_logon_exception` (
  `logonid` 	bigint(20)  DEFAULT NULL,
  `acctnum`		bigint(20)  DEFAULT NULL,
  `userid` 		varchar(60) DEFAULT NULL,
  `pwd` 		varchar(60) DEFAULT NULL,
  `logonstatus` varchar(1)  DEFAULT NULL,
  `lastname` 	varchar(25) DEFAULT NULL,
  `firstname` 	varchar(25) DEFAULT NULL,
  `email` 		varchar(60) DEFAULT NULL,
  `created` 	DATETIME    DEFAULT NULL,
  `lastupdated` DATETIME    DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
