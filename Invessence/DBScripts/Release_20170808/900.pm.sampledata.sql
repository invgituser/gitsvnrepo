DELETE FROM `invdb`.`sec_performance_mapping` where `theme` = '0.SGWealthSGD';

INSERT INTO `invdb`.`sec_performance_mapping`
(`theme`,`ticker`,`displayName`,`status`,`color`,`created`,`lastUpdated`)
VALUES
 ('0.SGWealthSGD','GLD.N','Commodities','A','red',now(),null)
,('0.SGWealthSGD','VUSD.L','Equity','A','red',now(),null)
,('0.SGWealthSGD','IUAG.L','Fixed Income','A','red',now(),null)
;

