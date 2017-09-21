ALTER TABLE `invdb`.`download_files` 
ADD COLUMN `seqno` INT NULL COMMENT '' AFTER `filename`;

UPDATE `invdb`.`download_files` SET `seqno`='1'
, `tmp_tableName`='temp.tmp_td_demographic'
WHERE `vendor`='TD' and`filename`='TRD';
UPDATE `invdb`.`download_files` SET `seqno`='2'
, `tmp_tableName`='temp.tmp_td_security'
WHERE `vendor`='TD' and`filename`='SEC';
UPDATE `invdb`.`download_files` SET `seqno`='3'
, `tmp_tableName`='temp.tmp_td_price'
WHERE `vendor`='TD' and`filename`='PRI';
UPDATE `invdb`.`download_files` SET `seqno`='4'
, `active`='N'
, `tmp_tableName`='temp.tmp_td_position'
 WHERE `vendor`='TD' and`filename`='POS';
UPDATE `invdb`.`download_files` SET `seqno`='5'
, `tmp_tableName`='temp.tmp_td_transaction'
WHERE `vendor`='TD' and`filename`='TRN';

INSERT INTO `invdb`.`download_files` 
(`vendor`, `filename`, `seqno`, `active`, `tmp_tableName`, `available`, `sourcepath`, `downloaddir`, `loadFormat`, `required`, `canbeempty`, `postProcess`, `postInstruction`, `created`, `containsheader`, `keyData`, `fileExtension`) 
VALUES ('TD', 'CBL', '6', 'Y', 'temp.tmp_td_unrealized', 'DAILY', 'DIR', 'AGWQT', 'csv', 'Y', 'N', 'DB', '', '2015-01-28 14:07:58', 'N', '1', 'csv');

