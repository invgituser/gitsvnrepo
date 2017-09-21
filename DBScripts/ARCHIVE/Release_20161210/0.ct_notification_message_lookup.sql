DROP TABLE IF EXISTS `invdb`.`notification_message_lookup`;

CREATE TABLE `invdb`.`notification_message_lookup` (
  `advisor`				varchar(20) NOT NULL,
  `messageType`			varchar(20) NOT NULL,
  `includeAdvisor`		varchar(1) DEFAULT NULL, -- Y = Yes
  `advisorsubject`		varchar(30) DEFAULT NULL,
  `includeAdvisorEmail` varchar(1) DEFAULT NULL, -- Y = Yes
  `emailAdvisorSubject`   varchar(30) DEFAULT NULL,
  `emailAdvisorRecepient` varchar(30) DEFAULT NULL,
  `includeUser`			varchar(1) DEFAULT NULL, -- Y = Yes
  `userSubject`			varchar(30) DEFAULT NULL,
  `includeUserEmail` 	varchar(1) DEFAULT NULL, -- Y = Yes
  `emailUserSubject`	varchar(30) DEFAULT NULL,
  `created` 			datetime DEFAULT NULL,
  `updated` 			datetime DEFAULT NULL,
  PRIMARY KEY (`advisor`,`messageType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`notification_message_lookup`
(`advisor`,`messageType`,`includeAdvisor`,`advisorsubject`,`includeAdvisorEmail`,`emailAdvisorSubject`,`emailAdvisorRecepient`
,`includeUser`,`userSubject`,`includeUserEmail`,`emailUserSubject`,`created`,`updated`)
VALUES
('BB', -- advisor
'PROCESSED', -- messageType
'Y', -- includeAdvisor
'Application Submitted to TD', -- advisorsubject
'Y', -- includeAdvisorEmail
'Application Submitted to TD', -- emailAdvisorSubject
null, -- emailAdvisorRecepient
'N', -- includeUser
null, -- userSubject
null, -- includeUserEmail
null, -- emailUserSubject
now(),
null),
('BB', -- advisor
'REBALANCE', -- messageType
'Y', -- includeAdvisor
'Changed Strategy', -- advisorsubject
'Y', -- includeAdvisorEmail,
'Changed Strategy', -- emailAdvisorSubject
null, -- emailAdvisorRecepient
'N', -- includeUser
null, -- userSubject
null, -- includeUserEmail
null, -- emailUserSubject
now(),
null),
('BB', -- advisor
'FUNDED', -- messageType
'Y', -- includeAdvisor
'Funded Account', -- advisorsubject
'Y', -- includeAdvisorEmail,
'Funded Account', -- emailAdvisorSubject
null, -- emailAdvisorRecepient
'N', -- includeUser
null, -- userSubject
null, -- includeUserEmail
null, -- emailUserSubject
now(),
null),
('BB', -- advisor
'ACTIVE', -- messageType
'Y', -- includeAdvisor
'Account Activated and Funded',-- advisorsubject
'Y', -- includeAdvisorEmail,
'Account Activated and Funded', -- emailAdvisorSubject
null, -- emailAdvisorRecepient
'N', -- includeUser
null, -- userSubject
null, -- includeUserEmail
null, -- emailUserSubject
now(),
null);

