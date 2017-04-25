ALTER TABLE `temp`.`download_files` 
CHANGE COLUMN `sourcepath` `sourcepath` VARCHAR(100) NULL ,
CHANGE COLUMN `downloaddir` `downloaddir` VARCHAR(100) NOT NULL ;

ALTER TABLE `temp`.`download_files` 
CHANGE COLUMN `seqno` `seqno` INT(11) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`vendor`, `filename`, `seqno`);
