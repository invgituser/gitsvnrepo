DELETE FROM `invdb`.`user_logon` where logonid <100;
DELETE FROM `invdb`.`user_advisor_info`  where logonid <100;
DELETE FROM `invdb`.`user_advisor_access`  where logonid <100;
DELETE FROM invdb.role where logonid < 100 ;

INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`stateRegistered`,`advisor`,`rep`,`emailmsgtype`,`access`,`created`)
VALUES
(1,'demo.advisor','c39680887e67fa9860fd197f1279805d','A','Demo','Advisor','demoadvisor@invessence.com','NJ','DEMO','','HTML','advisor',now()),
(2,'bb.advisor','c39680887e67fa9860fd197f1279805d','A','BBDemo','Advisor','bb.advisor@invessence.com','NJ','BB','','HTML','advisor',now()),
(3,'tcm.advisor','c39680887e67fa9860fd197f1279805d','A','TCMDemo','Adviser','tcmadviser@invessence.com','NJ','BB-TCM','','HTML','advisor',now()),
(4,'uob.advisor','c39680887e67fa9860fd197f1279805d','A','UOB','Advisor','uob@invessence.com','NJ','UOB','','HTML','advisor',now()),
(5,'citi.advisor','c39680887e67fa9860fd197f1279805d','A','UOB','Advisor','citi@invessence.com','NJ','CITI','','HTML','advisor',now()),
(6,'ey.advisor','c39680887e67fa9860fd197f1279805d','A','UOB','Advisor','ey@invessence.com','NJ','EY','','HTML','advisor',now()),
(10,'tcmintrep.advisor','c39680887e67fa9860fd197f1279805d','A','TCMIntRep100','Advisor','tcmintrep@invessence.com','NJ','BB-TCM','100','HTML','advisor',now()),
(11,'tcmextrep.advisor','c39680887e67fa9860fd197f1279805d','A','TCMExtRep200','Advisor','tcmextrep@invessence.com','NJ','BB-TCM','200','HTML','advisor',now())
;

INSERT INTO `invdb`.`user_advisor_info` (`logonid`, `advisor`, `rep`, `accttype`, `companyname`, `displayName`, `created`) 
VALUES 
('1', 'DEMO', 'CATCHALL', 'REP', 'Demo Adviseres', 'Demo Advisor', now()),
('2', 'BB', 'CATCHALL', 'REP', 'Building Benjamins', 'BBDemo Advisor', now()),
('3', 'BB-TCM', 'CATCHALL', 'REP', 'Tradition Advisors', 'Tradition Advisor', now()),
('4', 'UOB', 'CATCHALL', 'REP', 'UOB Kay Hain', 'UOB Kay Hain', now()),
('5', 'CITI', 'CATCHALL', 'REP', 'Citi Bank', 'Citi Bank', now()),
('6', 'EY', 'CATCHALL', 'REP', 'Ernst and Young', 'Ernst and Young', now()),
('10', 'BB-TCM', '100', 'REP', 'Tradition Adviseres', 'TCMIntRep100 Advisor', now()),
('11', 'BB-TCM', '200', 'REP', 'Tradition Adviseres', 'TCMExtRep200 Advisor', now());


INSERT INTO `invdb`.`user_advisor_access` (`logonid`, `advisor`, `rep`, `privileges`, `created`) 
VALUES 
('1', '%', '%', 'V', now()),
('2', 'BB%', '%', 'V', now()),
('3', 'BB-TCM', '%', 'V', now()),
('4', 'UOB', '%', 'V', now()),
('5', 'CITI', '%', 'V', now()),
('6', 'EY', '%', 'V', now()),
('10', 'BB-TCM', '100', 'V', now()),
('11', 'BB-TCM', '200', 'V', now());

INSERT INTO `invdb`.`role`
(`logonid`,`role`,`status`)
VALUES
('1', 'admin', 'A'),
('1', 'Demo', 'A'),
('2', 'admin', 'A'),
('3', 'admin', 'A'),
('4', 'admin', 'A'),
('5', 'admin', 'A'),
('6', 'admin', 'A')
;


INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`advisor`,`rep`,`stateRegistered`,`emailmsgtype`,`access`,`created`)
VALUES
(50,'mobile.bb','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobilebb@invessence.com','BB','','NJ','HTML','user',now()),
(51,'mobile.tcm','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobiletcm@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(61,'demo.user','c39680887e67fa9860fd197f1279805d','A','DEMO','User','demouser@invessence.com','DEMO','','NJ','HTML','user',now()),
(62,'bb.user','c39680887e67fa9860fd197f1279805d','A','BB','User','bbuser@invessence.com','BB','','NJ','HTML','user',now()),
(63,'tcmintrep.user','c39680887e67fa9860fd197f1279805d','A','TCMIntRep','User','tcmintrepuser@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(64,'tcmextrep.user','c39680887e67fa9860fd197f1279805d','A','TCMExtRep','User','tcmextrepuser@invessence.com','BB-TCM','200','NJ','HTML','user',now()),
(65,'uob.user','c39680887e67fa9860fd197f1279805d','A','UOB','User','uobuser@invessence.com','UOB','','NJ','HTML','user',now()),
(66,'citi.user','c39680887e67fa9860fd197f1279805d','A','CITI','User','citiuser@invessence.com','CITI','','NJ','HTML','user',now()),
(67,'ey.user','c39680887e67fa9860fd197f1279805d','A','EY','User','eyuser@invessence.com','EY','','NJ','HTML','user',now()),
(70,'bbqa.user','c39680887e67fa9860fd197f1279805d','A','BBQA','User','bbqauser@invessence.com','BB','','NJ','HTML','user',now()),
(71,'tcmintrepqa.user','c39680887e67fa9860fd197f1279805d','A','TCMIntRepQa','User','tcmintrepqauser@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(72,'tcmextrepqa.user','c39680887e67fa9860fd197f1279805d','A','TCMExtRepQa','User','tcmextrepqauser@invessence.com','BB-TCM','200','NJ','HTML','user',now());


DELETE FROM `invdb`.`user_basket_access`;

INSERT INTO `invdb`.`user_basket_access` 
(`advisor`, `theme`, `status`, `displayname`, `sortorder`, `primary`, `taxable`, `created`, `lastupdated`) 
VALUES 
('BB', '0.BB', 'A', 'BB Strategy', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('BB', 'T.0.BB', 'A', 'BB Strategy', '0', 'N', 'Y', '2016-08-01 12:36:46', null),
('BB-TCM', '0.BB', 'A', 'BB Strategy', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('BB-TCM', 'T.0.BB', 'A', 'BB Strategy', '0', 'N', 'Y', '2016-08-01 12:36:46', null),
('CITI', '0.CITI', 'A', 'Grow Wealth', '1', 'Y', 'N', '2017-01-30 00:00:00', null),
('CITI', 'T.0.CITI', 'A', 'Grow Wealth', '0', 'Y', 'N', '2017-01-30 00:00:00', null),
('DEFAULT', '0.Core', 'A', 'Default', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('DEFAULT', 'T.0.Core', 'A', 'Default', '1', 'N', 'Y', '2016-08-01 12:36:46', null),
('SAMPLE', '0.PREDEFINED', 'A', 'Predefined', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('SAMPLE', 'T.0.PREDEFINED', 'A', 'Predefined', '1', 'N', 'Y', '2016-08-01 12:36:46', null),
('SAMPLE', '0.OPTIMIZED', 'A', 'Optimized', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('SAMPLE', 'T.0.OPTIMIZED', 'A', 'Optimized', '1', 'N', 'Y', '2016-08-01 12:36:46', null),
('Demo', '0.Demo', 'A', 'Core Strategy', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('Demo', 'T.0.Demo', 'A', 'Taxable Core Strategy', '0', 'N', 'Y', '2016-08-01 12:36:46', null),
('Invessence', '0.Income', 'A', 'Generate Income', '1', 'N', 'N', '2017-01-05 18:15:21', null),
('Invessence', '0.Safety', 'A', 'Safety', '1', 'N', 'N', '2017-01-05 18:15:21', null),
('Invessence', '0.Wealth', 'A', 'Build Wealth', '0', 'Y', 'N', '2017-01-05 18:15:21', null),
('Invessence', 'T.0.Income', 'A', 'Generate Income', '2', 'N', 'Y', '2017-01-05 18:15:21', null),
('Invessence', 'T.0.Safety', 'A', 'Safety', '2', 'N', 'Y', '2017-01-05 18:15:21', null),
('Invessence', 'T.0.Wealth', 'A', 'Build Wealth', '0', 'N', 'Y', '2017-01-05 18:15:21', null),
('UOB', '0.SGWealth', 'A', 'Grow Wealth', '1', 'Y', 'N', '2017-01-30 00:00:00', null),
('UOB', 'T.0.SGWealth', 'A', 'Grow Wealth', '0', 'Y', 'N', '2017-01-30 00:00:00', null)
;


