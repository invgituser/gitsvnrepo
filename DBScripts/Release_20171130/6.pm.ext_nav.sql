ALTER TABLE `invdb`.`ext_nav` 
CHANGE COLUMN `cash` `cash` DOUBLE NULL DEFAULT 0 ,
CHANGE COLUMN `stock` `stock` DOUBLE NULL DEFAULT NULL ,
ADD COLUMN `base_currency` VARCHAR(3) NOT NULL DEFAULT 'USD' AFTER `clientAccountID`,
ADD COLUMN `exchangeRate` DOUBLE NULL DEFAULT 1 AFTER `reportDate`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`clientAccountID`, `base_currency`, `reportDate`);

ALTER TABLE `invdb`.`ext_nav` 
ADD COLUMN `dest_currency` VARCHAR(3) NOT NULL DEFAULT 'USD' AFTER `exchangeRate`;
