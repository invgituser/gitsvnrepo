-- Table Added for Capture Acct Owner History
drop table if exists invdb.dc_acct_owners_details_history;
CREATE TABLE invdb.dc_acct_owners_details_history (
  srno bigint(20) NOT NULL AUTO_INCREMENT,
  acctnum bigint(20) NOT NULL,
  acctOwnerId int(2) NOT NULL,
  ownership varchar(45) DEFAULT NULL,
  firstName varchar(45) DEFAULT NULL,
  midInitial varchar(45) DEFAULT NULL,
  lastName varchar(45) DEFAULT NULL,
  ssn varchar(45) DEFAULT NULL,
  dob varchar(45) DEFAULT NULL,
  phoneNumber varchar(45) DEFAULT NULL,
  phoneNumberNonUS varchar(45) DEFAULT NULL,
  secondPhoneNumber varchar(45) DEFAULT NULL,
  secondPhoneNumberNonUS varchar(45) DEFAULT NULL,
  emailAddress varchar(45) DEFAULT NULL,
  physicalAddressStreet varchar(50) DEFAULT NULL,
  physicalAddressCity varchar(45) DEFAULT NULL,
  physicalAddressState varchar(45) DEFAULT NULL,
  physicalAddressZipCode varchar(45) DEFAULT NULL,
  mailingAddressStreet varchar(50) DEFAULT NULL,
  mailingAddressCity varchar(45) DEFAULT NULL,
  mailingAddressState varchar(45) DEFAULT NULL,
  mailingAddressZipCode varchar(45) DEFAULT NULL,
  citizenshiId varchar(45) DEFAULT NULL,
  countryOfCitizenship varchar(45) DEFAULT NULL,
  countryOfDualCitizenship varchar(45) DEFAULT NULL,
  countryOfBirth varchar(45) DEFAULT NULL,
  isSPF char(1) DEFAULT NULL,
  spfDetail varchar(255) DEFAULT NULL,
  isDirectorShareholder char(1) DEFAULT NULL,
  directorShareholderDetail varchar(255) DEFAULT NULL,
  bd char(1) DEFAULT NULL,
  bdDetail varchar(255) DEFAULT NULL,
  ownershipPercent double DEFAULT NULL,
  created date DEFAULT NULL,
  createdBy varchar(45) DEFAULT NULL,
  updated date DEFAULT NULL,
  updatedBy varchar(45) DEFAULT NULL,
  PRIMARY KEY (srno)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Create View for get Change Address Dc Request Information
drop view if exists invdb.vwdc_td_chng_addrs_details;
CREATE VIEW invdb.vwdc_td_chng_addrs_details AS
    SELECT 
        daodh.srno AS srno,
        daod.acctnum AS accountNumber,
        GETTITLEREGI(daod.acctnum) AS accountTitle,
        daodh.physicalAddressStreet AS physicalAddressStreet,
        daodh.physicalAddressCity AS physicalAddressCity,
        daodh.physicalAddressState AS physicalAddressState,
        daodh.physicalAddressZipCode AS physicalAddressZipCode,
        daodh.mailingAddressStreet AS mailingAddressStreet,
        daodh.mailingAddressCity AS mailingAddressCity,
        daodh.mailingAddressState AS mailingAddressState,
        daodh.mailingAddressZipCode AS mailingAddressZipCode,
        daod.physicalAddressStreet AS newPhysicalAddressStreet,
        daod.physicalAddressCity AS newPhysicalAddressCity,
        daod.physicalAddressState AS newPhysicalAddressState,
        daod.physicalAddressZipCode AS newPhysicalAddressZipCode,
        daod.mailingAddressStreet AS newMailingAddressStreet,
        daod.mailingAddressCity AS newMailingAddressCity,
        daod.mailingAddressState AS newMailingAddressState,
        daod.mailingAddressZipCode AS newMailingAddressZipCode,
        daod.emailAddress AS newEmail,
        daod.phoneNumber AS newPhoneNumber,
        IFNULL(daod.secondPhoneNumber, '') AS newSecondPhoneNumber,
        daodh.created AS newCreated
    FROM
        (dc_acct_owners_details daod
        JOIN dc_acct_owners_details_history daodh ON ((daod.acctnum = daodh.acctnum)))
    WHERE
        (daodh.srno = (SELECT 
                MAX(daodh2.srno)
            FROM
                dc_acct_owners_details_history daodh2
            WHERE
                (daodh.acctnum = daodh2.acctnum)))
    GROUP BY daodh.srno , daodh.created;
    
-- Insert Queries 
-- Insert for DC Service Definition
INSERT INTO service.service_operation_details (company, service, operation, vendor, status, priority, refValue) VALUES ('BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'ACCT_CHNG_ADDR', 'DOCUSIGN', 'A', '0', 'BB_CHNG_ADDRS');
-- Insert For Change Address Template Details
INSERT INTO service.dc_template_details (mode, company, service, tempCode, tempId, tempName, authRequired, status, remark, created, createdBy, updated, updatedBy, configFile) VALUES ('UAT','BUILDINGBENJAMINS','DOCUSIGN-SERVICES','BB_CHNG_ADDRS','bba60794-5788-4656-8bb7-5857a228a52a','Change Address','Y','A',NULL,NULL,NULL,NULL,NULL,NULL);
-- Insert For Change Addres Template Field Mapping
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountNumber','clientAccountID','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountNumber1','clientAccountID','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountTitle1','accountTitle','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountNumber2','','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountTitle2','','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountNumber3','','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','AccountTitle3','','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','PhysicalAddressStreet','physicalAddressStreet','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','PhysicalAddressCity','physicalAddressCity','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','PhysicalAddressState','physicalAddressState','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','PhysicalAddressZipCode','physicalAddressZipCode','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','MailingAddressStreet','mailingAddressStreet','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','MailingAddressCity','mailingAddressCity','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','MailingAddressState','mailingAddressState','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','MailingAddressZipCode','mailingAddressZipCode','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewPhysicalAddressStreet','newPhysicalAddressStreet','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewPhysicalAddressCity','newPhysicalAddressCity','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewPhysicalAddressState','newPhysicalAddressState','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewPhysicalAddressZipCode','newPhysicalAddressZipCode','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewMailingAddressStreet','newMailingAddressStreet','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewMailingAddressCity','newMailingAddressCity','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewMailingAddressState','newMailingAddressState','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewMailingAddressZipCode','newMailingAddressZipCode','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewPhoneNumber','newPhoneNumber','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewSecondPhoneNumber','newSecondPhoneNumber','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','NewEmail','newEmail','Client','N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_CHNG_ADDRS','Textbox','repcode','advisorCode','Client','N');
-- Insert For Change Address Advisory Notification 
INSERT INTO invdb.notification_message_lookup (advisor, messageType, includeAdvisor, advisorsubject, includeAdvisorEmail, emailAdvisorSubject, includeUser, created) VALUES ('BB', 'CHNGADDRS', 'Y', 'Change Address', 'Y', 'Change Address', 'N', sysdate());

-- Insert For ADV Form Generations
INSERT INTO service.dc_template_details (mode, company, service, tempCode, tempId, tempName, authRequired, status, remark, created, createdBy, updated, updatedBy, configFile) VALUES ('UAT','BUILDINGBENJAMINS','DOCUSIGN-SERVICES','BB_ACCT_ADV','c3818a4d-320f-4a6c-8181-5c0f45206d69','Account ADV Forms','Y','A',NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_ACCT_ADV', 'Textbox', 'FullName', 'fullName', 'Client', 'N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_ACCT_ADV', 'Textbox', 'JointAHFullName', 'fullName', 'Joint', 'N');
INSERT INTO service.dc_template_mapping (tempCode, tab, lable, dbColumn, role, isDisabled) VALUES ('BB_ACCT_ADV', 'Textbox', 'AdvisorName', 'firmName', 'Client', 'N');


INSERT INTO service.service_operation_details (company, service, operation, vendor, status, priority, refValue) VALUES ('BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'ACCT_ADV_FORM', 'DOCUSIGN', 'A', '0', 'BB_ACCT_ADV');


INSERT INTO service.dc_template_details (mode, company, service, tempCode, tempId, tempName, authRequired, status, remark, created, createdBy, updated, updatedBy, configFile) VALUES ('PROD','BUILDINGBENJAMINS','DOCUSIGN-SERVICES','BB_ACCT_ADV','c3818a4d-320f-4a6c-8181-5c0f45206d69','Account ADV Forms','Y','A',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO service.dc_template_details (mode, company, service, tempCode, tempId, tempName, authRequired, status, remark, created, createdBy, updated, updatedBy, configFile) VALUES ('PROD','BUILDINGBENJAMINS','DOCUSIGN-SERVICES','BB_CHNG_ADDRS','bba60794-5788-4656-8bb7-5857a228a52a','Change Address','Y','A',NULL,NULL,NULL,NULL,NULL,NULL);