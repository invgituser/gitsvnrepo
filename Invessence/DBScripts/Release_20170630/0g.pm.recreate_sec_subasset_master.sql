DROP TABLE `sec_subasset_master`;

CREATE TABLE `sec_subasset_master` (
  `theme` 			varchar(20) NOT NULL,
  `ticker` 			varchar(40) NOT NULL,
  `assetclass`		varchar(40) NOT NULL,
  `subassetName`	varchar(60) DEFAULT NULL,
  `subassetcolor`	varchar(9) DEFAULT NULL,
  `sortorder`		int(11) NOT NULL DEFAULT '0',
  `created` 		datetime DEFAULT NULL,
  `lastUpdated`		datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`sec_subasset_master`
(`theme`,`ticker`,`assetclass`, `subassetName`,`subassetcolor`,`sortorder`,`created`)
SELECT `sec_prime_asset_group`.`theme`,
    `sec_prime_asset_group`.`ticker`,
    `sec_prime_asset_group`.`assetclass`,
    `sec_master`.`subclass`,
    `sec_prime_asset_group`.`color`,
    `sec_prime_asset_group`.`sortorder`,
    `sec_prime_asset_group`.`created`
FROM `invdb`.`sec_prime_asset_group`
, `invdb`.`sec_master`
WHERE `sec_prime_asset_group`.`ticker` = `sec_master`.`ticker`;

UPDATE `sec_fixedmodel_subasset` set sortorder = 305 where theme = '0.TA' and keyname = 'MGCIX' and sortorder is null;
UPDATE `sec_fixedmodel_subasset` set sortorder = 305 where theme = '0.TA' and keyname = 'MGCIX' and sortorder = 306;

INSERT INTO `invdb`.`sec_subasset_master`
(`theme`,`ticker`,`assetclass`, `subassetName`,`subassetcolor`,`sortorder`,`created`)
SELECT 
	DISTINCT
    `sec_fixedmodel_subasset`.`theme`,
    `sec_fixedmodel_subasset`.`keyname`,
    `sec_fixedmodel_subasset`.`asset`,
    `sec_fixedmodel_subasset`.`keydescription`,
    null as color,
    `sec_fixedmodel_subasset`.`sortorder`,
    `sec_fixedmodel_subasset`.`created`
FROM `invdb`.`sec_fixedmodel_subasset`
where `sec_fixedmodel_subasset`.`theme` not like '%CORE%'
ORDER BY 2,1;

