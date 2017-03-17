DROP TABLE IF EXISTS `invdb`.`advisor_notification`;

CREATE TABLE `invdb`.`advisor_notification` (
  `messageid` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(1) NOT NULL,
  `advisorlogonid` bigint(20) NOT NULL,
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
  KEY `AK1_advisor_notification` (`advisor`,`status`,`noticetype`),
  KEY `AK2_advisor_notification` (`advisorlogonid`,`status`,`noticetype`),
  KEY `AK3_advisor_notification` (`acctnum`,`status`,`noticetype`),
  KEY `AK4_advisor_notification` (`tagid`,`status`,`acctnum`),
  KEY `AK5_advisor_notification` (`status`,`noticetype`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
