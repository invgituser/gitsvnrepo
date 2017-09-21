ALTER TABLE `invdb`.`ext_acct_info` 
ADD COLUMN `acctType` VARCHAR(40) NULL DEFAULT NULL COMMENT '' AFTER `dob`;
