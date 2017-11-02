ALTER TABLE `invdb`.`ext_nav` 
CHANGE COLUMN `cash` `cash` DOUBLE NULL DEFAULT 0 ,
CHANGE COLUMN `stock` `stock` DOUBLE NULL DEFAULT NULL ,
ADD COLUMN `tradeCurrency` VARCHAR(3) NOT NULL DEFAULT 'USD' AFTER `clientAccountID`,
ADD COLUMN `exchangeRate` DOUBLE NULL DEFAULT 1 AFTER `reportDate`,
ADD COLUMN `settleCurrency` VARCHAR(3) NOT NULL DEFAULT 'USD' AFTER `exchangeRate`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`clientAccountID`, `tradeCurrency`, `reportDate`, `settleCurrency`);

/*
ALTER TABLE `invdb`.`ext_nav` 
CHANGE COLUMN `base_currency` `tradeCurrency` VARCHAR(3) NOT NULL DEFAULT 'USD' ,
CHANGE COLUMN `dest_currency` `settleCurrency` VARCHAR(3) NOT NULL DEFAULT 'USD',
DROP PRIMARY KEY,
ADD PRIMARY KEY (`clientAccountID`, `tradeCurrency`, `reportDate`, `settleCurrency`);
*/

