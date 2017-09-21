DROP TABLE IF EXISTS `invdb`.`web_advisor_mapping`;

CREATE TABLE `invdb`.`web_advisor_mapping` (
  `advisor` varchar(20) NOT NULL,
  `name`    varchar(30) NOT NULL,
  `value` 	varchar(120) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`advisor`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`,`created`) 
VALUES 
('INVESSENCE', 'WEB.URL', 'www.invessence.com',now()),
('INVESSENCE', 'ADVISOR.LOGO', '',now()),
('INVESSENCE', 'EMAIL.OPS', 'operations@invessence.com',now()),
('INVESSENCE', 'EMAIL.SUPPORT', 'support@invessence.com',now()),
('INVESSENCE', 'PHONE.SUPPORT', '(XXX) XXX-XXXX',now()),
('INVESSENCE', 'PHONE.MAIN', '(XXX) XXX-XXXX',now());


INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`,`created`) 
VALUES 
('BB', 'WEB.URL', 'www.buildingbenjamins.com',now()),
('BB', 'ADVISOR.LOGO', '',now()),
('BB', 'EMAIL.OPS', 'operations@traditioncm.com',now()),
('BB', 'EMAIL.SUPPORT', 'support@buildingbenjamins.com',now()),
('BB', 'PHONE.SUPPORT', '(908) 333-4733 ',now()),
('BB', 'PHONE.MAIN', '(908) 333-4733',now());


INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`,`created`) 
VALUES 
('UOB', 'WEB.URL', 'uob.invessence.com:8080',now()),
('UOB', 'ADVISOR.LOGO', '',now()),
('UOB', 'EMAIL.OPS', 'operations@invessence.com',now()),
('UOB', 'EMAIL.SUPPORT', 'support@invessence.com',now()),
('UOB', 'PHONE.SUPPORT', '(XXX) XXX-XXXX ',now()),
('UOB', 'PHONE.MAIN', '(XXX) XXX-XXXX',now());

