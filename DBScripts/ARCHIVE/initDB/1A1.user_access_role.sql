CREATE TABLE `user_access_role_20160815`
as SELECT * FROM `user_access_role`;

DROP TABLE IF EXISTS `user_access_role`;

CREATE TABLE `user_access_role` (
  `logonid` bigint(20) NOT NULL,
  `acctnum` bigint(20) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `privileges` varchar(1) DEFAULT NULL COMMENT 'R-Read,W-Write',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logonid`,`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user_access_role`
( `logonid`, `acctnum`, `role`, `privileges`, `created`, `lastupdated` )
SELECT 
	`u2`.`logonid`,
	`u2`.`acctnum`,
	`u2`.`role`,
    'W' as `privileges`,
	`u2`.`created`,
	null
FROM `user_access_role_20160815` `u2`;
