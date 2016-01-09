ALTER TABLE `virtual_portfolio` 
CHANGE COLUMN `weight` `weightByAsset` FLOAT NULL DEFAULT NULL ,
ADD COLUMN `weightByPortfolio` FLOAT NULL AFTER `lastupdated`;