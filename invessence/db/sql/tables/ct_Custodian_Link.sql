DROP TABLE `custodian_link`;

CREATE TABLE `custodian_link` (
  `acctnum` bigint(20) NOT NULL,
  `action`  varchar(10) NOT NULL,
  `URL`     varchar(150) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`, `action`, `URL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
