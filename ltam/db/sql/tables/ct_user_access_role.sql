DROP TABLE IF EXISTS `user_access_role`;

delimiter $$

CREATE TABLE `user_access_role` (
  `logonid` varchar(20) NOT NULL,
  `acctnum` bigint(20) NOT NULL,
  `functionid` tinyint(4) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `privileges` varchar(2) DEFAULT NULL COMMENT 'Valid Status:\\\\nR - Read Only\\\\nW - Read/Write\\\\n- - No Access',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`logonid`,`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

