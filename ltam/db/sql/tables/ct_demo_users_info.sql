DROP TABLE IF EXISTS `demo_users_info`;

CREATE TABLE `demo_users_info` (
  `demoID`    BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`     VARCHAR(25) NOT NULL,
  `lastname`  varchar(25) default null,
  `firstname` VARCHAR(25) DEFAULT NULL,
  `info`      VARCHAR(25) DEFAULT NULL,
  `attempts`  INTEGER DEFAULT 1,
  `created`     date DEFAULT null,
  `lastupdated` date default null,
PRIMARY KEY (`demoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX demo_email on `demo_users_info`
(`email`);
