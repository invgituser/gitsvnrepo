USE `service`;

set sql_safe_updates=0;
DELETE from `service`.`service_master` where company= 'UOB' and service='FILE-PROCESS';
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('UOB', 'FILE-PROCESS', 'A', 'VENDOR');

DELETE from `service`.`service_config_details` where company= 'UOB' and service='FILE-PROCESS';
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_ISSUE_EMAIL', 'support@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_LOCAL_SRC_DIRECTORY', 'D:/data/eodfiles', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_POST_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_POST_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_PRE_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_PRE_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_SRC_DIRECTORY', '/data/download/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SUCCESS_EMAIL', 'operations@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_ISSUE_EMAIL', 'support@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_LOCAL_SRC_DIRECTORY', 'D:/data/tradefiles', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_POST_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_POST_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_PRE_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_PRE_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_SRC_DIRECTORY', '/data/eodfiles/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SUCCESS_EMAIL', 'operations@dot.com', 'N');


CREATE
     OR REPLACE ALGORITHM = UNDEFINED
    SQL SECURITY DEFINER
VIEW `service`.`vw_invessence_switch` AS
    SELECT
        `invdb`.`invessence_switch`.`name` AS `name`,
        `invdb`.`invessence_switch`.`description` AS `description`,
        `invdb`.`invessence_switch`.`value` AS `value`,
        `invdb`.`invessence_switch`.`format` AS `format`,
        `invdb`.`invessence_switch`.`created` AS `created`,
        `invdb`.`invessence_switch`.`lastupdated` AS `lastupdated`
    FROM
        `invdb`.`invessence_switch`;

DROP TABLE IF EXISTS `service`.`file_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service`.`file_details` (
  `vendor` varchar(20) NOT NULL,
  `fileName` varchar(40) NOT NULL,
  `processId` varchar(40) NOT NULL,
  `process` varchar(40) NOT NULL,
  `seqNo` int(2) NOT NULL,
  `fileId` varchar(45) NOT NULL,
  `fileType` varchar(10) DEFAULT NULL,
  `fileExtension` varchar(10) DEFAULT NULL,
  `containsHeader` varchar(1) DEFAULT 'N',
  `active` varchar(1) NOT NULL DEFAULT 'N',
  `fileNameAppender` varchar(20) DEFAULT NULL,
  `appenderFormat` varchar(20) DEFAULT NULL,
  `available` varchar(7) DEFAULT NULL,
  `uploadDir` varchar(80) DEFAULT NULL COMMENT 'Local Directory',
  `sourcePath` varchar(80) DEFAULT NULL COMMENT 'SFTP Server Directory',
  `downloadDir` varchar(80) DEFAULT NULL,
  `loadFormat` varchar(10) DEFAULT NULL,
  `delimiter` varchar(5) DEFAULT NULL,
  `required` varchar(1) DEFAULT 'N',
  `canBeEmpty` varchar(1) DEFAULT NULL,
  `canBeDups` varchar(1) DEFAULT 'N',
  `encryptionMethod` varchar(10) DEFAULT NULL,
  `tmpTableName` varchar(45) DEFAULT NULL,
  `fileProcessType` varchar(45) DEFAULT NULL,
  `parentPreDBProcess` varchar(80) DEFAULT NULL,
  `parentPostDBProcess` varchar(80) DEFAULT NULL,
  `parentPreInstruction` varchar(80) DEFAULT NULL,
  `parentPostInstruction` varchar(80) DEFAULT NULL,
  `preDBProcess` varchar(80) DEFAULT NULL,
  `postDBProcess` varchar(80) DEFAULT NULL,
  `preInstruction` varchar(80) DEFAULT NULL,
  `postInstruction` varchar(80) DEFAULT NULL,
  `delFlagServerFile` varchar(1) DEFAULT 'N',
  `delDayServerFile` int(3) DEFAULT '30',
  `delFlagLocalFile` varchar(1) DEFAULT 'N',
  `delDayLocalFile` int(3) DEFAULT '0',
  `delFlagDecrFile` varchar(1) DEFAULT 'N',
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`vendor`,`fileName`,`processId`,`seqNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `service`.`file_details` WRITE;
/*!40000 ALTER TABLE `service`.`file_details` DISABLE KEYS */;
INSERT INTO `service`.`file_details` (`vendor`, `fileName`, `processId`, `process`, `seqNo`, `fileId`, `fileType`, `fileExtension`, `containsHeader`, `active`, `fileNameAppender`, `appenderFormat`, `uploadDir`, `sourcePath`, `downloadDir`, `loadFormat`, `required`, `canBeEmpty`, `canBeDups`, `tmpTableName`, `parentPreDBProcess`, `parentPreInstruction`, `postDBProcess`, `delFlagServerFile`, `delDayServerFile`, `delFlagLocalFile`, `delDayLocalFile`, `delFlagDecrFile`) VALUES ('UOB', 'CLNTBAL', 'UPLD1', 'UPLOAD', '2', 'UOB_CLIENT_ACCT_BALANCE_FILE', 'TEXT', 'dat', 'N', 'A', 'PREFIX', 'YYYYMMDD', '/eodFiles', '/uob/', '/uob/', 'FIXED', 'Y', 'Y', 'N', 'temp.tmp_nav_daily', 'temp.parentDBProcedure', '/data/eodFiles/test.sh', 'temp.sp_upload_nav', 'Y', '5', 'N', '0', 'N');
INSERT INTO `service`.`file_details` (`vendor`, `fileName`, `processId`, `process`, `seqNo`, `fileId`, `fileType`, `fileExtension`, `containsHeader`, `active`, `fileNameAppender`, `appenderFormat`, `uploadDir`, `sourcePath`, `downloadDir`, `loadFormat`, `required`, `canBeEmpty`, `canBeDups`, `tmpTableName`, `parentPreDBProcess`, `postDBProcess`, `delFlagServerFile`, `delDayServerFile`, `delFlagLocalFile`, `delDayLocalFile`, `delFlagDecrFile`) VALUES ('UOB', 'CLNTHOLD', 'UPLD1', 'UPLOAD', '3', 'UOB_CLIENT_ACCT_HOLDING_FILE', 'TEXT', 'dat', 'N', 'A', 'PREFIX', 'YYYYMMDD', '/eodFiles', '/uob/', '/uob/', 'FIXED', 'Y', 'N', 'N', 'temp.tmp_position', 'temp.parentDBProcedure', 'temp.sp_upload_position', 'N', '30', 'N', '0', 'N');
INSERT INTO `service`.`file_details` (`vendor`, `fileName`, `processId`, `process`, `seqNo`, `fileId`, `fileType`, `fileExtension`, `containsHeader`, `active`, `fileNameAppender`, `appenderFormat`, `uploadDir`, `loadFormat`, `required`, `canBeDups`, `fileProcessType`, `preDBProcess`, `postDBProcess`, `delFlagServerFile`, `delDayServerFile`, `delFlagLocalFile`, `delDayLocalFile`, `delFlagDecrFile`) VALUES ('UOB', 'CLNTORD', 'DOWD1', 'DOWNLOAD', '1', 'UOB_ORDER_INSTRUCTION_FILE', 'TEXT', 'dat', 'N', 'A', 'PREFIX', 'YYYYMMDD', '/tradeFiles', 'FIXED', 'N', 'N', 'SFTP', 'temp.sp_trade_process_both', 'temp.sp_trade_process_both_update', 'N', '30', 'N', '0', 'N');
INSERT INTO `service`.`file_details` (`vendor`, `fileName`, `processId`, `process`, `seqNo`, `fileId`, `fileType`, `fileExtension`, `containsHeader`, `active`, `fileNameAppender`, `appenderFormat`, `uploadDir`, `sourcePath`, `downloadDir`, `loadFormat`, `required`, `canBeEmpty`, `canBeDups`, `tmpTableName`, `parentPreDBProcess`, `postDBProcess`, `delFlagServerFile`, `delDayServerFile`, `delFlagLocalFile`, `delDayLocalFile`, `delFlagDecrFile`) VALUES ('UOB', 'CLNTORDEXE', 'UPLD1', 'UPLOAD', '4', 'UOB_EOD_ORDER_EXEC_FILE', 'TEXT', 'dat', 'N', 'A', 'POSTFIX', 'YYYYMMDD', '/eodFiles', '/uob/', '/uob/', 'FIXED', 'Y', 'N', 'N', 'temp.tmp_transaction', 'temp.parentDBProcedure', '', 'N', '30', 'N', '0', 'N');
INSERT INTO `service`.`file_details` (`vendor`, `fileName`, `processId`, `process`, `seqNo`, `fileId`, `fileType`, `fileExtension`, `containsHeader`, `active`, `fileNameAppender`, `appenderFormat`, `uploadDir`, `sourcePath`, `downloadDir`, `loadFormat`, `required`, `canBeEmpty`, `canBeDups`, `tmpTableName`, `parentPreDBProcess`, `parentPostInstruction`, `postDBProcess`, `delFlagServerFile`, `delDayServerFile`, `delFlagLocalFile`, `delDayLocalFile`, `delFlagDecrFile`) VALUES ('UOB', 'CLNTSTATUS', 'UPLD1', 'UPLOAD', '1', 'UOB_CLIENT_STATUS_FILE', 'TEXT', 'dat', 'N', 'A', 'POSTFIX', 'YYYYMMDD', '/eodFiles', '/uob/', '/uob/', 'FIXED', 'N', 'N', 'N', 'temp.tmp_client_data', 'temp.parentDBProcedure', '/data/eodFiles/test.sh', 'temp.sp_upload_ext_acct_info', 'N', '30', 'N', '0', 'N');
/*!40000 ALTER TABLE `service`.`file_details` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `service`.`file_process_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service`.`file_process_rules` (
  `fileId` varchar(45) NOT NULL,
  `dataField` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `seqNo` int(11) DEFAULT NULL,
  `startPos` int(11) DEFAULT NULL,
  `endPos` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `format` varchar(45) DEFAULT NULL,
  `decimals` int(11) DEFAULT NULL,
  `isDelimited` varchar(45) DEFAULT NULL,
  `delimiter` varchar(5) DEFAULT NULL,
  `justified` varchar(45) DEFAULT NULL,
  `dbColumn` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fileId`,`dataField`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;
LOCK TABLES `service`.`file_process_rules` WRITE;
/*!40000 ALTER TABLE `file_process_rules` DISABLE KEYS */;
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'ACCOUNTNUMBER', 'accountNumber', '3', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'ACCOUNTTYPE', 'accountType', '4', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountType');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'ADJUSTEDCOSTBASIS', 'adjustedCostBasis', '9', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'adjustedCostBasis');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'AVERAGEDCOST', 'averagedCost', '17', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'averagedCost');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'BOOKCOST', 'bookCost', '18', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'bookCost');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'BOOKPROCEEDS', 'bookProceeds', '19', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'bookProceeds');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'BUSINESSDATE', 'businessDate', '2', '0', '0', '0', 'TEXT', '0', 'N', 'Left', '');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'CERTIFIEDFLAG', 'certifiedFlag', '12', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'certifiedFlag');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'COSTBASIS', 'costBasis', '8', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'costBasis');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'COSTBASISFULLYKNOWN', 'costBasisFullyKnown', '11', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'costBasisFullyKnown');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'COVERED', 'covered', '23', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'covered');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'CURRENTQUANTITY', 'currentQuantity', '7', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'currentQuantity');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'CUSTODIALID', 'custodialID', '1', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'custodialID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'DISALLOWEDAMOUNT', 'disallowedAmount', '16', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'disallowedAmount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'FIXEDINCOMECOSTADJUSTMENT', 'fixedIncomeCostAdjustment', '20', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'fixedIncomeCostAdjustment');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'ID', 'ID', '21', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'ID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'ORIGINALPURCHASEDATE', 'originalPurchaseDate', '13', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'originalPurchaseDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'ORIGINALPURCHASEPRICE', 'originalPurchasePrice', '14', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'originalPurchasePrice');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'SECURITYNAME', 'securityName', '22', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'securityName');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'SECURITYTYPE', 'securityType', '5', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'securityType');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'SYMBOLCUSIP', 'symbolCUSIP', '6', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'symbolCUSIP');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'UNKNOWNTOTAL', 'unknownTotal', '24', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'unknownTotal');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'UNREALIZEDGAINLOSS', 'unrealizedGainLoss', '10', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'unrealizedGainLoss');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_CBL', 'WASHSALEINDICATOR', 'washSaleIndicator', '15', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'washSaleIndicator');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_POS', 'ACCOUNTNUMBER', 'accountNumber', '1', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_POS', 'ACCOUNTTYPE', 'accountType', '2', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountType');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_POS', 'AMOUNT', 'amount', '6', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'amount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_POS', 'QUANTITY', 'quantity', '5', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'quantity');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_POS', 'SECURITYTYPE', 'securityType', '3', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'securityType');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_POS', 'SYMBOLCUSIP', 'symbolCUSIP', '4', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'symbolCUSIP');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ACCOUNTNUMBER', 'accountNumber', '14', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ACCOUNTTYPE', 'accountType', '19', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountType');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ADDRESS2', 'address2', '5', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'address2');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ADDRESS3', 'address3', '6', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'address3');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ADDRESS4', 'address4', '7', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'address4');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ADDRESS5', 'address5', '8', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'address5');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ADDRESS6', 'address6', '9', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'address6');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ADVISORID', 'advisorID', '15', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'advisorID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'BILLINGACCOUNTNUMBER', 'billingAccountNumber', '21', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'billingAccountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'BILLINGINCEPTIONDATE', 'billingInceptionDate', '25', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'billingInceptionDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'BIRTHDATE', 'birthDate', '34', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'birthDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'CITY', 'city', '10', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'city');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'COMMENTS', 'comments', '19', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'comments');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'COMPANYNAME', 'companyName', '1', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'companyName');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'DEFAULTACCOUNT', 'defaultAccount', '22', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'defaultAccount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'DISCOUNTRATE', 'discountRate', '35', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'discountRate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'DISPLAYACCRUEDDIVIDENDS', 'displayAccruedDividends', '32', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'displayAccruedDividends');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'DISPLAYACCRUEDGAINS', 'displayAccruedGains', '33', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'displayAccruedGains');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'DISPLAYACCRUEDINTEREST', 'displayAccruedInterest', '31', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'displayAccruedInterest');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'FAXNUMBER', 'faxNumber', '18', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'faxNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'FEDERALTAXRATE', 'federalTaxRate', '26', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'federalTaxRate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'FIRSTNAME', 'firstName', '3', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'firstName');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'FISCALYEAREND', 'fiscalYearEnd', '29', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'fiscalYearEnd');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'LASTNAME', 'lastName', '2', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'lastName');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'MONTHSINSHORTTERMHOLDINGPERIOD', 'monthsInShortTermholdingperiod', '28', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'monthsInShortTermholdingperiod');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'OBJECTIVE', 'objective', '20', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'objective');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'PAYOUTRATE', 'payoutRate', '36', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'payoutRate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'PERFORMANCEINCEPTIONDATE', 'performanceInceptionDate', '24', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'performanceInceptionDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'PHONENUMBER', 'phoneNumber', '17', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'phoneNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'SSNORTAXID', 'SSNOrTaxID', '13', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'SSNOrTaxID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'STATE', 'state', '11', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'state');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'STATEOFPRIMARYRESIDENCE', 'stateOfPrimaryResidence', '23', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'stateOfPrimaryResidence');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'STATETAXRATE', 'stateTaxRate', '27', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'stateTaxRate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'STREET', 'street', '4', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'street');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'TAXABLE', 'taxable', '16', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'taxable');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'USEAVERAGECOSTACCOUNTING', 'useAverageCostAccounting', '30', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'useAverageCostAccounting');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRD', 'ZIPCODE', 'zipCode', '12', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'zipCode');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'ACCOUNTNUMBER', 'accountNumber', '3', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'ACCOUNTTYPE', 'accountType', '16', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accountType');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'ACCRUEDINTEREST', 'accruedInterest', '17', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'accruedInterest');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'BROKERACCOUNT', 'brokerAccount', '1', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'brokerAccount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'BROKERFEE', 'brokerFee', '12', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'brokerFee');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'CANCELSTATUSFLAG', 'cancelStatusFlag', '5', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'cancelStatusFlag');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'CLOSINGACCOUNTINGMETHOD', 'closingAccountingMethod', '18', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'closingAccountingMethod');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'COMMENTS', 'comments', '19', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'comments');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'FILEDATE', 'fileDate', '2', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'fileDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'FROMTOACCOUNT', 'fromToAccount', '15', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'fromToAccount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'NETAMOUNT', 'netAmount', '10', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'netAmount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'OTHERFEES', 'otherFees', '13', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'otherFees');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'PRINCIPALGROSSAMOUNT', 'principalGrossAmount', '11', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'principalGrossAmount');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'QUANTITY', 'quantity', '9', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'quantity');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'SECURITYCODE', 'securityCode', '7', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'securityCode');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'SETTLEDATEEXDATE', 'settleDateExDate', '14', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'settleDateExDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'SYMBOLCUSIP', 'symbolCUSIP', '6', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'symbolCUSIP');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'TRADEDATEPAYDATE', 'tradeDatePayDate', '8', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'tradeDatePayDate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('BB_TRN', 'TRANSACTIONCODE', 'transactionCode', '4', '0', '0', '0', 'TEXT', '0', 'N', 'Left', 'transactionCode');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'ACTION', 'Transaction Action', '6', '91', '91', '1', 'TEXT', '0', 'N', 'Left', 'action');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'EXCHANGE', 'Exchange Code', '2', '21', '24', '4', 'TEXT', '0', 'N', 'Left', 'exchange');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'INDPRICE', 'Indicative Transaction Price', '7', '92', '102', '11', 'NUMERIC', '5', 'N', 'Left', 'tradeprice');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'QUANTITY', 'Order Quantity', '5', '80', '90', '11', 'TEXT', '0', 'N', 'Left', 'qty');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'RIC', 'Exchange stock code', '3', '25', '39', '15', 'TEXT', '0', 'N', 'Left', 'ticker');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'N', 'Left', 'clientAccountID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'STKNAME', 'Stock name', '4', '40', '79', '40', 'TEXT', '0', 'N', 'Left', 'ticker');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('TRADE_FILE', 'TRANSDATE', 'Transaction Date', '8', '103', '112', '10', 'TEXT', '0', 'N', 'Left', 'tradedate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'ACTION', 'Transaction Action', '6', '91', '91', '1', 'TEXT', '0', 'Y', ',', 'Left', 'action');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'EXCHANGE', 'Exchange Code', '2', '21', '24', '4', 'TEXT', '0', 'Y', ',', 'Left', 'exchange');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'INDPRICE', 'Indicative Transaction Price', '7', '92', '102', '11', 'NUMERIC', '0', 'Y', ',', 'Left', 'tradeprice');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'QUANTITY', 'Order Quantity', '5', '80', '90', '11', 'TEXT', '0', 'Y', ',', 'Left', 'qty');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'RIC', 'Exchange stock code', '3', '25', '39', '15', 'TEXT', '0', 'Y', ',', 'Left', 'ticker');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'Y', ',', 'Left', 'clientAccountID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'STKNAME', 'Stock name', '4', '40', '79', '40', 'TEXT', '0', 'Y', ',', 'Left', 'ticker');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `delimiter`, `justified`, `dbColumn`) VALUES ('TRADE_FILE_SELL', 'TRANSDATE', 'Transaction Date', '8', '103', '112', '10', 'TEXT', '0', 'Y', ',', 'Left', 'tradedate');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_CLIENT_ACCT_BALANCE_FILE', 'ACCTBAL', 'Account Balance', '3', '25', '39', '15', 'NUMERIC', '2', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_CLIENT_ACCT_BALANCE_FILE', 'CCYCDE', 'Currency Code', '2', '21', '24', '4', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_CLIENT_ACCT_BALANCE_FILE', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'N', 'Left', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_CLIENT_ACCT_HOLDING_FILE', 'EXCHANGE', 'Exchange Code', '2', '21', '24', '4', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_CLIENT_ACCT_HOLDING_FILE', 'QUANTITY', 'Holding Quantity', '5', '80', '90', '11', 'TEXT', '0', 'N', 'Left', 'quantity');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_CLIENT_ACCT_HOLDING_FILE', 'RIC', 'Exchange stock code', '3', '25', '39', '15', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_CLIENT_ACCT_HOLDING_FILE', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'N', 'Left', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_CLIENT_ACCT_HOLDING_FILE', 'STKNAME', 'Stock name', '4', '40', '79', '40', 'TEXT', '0', 'N', 'Left', 'symbolCUSIP');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_CLIENT_STATUS_FILE', 'NAME', 'Name', '2', '21', '80', '60', 'TEXT', '0', 'N', 'Left', 'firstName');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_CLIENT_STATUS_FILE', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'N', 'Left', 'acctnum');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_CLIENT_STATUS_FILE', 'STATUS', 'Status Code', '3', '81', '84', '4', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'ACTION', 'Transaction Action', '6', '91', '91', '1', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'EXCHANGE', 'Exchange Code', '2', '21', '24', '4', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'EXEPRICE', 'Execution Price', '7', '92', '102', '11', 'NUMERIC', '5', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'QUANTITY', 'Order Quantity', '5', '80', '90', '11', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'RIC', 'Exchange stock code', '3', '25', '39', '15', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'N', 'Right', 'accountNumber');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'STKNAME', 'Stock name', '4', '40', '79', '40', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`) VALUES ('UOB_EOD_ORDER_EXEC_FILE', 'TRANSDATE', 'Transaction Date', '8', '103', '112', '10', 'TEXT', '0', 'N', 'Left');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'ACTION', 'Transaction Action', '6', '91', '91', '1', 'TEXT', '0', 'N', 'Left', 'action');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'EXCHANGE', 'Exchange Code', '2', '21', '24', '4', 'TEXT', '0', 'N', 'Left', 'exchange');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'INDPRICE', 'Indicative Transaction Price', '7', '92', '102', '11', 'NUMERIC', '5', 'N', 'Left', 'tradeprice');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'QUANTITY', 'Order Quantity', '5', '80', '90', '11', 'TEXT', '0', 'N', 'Left', 'qty');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'RIC', 'Exchange stock code', '3', '25', '39', '15', 'TEXT', '0', 'N', 'Left', 'ticker');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'ROBOID', 'Unique Robo User ID', '1', '1', '20', '20', 'TEXT', '0', 'N', 'Left', 'clientAccountID');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'STKNAME', 'Stock name', '4', '40', '79', '40', 'TEXT', '0', 'N', 'Left', 'ticker');
INSERT INTO `service`.`file_process_rules` (`fileId`, `dataField`, `description`, `seqNo`, `startPos`, `endPos`, `length`, `format`, `decimals`, `isDelimited`, `justified`, `dbColumn`) VALUES ('UOB_ORDER_INSTRUCTION_FILE', 'TRANSDATE', 'Transaction Date', '8', '103', '112', '10', 'TEXT', '0', 'N', 'Left', 'tradedate');
/*!40000 ALTER TABLE `service`.`file_process_rules` ENABLE KEYS */;
UNLOCK TABLES;



USE `service`;
DROP procedure IF EXISTS `sel_service_details`;

DELIMITER $$
USE `service`$$
CREATE PROCEDURE `sel_service_details`(
IN p_product  varchar(50),
IN p_service  varchar(50),
IN p_type  varchar(50),
IN p_info varchar(50)
)
BEGIN
if(p_service ='FILE-PROCESS' and p_type='ADDITIONAL_DETAILS' and p_info='FILE_DETAILS')then

select vendor, fileName, processId, process, fileType, fileExtension, fileId, containsHeader,
    active, seqNo, uploadDir, preDBProcess, postDBProcess, preInstruction, postInstruction, fileNameAppender,
    appenderFormat, available, sourcePath, downloadDir, loadFormat, required, canBeEmpty, encryptionMethod,
    tmpTableName, canBeDups, delimiter, delFlagServerFile, delDayServerFile, delFlagLocalFile,
	delDayLocalFile, delFlagDecrFile, fileProcessType, parentPreDBProcess, parentPostDBProcess, parentPreInstruction,
    parentPostInstruction, created, lastupdated
	from service.file_details
    where vendor= p_product order by processId, seqNo;
elseif(p_service ='FILE-PROCESS' and p_type='COMMON_DETAILS' and p_info='FILE_RULES')then

	select fcr.fileId, fcr.dataField, fcr.description, fcr.seqNo, fcr.startPos, fcr.endPos, 
    fcr.length, fcr.format, fcr.decimals, fcr.isDelimited, fcr.delimiter, fcr.justified,fcr.dbColumn, fcr.isRequired, fcr.needToEncrypt
	from service.file_process_rules fcr 
	where fcr.fileId in(select fileId from service.file_details where vendor= p_product) order by fileId, fcr.seqNo;
elseif(p_service ='DOCUSIGN-SERVICES' and p_type='COMMON_DETAILS' and p_info='DOCUSIGN_MAPPING')then

	select * from service.dc_template_mapping
    where (dbColumn IS NOT NULL or dbColumn != '')
    order by tempCode, role, tab;
 elseif(p_service ='DOCUSIGN-SERVICES' and p_type='ADDITIONAL_DETAILS' and p_info='TEMPLATE_DETAILS')then

	select * from service.dc_template_details
    where company= p_product
    order by company,mode, tempCode;

elseif(p_type='OPERATION_DETAILS' and p_info='OPERATION_DETAILS')then

	select * from service.service_operation_details
    where  status='A' and company=p_product and service=p_service
	order by operation;
end if;

END$$

DELIMITER ;



UPDATE `service`.`file_process_rules` SET `dbColumn`='clientAccountID' WHERE `fileId`='UOB_CLIENT_ACCT_BALANCE_FILE' and`dataField`='ROBOID';
UPDATE `service`.`file_process_rules` SET `dbColumn`='clientAccountID' WHERE `fileId`='UOB_CLIENT_ACCT_HOLDING_FILE' and`dataField`='ROBOID';
UPDATE `service`.`file_process_rules` SET `dbColumn`='clientAccountID' WHERE `fileId`='UOB_EOD_ORDER_EXEC_FILE' and`dataField`='ROBOID';
UPDATE `service`.`file_process_rules` SET `dbColumn`='clientAccountID' WHERE `fileId`='UOB_CLIENT_STATUS_FILE' and`dataField`='ROBOID';
UPDATE `service`.`file_process_rules` SET `dbColumn`='lastName' WHERE `fileId`='UOB_CLIENT_STATUS_FILE' and`dataField`='NAME';


UPDATE `service`.`service_config_details` SET `value`='D:/data/tradeFiles' WHERE `mode`='UAT' and`company`='UOB' and`service`='FILE-PROCESS' and`vendor`='VENDOR' and`name`='DOWNLOAD_LOCAL_SRC_DIRECTORY';



ALTER TABLE `service`.`file_process_rules`
ADD COLUMN `isRequired` VARCHAR(1) NULL DEFAULT 'N' AFTER `dbColumn`,
ADD COLUMN `needToEncrypt` VARCHAR(1) NULL DEFAULT 'N' AFTER `isRequired`;



UPDATE `service`.`service_config_details` SET `value`='sftpuser' WHERE `mode`='UAT' and`company`='UOB' and`service`='FILE-PROCESS' and`vendor`='VENDOR' and`name`='DOWNLOAD_SFTP_USERNAME';
UPDATE `service`.`service_config_details` SET `value`='sftpuser' WHERE `mode`='UAT' and`company`='UOB' and`service`='FILE-PROCESS' and`vendor`='VENDOR' and`name`='UPLOAD_SFTP_USERNAME';
UPDATE `service`.`service_config_details` SET `value`='y91C9ry0PCOfH2AR' WHERE `mode`='UAT' and`company`='UOB' and`service`='FILE-PROCESS' and`vendor`='VENDOR' and`name`='UPLOAD_SFTP_PASSWORD';
UPDATE `service`.`service_config_details` SET `value`='y91C9ry0PCOfH2AR' WHERE `mode`='UAT' and`company`='UOB' and`service`='FILE-PROCESS' and`vendor`='VENDOR' and`name`='DOWNLOAD_SFTP_PASSWORD';


INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'ENCRY_DECRY_KEY', 'aRXDugfr4WQpVrxu', 'N');


INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'BROKER-SERVICES', 'TD', 'ENCRY_DECRY_KEY', 'aRXDugfr4WQpVrxu', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'BROKER-SERVICES', 'TD', 'SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'BROKER-SERVICES', 'TD', 'SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'BROKER-SERVICES', 'TD', 'SFTP_SRC_DIRECTORY', '/data/download/tcm', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'BROKER-SERVICES', 'TD', 'SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ACCOUNT_ID', '18036', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'BASE_URL', 'https://demo.docusign.net/restapi', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAIL', 'prashant@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAILNAME', 'Building Benjamins', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'DOC_PATH', '/inv/www/invessence/ROOT/resources/tcm/docs', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_AFTER', '60', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_WARN', '5', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ID_CHECK_CONF_NAME', 'ID Check $', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'INTEGRATOR_KEY', 'TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'PASSWORD', 'Inv3ss3nc3!', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_DELAY', '1', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_FREQUENCY', '2', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USERNAME', 'prashant@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USE_ACCT_DEFAULT_NOTIFICATION', 'true', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'ENCRY_DECRY_KEY', 'aRXDugfr4WQpVrxu', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'LOCAL_SRC_DIRECTORY', 'C:/TCM/data/eodfiles', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'POST_PROCESSOR', 'sp_td_eod_process', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'POST_SCRIPT', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'PRE_PROCESSOR', 'sp_td_start_process', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'PRE_SCRIPT', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'SFTP_SRC_DIRECTORY', '/data/download/tcm/AGWQT', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'DESTINATION.DIRECTORY', 'D:/PROJECT/SUDHIR/LOGS/CSIDATA/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.DIRECTORY', '/home/abhangp/Prices/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.HOST', 'preuat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.PORT', '22', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.USER', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'FIS', 'DAILY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'BUILDINGBENJAMINS', 'PRICING', 'FIS', 'HISTORY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1600&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'BROKER-WEBSERVICES', 'GEMINI', 'ENCRY_DECRY_KEY', 'aRXDugfr4WQpVrxu', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'BROKER-WEBSERVICES', 'GEMINI', 'FUND_GROUP_NAME', 'landenburgfund', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'BROKER-WEBSERVICES', 'GEMINI', 'SERVICE', 'Inactive', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'BROKER-WEBSERVICES', 'GEMINI', 'WS_END_POINT_URL', 'http://testpawebservices.geminifund.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'DOWNLOAD-SERVICES', 'GEMINI', 'ENCRY_DECRY_KEY', 'aRXDugfr4WQpVrxu', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'DOWNLOAD-SERVICES', 'GEMINI', 'SFTP_HOST', 'sftp.geminifund.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'DOWNLOAD-SERVICES', 'GEMINI', 'SFTP_PASSWORD', 'Vu!oth6BYa7ooa3', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'DOWNLOAD-SERVICES', 'GEMINI', 'SFTP_SRC_DIRECTORY', '/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'Symbil', 'DOWNLOAD-SERVICES', 'GEMINI', 'SFTP_USERNAME', 'Invessence', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ACCOUNT_ID', '18036', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'BASE_URL', 'https://demo.docusign.net/restapi', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAIL', 'docusign@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAILNAME', 'Building Benjamins', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'DOC_PATH', 'D:\\Project\\Abhang\\Project Work\\TCM\\documents', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_AFTER', '60', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_WARN', '5', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ID_CHECK_CONF_NAME', 'ID Check $', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'INTEGRATOR_KEY', 'TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'PASSWORD', 'Inv3ss3nc3!', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_DELAY', '1', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_FREQUENCY', '2', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USERNAME', 'prashant@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USE_ACCT_DEFAULT_NOTIFICATION', 'true', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_ISSUE_EMAIL', 'support@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_LOCAL_SRC_DIRECTORY', 'D:/data/tradeFiles', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_POST_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_POST_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_PRE_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_PRE_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_SRC_DIRECTORY', '/data/download/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SUCCESS_EMAIL', 'operations@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'ENCRY_DECRY_KEY', 'aRXDugfr4WQpVrxu', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_ISSUE_EMAIL', 'support@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_LOCAL_SRC_DIRECTORY', 'D:/data/eodfiles', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_POST_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_POST_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_PRE_DB_PROCESS', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_PRE_INSTRUCTION', '', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_SRC_DIRECTORY', '/data/eodfiles/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SUCCESS_EMAIL', 'operations@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'CSIDATA', 'DESTINATION.DIRECTORY', 'D:/PROJECT/SUDHIR/LOGS/CSIDATA/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.DIRECTORY', '/home/abhangp/Prices/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.HOST', 'preuat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.PORT', '22', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.USER', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'FIS', 'DAILY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'PRICING', 'FIS', 'HISTORY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1600&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');

-- for GPG encryption/decryption
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'GPG_PRIVATE_KEY', 'C:/INV/data/gnupg/secring.gpg', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'GPG_PUBLIC_KEY', 'C:/INV/data/gnupg/pubring.gpg', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('DEV', 'UOB', 'FILE-PROCESS', 'VENDOR', 'GPG_PASSWORD', 'Inv3ss3nc3', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'GPG_PRIVATE_KEY', '/data/gnupg/secring.gpg', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'GPG_PUBLIC_KEY', '/data/gnupg/pubring.gpg', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'GPG_PASSWORD', 'Inv3ss3nc3', 'N');
