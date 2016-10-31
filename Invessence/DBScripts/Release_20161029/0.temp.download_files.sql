CREATE TABLE `temp`.`download_files` (
  `vendor` varchar(20) NOT NULL,
  `filename` varchar(40) NOT NULL,
  `seqno` int(11) DEFAULT NULL,
  `active` varchar(1) NOT NULL DEFAULT 'N',
  `tmp_tableName` varchar(45) DEFAULT NULL,
  `available` varchar(7) NOT NULL DEFAULT 'N',
  `sourcepath` varchar(7) DEFAULT NULL,
  `downloaddir` varchar(45) NOT NULL,
  `loadFormat` varchar(10) NOT NULL,
  `required` varchar(1) NOT NULL DEFAULT 'N',
  `canbeempty` varchar(1) NOT NULL DEFAULT 'Y',
  `postProcess` varchar(7) DEFAULT NULL,
  `postInstruction` varchar(80) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  `containsheader` varchar(1) DEFAULT 'N',
  `keyData` varchar(3) DEFAULT '0',
  `encColumns` varchar(20) DEFAULT NULL,
  `fileExtension` varchar(10) NOT NULL,
  `encryptionMethod` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`vendor`,`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `temp`.`download_files`
select * from `invdb`.`download_files`;

UPDATE `temp`.`download_files` SET `tmp_tableName`='tmp_td_unrealized' WHERE `vendor`='TD' and`filename`='CBL';
UPDATE `temp`.`download_files` SET `tmp_tableName`='tmp_td_position' WHERE `vendor`='TD' and`filename`='POS';
UPDATE `temp`.`download_files` SET `tmp_tableName`='tmp_td_price' WHERE `vendor`='TD' and`filename`='PRI';
UPDATE `temp`.`download_files` SET `tmp_tableName`='tmp_td_security' WHERE `vendor`='TD' and`filename`='SEC';
UPDATE `temp`.`download_files` SET `tmp_tableName`='tmp_td_demographic' WHERE `vendor`='TD' and`filename`='TRD';
UPDATE `temp`.`download_files` SET `tmp_tableName`='tmp_td_transaction' WHERE `vendor`='TD' and`filename`='TRN';
