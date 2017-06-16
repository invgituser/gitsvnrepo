DROP TABLE IF EXISTS `invdb`.`user_advisor_mapping`;

CREATE TABLE `invdb`.`user_advisor_mapping` (
  `advisor` varchar(20) NOT NULL,
  `suportEmail` varchar(60) DEFAULT NULL,
  `supportPhone` varchar(15) DEFAULT NULL,
  `operationsEmail` varchar(60) DEFAULT NULL,
  `logo` varchar(40) DEFAULT NULL,
  `minInvestment` int(11) DEFAULT NULL,
  `min2ndACH` int(11) DEFAULT NULL,
  `min2ndAcctInvestment` int(11) DEFAULT NULL,
  `minRecurring` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`advisor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`user_advisor_mapping`
VALUES
(
  'BB'-- advisor
, 'support@BuildingBenjamins.com' -- supportEmail
, '(908) 333-4733' -- supportPhone
, 'operations@BuildingBenjamins.com' -- operations email
, null -- advisorLogo
, 2000 -- minInvestment
, 50 -- min2ndACH
, 2000 -- min2ndAcctInvestment
, 50 -- minRecurring
, now()
, null);

