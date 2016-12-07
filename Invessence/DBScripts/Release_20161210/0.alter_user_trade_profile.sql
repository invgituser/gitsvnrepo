ALTER TABLE `invdb`.`user_trade_profile` 
ADD COLUMN `status` VARCHAR(1) NULL DEFAULT 'N' COMMENT '' AFTER `clientAccountID`,
ADD COLUMN `customName` VARCHAR(60) NULL DEFAULT NULL COMMENT '' AFTER `status`;
