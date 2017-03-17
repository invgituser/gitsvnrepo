ALTER TABLE `invdb`.`user_trade_profile` 
ADD COLUMN `managed` VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '' AFTER `lastUpdated`,
ADD COLUMN `clientAccountID` VARCHAR(15) NULL DEFAULT NULL COMMENT '' AFTER `managed`;
