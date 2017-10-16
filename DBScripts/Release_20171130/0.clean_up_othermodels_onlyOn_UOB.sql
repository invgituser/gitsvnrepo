delete from invdb.sec_assetclass_group
where theme in ('POSITION', 'PRIME-ASSET', '0.CITI', '0.Income', '0.Mfs', '0.Safety', '0.TRowe','T.0.CITI', 'T.0.Income',  'T.0.Mfs', 'T.0.Safety', 'T.0.TRowe');

delete from invdb.sec_prime_asset_group
where theme in ('POSITION', 'PRIME-ASSET', '0.CITI', '0.Income', '0.Mfs', '0.Safety', '0.TRowe','T.0.CITI', 'T.0.Income',  'T.0.Mfs', 'T.0.Safety', 'T.0.TRowe');

delete from invdb.sec_asset_mapping
where theme in ('POSITION', 'PRIME-ASSET', '0.CITI', '0.Income', '0.Mfs', '0.Safety', '0.TRowe','T.0.CITI', 'T.0.Income',  'T.0.Mfs', 'T.0.Safety', 'T.0.TRowe');

delete from invdb.sec_rbsa
where theme in ('POSITION', 'PRIME-ASSET', '0.CITI', '0.Income', '0.Mfs', '0.Safety', '0.TRowe','T.0.CITI', 'T.0.Income',  'T.0.Mfs', 'T.0.Safety', 'T.0.TRowe');

delete from invdb.user_basket_access
where theme in ('POSITION', 'PRIME-ASSET', '0.CITI', '0.Income', '0.Mfs', '0.Safety', '0.TRowe','T.0.CITI', 'T.0.Income',  'T.0.Mfs', 'T.0.Safety', 'T.0.TRowe');

delete from invdb.sec_prime_asset_group
where theme in ('T.0.SGWealthSGD');

insert into invdb.sec_prime_asset_group
SELECT 'T.0.SGWealthSGD',
    `sec_prime_asset_group`.`assetclass`,
    `sec_prime_asset_group`.`primeassetclass`,
    `sec_prime_asset_group`.`ticker`,
    `sec_prime_asset_group`.`status`,
    `sec_prime_asset_group`.`sortorder`,
    `sec_prime_asset_group`.`color`,
    `sec_prime_asset_group`.`lowerBound`,
    `sec_prime_asset_group`.`upperBound`,
    `sec_prime_asset_group`.`expectedReturn`,
    `sec_prime_asset_group`.`risk`,
    `sec_prime_asset_group`.`yield`,
    `sec_prime_asset_group`.`created`,
    `sec_prime_asset_group`.`lastUpdated`
FROM `invdb`.`sec_prime_asset_group`
where theme = '0.SGWealthSGD';
