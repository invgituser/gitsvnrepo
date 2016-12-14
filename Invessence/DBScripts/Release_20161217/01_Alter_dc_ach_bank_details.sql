use invdb;
ALTER TABLE `invdb`.`dc_ach_bank_details` 
ADD COLUMN `acctnum` BIGINT(20) NOT NULL AFTER `achId`;