create table sec_master_20170524
as select * from sec_master;

DROP TABLE `sec_master`;
 
CREATE TABLE `sec_master` (
  `instrumentid` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(1) DEFAULT 'A' COMMENT 'A - Active, I - Inactive',
  `securityStatus` varchar(1) DEFAULT 'A' COMMENT 'A-Active,M - Matured,D - Defaulted,S - Split,R - Reverse Split',
  `ticker` varchar(20) NOT NULL,
  `cusip` varchar(12) DEFAULT NULL,
  `isin` varchar(15) DEFAULT NULL,
  `ric` varchar(45) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `assetclass` varchar(40) DEFAULT NULL COMMENT 'Equities, Bonds, Commodities, etc.',
  `subclass` varchar(40) DEFAULT NULL COMMENT 'LargeCap, SmallCap, etc.',
  `type` varchar(30) DEFAULT NULL COMMENT 'Equity,Bond,Cash',
  `style` varchar(30) DEFAULT NULL COMMENT 'Value,Growth,Divident',
  `expenseRatio` double DEFAULT NULL,
  `inception` date DEFAULT NULL,
  `issuer` varchar(20) DEFAULT NULL,
  `securityRiskSTD` double DEFAULT NULL,
  `rbsaFlag` varchar(1) DEFAULT NULL,
  `exchange` varchar(10) DEFAULT 'NYSE',
  `base_currency` varchar(3) DEFAULT 'USD',
  PRIMARY KEY (`instrumentid`),
  INDEX `AK1_sec_master` (`ticker`), 
  INDEX `AK2_sec_master` (`cusip`),
  INDEX `AK3_sec_master` (`isin`),
  INDEX `AK4_sec_master` (`ric`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`sec_master`
(`instrumentid`,
`status`,
`ticker`,
`cusip`,
`isin`,
`name`,
`assetclass`,
`subclass`,
`type`,
`style`,
`expenseRatio`,
`inception`,
`issuer`,
`securityRiskSTD`,
`rbsaFlag`,
`exchange`,
`ric`)
SELECT 
`instrumentid`,
`status`,
`ticker`,
`cusip`,
`isin`,
`name`,
`assetclass`,
`subclass`,
`type`,
`style`,
`expenseRatio`,
`inception`,
`issuer`,
`securityRiskSTD`,
`rbsaFlag`,
`exchange`,
`ric`
FROM `invdb`.`sec_master_20170524`;

update `invdb`.`sec_master`
set cusip = null, isin = null;

 
