DELETE FROM `invdb`.`sec_performance_mapping` where theme = '0.SGWealthSGD';

INSERT INTO `invdb`.`sec_performance_mapping` (`theme`, `type`, `ticker`, `displayName`, `status`, `color`, `created`) VALUES ('0.SGWealthSGD', 'Index', 'GLD.N', 'Commodities', 'A', 'blue', '2017-07-24 16:04:11');
INSERT INTO `invdb`.`sec_performance_mapping` (`theme`, `type`, `ticker`, `displayName`, `status`, `color`, `created`) VALUES ('0.SGWealthSGD', 'Index', 'IUAG.L', 'Fixed Income', 'A', 'green', '2017-07-24 16:04:11');
INSERT INTO `invdb`.`sec_performance_mapping` (`theme`, `type`, `ticker`, `displayName`, `status`, `color`, `created`) VALUES ('0.SGWealthSGD', 'Index', 'VUSD.L', 'Equity', 'A', 'yellow', '2017-07-24 16:04:11');
INSERT INTO `invdb`.`sec_performance_mapping` (`theme`, `type`, `ticker`, `displayName`, `status`, `color`, `created`) VALUES ('0.SGWealthSGD', 'Portfolio', 'ALL', 'Growth', 'A', 'red', '2017-07-24 16:04:11');
