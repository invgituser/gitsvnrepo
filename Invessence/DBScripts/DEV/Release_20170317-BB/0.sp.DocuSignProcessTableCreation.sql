CREATE TABLE invdb.adv_request_document_mappings (
   advisorid int(11) NOT NULL,
   action varchar(45) NOT NULL,
   subaction varchar(45) NOT NULL,
   reqType varchar(45) NOT NULL,
   envelopeHeading varchar(100) DEFAULT NULL,
   seqno int(11) NOT NULL,
   formType varchar(45) NOT NULL,
   PRIMARY KEY (action,subaction,reqType,advisorid)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invdb.dc_requests_final (
   reqId bigint(20) NOT NULL AUTO_INCREMENT,
   refReqId bigint(20) NOT NULL,
   acctnum bigint(20) NOT NULL,
   advisorid int(11) NOT NULL,
   eventNum int(11) NOT NULL,
   caseNumber varchar(45) DEFAULT NULL,
   reqType varchar(45) NOT NULL,
   seqno int(11) NOT NULL,
   envelopeHeading varchar(100) DEFAULT NULL,
   envelopeId varchar(45) DEFAULT NULL,
   status varchar(45) NOT NULL COMMENT 'I = Init, S = Sent, C = Completed, E= Error',
   created date DEFAULT NULL,
   updated date DEFAULT NULL,
   terminalDetails varchar(100) DEFAULT NULL,
   dc_requestsFor varchar(45) DEFAULT NULL,
   dc_requestscol varchar(45) DEFAULT NULL,
   dc_requestFor varchar(45) DEFAULT NULL,
   formType varchar(45) DEFAULT NULL,
   PRIMARY KEY (reqId),
   KEY ak1_dc_request (acctnum,reqType)
 ) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8;


ALTER TABLE invdb.dc_advisor_details
ADD COLUMN advisorName varchar(45) DEFAULT NULL after advisorCode;

ALTER TABLE invdb.dc_advisor_details
ADD COLUMN repId varchar(45) DEFAULT NULL after advisorName;

ALTER TABLE invdb.dc_requests
ADD COLUMN seqno int(11) NOT NULL after reqType;

ALTER TABLE invdb.dc_requests
ADD COLUMN advisorid int(11) NOT NULL after acctnum;

ALTER TABLE invdb.dc_requests
ADD COLUMN action varchar(45) DEFAULT NULL after dc_requestFor;

ALTER TABLE invdb.dc_requests
ADD COLUMN subaction varchar(45) DEFAULT NULL after action;


-- insertion for relation of product,advisorid & repId in dc_advisor_details

INSERT INTO `invdb`.`dc_advisor_details` (`id`, `advisorCode`, `advisorName`, `repId`, `firmName`, `primaryContact`, `email`) VALUES ('1', 'AGWQ', 'BB', 'CATCHALL', 'Tradition Capital Management', 'N/A', 'operations@traditioncm.com');
INSERT INTO `invdb`.`dc_advisor_details` (`id`, `advisorCode`, `advisorName`, `repId`, `firmName`, `primaryContact`, `email`) VALUES ('2', 'TCM100', 'BB-TCM', '100', 'Tradition Capital Management', 'N/A', 'operations@traditioncm.com');
INSERT INTO `invdb`.`dc_advisor_details` (`id`, `advisorCode`, `advisorName`, `repId`, `firmName`, `primaryContact`, `email`) VALUES ('3', 'TCM200', 'BB-TCM', '200', 'Tradition Capital Management', 'N/A', 'operations@traditioncm.com');
INSERT INTO `invdb`.`dc_advisor_details` (`id`, `advisorCode`, `advisorName`, `repId`, `firmName`, `primaryContact`, `email`) VALUES ('4', 'TCMDEF', 'BB-TCM', 'CATCHALL', 'Tradition Capital Management', 'N/A', 'operations@traditioncm.com');

-- insertion for docusign forms in adv_request_document_mappings
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'FUNDING', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'FUNDING', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'FUNDING', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'DEFAULT', 'BB_TCM_ADV_AGREE', 'Please sign account opening document.', '1', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'FUNDING', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'FUNDING', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'IRA_QRP_BEN', 'IRA_QRP_BENE_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'ACCT_APP_NEW', 'ACCT_APPLI_NEW', 'Please sign Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'IRA_NEW', 'IRA_APPLI_NEW', 'Please sign IRA Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'FUNDING', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'ACH_IRA', 'IRA_MOVE_MONEY_NEW', 'Please sign move money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '4', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '5', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'DEFAULT', 'TCM_ADV_2AB', 'Please sign account opening document.', '98', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'ACCT_OPEN', 'DEFAULT', 'TCM_PRIVACY_NOTICE', 'Please sign account opening document.', '99', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('1', 'CHNG_ADDR', 'CHNG_ADDR', 'ACCT_CHNG_ADDR', 'Please sign change address document.', '1000', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'FUNDING', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'DEFAULT', 'TAI_ADV_AGREE', 'Please sign account opening document.', '1', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'FUNDING', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'FUNDING', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'FUNDING', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'FUNDING', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'IRA_NEW', 'IRA_APPLI_NEW', 'Please sign IRA Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'FUNDING', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'IRA_QRP_BEN', 'IRA_QRP_BENE_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'ACCT_APP_NEW', 'ACCT_APPLI_NEW', 'Please sign Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'ACH_IRA', 'IRA_MOVE_MONEY_NEW', 'Please sign move money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '4', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '5', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'DEFAULT', 'TCM_ADV_2AB', 'Please sign account opening document.', '98', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'ACCT_OPEN', 'DEFAULT', 'TCM_PRIVACY_NOTICE', 'Please sign account opening document.', '99', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('2', 'CHNG_ADDR', 'CHNG_ADDR', 'ACCT_CHNG_ADDR', 'Please sign change address document.', '1000', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'FUNDING', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'FUNDING', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'FUNDING', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'FUNDING', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'DEFAULT', 'TAE_ADV_AGREE', 'Please sign account opening document.', '1', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'FUNDING', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'FUNDING', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'IRA_NEW', 'IRA_APPLI_NEW', 'Please sign IRA Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'IRA_QRP_BEN', 'IRA_QRP_BENE_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'ACCT_APP_NEW', 'ACCT_APPLI_NEW', 'Please sign Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'ACH_IRA', 'IRA_MOVE_MONEY_NEW', 'Please sign move money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '4', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '5', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'DEFAULT', 'TCM_ADV_2AB', 'Please sign account opening document.', '98', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'ACCT_OPEN', 'DEFAULT', 'TCM_PRIVACY_NOTICE', 'Please sign account opening document.', '99', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('3', 'CHNG_ADDR', 'CHNG_ADDR', 'ACCT_CHNG_ADDR', 'Please sign change address document.', '1000', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'FUNDING', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'FUNDING', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'FUNDING', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'FUNDING', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'FUNDING', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '1', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'DEFAULT', 'TAI_ADV_AGREE', 'Please sign account opening document.', '1', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'IRA_QRP_BEN', 'IRA_QRP_BENE_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'ACCT_APP_NEW', 'ACCT_APPLI_NEW', 'Please sign Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'IRA_NEW', 'IRA_APPLI_NEW', 'Please sign IRA Account Application document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'FUNDING', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '2', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'ACH', 'MOVE_MONEY_NEW', 'Please sign Move Money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account transfer document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign Agreement document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'ACH_IRA', 'IRA_MOVE_MONEY_NEW', 'Please sign move money document.', '3', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '4', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign Electric Fund Transfer document.', '5', 'DC');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'DEFAULT', 'TCM_ADV_2AB', 'Please sign account opening document.', '98', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'ACCT_OPEN', 'DEFAULT', 'TCM_PRIVACY_NOTICE', 'Please sign account opening document.', '99', 'ADV');
INSERT INTO `invdb`.`adv_request_document_mappings` (`advisorid`, `action`, `subaction`, `reqType`, `envelopeHeading`, `seqno`, `formType`) VALUES ('4', 'CHNG_ADDR', 'CHNG_ADDR', 'ACCT_CHNG_ADDR', 'Please sign change address document.', '1000', 'DC');

-- Updation for web_site_info
UPDATE `invdb`.`web_site_info` SET `value`='Tradition Capital Management' WHERE `url`='localhost' and`name`='WEB.COMPANYNAME';
UPDATE `invdb`.`web_site_info` SET `value`='Welcome to Tradition Capital Management - Activate Your Account' WHERE `url`='localhost' and`name`='SUBJECT.EMAIL.WELCOME';
UPDATE `invdb`.`web_site_info` SET `value`='Tradition Capital Management - Reset Your Password' WHERE `url`='localhost' and`name`='SUBJECT.EMAIL.RESET';
UPDATE `invdb`.`web_site_info` SET `value`='Tradition Capital Management - Account is locked' WHERE `url`='localhost' and`name`='SUBJECT.EMAIL.LOCKED';
UPDATE `invdb`.`web_site_info` SET `value`='Welcome to Tradition Capital Management - Activate Your Account' WHERE `url`='localhost' and`name`='SUBJECT.EMAIL.ACTIVATE';
UPDATE `invdb`.`web_site_info` SET `value`='Welcome to Tradition Capital Management - Activate Your Account' WHERE `url`='demo' and`name`='SUBJECT.EMAIL.ACTIVATE';
UPDATE `invdb`.`web_site_info` SET `value`='Tradition Capital Management - Account is locked' WHERE `url`='demo' and`name`='SUBJECT.EMAIL.LOCKED';
UPDATE `invdb`.`web_site_info` SET `value`='Tradition Capital Management - Reset Your Password' WHERE `url`='demo' and`name`='SUBJECT.EMAIL.RESET';
UPDATE `invdb`.`web_site_info` SET `value`='Welcome to Tradition Capital Management - Activate Your Account' WHERE `url`='demo' and`name`='SUBJECT.EMAIL.WELCOME';
UPDATE `invdb`.`web_site_info` SET `value`='Tradition Capital Management' WHERE `url`='demo' and`name`='WEB.COMPANYNAME';
UPDATE `invdb`.`web_site_info` SET `value`='/javax.faces.resource/images/tcmicon.png.xhtml?ln=tcm' WHERE `url`='demo' and`name`='WEB.FAVICONLOGO';
UPDATE `invdb`.`web_site_info` SET `value`='/javax.faces.resource/images/tcmicon.png.xhtml?ln=tcm' WHERE `url`='localhost' and`name`='WEB.FAVICONLOGO';
UPDATE `invdb`.`web_site_info` SET `value`='(908) 598-0909' WHERE `url`='demo' and`name`='PHONE.SUPPORT';
UPDATE `invdb`.`web_site_info` SET `value`='(908) 598-0909' WHERE `url`='localhost' and`name`='PHONE.SUPPORT';
UPDATE `invdb`.`web_site_info` SET `value`='support@traditioncm.com' WHERE `url`='demo' and`name`='EMAIL.SUPPORT';
UPDATE `invdb`.`web_site_info` SET `value`='support@traditioncm.com' WHERE `url`='localhost' and`name`='EMAIL.SUPPORT';