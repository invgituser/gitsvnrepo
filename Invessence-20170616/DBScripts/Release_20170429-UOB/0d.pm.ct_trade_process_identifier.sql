DROP TABLE IF EXISTS `invdb`.`trade_process_identifier`;

CREATE TABLE `invdb`.`trade_process_identifier` (
  `acctnum` bigint(20) NOT NULL,
  `tradeStatus` varchar(1) DEFAULT NULL COMMENT 'N = New\nA = Allocation\nD = DateBreak\nV = View',
  `processStatus` varchar(1) DEFAULT NULL COMMENT 'N : Not Processed\nY : User Selected\nR : ReBalance\nP : Processed\nS : Sent',
  `reason` varchar(100) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
