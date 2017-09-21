USE `service`;

INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('UOB', 'FILE-PROCESS', 'A', 'VENDOR');

INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'SFTP_HOST', 'uat.invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'SFTP_PASSWORD', 'y91C9ry0PCOfH2AR', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'SFTP_SRC_DIRECTORY', '/data/download/tcm', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'SFTP_USERNAME', 'sftuser', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_PASSWORD', 'T35t123', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_SRC_DIRECTORY', '/data/downloadfiles/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_USERNAME', 'abhangp', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_ISSUE_EMAIL', 'support@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'UPLOAD_SUCCESS_EMAIL', 'operations@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_ISSUE_EMAIL', 'support@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SUCCESS_EMAIL', 'operations@dot.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'LOCAL_SRC_DIRECTORY', 'D:/data/eodfiles', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'UOB', 'FILE-PROCESS', 'VENDOR', 'DOWNLOAD_SFTP_HOST', 'uat.invessence.com', 'N');



DROP TABLE IF EXISTS `service`.`file_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service`.`file_details` (
  `vendor` varchar(20) NOT NULL,
  `fileName` varchar(40) NOT NULL,
  `processId` varchar(40) NOT NULL,
  `process` varchar(40) NOT NULL,
  `fileType` varchar(10) DEFAULT NULL,
  `fileExtension` varchar(10) DEFAULT NULL,
  `fileId` varchar(45) NOT NULL,
  `containsHeader` varchar(1) DEFAULT 'N',
  `active` varchar(1) NOT NULL DEFAULT 'N',
  `seqNo` int(2) DEFAULT NULL,
  `uploadDir` varchar(80) DEFAULT NULL,
  `preDBProcess` varchar(80) DEFAULT NULL,
  `postDBProcess` varchar(80) DEFAULT NULL,
  `preInstruction` varchar(80) DEFAULT NULL,
  `postInstruction` varchar(80) DEFAULT NULL,
  `fileNameAppender` varchar(20) DEFAULT NULL,
  `appenderFormat` varchar(20) DEFAULT NULL,
  `available` varchar(7) DEFAULT NULL,
  `sourcePath` varchar(80) DEFAULT NULL,
  `downloadDir` varchar(80) DEFAULT NULL,
  `loadFormat` varchar(10) DEFAULT NULL,
  `required` varchar(1) DEFAULT 'N',
  `canBeEmpty` varchar(1) DEFAULT NULL,
  `keyData` varchar(5) DEFAULT NULL,
  `encryptionMethod` varchar(10) DEFAULT NULL,
  `encColumns` varchar(20) DEFAULT NULL,
  `tmpTableName` varchar(45) DEFAULT NULL,
  `canBeDups` varchar(1) DEFAULT 'N',
  `delimiter` varchar(5) DEFAULT NULL,
  `delFlagServerFile` varchar(1) DEFAULT 'N',
  `delDayServerFile` int(3) DEFAULT '30',
  `delFlagLocalFile` varchar(1) DEFAULT 'N',
  `delDayLocalFile` int(3) DEFAULT '0',
  `delFlagDecrFile` varchar(1) DEFAULT 'N',
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`vendor`,`fileName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `service`.`file_details` WRITE;
/*!40000 ALTER TABLE `service`.`file_details` DISABLE KEYS */;
INSERT INTO `file_details` VALUES ('BUILDINGBENJAMINS','TRADE_DETAILS','UPLD1','UPLOAD','TEXT','trd','TRADE_FILE','N','A',1,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_buy_sell',NULL,NULL,NULL,'POSTFIX','YYYYMMDD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',30,'N',0,'N',NULL,NULL),
('BUILDINGBENJAMINS','TRADE_SUMMURY_BUY','UPLD1','UPLOAD','TEXT','buy','TRADE_FILE','N','A',2,'D:\\Project\\Abhang\\trade files\\asach','invdb.sp_trade_process_buy_sell',NULL,NULL,NULL,'POSTFIX','YYYYMMDD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',30,'N',0,'N',NULL,NULL),
('BUILDINGBENJAMINS','TRADE_SUMMURY_SELL','UPD1','UPLOAD','TEXT','trd','TRADE_FILE','N','A',3,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_buy_sell',NULL,NULL,NULL,'POSTFIX','YYYYMMDD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','CBL','UPLD3','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'temp.sp_upload_td_unrealized',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N',',','N',30,'N',0,'N',NULL,NULL),
('UOB','CBLCOLON','UPLD2','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'temp.sp_upload_td_unrealized',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N',':','N',30,'N',0,'N',NULL,NULL),
('UOB','CBLDOLLAR','UPLD3','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N','$','N',30,'N',0,'N',NULL,NULL),
('UOB','CBLHASH','UPLD3','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N','#','N',30,'N',0,'N',NULL,NULL),
('UOB','CBLNEGETION','UPLD3','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N','!','N',30,'N',0,'N',NULL,NULL),
('UOB','CBLPIPE','UPLD3','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N','|','N',30,'N',0,'N',NULL,NULL),
('UOB','CBLSTAR','UPLD3','UPLOAD','TEXT','csv','BB_CBL','N','A',1,'D:\\Project\\Abhang\\trade files',NULL,'',NULL,NULL,'POSTFIX','YYYYMMDD',NULL,'/tcm/AGWQ/','/tcm/TEST/','DELIMITED','Y','N',NULL,NULL,NULL,'temp_td_unrealized','N','*','N',30,'N',0,'N',NULL,NULL),
('UOB','CLNTBAL','UPLD1','UPLOAD','TEXT','dat','UOB_CLIENT_ACCT_BALANCE_FILE','N','A',6,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_sell',NULL,NULL,NULL,NULL,'YYYYMMDD',NULL,'/uob/','/uob/','FIXED',NULL,NULL,NULL,NULL,NULL,'temp_td_security','N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','CLNTHOLD','UPLD1','UPLOAD','TEXT','dat','UOB_CLIENT_ACCT_HOLDING_FILE','N','A',6,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_sell',NULL,NULL,NULL,NULL,'YYYYMMDD',NULL,'/uob/','/uob/','FIXED',NULL,NULL,NULL,NULL,NULL,'temp_td_position','N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','CLNTORDEXE','UPLD1','UPLOAD','TEXT','dat','UOB_EOD_ORDER_EXEC_FILE','N','A',6,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_sell',NULL,NULL,NULL,NULL,'YYYYMMDD',NULL,'/uob/','/uob/','FIXED',NULL,NULL,NULL,NULL,NULL,'temp_td_transaction','N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','CLNTSTATUS','UPLD1','UPLOAD','TEXT','dat','UOB_CLIENT_STATUS_FILE','N','A',6,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_sell',NULL,NULL,NULL,NULL,'YYYYMMDD',NULL,'/uob/','/uob/','FIXED',NULL,NULL,NULL,NULL,NULL,'temp_td_demographic','N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','TRADE_DETAILS','DOWD1','DOWNLOAD','TEXT','trd','TRADE_FILE','N','A',4,'D:\\Project\\Abhang\\trade files\\asach','invdb.sp_trade_process_both',NULL,NULL,NULL,'PREFIX','YYYYMMDD',NULL,NULL,NULL,'FIXED',NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','TRADE_SUMMURY_BUY','DOWD1','DOWNLOAD','TEXT','buy','TRADE_FILE','N','A',5,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_buy',NULL,NULL,NULL,'POSTFIX','YYYYMMDD',NULL,NULL,NULL,'FIXED',NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',30,'N',0,'N',NULL,NULL),
('UOB','TRADE_SUMMURY_SELL','DOWD2','DOWNLOAD','TEXT','trd','TRADE_FILE_SELL','Y','A',6,'D:\\Project\\Abhang\\trade files','invdb.sp_trade_process_sell',NULL,NULL,NULL,NULL,'YYYYMMDD',NULL,NULL,NULL,'FIXED',NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,'N',30,'N',0,'N',NULL,NULL);
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
INSERT INTO `service`.`file_process_rules` VALUES ('TRADE_FILE','ACTION','Transaction Action',6,91,91,1,'TEXT',0,'N',NULL,'Left','action'),
('TRADE_FILE','EXCHANGE','Exchange Code',2,21,24,4,'TEXT',0,'N',NULL,'Left','exchange'),
('TRADE_FILE','INDPRICE','Indicative Transaction Price',7,92,102,11,'NUMERIC',5,'N',NULL,'Left','tradeprice'),
('TRADE_FILE','QUANTITY','Order Quantity',5,80,90,11,'TEXT',0,'N',NULL,'Left','qty'),
('TRADE_FILE','RIC','Exchange stock code',3,25,39,15,'TEXT',0,'N',NULL,'Left','ticker'),
('TRADE_FILE','ROBOID','Unique Robo User ID',1,1,20,20,'TEXT',0,'N',NULL,'Left','clientAccountID'),
('TRADE_FILE','STKNAME','Stock name',4,40,79,40,'TEXT',0,'N',NULL,'Left','ticker'),
('TRADE_FILE','TRANSDATE','Transaction Date',8,103,112,10,'TEXT',0,'N',NULL,'Left','tradedate'),
('TRADE_FILE_SELL','ROBOID','Unique Robo User ID',1,1,20,20,'TEXT',0,'Y',',','Left','clientAccountID'),
('TRADE_FILE_SELL','EXCHANGE','Exchange Code',2,21,24,4,'TEXT',0,'Y',',','Left','exchange'),
('TRADE_FILE_SELL','RIC','Exchange stock code',3,25,39,15,'TEXT',0,'Y',',','Left','ticker'),
('TRADE_FILE_SELL','STKNAME','Stock name',4,40,79,40,'TEXT',0,'Y',',','Left','ticker'),
('TRADE_FILE_SELL','QUANTITY','Order Quantity',5,80,90,11,'TEXT',0,'Y',',','Left','qty'),
('TRADE_FILE_SELL','ACTION','Transaction Action',6,91,91,1,'TEXT',0,'Y',',','Left','action'),
('TRADE_FILE_SELL','INDPRICE','Indicative Transaction Price',7,92,102,11,'NUMERIC',0,'Y',',','Left','tradeprice'),
('TRADE_FILE_SELL','TRANSDATE','Transaction Date',8,103,112,10,'TEXT',0,'Y',',','Left','tradedate'),
('UOB_CLIENT_STATUS_FILE','ROBOID','Unique Robo User ID',1,1,20,20,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_CLIENT_STATUS_FILE','STATUS','Status Code',2,21,24,4,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_CLIENT_ACCT_BALANCE_FILE','ROBOID','Unique Robo User ID',1,1,20,20,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_CLIENT_ACCT_BALANCE_FILE','CCYCDE','Currency Code',2,21,24,4,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_CLIENT_ACCT_BALANCE_FILE','ACCTBAL','Account Balance',3,25,39,15,'NUMERIC',2,'N',NULL,'Left',NULL),
('UOB_CLIENT_ACCT_HOLDING_FILE','ROBOID','Unique Robo User ID',1,1,20,20,'TEXT',0,'N',NULL,'Left','accountNumber'),
('UOB_CLIENT_ACCT_HOLDING_FILE','EXCHANGE','Exchange Code',2,21,24,4,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_CLIENT_ACCT_HOLDING_FILE','RIC','Exchange stock code',3,25,39,15,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_CLIENT_ACCT_HOLDING_FILE','STKNAME','Stock name',4,40,79,40,'TEXT',0,'N',NULL,'Left','symbolCUSIP'),
('UOB_CLIENT_ACCT_HOLDING_FILE','QUANTITY','Holding Quantity',5,80,90,11,'TEXT',0,'N',NULL,'Left','quantity'),
('UOB_EOD_ORDER_EXEC_FILE','ROBOID','Unique Robo User ID',1,1,20,20,'TEXT',0,'N',NULL,'Right','accountNumber'),
('UOB_EOD_ORDER_EXEC_FILE','EXCHANGE','Exchange Code',2,21,24,4,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_EOD_ORDER_EXEC_FILE','RIC','Exchange stock code',3,25,39,15,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_EOD_ORDER_EXEC_FILE','STKNAME','Stock name',4,40,79,40,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_EOD_ORDER_EXEC_FILE','QUANTITY','Order Quantity',5,80,90,11,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_EOD_ORDER_EXEC_FILE','ACTION','Transaction Action',6,91,91,1,'TEXT',0,'N',NULL,'Left',NULL),
('UOB_EOD_ORDER_EXEC_FILE','EXEPRICE','Execution Price',7,92,102,11,'NUMERIC',5,'N',NULL,'Left',NULL),
('UOB_EOD_ORDER_EXEC_FILE','TRANSDATE','Transaction Date',8,103,112,10,'TEXT',0,'N',NULL,'Left',NULL),
('BB_CBL','CUSTODIALID','custodialID',1,0,0,0,'TEXT',0,'N',NULL,'Left','custodialID'),
('BB_CBL','BUSINESSDATE','businessDate',2,0,0,0,'TEXT',0,'N',NULL,'Left',''),
('BB_CBL','ACCOUNTNUMBER','accountNumber',3,0,0,0,'TEXT',0,'N',NULL,'Left','accountNumber'),
('BB_CBL','ACCOUNTTYPE','accountType',4,0,0,0,'TEXT',0,'N',NULL,'Left','accountType'),
('BB_CBL','SECURITYTYPE','securityType',5,0,0,0,'TEXT',0,'N',NULL,'Left','securityType'),
('BB_CBL','SYMBOLCUSIP','symbolCUSIP',6,0,0,0,'TEXT',0,'N',NULL,'Left','symbolCUSIP'),
('BB_CBL','CURRENTQUANTITY','currentQuantity',7,0,0,0,'TEXT',0,'N',NULL,'Left','currentQuantity'),
('BB_CBL','COSTBASIS','costBasis',8,0,0,0,'TEXT',0,'N',NULL,'Left','costBasis'),
('BB_CBL','ADJUSTEDCOSTBASIS','adjustedCostBasis',9,0,0,0,'TEXT',0,'N',NULL,'Left','adjustedCostBasis'),
('BB_CBL','UNREALIZEDGAINLOSS','unrealizedGainLoss',10,0,0,0,'TEXT',0,'N',NULL,'Left','unrealizedGainLoss'),
('BB_CBL','COSTBASISFULLYKNOWN','costBasisFullyKnown',11,0,0,0,'TEXT',0,'N',NULL,'Left','costBasisFullyKnown'),
('BB_CBL','CERTIFIEDFLAG','certifiedFlag',12,0,0,0,'TEXT',0,'N',NULL,'Left','certifiedFlag'),
('BB_CBL','ORIGINALPURCHASEDATE','originalPurchaseDate',13,0,0,0,'TEXT',0,'N',NULL,'Left','originalPurchaseDate'),
('BB_CBL','ORIGINALPURCHASEPRICE','originalPurchasePrice',14,0,0,0,'TEXT',0,'N',NULL,'Left','originalPurchasePrice'),
('BB_CBL','WASHSALEINDICATOR','washSaleIndicator',15,0,0,0,'TEXT',0,'N',NULL,'Left','washSaleIndicator'),
('BB_CBL','DISALLOWEDAMOUNT','disallowedAmount',16,0,0,0,'TEXT',0,'N',NULL,'Left','disallowedAmount'),
('BB_CBL','AVERAGEDCOST','averagedCost',17,0,0,0,'TEXT',0,'N',NULL,'Left','averagedCost'),
('BB_CBL','BOOKCOST','bookCost',18,0,0,0,'TEXT',0,'N',NULL,'Left','bookCost'),
('BB_CBL','BOOKPROCEEDS','bookProceeds',19,0,0,0,'TEXT',0,'N',NULL,'Left','bookProceeds'),
('BB_CBL','FIXEDINCOMECOSTADJUSTMENT','fixedIncomeCostAdjustment',20,0,0,0,'TEXT',0,'N',NULL,'Left','fixedIncomeCostAdjustment'),
('BB_CBL','ID','ID',21,0,0,0,'TEXT',0,'N',NULL,'Left','ID'),
('BB_CBL','SECURITYNAME','securityName',22,0,0,0,'TEXT',0,'N',NULL,'Left','securityName'),
('BB_CBL','COVERED','covered',23,0,0,0,'TEXT',0,'N',NULL,'Left','covered'),
('BB_CBL','UNKNOWNTOTAL','unknownTotal',24,0,0,0,'TEXT',0,'N',NULL,'Left','unknownTotal'),
('BB_POS','ACCOUNTNUMBER','accountNumber',1,0,0,0,'TEXT',0,'N',NULL,'Left','accountNumber'),
('BB_POS','ACCOUNTTYPE','accountType',2,0,0,0,'TEXT',0,'N',NULL,'Left','accountType'),
('BB_POS','SECURITYTYPE','securityType',3,0,0,0,'TEXT',0,'N',NULL,'Left','securityType'),
('BB_POS','SYMBOLCUSIP','symbolCUSIP',4,0,0,0,'TEXT',0,'N',NULL,'Left','symbolCUSIP'),
('BB_POS','QUANTITY','quantity',5,0,0,0,'TEXT',0,'N',NULL,'Left','quantity'),
('BB_POS','AMOUNT','amount',6,0,0,0,'TEXT',0,'N',NULL,'Left','amount'),
('BB_TRN','BROKERACCOUNT','brokerAccount',1,0,0,0,'TEXT',0,'N',NULL,'Left','brokerAccount'),
('BB_TRN','FILEDATE','fileDate',2,0,0,0,'TEXT',0,'N',NULL,'Left','fileDate'),
('BB_TRN','ACCOUNTNUMBER','accountNumber',3,0,0,0,'TEXT',0,'N',NULL,'Left','accountNumber'),
('BB_TRN','TRANSACTIONCODE','transactionCode',4,0,0,0,'TEXT',0,'N',NULL,'Left','transactionCode'),
('BB_TRN','CANCELSTATUSFLAG','cancelStatusFlag',5,0,0,0,'TEXT',0,'N',NULL,'Left','cancelStatusFlag'),
('BB_TRN','SYMBOLCUSIP','symbolCUSIP',6,0,0,0,'TEXT',0,'N',NULL,'Left','symbolCUSIP'),
('BB_TRN','SECURITYCODE','securityCode',7,0,0,0,'TEXT',0,'N',NULL,'Left','securityCode'),
('BB_TRN','TRADEDATEPAYDATE','tradeDatePayDate',8,0,0,0,'TEXT',0,'N',NULL,'Left','tradeDatePayDate'),
('BB_TRN','QUANTITY','quantity',9,0,0,0,'TEXT',0,'N',NULL,'Left','quantity'),
('BB_TRN','NETAMOUNT','netAmount',10,0,0,0,'TEXT',0,'N',NULL,'Left','netAmount'),
('BB_TRN','PRINCIPALGROSSAMOUNT','principalGrossAmount',11,0,0,0,'TEXT',0,'N',NULL,'Left','principalGrossAmount'),
('BB_TRN','BROKERFEE','brokerFee',12,0,0,0,'TEXT',0,'N',NULL,'Left','brokerFee'),
('BB_TRN','OTHERFEES','otherFees',13,0,0,0,'TEXT',0,'N',NULL,'Left','otherFees'),
('BB_TRN','SETTLEDATEEXDATE','settleDateExDate',14,0,0,0,'TEXT',0,'N',NULL,'Left','settleDateExDate'),
('BB_TRN','FROMTOACCOUNT','fromToAccount',15,0,0,0,'TEXT',0,'N',NULL,'Left','fromToAccount'),
('BB_TRN','ACCOUNTTYPE','accountType',16,0,0,0,'TEXT',0,'N',NULL,'Left','accountType'),
('BB_TRN','ACCRUEDINTEREST','accruedInterest',17,0,0,0,'TEXT',0,'N',NULL,'Left','accruedInterest'),
('BB_TRN','CLOSINGACCOUNTINGMETHOD','closingAccountingMethod',18,0,0,0,'TEXT',0,'N',NULL,'Left','closingAccountingMethod'),
('BB_TRN','COMMENTS','comments',19,0,0,0,'TEXT',0,'N',NULL,'Left','comments'),
('BB_TRD','COMPANYNAME','companyName',1,0,0,0,'TEXT',0,'N',NULL,'Left','companyName'),
('BB_TRD','LASTNAME','lastName',2,0,0,0,'TEXT',0,'N',NULL,'Left','lastName'),
('BB_TRD','FIRSTNAME','firstName',3,0,0,0,'TEXT',0,'N',NULL,'Left','firstName'),
('BB_TRD','STREET','street',4,0,0,0,'TEXT',0,'N',NULL,'Left','street'),
('BB_TRD','ADDRESS2','address2',5,0,0,0,'TEXT',0,'N',NULL,'Left','address2'),
('BB_TRD','ADDRESS3','address3',6,0,0,0,'TEXT',0,'N',NULL,'Left','address3'),
('BB_TRD','ADDRESS4','address4',7,0,0,0,'TEXT',0,'N',NULL,'Left','address4'),
('BB_TRD','ADDRESS5','address5',8,0,0,0,'TEXT',0,'N',NULL,'Left','address5'),
('BB_TRD','ADDRESS6','address6',9,0,0,0,'TEXT',0,'N',NULL,'Left','address6'),
('BB_TRD','CITY','city',10,0,0,0,'TEXT',0,'N',NULL,'Left','city'),
('BB_TRD','STATE','state',11,0,0,0,'TEXT',0,'N',NULL,'Left','state'),
('BB_TRD','ZIPCODE','zipCode',12,0,0,0,'TEXT',0,'N',NULL,'Left','zipCode'),
('BB_TRD','SSNORTAXID','SSNOrTaxID',13,0,0,0,'TEXT',0,'N',NULL,'Left','SSNOrTaxID'),
('BB_TRD','ACCOUNTNUMBER','accountNumber',14,0,0,0,'TEXT',0,'N',NULL,'Left','accountNumber'),
('BB_TRD','ADVISORID','advisorID',15,0,0,0,'TEXT',0,'N',NULL,'Left','advisorID'),
('BB_TRD','TAXABLE','taxable',16,0,0,0,'TEXT',0,'N',NULL,'Left','taxable'),
('BB_TRD','PHONENUMBER','phoneNumber',17,0,0,0,'TEXT',0,'N',NULL,'Left','phoneNumber'),
('BB_TRD','FAXNUMBER','faxNumber',18,0,0,0,'TEXT',0,'N',NULL,'Left','faxNumber'),
('BB_TRD','ACCOUNTTYPE','accountType',19,0,0,0,'TEXT',0,'N',NULL,'Left','accountType'),
('BB_TRD','OBJECTIVE','objective',20,0,0,0,'TEXT',0,'N',NULL,'Left','objective'),
('BB_TRD','BILLINGACCOUNTNUMBER','billingAccountNumber',21,0,0,0,'TEXT',0,'N',NULL,'Left','billingAccountNumber'),
('BB_TRD','DEFAULTACCOUNT','defaultAccount',22,0,0,0,'TEXT',0,'N',NULL,'Left','defaultAccount'),
('BB_TRD','STATEOFPRIMARYRESIDENCE','stateOfPrimaryResidence',23,0,0,0,'TEXT',0,'N',NULL,'Left','stateOfPrimaryResidence'),
('BB_TRD','PERFORMANCEINCEPTIONDATE','performanceInceptionDate',24,0,0,0,'TEXT',0,'N',NULL,'Left','performanceInceptionDate'),
('BB_TRD','BILLINGINCEPTIONDATE','billingInceptionDate',25,0,0,0,'TEXT',0,'N',NULL,'Left','billingInceptionDate'),
('BB_TRD','FEDERALTAXRATE','federalTaxRate',26,0,0,0,'TEXT',0,'N',NULL,'Left','federalTaxRate'),
('BB_TRD','STATETAXRATE','stateTaxRate',27,0,0,0,'TEXT',0,'N',NULL,'Left','stateTaxRate'),
('BB_TRD','MONTHSINSHORTTERMHOLDINGPERIOD','monthsInShortTermholdingperiod',28,0,0,0,'TEXT',0,'N',NULL,'Left','monthsInShortTermholdingperiod'),
('BB_TRD','FISCALYEAREND','fiscalYearEnd',29,0,0,0,'TEXT',0,'N',NULL,'Left','fiscalYearEnd'),
('BB_TRD','USEAVERAGECOSTACCOUNTING','useAverageCostAccounting',30,0,0,0,'TEXT',0,'N',NULL,'Left','useAverageCostAccounting'),
('BB_TRD','DISPLAYACCRUEDINTEREST','displayAccruedInterest',31,0,0,0,'TEXT',0,'N',NULL,'Left','displayAccruedInterest'),
('BB_TRD','DISPLAYACCRUEDDIVIDENDS','displayAccruedDividends',32,0,0,0,'TEXT',0,'N',NULL,'Left','displayAccruedDividends'),
('BB_TRD','DISPLAYACCRUEDGAINS','displayAccruedGains',33,0,0,0,'TEXT',0,'N',NULL,'Left','displayAccruedGains'),
('BB_TRD','BIRTHDATE','birthDate',34,0,0,0,'TEXT',0,'N',NULL,'Left','birthDate'),
('BB_TRD','DISCOUNTRATE','discountRate',35,0,0,0,'TEXT',0,'N',NULL,'Left','discountRate'),
('BB_TRD','PAYOUTRATE','payoutRate',36,0,0,0,'TEXT',0,'N',NULL,'Left','payoutRate'),
('BB_TRD','COMMENTS','comments',19,0,0,0,'TEXT',0,'N',NULL,'Left','comments');
/*!40000 ALTER TABLE `service`.`file_process_rules` ENABLE KEYS */;
UNLOCK TABLES;


ALTER TABLE `service`.`file_details` 
ADD COLUMN `fileProcessType` VARCHAR(45) NULL DEFAULT NULL AFTER `delFlagDecrFile`;



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
    appenderFormat, available, sourcePath, downloadDir, loadFormat, required, canBeEmpty, keyData, encryptionMethod, 
    encColumns, tmpTableName, canBeDups, delimiter, delFlagServerFile, delDayServerFile, delFlagLocalFile, 
	delDayLocalFile, delFlagDecrFile, fileProcessType, created, lastupdated
	from service.file_details
    where vendor= p_product;
elseif(p_service ='FILE-PROCESS' and p_type='COMMON_DETAILS' and p_info='FILE_RULES')then

	select fcr.fileId, fcr.dataField, fcr.description, fcr.seqNo, fcr.startPos, fcr.endPos, 
    fcr.length, fcr.format, fcr.decimals, fcr.isDelimited, fcr.delimiter, fcr.justified, fcr.dbColumn 
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
