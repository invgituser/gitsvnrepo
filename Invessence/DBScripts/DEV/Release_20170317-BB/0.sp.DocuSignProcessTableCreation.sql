CREATE TABLE invdb.adv_request_document_mappings (
    advisorid INT(11) NOT NULL,
    action VARCHAR(45) NOT NULL,
    subaction VARCHAR(45) NOT NULL,
    reqType VARCHAR(45) NOT NULL,
    envelopeHeading VARCHAR(100) DEFAULT NULL,
    seqno INT(11) NOT NULL,
    formType VARCHAR(45) NOT NULL,
    PRIMARY KEY (action , subaction , reqType)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

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
 ) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8


ALTER TABLE invdb.dc_advisor_details
ADD COLUMN advisorName varchar(45) DEFAULT NULL after advisorCode;

ALTER TABLE invdb.dc_advisor_details
ADD COLUMN repId varchar(45) DEFAULT NULL after adisorName;


ALTER TABLE invdb.dc_requests
ADD COLUMN seqno int(11) NOT NULL after reqType;

ALTER TABLE invdb.dc_requests
ADD COLUMN advisorid int(11) NOT NULL after acctnum;

ALTER TABLE invdb.dc_requests
ADD COLUMN action varchar(45) DEFAULT NULL after dc_requestFor;

ALTER TABLE invdb.dc_requests
ADD COLUMN subaction varchar(45) DEFAULT NULL after action;

INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign account opening document.', '3', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign account opening document.', '3', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'ACCT_APP_NEW', 'ACCT_APPLI_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'ACH', 'MOVE_MONEY_NEW', 'Please sign account opening document.', '3', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign account opening document.', '4', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'ACH_IRA', 'IRA_MOVE_MONEY_NEW', 'Please sign account opening document.', '3', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'DEFAULT', 'BB_TCM_ADV_AGREE', 'Please sign account opening document.', '1', 'ADV');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'DEFAULT', 'TCM_ADV_2AB', 'Please sign account opening document.', '98', 'ADV');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'DEFAULT', 'TCM_PRIVACY_NOTICE', 'Please sign account opening document.', '99', 'ADV');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'IRA_NEW', 'IRA_APPLI_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign account opening document.', '5', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'ACCT_OPEN', 'TDTRF', 'TD_TRAN_NEW', 'Please sign account opening document.', '2', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'CHNG_ADDR', 'CHNG_ADDR', 'Change address form', 'Change of Address', '1000', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'FUNDING', 'ACAT', 'ACCT_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'FUNDING', 'ACAT2', 'ACAT_OTHER_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'FUNDING', 'ACH', 'MOVE_MONEY_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'FUNDING', 'ACH_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign TD transfer document.', '2', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'FUNDING', 'REC_EFT', 'ELEC_FUND_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');
INSERT INTO invdb.adv_request_document_mappings (advisorid, action, subaction, reqType, envelopeHeading, seqno, formType) VALUES ('1', 'FUNDING', 'TDTRF', 'TD_TRAN_NEW', 'Please sign TD transfer document.', '1', 'DC');

0.sp.DocuSignProcessTableCreation.sql