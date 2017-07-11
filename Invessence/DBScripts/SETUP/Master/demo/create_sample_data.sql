USE `invdb`;

DELETE FROM `invdb`.`user_logon` where logonid <100;
DELETE FROM `invdb`.`user_advisor_info`  where logonid <100;
DELETE FROM `invdb`.`user_advisor_access`  where logonid <100;
DELETE FROM invdb.role where logonid < 100 ;

INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`stateRegistered`,`advisor`,`rep`,`emailmsgtype`,`access`,`created`)
VALUES
(1,'demo.advisor','c39680887e67fa9860fd197f1279805d','A','Demo','Advisor','demo.advisor@invessence.com','NJ','DEMO','','HTML','advisor',now()),
(2,'bb.advisor','c39680887e67fa9860fd197f1279805d','A','BBDemo','Advisor','bb.advisor@invessence.com','NJ','BB','','HTML','advisor',now()),
(3,'tcm.advisor','c39680887e67fa9860fd197f1279805d','A','TCMDemo','Adviser','tcm.adviser@invessence.com','NJ','BB-TCM','','HTML','advisor',now()),
(4,'uob.advisor','c39680887e67fa9860fd197f1279805d','A','UOB','Advisor','uob.advisor@invessence.com','NJ','UOB','','HTML','advisor',now()),
(5,'citi.advisor','c39680887e67fa9860fd197f1279805d','A','UOB','Advisor','citi.advisor@invessence.com','NJ','CITI','','HTML','advisor',now()),
(6,'ey.advisor','c39680887e67fa9860fd197f1279805d','A','UOB','Advisor','ey.advisor@invessence.com','NJ','EY','','HTML','advisor',now()),
(10,'tcmintrep.advisor','c39680887e67fa9860fd197f1279805d','A','TCMIntRep100','Advisor','tcmintrep.advisor@invessence.com','NJ','BB-TCM','100','HTML','advisor',now()),
(11,'tcmextrep.advisor','c39680887e67fa9860fd197f1279805d','A','TCMExtRep200','Advisor','tcmextrep.advisor@invessence.com','NJ','BB-TCM','200','HTML','advisor',now()),
(12,'csi.advisor','c39680887e67fa9860fd197f1279805d','A','CSI','Advisor','csi.advisor@invessence.com','NJ','UOB','','HTML','advisor',now())
;

INSERT INTO `invdb`.`user_advisor_info` (`logonid`, `advisor`, `rep`, `accttype`, `companyname`, `displayName`, `created`) 
VALUES 
('1', 'DEMO', 'CATCHALL', 'REP', 'Demo Advisers', 'Demo Advisor', now()),
('2', 'BB', 'CATCHALL', 'REP', 'Building Benjamins', 'BBDemo Advisor', now()),
('3', 'BB-TCM', 'CATCHALL', 'REP', 'Tradition Advisors', 'Tradition Advisor', now()),
('4', 'UOB', 'CATCHALL', 'REP', 'UOB Kay Hain', 'UOB Kay Hain', now()),
('5', 'CITI', 'CATCHALL', 'REP', 'Citi Bank', 'Citi Bank', now()),
('6', 'EY', 'CATCHALL', 'REP', 'Ernst and Young', 'Ernst and Young', now()),
('10', 'BB-TCM', '100', 'REP', 'Tradition Adviseres', 'TCMIntRep100 Advisor', now()),
('11', 'BB-TCM', '200', 'REP', 'Tradition Adviseres', 'TCMExtRep200 Advisor', now()),
('12', 'UOB', 'DEMO', 'REP', 'CSI', 'CSI Advisor', now())
;


INSERT INTO `invdb`.`user_advisor_access` (`logonid`, `advisor`, `rep`, `privileges`, `created`) 
VALUES 
('1', '%', '%', 'V', now()),
('2', 'BB%', '%', 'V', now()),
('3', 'BB-TCM', '%', 'V', now()),
('4', 'UOB', '%', 'V', now()),
('5', 'CITI', '%', 'V', now()),
('6', 'EY', '%', 'V', now()),
('10', 'BB-TCM', '100', 'V', now()),
('11', 'BB-TCM', '200', 'V', now()),
('12', '%', '%', 'V', now());



INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`advisor`,`rep`,`stateRegistered`,`emailmsgtype`,`access`,`created`)
VALUES
(50,'mobile.bb','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobilebb@invessence.com','BB','','NJ','HTML','user',now()),
(51,'mobile.tcm','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobiletcm@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(61,'demo.user','c39680887e67fa9860fd197f1279805d','A','DEMO','User','demouser@invessence.com','BB','','NJ','HTML','user',now()),
(62,'bb.user','c39680887e67fa9860fd197f1279805d','A','BB','User','bbuser@invessence.com','BB','','NJ','HTML','user',now()),
(63,'tcmintrep.user','c39680887e67fa9860fd197f1279805d','A','TCMIntRep','User','tcmintrepuser@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(64,'tcmextrep.user','c39680887e67fa9860fd197f1279805d','A','TCMExtRep','User','tcmextrepuser@invessence.com','BB-TCM','200','NJ','HTML','user',now()),
(65,'uob.user','c39680887e67fa9860fd197f1279805d','A','UOB','User','uobuser@invessence.com','UOB','','NJ','HTML','user',now()),
(66,'citi.user','c39680887e67fa9860fd197f1279805d','A','CITI','User','citiuser@invessence.com','CITI','','NJ','HTML','user',now()),
(67,'ey.user','c39680887e67fa9860fd197f1279805d','A','EY','User','eyuser@invessence.com','EY','','NJ','HTML','user',now()),
(70,'bbqa.user','c39680887e67fa9860fd197f1279805d','A','BBQA','User','bbqauser@invessence.com','BB','','NJ','HTML','user',now()),
(71,'tcmintrepqa.user','c39680887e67fa9860fd197f1279805d','A','TCMIntRepQa','User','tcmintrepqauser@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(72,'tcmextrepqa.user','c39680887e67fa9860fd197f1279805d','A','TCMExtRepQa','User','tcmextrepqauser@invessence.com','BB-TCM','200','NJ','HTML','user',now());

INSERT INTO `invdb`.`role`
(`logonid`,`role`,`status`)
VALUES
('1', 'admin', 'A'),
('61', 'admin', 'A')
;

INSERT INTO `invdb`.`role`
(`logonid`,`role`,`status`)
SELECT
`logonid`, 'Demo', 'A'
from invdb.user_logon
where logonid < 100;


DELETE FROM `invdb`.`user_trade_profile` where `acctnum` <= 100;
INSERT INTO `invdb`.`user_trade_profile` 
(`acctnum`, `advisor`, `rep`, `theme`, `firstname`, `lastname`, `portfolioName`, `goal`, `acctType`, `age`, `horizon`, `initialInvestment`, `recurringInvestment`, `experience`, `longTermGoal`, `stayInvested`, `charitablegoals`, `dependent`, `riskIndex`, `tradePreference`, `keepLiquid`, `taxable`, `calcModel`, `assetIndex`, `portfolioIndex`, `goalAmount`, `email`, `ip`, `created`, `lastUpdated`, `managed`, `clientAccountID`, `status`, `customName`)
 VALUES 
 (1, 'BB', '', '0.BB', 'First1', 'Last1', 'Capital Appreciation', 'Retirement', 'Rollover IRA', '53', '32', '57488', '0', '2', '2', '1', '0', '0', '92', 'A', '75', 'N', null, '4', '4', '0', null, null, '2016-10-14', '2017-06-28', 'A', '938102527', 'A', null)
,(2, 'BB-TCM', '101D', '0.TA', 'First2', 'Last2', 'Capital Appreciation', 'Wealth', 'Individual', '0', '10', '100000', '0', '2', '2', '1', '0', '0', '100', 'A', '0', 'N', null, '4', '4', '0', null, null, '2016-10-31', '2017-01-10', 'A', '939690435', 'A', null)
,(3, 'UOB', '', '0.SGWealthSGD', 'First3', 'Last3', 'Grow Wealth', 'Retirement', 'Individual', '50', '20', '50000', '0', '2', '2', '1', '0', '0', '44', 'A', '75', 'N', null, '1', '1', '0', null, null, '2016-10-18', '2016-12-27', 'A', '939538073', 'R', null)
,(4, 'UOB', '', '0.BB', 'Robert', 'Capobianco', 'Aggressive Appreciation', 'Wealth', 'ROTH IRA', '0', '20', '50000', '0', '2', '2', '1', '0', '0', '102', 'A', '75', 'N', null, '5', '5', '0', null, null, '2016-11-08', '2016-11-11', 'A', null, 'O', null)
,(5, 'UOB', '', '0.BB', 'Chris', 'Lengle', 'Capital Appreciation', 'Other', 'Individual', '30', '23', '100000', '0', '2', '2', '1', '0', '0', '88', 'A', '0', 'N', null, '4', '4', null, null, null, '2016-12-27', '2016-12-27', 'A', null, 'O', null)
,(6, 'UOB', '', '0.BB', 'Prashant', 'Mehta', 'Balanced Opportunity', 'College', 'Joint ,(JTWROS)', '0', '10', '51000', '0', '2', '2', '1', '0', '0', '70', 'A', '75', 'N', null, '3', '3', '0', null, null, '2016-11-12', '2016-11-12', 'A', null, 'O', null)
,(7, 'UOB', 'Invessence', '0.Income', null, null, 'Aggressive Appreciation', 'College', null, '30', '5', '100000', '0', '2', '2', '1', '0', '0', '59', 'A', '0', 'N', null, '59', '70', null, null, null, '2017-04-13', '2017-04-13', 'N', null, 'V', null)
,(8, 'Invessence', '', '0.TA', null, null, 'Balanced Opportunity', 'College', null, '30', '14', '100000', '0', '2', '2', '1', '0', null, '71', 'A', '0', 'N', null, '3', '3', null, null, null, '2017-06-30', '2017-06-30', 'N', null, 'V', null)
,(9, 'UOB', '', null, null, null, 'College', 'College', 'Non-Taxable', '30', '20', '100000', '0', '2', '2', '1', '0', '1', '0', 'A', '0', 'N', null, '10', '70', null, null, null, '2017-06-30', '2017-06-30', 'N', null, 'N', null)
,(10, 'BB', '', '0.BB', null, null, 'Income Focus', 'College', null, '30', '16', '100000', '0', '2', '2', '1', '0', null, '82', 'A', '0', 'N', null, '0', '0', null, null, null, '2017-06-30', '2017-06-30', 'N', null, 'V', null)
;

update `invdb`.`user_trade_profile`
set firstname = concat('FirstName-',`acctnum`), `lastname` = concat('LastName-',`acctnum`)
, managed = 'N', status = 'N'
where `acctnum` <= 100
;


DELETE FROM `invdb`.`user_access_role` where `acctnum` <= 100;
INSERT INTO `invdb`.`user_access_role`
(`logonid`,`acctnum`,`role`,`privileges`,`created`)
VALUES 
(62,1,'OWNER','F',now()),
(63,2,'OWNER','F',now()),
(65,3,'OWNER','F',now()),
(65,4,'OWNER','F',now()),
(65,5,'OWNER','F',now()),
(65,6,'OWNER','F',now()),
(65,7,'OWNER','F',now()),
(65,9,'OWNER','F',now()),
(62,10,'OWNER','F',now())
 ;


DELETE FROM `invdb`.`user_risk_questions` where `acctnum` <= 100;
INSERT INTO `invdb`.`user_risk_questions` 
(`acctnum`, `investmentgoal`, `age`, `retireage`, `retired`, `horizon`, `ans1`, `ans2`, `ans3`, `ans4`, `ans5`, `ans6`, `ans7`, `ans8`, `ans9`, `ans10`, `ans11`, `ans12`, `ans13`, `ans14`, `ans15`, `formula`, `risk1`, `risk2`, `risk3`, `risk4`, `risk5`, `risk6`, `risk7`, `risk8`, `risk9`, `risk10`, `risk11`, `risk12`, `risk13`, `risk14`, `risk15`, `totalrisk`, `created`, `lastUpdated`) 
VALUES 
 (1, 'retirement', '40', '91', '0', '51', '40', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '120', '2016-10-10', '2016-10-10')
,(2, 'college', '0', '0', '0', '12', '0', '0', '5', '5', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '24', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '96', '2016-10-10', '2016-10-10')
,(3, 'retirement', '25', '70', '0', '45', '25', '0', '3', '4', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '10', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '105', '2016-10-10', '2016-10-10')
,(4, 'wealth', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2016-10-14', null)
,(5, 'retirement', '40', '91', '0', '51', '40', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '120', '2016-10-10', '2016-10-10')
,(6, 'college', '0', '0', '0', '12', '0', '0', '5', '5', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '24', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '96', '2016-10-10', '2016-10-10')
,(7, 'retirement', '25', '70', '0', '45', '25', '0', '3', '4', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '10', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '105', '2016-10-10', '2016-10-10')
,(8, 'wealth', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2016-10-14', null)
,(9, 'retirement', '25', '70', '0', '45', '25', '0', '3', '4', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '10', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '105', '2016-10-10', '2016-10-10')
,(10, 'Other', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'C', '120', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2016-10-14', null)
;

DELETE FROM `invdb`.`acct_financial` where `acctnum` <= 100;

INSERT INTO `invdb`.`acct_financial` 
(`acctnum`, `dependent`, `estdDependentExpense`, `householdwages`, `otherincome`, `bonusincome`, `interestincome`, `dividentincome`, `rentalIncome`, `totalIncome`, `totalIncomeAnnulized`, `householdPayment`, `otherPropertiesPayment`, `automobilePayment`, `medicalPayment`, `federaltaxes`, `stateTaxes`, `propertyTax`, `otherPropertyTax`, `homeInsurance`, `lifeInsurance`, `autoInsurance`, `educationPayment`, `creditCardPayment`, `miscExpenses`, `totalExpense`, `totalExpenseAnnulized`, `homeEquity`, `autoValue`, `moneyMarket`, `checkingAcct`, `savingAcct`, `investment`, `equityOtherProperties`, `retirementInvestement`, `miscInvestment`, `totalAsset`, `mortgageLoan`, `autoLoan`, `educationLoan`, `creditCardDebt`, `otherPropertiesLoan`, `medicalDebt`, `otherDebt`, `totalDebt`, `liquidnetworth`, `networth`, `created`, `lastupdated`) 
VALUES 
 (1, '1', null, '1000', null, null, null, '1', null, '1001', '12012', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2000', '24000', null, null, null, null, null, '10000', '10000', null, null, '20000', null, null, null, null, null, null, null, '2000', '10000', '10012', '2017-06-12 04:54:07', '2017-06-12 04:55:31')
,(2, '1', null, '2000', null, null, null, '1', null, '2001', '24012', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1000', '12000', null, null, null, null, null, '2000', '2000', null, null, '4000', null, null, null, null, null, null, null, '1000', '2000', '17012', '2017-06-12 04:59:06', '2017-06-12 05:00:08')
,(3, '3', null, '1000', null, null, null, '3', null, '1003', '12036', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1000', '12000', null, null, null, null, null, '1000', '1000', null, null, '2000', null, null, null, null, null, null, null, '1000', '1000', '3036', '2017-06-12 05:10:39', '2017-06-12 05:11:26')
,(4, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, '0', '0', '0', '2017-06-12 05:19:04', null)
,(5, '1', '0', '1000', '0', '0', '0', '1', '0', '1001', '0', null, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1000', '12000', '0', '0', '0', '0', '0', '1000', '1000', '0', '0', '2000', '0', '0', '0', '0', '0', '0', '0', '1000', '1000', '3012', '2017-06-12 05:20:03', '2017-06-12 05:23:19')
,(6, '1', null, '1000', null, null, null, '1', null, '1001', '12012', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1000', '12000', null, null, null, null, null, '1000', '1000', null, null, '2000', null, null, null, null, null, null, null, '1000', '1000', '3012', '2017-06-12 05:24:43', '2017-06-12 05:25:27')
,(7, '1', null, '2000', null, null, null, '1', null, '2001', '24012', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2000', '24000', null, null, null, null, null, '2000', '2000', null, null, '4000', null, null, null, null, null, null, null, '2000', '2000', '6012', '2017-06-12 06:55:07', '2017-06-12 06:56:09')
,(8, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, '0', '0', '0', '2017-06-12 07:32:19', null)
,(9, '0', null, '2000', null, null, null, '0', null, '2000', '24000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1000', '12000', null, null, null, null, null, '2000', '2000', null, null, '4000', null, null, null, null, null, null, null, '1000', '2000', '17000', '2017-06-12 07:34:59', '2017-06-12 07:36:43')
,(10, '1', null, '1000', null, null, null, '1', null, '1001', '12012', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1000', '12000', null, null, null, null, null, '1000', '1000', null, null, '2000', null, null, null, null, null, null, null, '1000', '1000', '3012', '2017-06-12 08:03:50', '2017-06-12 08:18:40')
;

DELETE FROM `invdb`.`ext_acct_info` where `acctnum` <= 100;
INSERT INTO `invdb`.`ext_acct_info` 
(`clientAccountID`, `acctnum`, `status`, `rep`, `email`, `accountType`, `applicantFName`, `applicantMName`, `applicantLName`, `address1`, `address2`, `address3`, `city`, `state`, `zipcode`, `country`, `primaryPhoneNbr`, `secondayPhoneNbr`, `workPhoneNbr`, `faxNbr`, `ssn`, `dob`, `acctType`, `taxable`, `objective`, `dateOpened`, `created`, `lastUpdated`) 
VALUES 
 ('Active1',    '1', 'A', 'AGWQ', 'bchalliburton@gmail.com', 'BENJAMIN HALLIBURTON IRA', 'BENJAMIN', null, 'HALLIBURTON', '12 DEER RUN CIR', '', '', 'CHATHAM', 'NJ', '079281003', null, '', null, null, '', '413295998', '19630218', 'BENJAMIN HALLIBURTON IRA', 'Y', '', '20161030', '2016-10-30 22:35:45', '2016-11-04 16:07:35')
,('Active2', '2', 'X', 'AGWQ', null, 'PRASHANT MEHTA IRA', 'PRASHANT', null, 'MEHTA', '22 DEERFIELD TRL', '', '', 'MONMOUTH JUNCTION', 'NJ', '088522675', null, '', null, null, '', '144646280', '19640104', 'PRASHANT MEHTA IRA', 'Y', '', '20161104', '2016-11-04 16:07:35', null)
,('Active3', '3', 'X', 'AGWQ', 'jigar@invessence.com', 'JIGAR VYAS INDIVIDUAL', 'JIGAR', null, 'VYAS', '693 RIVER RD', '', '', 'CHATHAM', 'NJ', '079281135', null, '', null, null, '', '153702226', '19651129', 'JIGAR VYAS INDIVIDUAL', 'Y', '', '20161104', '2016-11-04 16:07:35', '2016-11-04 16:07:35')
,('Active4', '4', 'X', 'AGWQ', 'bchalliburton@gmail.com', 'BENJAMIN HALLIBURTON INDIVIDUAL', 'BENJAMIN', null, 'HALLIBURTON', '12 DEER RUN CIR', '', '', 'CHATHAM', 'NJ', '079281003', null, '', null, null, '', '413295998', '19630218', 'BENJAMIN HALLIBURTON INDIVIDUAL', 'Y', '', '20161030', '2016-10-30 22:35:45', '2017-01-16 09:00:06')
,('Active5', '5', 'O', 'AGWQ', 'michaelcicconejs@gmail.com', 'MICHAEL CICCONE INDIVIDUAL', 'MICHAEL', null, 'CICCONE', '955 CONCORD WAY', '', '', 'BRANCHBURG', 'NJ', '088534173', null, '', null, null, '', '139822412', '19840908', 'MICHAEL CICCONE INDIVIDUAL', 'Y', '', '20161102', '2016-11-02 17:00:41', '2017-01-17 21:57:35')
,('Active6', '6', 'X', 'AGWQ', 'mfrank@gatewaypartners.com', 'PAMELA FRANK INDIVIDUAL', 'PAMELA', null, 'FRANK', '21 STEWART RD', '', '', 'SHORT HILLS', 'NJ', '070781921', null, '', null, null, '', '146441897', '19570502', 'PAMELA FRANK INDIVIDUAL', 'Y', '', '20161102', '2016-11-02 17:00:41', '2016-11-04 16:07:35')
,('Active7', '7', 'X', 'AGWQ', 'bchalliburton@gmail.com', 'BENJAMIN HALLIBURTON INDIVIDUAL', 'BENJAMIN', null, 'HALLIBURTON', '12 DEER RUN CIR', '', '', 'CHATHAM', 'NJ', '079281003', null, '', null, null, '', '413295998', '19630218', 'BENJAMIN HALLIBURTON INDIVIDUAL', 'Y', '', '20161104', '2016-11-04 15:54:07', '2017-01-16 09:00:06')
,('Active8', '8', 'X', 'AGWQ', 'michaelcicconejs@gmail.com', 'MICHAEL CICCONE INDIVIDUAL', 'MICHAEL', null, 'CICCONE', '955 CONCORD WAY', '', '', 'BRANCHBURG', 'NJ', '088534173', null, '', null, null, '', '139822412', '19840908', 'MICHAEL CICCONE INDIVIDUAL', 'Y', '', '20161115', '2016-11-15 09:00:06', '2017-01-17 21:57:35')
,('Active9', '9', 'A', 'AGWQ', 'sross.endomd@gmail.com', 'SHAUN ROSS INDIVIDUAL', 'SHAUN', null, 'ROSS', '2841 LAKESIDE DR', '', '', 'POPLAR BLUFF', 'MO', '639012233', null, '', null, null, '', '249336865', '19690402', 'SHAUN ROSS INDIVIDUAL', 'Y', '', '20161122', '2016-11-22 09:00:07', '2016-12-05 11:36:32')
,('Active10', '10', 'X', 'AGWQ', 'michaelcicconejs@gmail.com', 'MICHAEL CICCONE JOINT TENANTS WROS', 'MICHAEL', null, 'CICCONE', '955 CONCORD WAY', '', '', 'BRANCHBURG', 'NJ', '088534173', null, '', null, null, '', '139822412', '19840908', 'MICHAEL CICCONE JOINT TENANTS WROS', 'Y', '', '20161205', '2016-12-05 11:35:40', '2017-01-17 21:56:21')
;

UPDATE `invdb`.`ext_acct_info`
set `clientAccountID` = CONCAT('Active',`acctnum`), `status` = 'O', `email` = CONCAT('Active',`acctnum`,'@invessence.com')
   , `applicantFName`= CONCAT('FirstName-',`acctnum`), `applicantMName` = null, `applicantLName` = CONCAT('LastName-',`acctnum`)
where acctnum <= 100;

DELETE FROM `invdb`.`ext_position` where `acctnum` <= 100;
-- BB client's position
INSERT INTO `invdb`.`ext_position` 
(`acctnum`, `clientAccountID`, `currencyPrimary`, `fxRateToBase`, `symbol`, `reportDate`, `purchaseDate`, `side`, `quantity`, `costBasisPrice`, `costBasisMoney`, `markPrice`, `positionValue`, `pnlUnrealized`, `levelOfDetail`, `created`) 
VALUES
 (1, 'Active1', 'USD', '1', 'AGAZX', '20170703', '20160427', 'Long', '234.095', '11.0425254704287', '2585', '11.57', '2708.47915', '123.47915', '14894115251', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'AVRPX', '20170703', '20150410', 'Long', '336.538', '10.024009175784', '3373.46', '10.67', '3590.86046', '217.40046', '13076615835', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'Cash', '20170703', '20170703', 'Long', '617.99', '1', '617.99', '1', '617.99', '0', 'Cash', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'GBFAX', '20170703', '20161230', 'Long', '131.63', '12.3300159538099', '1623', '15.47', '2036.3161', '413.3161', '16132144692', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'LENDX', '20170703', '20160622', 'Long', '492.153', '10.4987676596506', '5167', '10.18', '5010.11754', '-156.88246', '15146696926', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'LJMIX', '20170703', '20160222', 'Long', '237.403', '10.3831880810268', '2465', '11.02', '2616.18106', '151.18106', '14581963837', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'MGCEX', '20170703', '20161230', 'Long', '153.403', '10.5799756197728', '1623', '13.45', '2063.27035', '440.27035', '16132248395', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'SCZ', '20170703', '20151231', 'Long', '10', '50.26', '502.6', '57.41', '574.1', '71.4999999999999', '14288468322', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'SRRIX', '20170703', '20151027', 'Long', '325.885', '10.0239961949768', '3266.67', '10.43', '3398.98055', '132.31055', 'AI56', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'SRRIX', '20170703', '20160324', 'Long', '166.829', '10.3399289092424', '1725', '10.43', '1740.02647', '15.02647', '14731453065', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'SRRIX', '20170703', '20170109', 'Long', '256', '10.0240234375', '2566.15', '10.43', '2670.08', '103.93', 'AI55', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VB', '20170703', '20161230', 'Long', '4', '129.17', '516.68', '136.49', '545.96', '29.2800000000001', '16128013947', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VCMIX', '20170703', '20151229', 'Long', '131.321', '26.9799955833416', '3543.04', '27.74', '3642.84454', '99.8045399999996', '14269553139', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VCMIX', '20170703', '20170203', 'Long', '9.141', '27.3493053276447', '250', '27.74', '253.57134', '3.57133999999999', '16326800528', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VEA', '20170703', '20151231', 'Long', '14', '37.01', '518.14', '41.24', '577.36', '59.22', '14288450838', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VRGIX', '20170703', '20170118', 'Long', '623.145', '10.1485208097634', '6324', '10.59', '6599.10555', '275.105549999999', '16235235039', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VRIIX', '20170703', '20140214', 'Long', '70', '10.0585714285714', '704.1', '10.28', '719.6', '15.4999999999999', '11187656494', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VRPIX', '20170703', '20131216', 'Long', '64', '10.4646875', '669.74', '11.21', '717.44', '47.7', '10910209439', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VSS', '20170703', '20151231', 'Long', '5', '93.36', '466.8', '107.83', '539.15', '72.35', '14288447994', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VTI', '20170703', '20151231', 'Long', '43', '104.93', '4511.99', '124.75', '5364.25', '852.26', '14288452840', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VTV', '20170703', '20151231', 'Long', '39', '81.96', '3196.44', '97.19', '3790.41', '593.97', '14288448071', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VUG', '20170703', '20151231', 'Long', '33', '107.06', '3532.98', '126.67', '4180.11', '647.13', '14288448142', '2017-07-05 10:32:30')
,(1, 'Active1', 'USD', '1', 'VYM', '20170703', '20160427', 'Long', '50', '70.441', '3522.05', '78.5', '3925', '402.95', '14893846417', '2017-07-05 10:32:30')
;

-- BB-TCM client's position
INSERT INTO `invdb`.`ext_position` 
(`acctnum`, `clientAccountID`, `currencyPrimary`, `fxRateToBase`, `symbol`, `reportDate`, `purchaseDate`, `side`, `quantity`, `costBasisPrice`, `costBasisMoney`, `markPrice`, `positionValue`, `pnlUnrealized`, `levelOfDetail`, `created`) 
VALUES
 (2, 'Active2', 'USD', '1', 'AGAZX', '20170703', '20160427', 'Long', '234.095', '11.0425254704287', '2585', '11.57', '2708.47915', '123.47915', '14894115251', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'AVRPX', '20170703', '20150410', 'Long', '336.538', '10.024009175784', '3373.46', '10.67', '3590.86046', '217.40046', '13076615835', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'Cash', '20170703', '20170703', 'Long', '617.99', '1', '617.99', '1', '617.99', '0', 'Cash', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'GBFAX', '20170703', '20161230', 'Long', '131.63', '12.3300159538099', '1623', '15.47', '2036.3161', '413.3161', '16132144692', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'LENDX', '20170703', '20160622', 'Long', '492.153', '10.4987676596506', '5167', '10.18', '5010.11754', '-156.88246', '15146696926', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'LJMIX', '20170703', '20160222', 'Long', '237.403', '10.3831880810268', '2465', '11.02', '2616.18106', '151.18106', '14581963837', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'MGCEX', '20170703', '20161230', 'Long', '153.403', '10.5799756197728', '1623', '13.45', '2063.27035', '440.27035', '16132248395', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'SCZ', '20170703', '20151231', 'Long', '10', '50.26', '502.6', '57.41', '574.1', '71.4999999999999', '14288468322', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'SRRIX', '20170703', '20151027', 'Long', '325.885', '10.0239961949768', '3266.67', '10.43', '3398.98055', '132.31055', 'AI56', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'SRRIX', '20170703', '20160324', 'Long', '166.829', '10.3399289092424', '1725', '10.43', '1740.02647', '15.02647', '14731453065', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'SRRIX', '20170703', '20170109', 'Long', '256', '10.0240234375', '2566.15', '10.43', '2670.08', '103.93', 'AI55', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VB', '20170703', '20161230', 'Long', '4', '129.17', '516.68', '136.49', '545.96', '29.2800000000001', '16128013947', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VCMIX', '20170703', '20151229', 'Long', '131.321', '26.9799955833416', '3543.04', '27.74', '3642.84454', '99.8045399999996', '14269553139', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VCMIX', '20170703', '20170203', 'Long', '9.141', '27.3493053276447', '250', '27.74', '253.57134', '3.57133999999999', '16326800528', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VEA', '20170703', '20151231', 'Long', '14', '37.01', '518.14', '41.24', '577.36', '59.22', '14288450838', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VRGIX', '20170703', '20170118', 'Long', '623.145', '10.1485208097634', '6324', '10.59', '6599.10555', '275.105549999999', '16235235039', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VRIIX', '20170703', '20140214', 'Long', '70', '10.0585714285714', '704.1', '10.28', '719.6', '15.4999999999999', '11187656494', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VRPIX', '20170703', '20131216', 'Long', '64', '10.4646875', '669.74', '11.21', '717.44', '47.7', '10910209439', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VSS', '20170703', '20151231', 'Long', '5', '93.36', '466.8', '107.83', '539.15', '72.35', '14288447994', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VTI', '20170703', '20151231', 'Long', '43', '104.93', '4511.99', '124.75', '5364.25', '852.26', '14288452840', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VTV', '20170703', '20151231', 'Long', '39', '81.96', '3196.44', '97.19', '3790.41', '593.97', '14288448071', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VUG', '20170703', '20151231', 'Long', '33', '107.06', '3532.98', '126.67', '4180.11', '647.13', '14288448142', '2017-07-05 10:32:30')
,(2, 'Active2', 'USD', '1', 'VYM', '20170703', '20160427', 'Long', '50', '70.441', '3522.05', '78.5', '3925', '402.95', '14893846417', '2017-07-05 10:32:30')
;

-- UOB client's position
INSERT INTO `invdb`.`ext_position` 
(`acctnum`, `clientAccountID`, `currencyPrimary`, `fxRateToBase`, `symbol`, `reportDate`, `purchaseDate`, `side`, `quantity`, `costBasisPrice`, `costBasisMoney`, `markPrice`, `positionValue`, `pnlUnrealized`, `levelOfDetail`, `created`) 
VALUES
 (3, 'Active3', 'USD', '1', 'AGAZX', '20170703', '20160427', 'Long', '234.095', '11.0425254704287', '2585', '11.57', '2708.47915', '123.47915', '14894115251', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'AVRPX', '20170703', '20150410', 'Long', '336.538', '10.024009175784', '3373.46', '10.67', '3590.86046', '217.40046', '13076615835', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'Cash', '20170703', '20170703', 'Long', '617.99', '1', '617.99', '1', '617.99', '0', 'Cash', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'GBFAX', '20170703', '20161230', 'Long', '131.63', '12.3300159538099', '1623', '15.47', '2036.3161', '413.3161', '16132144692', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'LENDX', '20170703', '20160622', 'Long', '492.153', '10.4987676596506', '5167', '10.18', '5010.11754', '-156.88246', '15146696926', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'LJMIX', '20170703', '20160222', 'Long', '237.403', '10.3831880810268', '2465', '11.02', '2616.18106', '151.18106', '14581963837', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'MGCEX', '20170703', '20161230', 'Long', '153.403', '10.5799756197728', '1623', '13.45', '2063.27035', '440.27035', '16132248395', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'SCZ', '20170703', '20151231', 'Long', '10', '50.26', '502.6', '57.41', '574.1', '71.4999999999999', '14288468322', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'SRRIX', '20170703', '20151027', 'Long', '325.885', '10.0239961949768', '3266.67', '10.43', '3398.98055', '132.31055', 'AI56', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'SRRIX', '20170703', '20160324', 'Long', '166.829', '10.3399289092424', '1725', '10.43', '1740.02647', '15.02647', '14731453065', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'SRRIX', '20170703', '20170109', 'Long', '256', '10.0240234375', '2566.15', '10.43', '2670.08', '103.93', 'AI55', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VB', '20170703', '20161230', 'Long', '4', '129.17', '516.68', '136.49', '545.96', '29.2800000000001', '16128013947', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VCMIX', '20170703', '20151229', 'Long', '131.321', '26.9799955833416', '3543.04', '27.74', '3642.84454', '99.8045399999996', '14269553139', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VCMIX', '20170703', '20170203', 'Long', '9.141', '27.3493053276447', '250', '27.74', '253.57134', '3.57133999999999', '16326800528', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VEA', '20170703', '20151231', 'Long', '14', '37.01', '518.14', '41.24', '577.36', '59.22', '14288450838', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VRGIX', '20170703', '20170118', 'Long', '623.145', '10.1485208097634', '6324', '10.59', '6599.10555', '275.105549999999', '16235235039', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VRIIX', '20170703', '20140214', 'Long', '70', '10.0585714285714', '704.1', '10.28', '719.6', '15.4999999999999', '11187656494', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VRPIX', '20170703', '20131216', 'Long', '64', '10.4646875', '669.74', '11.21', '717.44', '47.7', '10910209439', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VSS', '20170703', '20151231', 'Long', '5', '93.36', '466.8', '107.83', '539.15', '72.35', '14288447994', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VTI', '20170703', '20151231', 'Long', '43', '104.93', '4511.99', '124.75', '5364.25', '852.26', '14288452840', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VTV', '20170703', '20151231', 'Long', '39', '81.96', '3196.44', '97.19', '3790.41', '593.97', '14288448071', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VUG', '20170703', '20151231', 'Long', '33', '107.06', '3532.98', '126.67', '4180.11', '647.13', '14288448142', '2017-07-05 10:32:30')
,(3, 'Active3', 'USD', '1', 'VYM', '20170703', '20160427', 'Long', '50', '70.441', '3522.05', '78.5', '3925', '402.95', '14893846417', '2017-07-05 10:32:30')
;

-- Fund These acct
INSERT INTO `invdb`.`ext_position` 
(`acctnum`, `clientAccountID`, `currencyPrimary`, `fxRateToBase`, `symbol`, `reportDate`, `purchaseDate`, `side`, `quantity`, `costBasisPrice`, `costBasisMoney`, `markPrice`, `positionValue`, `pnlUnrealized`, `levelOfDetail`, `created`) 
VALUES
 (4, 'Active4', 'USD', '1', 'Cash', '20170703', '20160427', 'Long', '25000', '1.0', '25000', '1.0', '25000', '0', 'Fund', '2017-07-05 10:32:30')
,(5, 'Active5', 'USD', '1', 'Cash', '20170703', '20160427', 'Long', '50000', '1.0', '50000', '1.0', '50000', '0', 'Fund', '2017-07-05 10:32:30')
,(6, 'Active6', 'USD', '1', 'Cash', '20170703', '20160427', 'Long', '100000', '1.0', '100000', '1.0', '100000', '0', 'Fund', '2017-07-05 10:32:30')
,(7, 'Active7', 'USD', '1', 'Cash', '20170703', '20160427', 'Long', '150000', '1.0', '150000', '1.0', '150000', '0', 'Fund', '2017-07-05 10:32:30')
;

update `invdb`.`ext_position`
set `clientAccountID` = CONCAT('Active',`acctnum`)
  , reportDate = `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE')
WHERE `ext_position`.`acctnum` <= 100;

DELETE FROM `invdb`.`ext_nav` where `clientAccountID` in (select `ext_acct_info`.`clientAccountID` FROM `ext_acct_info` where `ext_acct_info`.`acctnum` <= 100);
INSERT INTO `invdb`.`ext_nav`
(`clientAccountID`,`reportDate`,`cash`,`stock`,`funds`,`interestAccrual`,`dividentAccrual`,`total`)
SELECT 
`clientAccountID`, `reportDate`
, SUM(CASE WHEN (upper(`symbol`) = 'CASH') THEN `positionValue`
		   ELSE 0
		END) as `cash`
, SUM(CASE WHEN (upper(`symbol`) = 'CASH') THEN 0
		   ELSE `positionValue`
		END) as `stocks`
, 0, 0, 0, SUM(`positionValue`) as `total`
FROM `invdb`.`ext_position`
WHERE `ext_position`.`acctnum` <= 100
GROUP BY `clientAccountID`, `reportDate`
;

update `invdb`.`user_trade_profile`, `invdb`.`ext_acct_info`
set `user_trade_profile`.`managed` = 'A'
, `user_trade_profile`.`status` = 'O'
, `user_trade_profile`.`clientAccountID` = `ext_acct_info`.`clientAccountID`
where `user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`
AND `user_trade_profile`.`acctnum`  <= 100;
;

update `invdb`.`user_trade_profile`
set `status` = 'A'
where `user_trade_profile`.`clientAccountID` in (select distinct `ext_nav`.`clientAccountID` from `invdb`.`ext_nav`)
AND `user_trade_profile`.`acctnum`  <= 100;
;

update `invdb`.`ext_acct_info`
set `status` = 'A'
where  `ext_acct_info`.`clientAccountID` in (select distinct `ext_nav`.`clientAccountID` from `invdb`.`ext_nav`)
AND `ext_acct_info`.`acctnum`  <= 100;
;

DELETE FROM `invdb`.`trade_process_identifier` where `acctnum` < 100;
INSERT INTO `invdb`.`trade_process_identifier`
(`acctnum`,`tradeStatus`,`processStatus`,`reason`,`created`)
VALUES
 (1,'N','N','Allocation',now())
,(2,'N','N','Allocation',now())
,(3,'N','N','Date',now())
,(4,'N','N','Funded',now())
,(5,'N','N','Funded',now())
,(6,'N','N','Funded',now())
,(7,'N','N','Funded',now())
;

DELETE FROM `invdb`.`user_reports` where `acctnum` < 100;
INSERT INTO `invdb`.`user_reports` 
(`acctnum`, `reportDate`, `reportName`, `filename`, `created`)
SELECT 
`ext_acct_info`.`acctnum`, DATE_FORMAT(now(),'%Y%m%d'), 'Billing', '/reports/Billing.U1288926.20151001.pdf', DATE_FORMAT(now(),'%Y-%m-%d')
from `invdb`.`ext_acct_info`
WHERE `ext_acct_info`.`acctnum` < 100
AND `ext_acct_info`.`status` = 'A'
;

INSERT INTO `invdb`.`user_reports` 
(`acctnum`, `reportDate`, `reportName`, `filename`, `created`)
SELECT 
`ext_acct_info`.`acctnum`, DATE_FORMAT(now(),'%Y%m%d'), 'Fees_Charged', '/reports/U1261187.Fees_Charged.295342.20151103.html', DATE_FORMAT(now(),'%Y-%m-%d')
from `invdb`.`ext_acct_info`
WHERE `ext_acct_info`.`acctnum` < 100
AND `ext_acct_info`.`status` = 'A'
;
