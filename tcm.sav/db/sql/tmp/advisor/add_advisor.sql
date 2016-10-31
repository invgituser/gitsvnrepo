DELETE FROM `advisor_info`
WHERE `logonid`='62';

INSERT INTO `invdb`.`advisor_info` 
(`logonid`, `groupname`, `accttype`, `lastname`, `firstname`, `companyname`, `state`, `logo`,`created`) 
VALUES ('62', 'sam.mcpherson', 'FP', 'McPherson', 'Sam.McPherson', 'Sam.McPherson', 'NY', '/images/institution/sam-mcpherson.png',now());

update asset_mapping_group
set groupname = 'Sam.McPherson'
where groupname = 'Sam';

update sec_master_group
set groupname = 'Sam.McPherson'
where groupname = 'Sam';

ALTER TABLE `invdb`.`user_trade_profile` 
ADD COLUMN `advisor` VARCHAR(20) NULL AFTER `acctnum`,
ADD COLUMN `theme` VARCHAR(30) NULL AFTER `advisor`
;
