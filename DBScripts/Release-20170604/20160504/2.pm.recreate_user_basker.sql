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
('UOB', 'T.0.SGWealth', 'A', 'Grow Wealth', '0', 'Y', 'N', '2017-01-30 00:00:00', null),
('UOB2', '0.SGWealthSGD', 'I', 'Grow Wealth', '1', 'Y', 'N', '2017-01-30 00:00:00', null),
('UOB2', 'T.0.SGWealthSGD', 'I', 'Grow Wealth', '0', 'Y', 'N', '2017-01-30 00:00:00', null)
;

