DROP TABLE IF EXISTS `user_advisor_info`;

CREATE TABLE `user_advisor_info` (
  `logonid` bigint(20) NOT NULL,
  `advisor` varchar(20) NOT NULL,
  `rep` varchar(20) DEFAULT NULL,
  `accttype` varchar(20) NOT NULL COMMENT 'FP - Fiancial Planner, IB - Institution Broker, , Bank',
  `displayName` varchar(60) DEFAULT NULL,
  `logo` varchar(60) DEFAULT NULL,
  `advisor_css` varchar(30) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logonid`),
  UNIQUE KEY `PK2_user_advisor_info` (`advisor`,`rep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
