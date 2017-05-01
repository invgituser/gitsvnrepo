DELETE FROM `invdb`.`user_basket_access`;

INSERT INTO `invdb`.`user_basket_access` 
(`advisor`, `theme`, `status`, `displayname`, `sortorder`, `primary`, `taxable`, `created`, `lastupdated`) 
VALUES
('BB', '0.BB', 'A', 'BB Strategy', '0', 'Y', 'N', '2017-04-08 20:02:32', null),
('BB', 'T.0.BB', 'A', 'BB Strategy', '0', 'N', 'Y', '2017-04-08 20:02:32', null),
('BB-TCM', '0.TA', 'A', 'TCM Strategy', '0', 'Y', 'N', '2017-04-08 20:02:32', null),
('BB-TCM', 'T.0.TA', 'A', 'TCM Strategy', '0', 'N', 'Y', '2017-04-08 20:02:32', null),
('DEFAULT', '0.Core', 'A', 'Default', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('DEFAULT', 'T.0.Core', 'A', 'Default', '1', 'N', 'Y', '2016-08-01 12:36:46', null),
('Invessence', '0.Income', 'A', 'Generate Income', '1', 'N', 'N', '2016-08-01 12:36:46', null),
('Invessence', '0.Safety', 'A', 'Safety', '1', 'N', 'N', '2016-08-01 12:36:46', null),
('Invessence', '0.Wealth', 'A', 'Core Strategy', '0', 'Y', 'N', '2016-08-01 12:36:46', null),
('Invessence', 'T.0.Income', 'A', 'Generate Income', '2', 'N', 'Y', '2016-08-01 12:36:46', null),
('Invessence', 'T.0.Safety', 'A', 'Safety', '2', 'N', 'Y', '2016-08-01 12:36:46', null),
('Invessence', 'T.0.Wealth', 'A', 'Taxable Core Strategy', '0', 'N', 'Y', '2016-08-01 12:36:46', null),
('UOB', '0.SGWealth', 'A', 'Grow Wealth', '1', 'Y', 'N', '2017-01-30 00:00:00', null),
('UOB', 'T.0.SGWealth', 'A', 'Grow Wealth', '0', 'Y', 'N', '2017-01-30 00:00:00', null),
('UOB.FIX', '1.UOBFIX', 'I', 'UOB Strategy', '0', 'Y', 'N', '2016-11-18 00:00:00', null),
('UOB.FIX', 'T.1.UOBFIX', 'I', 'Taxable UOB Strategy', '1', 'N', 'Y', '2016-11-18 00:00:00', null),
('UOB2', '0.SGWealthSGD', 'I', 'Grow Wealth', '1', 'Y', 'N', '2017-01-30 00:00:00', null),
('UOB2', 'T.0.SGWealthSGD', 'I', 'Grow Wealth', '0', 'Y', 'N', '2017-01-30 00:00:00', null)
;

