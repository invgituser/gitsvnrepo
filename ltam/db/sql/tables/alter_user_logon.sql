ALTER TABLE `invdb`.`user_logon` 
ADD COLUMN `access` VARCHAR(10) NULL AFTER `emailmsgtype`;
