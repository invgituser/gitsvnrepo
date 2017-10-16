ALTER TABLE `rbsa`.`rbsa_daily` 
ADD COLUMN `exchangeRate` DOUBLE NULL AFTER `adjusted_price`,
ADD COLUMN `base_currency` VARCHAR(3) NULL AFTER `monthly_return`;

ALTER TABLE `invdb`.`sec_daily_info` 
ADD COLUMN `exchangeRate` DOUBLE NULL AFTER `adjusted_price`,
ADD COLUMN `base_currency` VARCHAR(3) NULL AFTER `monthly_return`;

ALTER TABLE `invdb`.`sec_rbsa` 
ADD COLUMN `base_currency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `created`,
ADD COLUMN `dest_currency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `base_currency`
;

ALTER TABLE `invdb`.`sec_rbsa` 
ADD COLUMN `sec_type` VARCHAR(3) NULL DEFAULT 'USD' AFTER `base_currency`
;

ALTER TABLE `invdb`.`asset_alloc` 
ADD COLUMN `base_currency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `weight`,
ADD COLUMN `money` DOUBLE NULL DEFAULT 0.0 AFTER `base_currency`
;

ALTER TABLE `invdb`.`virtual_portfolio` 
ADD COLUMN `base_currency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `tradeprice`,
ADD COLUMN `exchangeRate` DOUBLE NULL DEFAULT 1.0 AFTER `investmentvalue`,
ADD COLUMN `dest_currency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `exchangeRate`,
ADD COLUMN `dest_qty` DOUBLE NULL AFTER `dest_currency`,
ADD COLUMN `dest_price` DOUBLE NULL AFTER `dest_qty`,
ADD COLUMN `dest_value` DOUBLE NULL AFTER `dest_price`;
