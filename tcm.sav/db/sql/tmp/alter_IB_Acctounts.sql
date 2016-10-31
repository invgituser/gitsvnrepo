ALTER TABLE `IB_Accounts` 
ADD COLUMN `dateOpened` VARCHAR(10) NULL AFTER `accountStatus`,
ADD COLUMN `dateClosed` VARCHAR(10) NULL AFTER `dateOpened`;
