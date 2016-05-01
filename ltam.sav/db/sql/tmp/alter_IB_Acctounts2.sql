ALTER TABLE `invdb`.`IB_Accounts` 
ADD COLUMN `created` DATETIME NULL AFTER `dateClosed`,
ADD COLUMN `updated` DATETIME NULL AFTER `created`;