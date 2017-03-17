ALTER TABLE `invdb`.`user_advisor_mapping` 
ADD COLUMN `archiveUnopenedApps` INT NULL DEFAULT 30 COMMENT '' AFTER `minRecurring`;
