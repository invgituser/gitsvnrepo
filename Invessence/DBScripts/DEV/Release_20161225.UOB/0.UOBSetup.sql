DELETE FROM `invdb`.`user_basket_access`
where `advisor` in ('Invessence', 'UOB');

INSERT INTO `invdb`.`user_basket_access`
(`advisor`,`theme`,`status`,`displayname`,`sortorder`,`primary`,`taxable`,`created`)
VALUES
('Invessence','0.Wealth','A','Build Wealth',0,'Y','N',now()),
('Invessence','T.0.Wealth','A','Build Wealth',0,'N','Y',now()),
('Invessence', '0.Income', 'A', 'Generate Income', 1, 'N', 'N', now()),
('Invessence', '0.Safety', 'A', 'Safety', 1, 'N', 'N', now()),
('Invessence', 'T.0.Income', 'A', 'Generate Income', 2, 'N', 'Y', now()),
('Invessence', 'T.0.Safety', 'A', 'Safety', 2, 'N', 'Y', now())
;

INSERT INTO `invdb`.`user_basket_access` 
(`advisor`, `theme`, `status`, `displayname`, `sortorder`, `primary`, `taxable`, `created`) 
VALUES 
('UOB', '0.UOB', 'A', 'Growth Strategy', '0', 'Y', 'N', '2016-11-18'),
('UOB', 'T.0.UOB', 'A', 'Taxable Growth Strategy', '1', 'N', 'Y', '2016-11-18');

update `invdb`.`sec_assetclass_group`
set theme = replace(theme,'Core','Wealth')
where theme like '%Core';

update `invdb`.`sec_prime_asset_group`
set theme = replace(theme,'Core','Wealth')
where theme like '%Core';


INSERT INTO `invdb`.`sec_assetclass_group`
(`theme`,`status`,`assetclass`,`displayName`,`ticker`,`sortorder`,`lowerBound`,`upperBound`,`color`,`averageReturn`,`riskAdjustment`,`endAllocation`,`created`)
SELECT REPLACE(`sec_assetclass_group`.`theme`,'Wealth','UOB'),
    `sec_assetclass_group`.`status`,
    `sec_assetclass_group`.`assetclass`,
    `sec_assetclass_group`.`displayName`,
    `sec_assetclass_group`.`ticker`,
    `sec_assetclass_group`.`sortorder`,
    `sec_assetclass_group`.`lowerBound`,
    `sec_assetclass_group`.`upperBound`,
    `sec_assetclass_group`.`color`,
    `sec_assetclass_group`.`averageReturn`,
    `sec_assetclass_group`.`riskAdjustment`,
    `sec_assetclass_group`.`endAllocation`,
    now()
FROM `invdb`.`sec_assetclass_group`
WHERE `sec_assetclass_group`.`theme` like '%Wealth';

INSERT INTO `invdb`.`sec_prime_asset_group`
(`theme`,`assetclass`,`primeassetclass`,`ticker`,`status`,`sortorder`,`color`,`lowerBound`,`upperBound`,`expectedReturn`,`created`)
SELECT REPLACE(`sec_prime_asset_group`.`theme`,'Wealth','UOB'),
    `sec_prime_asset_group`.`assetclass`,
    `sec_prime_asset_group`.`primeassetclass`,
    `sec_prime_asset_group`.`ticker`,
    `sec_prime_asset_group`.`status`,
    `sec_prime_asset_group`.`sortorder`,
    `sec_prime_asset_group`.`color`,
    `sec_prime_asset_group`.`lowerBound`,
    `sec_prime_asset_group`.`upperBound`,
    `sec_prime_asset_group`.`expectedReturn`,
    now()
FROM `invdb`.`sec_prime_asset_group`
WHERE `sec_prime_asset_group`.`theme` like '%Wealth';


