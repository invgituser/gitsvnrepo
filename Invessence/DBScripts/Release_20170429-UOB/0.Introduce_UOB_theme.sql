INSERT INTO `invdb`.`user_basket_access` 
(`advisor`, `theme`, `status`, `displayname`, `sortorder`, `primary`, `taxable`, `created`, `lastupdated`) 
VALUES 
('UOB', '0.SGWealth', 'A', 'Grow Wealth', '1', 'Y', 'N', now(), null),
('UOB', 'T.0.SGWealth', 'A', 'Grow Wealth', '0', 'Y', 'N', now(), null),
('UOB.FIX', '1.UOBFIX', 'I', 'UOB Strategy', '0', 'Y', 'N', now(), null),
('UOB.FIX', 'T.1.UOBFIX', 'I', 'Taxable UOB Strategy', '1', 'N', 'Y', now(), null),
('UOB2', '0.SGWealthSGD', 'A', 'Grow Wealth', '1', 'Y', 'N', now(), null),
('UOB2', 'T.0.SGWealthSGD', 'A', 'Grow Wealth', '0', 'Y', 'N', now(), null);
