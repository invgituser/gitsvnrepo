DROP TABLE IF EXISTS  `user_advisor_access`;

CREATE TABLE `user_advisor_access` (
  `logonid` bigint(20) NOT NULL COMMENT 'Unique internal  id for the user.',
  `advisor` varchar(20) NOT NULL,
  `rep` varchar(20) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logonid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
