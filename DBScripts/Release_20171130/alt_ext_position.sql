ALTER TABLE `invdb`.`ext_position` 
CHANGE COLUMN `currencyPrimary` `currencyPrimary` VARCHAR(3) NULL DEFAULT 'USD' ,
ADD COLUMN `settlementCurrency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `levelOfDetail`,
ADD COLUMN `settlementQty` DOUBLE NULL DEFAULT 0 AFTER `settlementCurrency`,
ADD COLUMN `settlementPrice` DOUBLE NULL DEFAULT 0 AFTER `settlementQty`,
ADD COLUMN `settlementMoney` DOUBLE NULL AFTER `settlementPrice`,
ADD COLUMN `baseCurrencyMarkPrice` DOUBLE NULL AFTER `settlementMoney`,
ADD COLUMN `baseCurrencyPnL` DOUBLE NULL AFTER `baseCurrencyMarkPrice`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`clientAccountID`, `reportDate`, `purchaseDate`, `symbol`,`costBasisPrice`, `levelOfDetail`);
