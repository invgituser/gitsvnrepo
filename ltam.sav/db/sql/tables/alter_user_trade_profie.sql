ALTER TABLE `invdb`.`user_trade_profile` 
ADD COLUMN `tradePreference` VARCHAR(1) NULL DEFAULT 'A' AFTER `riskIndex`,
ADD COLUMN `keepLiquid` INT(11) NULL DEFAULT NULL AFTER `tradePreference`;