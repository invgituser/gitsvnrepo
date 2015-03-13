ALTER TABLE `invdb`.`IB_Accounts` 
CHANGE COLUMN `acctnum` `acctnum` BIGINT(20) NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`IB_acctnum`);

ALTER TABLE `invdb`.`ib_accounts` 
CHANGE COLUMN `email` `email` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `lastName` `lastName` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `firstName` `firstName` VARCHAR(45) NULL ;

