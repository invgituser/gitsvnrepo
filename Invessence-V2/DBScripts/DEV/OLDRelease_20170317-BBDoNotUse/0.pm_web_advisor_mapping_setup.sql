DELETE FROM `invdb`.`web_advisor_mapping`;

INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB', 'ADVISOR.LOGO', '', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB', 'EMAIL.OPS', 'operations@traditioncm.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB', 'EMAIL.SUPPORT', 'support@buildingbenjamins.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB', 'PHONE.MAIN', '(908) 333-4733', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB', 'PHONE.SUPPORT', '(908) 333-4733 ', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB', 'WEB.URL', 'buildingbenjamins', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB-TCM', 'ADVISOR.LOGO', '', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB-TCM', 'EMAIL.OPS', 'operations@traditioncm.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB-TCM', 'EMAIL.SUPPORT', 'support@buildingbenjamins.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB-TCM', 'PHONE.MAIN', '(908) 333-4733', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB-TCM', 'PHONE.SUPPORT', '(908) 333-4733 ', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('BB-TCM', 'WEB.URL', 'traditionadvisers', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('INVESSENCE', 'ADVISOR.LOGO', '', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('INVESSENCE', 'EMAIL.OPS', 'operations@invessence.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('INVESSENCE', 'EMAIL.SUPPORT', 'support@invessence.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('INVESSENCE', 'PHONE.MAIN', '(XXX) XXX-XXXX', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('INVESSENCE', 'PHONE.SUPPORT', '(XXX) XXX-XXXX', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('INVESSENCE', 'WEB.URL', 'invessence', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('UOB', 'ADVISOR.LOGO', '', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('UOB', 'EMAIL.OPS', 'operations@invessence.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('UOB', 'EMAIL.SUPPORT', 'support@invessence.com', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('UOB', 'PHONE.MAIN', '(XXX) XXX-XXXX', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('UOB', 'PHONE.SUPPORT', '(XXX) XXX-XXXX ', '2017-02-04 13:53:32', 'NULL');
INSERT INTO `invdb`.`web_advisor_mapping` (`advisor`, `name`, `value`, `created`, `updated`) VALUES ('UOB', 'WEB.URL', 'uob', '2017-02-04 13:53:32', 'NULL');

update `invdb`.`web_advisor_mapping`
set value = 'localhost'
where `advisor` = 'BB-TCM' and `name` = 'WEB.URL'