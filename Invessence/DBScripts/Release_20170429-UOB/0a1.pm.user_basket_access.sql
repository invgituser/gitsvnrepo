ALTER TABLE `invdb`.`user_basket_access` 
ADD COLUMN `model` VARCHAR(1) NULL DEFAULT 'F' COMMENT 'F-Fixed,O-Optimized' AFTER `taxable`,
ADD COLUMN `baseCurrency` VARCHAR(3) NULL DEFAULT 'USD' AFTER `model`;

UPDATE `invdb`.`user_basket_access` 
set `model` = 'O'
where advisor like 'UOB%' or advisor like 'CITI%';

UPDATE `invdb`.`user_basket_access` 
set `baseCurrency` = 'SGD'
where advisor like 'UOB%' or advisor like 'CITI%';
