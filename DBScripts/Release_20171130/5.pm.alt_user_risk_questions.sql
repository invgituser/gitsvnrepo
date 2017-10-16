ALTER TABLE `invdb`.`user_risk_questions` 
ADD COLUMN `standardRisk` TINYINT(4) NULL DEFAULT NULL AFTER `risk15`,
ADD COLUMN `overrideRisk` TINYINT(4) NULL DEFAULT NULL AFTER `standardRisk`;
