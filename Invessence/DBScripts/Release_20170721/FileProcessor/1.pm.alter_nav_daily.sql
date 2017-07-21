ALTER TABLE `invdb`.`nav_daily` 
ADD COLUMN `currencyPrimary` VARCHAR(3) NULL DEFAULT 'USD' AFTER `reportDate`,
ADD COLUMN `fxRateToBase` DOUBLE NULL DEFAULT 1.0 AFTER `currencyPrimary`;

ALTER TABLE `invdb`.`nav_daily` 
RENAME TO  `invdb`.`ext_nav_daily` ;

