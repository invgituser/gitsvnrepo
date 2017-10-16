INSERT INTO `invdb`.`sec_rbsa`
(`theme`,
`primeasset_ticker`,
`ticker`,
`weight`,
`created`,
`base_currency`,
`sec_type`,
`dest_currency`) 
SELECT `sec_prime_asset_group`.`theme`,
    `sec_prime_asset_group`.`ticker`,
    `sec_prime_asset_group`.`ticker`,
    1.0,
    `sec_prime_asset_group`.`created`,
    `sec_source_mapping`.base_currency,
    null,
    `sec_source_mapping`.dest_currency
FROM `invdb`.`sec_prime_asset_group`, `invdb`.`sec_source_mapping`, `invdb`.`user_basket_access`
WHERE `sec_prime_asset_group`.`theme` = `user_basket_access`.`theme`
AND `sec_prime_asset_group`.ticker = `sec_source_mapping`.ticker_source_name
AND `sec_source_mapping`.`dest_currency` = `user_basket_access`.`baseCurrency`
AND `sec_prime_asset_group`.`theme` = '8.UOB.UnCons';
