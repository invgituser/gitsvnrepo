delimiter $$

CREATE TABLE `custodian_config` (
  `customerid` int(10) NOT NULL,
  `firm` varchar(2) DEFAULT NULL,
  `correspondentid` int(10) DEFAULT NULL,
  `correspondentofficeid` int(10) DEFAULT NULL,
  `cryptkey` varchar(16) DEFAULT NULL,
  `officecd` varchar(3) DEFAULT NULL,
  `altbranch` varchar(5) DEFAULT NULL,
  `registeredrep` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

