ALTER TABLE `invdb`.`sec_rbsa` 
CHANGE COLUMN `base_currency` `settlecurrency` VARCHAR(3) NULL DEFAULT 'USD' ,
CHANGE COLUMN `dest_currency` `tradeCurrency` VARCHAR(3) NULL DEFAULT 'USD' ;

ALTER TABLE `invdb`.`user_basket_access` 
CHANGE COLUMN `baseCurrency` `tradeCurrency` VARCHAR(3) NULL DEFAULT 'USD' ;

ALTER TABLE `invdb`.`user_trade_profile` 
CHANGE COLUMN `tradeCurrency` `tradeCurrency` VARCHAR(3) NULL DEFAULT NULL ,
CHANGE COLUMN `settleCurrency` `settleCurrency` VARCHAR(3) NULL DEFAULT NULL ;

ALTER TABLE `invdb`.`ext_position` 
CHANGE COLUMN `currencyPrimary` `currencyPrimary` VARCHAR(3) NULL DEFAULT 'USD' ,
ADD COLUMN `settleCurrency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `levelOfDetail`,
ADD COLUMN `settleQty` DOUBLE NULL DEFAULT 0 AFTER `settleCurrency`,
ADD COLUMN `settlePrice` DOUBLE NULL DEFAULT 0 AFTER `settletQty`,
ADD COLUMN `settleMoney` DOUBLE NULL AFTER `settlePrice`,
ADD COLUMN `settleMarkPrice` DOUBLE NULL AFTER `settleMoney`,
ADD COLUMN `settlePnL` DOUBLE NULL AFTER `settleMarkPrice`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`clientAccountID`, `reportDate`, `purchaseDate`, `symbol`,`costBasisPrice`, `levelOfDetail`);


/*
ALTER TABLE `invdb`.`ext_position` 
CHANGE COLUMN `currencyPrimary` `tradeCurrency` VARCHAR(3) NULL DEFAULT 'USD' ,
CHANGE COLUMN `settlementCurrency` `settleCurrency` VARCHAR(3) NULL DEFAULT 'USD' ,
CHANGE COLUMN `settlementQty` `settleQty` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `settlementPrice` `settlePrice` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `settlementMoney` `settleMoney` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `baseCurrencyMarkPrice` `settleMarkPrice` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `baseCurrencyPnL` `settlePnL` DOUBLE NULL DEFAULT NULL ;
*/