

CREATE TABLE `invdb`.`virtual_portfolio_20171130`
AS  
SELECT `virtual_portfolio`.`acctnum`,
    `virtual_portfolio`.`itemnum`,
    `virtual_portfolio`.`instrumentid`,
    `virtual_portfolio`.`ticker`,
    `virtual_portfolio`.`active`,
    `virtual_portfolio`.`qty`,
    `virtual_portfolio`.`weight`,
    `virtual_portfolio`.`tradeprice`,
    `virtual_portfolio`.`investmentvalue`,
    `virtual_portfolio`.`created`,
    `virtual_portfolio`.`lastupdated`
FROM `invdb`.`virtual_portfolio`;

DROP TABLE IF EXISTS `invdb`.`virtual_portfolio`;

CREATE TABLE `invdb`.`virtual_portfolio` (
  `acctnum` bigint(20) NOT NULL,
  `itemnum` int(10) unsigned NOT NULL,
  `ticker` varchar(20) NOT NULL,
  `active` varchar(1) NOT NULL COMMENT 'Valid\\\\\\\\nA - Active\\\\\\\\nI - Inactive\\\\\\\\n',
  `tradeCurrency` varchar(3) DEFAULT 'USD',
  `qty` int(11) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `tradeprice` float DEFAULT NULL,
  `investmentvalue` double DEFAULT NULL,
  `settleCurrency` varchar(3) DEFAULT 'USD',
  `exchangeRate` double DEFAULT '1',
  `settleQty` double DEFAULT NULL,
  `settlePrice` double DEFAULT NULL,
  `settleValue` double DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`ticker`, `tradeCurrency`, `itemnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`virtual_portfolio`
(`acctnum`,`itemnum`,`ticker`,`active`,`qty`,`weight`,`tradeprice`,`investmentvalue`,`created`,`lastupdated`)
SELECT `vp`.`acctnum`,
    `vp`.`itemnum`,
    `vp`.`ticker`,
    `vp`.`active`,
    `vp`.`qty`,
    `vp`.`weight`,
    `vp`.`tradeprice`,
    `vp`.`investmentvalue`,
    `vp`.`created`,
    `vp`.`lastupdated`
FROM `invdb`.`virtual_portfolio_20171130` as `vp`;
