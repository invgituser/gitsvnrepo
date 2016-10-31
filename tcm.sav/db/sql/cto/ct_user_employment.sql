DROP TABLE IF EXISTS `user_employment`;

CREATE TABLE `user_employment` (
    `logonid` bigint(20) NOT NULL,
	`ownershipNum` Integer DEFAULT 0,
    `employer` varchar(40) DEFAULT NULL,
    `occupation` varchar(40) DEFAULT NULL,
    `business` varchar(40) DEFAULT NULL,
    `city` varchar(40) DEFAULT NULL,
    `country` varchar(40) DEFAULT NULL,
    `zip` varchar(40) DEFAULT NULL,
    `state` varchar(40) DEFAULT NULL,
    `street_1` varchar(40) DEFAULT NULL,
    `street_2` varchar(40) DEFAULT NULL,
    `phone` varchar(40) DEFAULT NULL,
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `lastupdated` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`logonid`, `ownershipNum`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;
