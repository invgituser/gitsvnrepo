ALTER TABLE `invdb`.`advisor_notification` 
ADD COLUMN `rep` VARCHAR(20) NULL DEFAULT NULL AFTER `advisor`,
ADD INDEX `AK6_advisor_notification` (`advisor` ASC, `rep` ASC, `status` ASC);
