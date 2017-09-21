DROP TABLE IF EXISTS `invdb`.`user_notification`;

CREATE TABLE `invdb`.`user_notification` (
  `messageid` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(1) NOT NULL,
  `logonid` bigint(20) NOT NULL,
  `advisor` varchar(20) NOT NULL,
  `acctnum` bigint(20) DEFAULT '0',
  `noticetype` varchar(1) NOT NULL,
  `tagid` varchar(20) NOT NULL,
  `alertdatetime` datetime NOT NULL,
  `message` varchar(320) NOT NULL,
  `link` varchar(120) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`messageid`),
  KEY `AK1_user_notification` (`logonid`,`status`,`noticetype`),
  KEY `AK2_user_notification` (`advisor`,`status`,`noticetype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
