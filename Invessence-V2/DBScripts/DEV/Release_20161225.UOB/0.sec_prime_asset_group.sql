create table invdb.sec_prime_asset_group_20170130 as
SELECT * FROM invdb.sec_prime_asset_group;

DROP TABLE IF EXISTS `invdb`.`sec_prime_asset_group`;

CREATE TABLE `invdb`.`sec_prime_asset_group` (
  `theme`			varchar(20) NOT NULL,
  `assetclass`		varchar(30) NOT NULL,
  `ticker`			varchar(20) NOT NULL,
  `status`			varchar(1) DEFAULT NULL,
  `sortorder`		int(11) DEFAULT '0',
  `color`			varchar(10) DEFAULT NULL,
  `lowerBound`		double DEFAULT NULL,
  `upperBound`		double DEFAULT NULL,
  `expectedReturn`	double DEFAULT NULL,
  `risk`			double DEFAULT NULL,
  `yield`			double DEFAULT NULL,
  `created`			datetime DEFAULT NULL,
  `lastUpdated`		datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`assetclass`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into `invdb`.`sec_prime_asset_group`
SELECT `sec_prime_asset_group_20170130`.`theme`,
    `sec_prime_asset_group_20170130`.`assetclass`,
    `sec_prime_asset_group_20170130`.`ticker`,
    `sec_prime_asset_group_20170130`.`status`,
    `sec_prime_asset_group_20170130`.`sortorder`,
    `sec_prime_asset_group_20170130`.`color`,
    `sec_prime_asset_group_20170130`.`lowerBound`,
    `sec_prime_asset_group_20170130`.`upperBound`,
    `sec_prime_asset_group_20170130`.`expectedReturn`,
    `sec_master`.`securityRiskSTD`,
    `sec_master`.`yield`,
    `sec_prime_asset_group_20170130`.`created`,
    `sec_prime_asset_group_20170130`.`lastUpdated`
FROM `invdb`.`sec_prime_asset_group_20170130`
LEFT JOIN `invdb`.`sec_master`
ON (`sec_prime_asset_group_20170130`.`ticker` = `sec_master`.`ticker`)
WHERE `sec_prime_asset_group_20170130`.`theme` like '%SGWealth'
