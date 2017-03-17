ALTER TABLE `invdb`.`dc_acct_details` 
ADD COLUMN `optoutRegulatory` CHAR(1) NULL AFTER `proxyAuthorizationId`,
ADD COLUMN `optoutBeneficiary` CHAR(1) NULL AFTER `optoutRegulatory`,
ADD COLUMN `optoutFunding` CHAR(1) NULL AFTER `optoutBeneficiary`,
ADD COLUMN `optoutRecurring` CHAR(1) NULL AFTER `optoutFunding`;