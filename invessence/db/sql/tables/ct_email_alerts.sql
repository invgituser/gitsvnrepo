
CREATE TABLE `email_alerts` (
  `messageid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender` varchar(250) NOT NULL,
  `receiver` varchar(250) DEFAULT NULL,
  `subject` varchar(250) NOT NULL,
  `cc` varchar(250) DEFAULT NULL,
  `bcc` varchar(250) DEFAULT NULL,
  `msg` mediumtext,
  `status` tinyint(4) DEFAULT NULL,
  `category` tinyint(4) DEFAULT NULL,
  `priority` tinyint(4) DEFAULT NULL,
  `logonid` bigint(20) DEFAULT NULL,
  `sentdate` timestamp NULL DEFAULT NULL,
  `comment` mediumtext,
  `created` timestamp NULL DEFAULT NULL,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
