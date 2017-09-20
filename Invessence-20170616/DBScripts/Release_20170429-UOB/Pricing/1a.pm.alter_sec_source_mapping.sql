ALTER TABLE `invdb`.`sec_source_mapping` 
ADD COLUMN `exchange_required` VARCHAR(1) NULL AFTER `pricing_required`,
ADD COLUMN `base_currency` VARCHAR(3) NULL AFTER `exchange_required`;

ALTER TABLE `invdb`.`sec_source_mapping` 
ADD COLUMN `sec_ticker` VARCHAR(20) NOT NULL FIRST,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`ticker`, `sec_ticker`);

ALTER TABLE `invdb`.`sec_source_mapping` 
CHANGE COLUMN `exchange_required` `exchange_required` VARCHAR(1) NULL DEFAULT 'N' ,
ADD COLUMN `dest_currency` VARCHAR(3) NULL DEFAULT NULL AFTER `base_currency`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`sec_ticker`, `ticker`);


update `invdb`.`sec_source_mapping`
set `sec_ticker` = `ticker`,
exchange_required = 'Y';

UPDATE `invdb`.`sec_source_mapping` SET 
`exchange_required`='N' 
WHERE `ticker` like '%SI';