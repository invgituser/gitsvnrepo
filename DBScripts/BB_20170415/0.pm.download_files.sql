ALTER TABLE `invdb`.`download_files` 
CHANGE COLUMN `sourcepath` `sourcepath` VARCHAR(7) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`vendor`, `filename`, `sourcepath`);


INSERT INTO `invdb`.`download_files` 
(`vendor`, `filename`, `seqno`, `active`, `tmp_tableName`, `available`, `sourcepath`, `downloaddir`, `loadFormat`, `required`, `canbeempty`, `postProcess`, `postInstruction`, `created`, `containsheader`, `keyData`, `fileExtension`) 
VALUES 
('TD', 'TRD', '11', 'Y', 'temp.tmp_td_demographic', 'DAILY', 'ALGR', 'ALGR', 'csv', 'N', 'Y', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'SEC', '12', 'Y', 'temp.tmp_td_security', 'DAILY', 'ALGR', 'ALGR', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'PRI', '13', 'Y', 'temp.tmp_td_price', 'DAILY', 'ALGR', 'ALGR', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'POS', '14', 'N', 'temp.tmp_td_position', 'DAILY', 'ALGR', 'ALGR', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'TRN', '15', 'Y', 'temp.tmp_td_transaction', 'DAILY', 'ALGR', 'ALGR', 'csv', 'N', 'Y', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'CBL', '16', 'Y', 'temp.tmp_td_unrealized', 'DAILY', 'ALGR', 'ALGR', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'TRD', '21', 'Y', 'temp.tmp_td_demographic', 'DAILY', 'DAFF', 'DAFF', 'csv', 'N', 'Y', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'SEC', '22', 'Y', 'temp.tmp_td_security', 'DAILY', 'DAFF', 'DAFF', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'PRI', '23', 'Y', 'temp.tmp_td_price', 'DAILY', 'DAFF', 'DAFF', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'POS', '24', 'N', 'temp.tmp_td_position', 'DAILY', 'DAFF', 'DAFF', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'TRN', '25', 'Y', 'temp.tmp_td_transaction', 'DAILY', 'DAFF', 'DAFF', 'csv', 'N', 'Y', 'DB', '', now(), 'N', '1', 'csv'),
('TD', 'CBL', '26', 'Y', 'temp.tmp_td_unrealized', 'DAILY', 'DAFF', 'DAFF', 'csv', 'Y', 'N', 'DB', '', now(), 'N', '1', 'csv');
