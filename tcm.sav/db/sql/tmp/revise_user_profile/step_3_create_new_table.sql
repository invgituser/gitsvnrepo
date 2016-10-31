DROP TABLE IF EXISTS user_trade_profile;

CREATE TABLE `user_trade_profile` (
  `acctnum` bigint(20) NOT NULL AUTO_INCREMENT,
  `goal`    varchar(30),
  `acctType` varchar(30),
  `age` integer,
  `horizon` integer,
  `initialInvestment` integer,
  `recurringInvestment` integer,
  `experience` tinyint,
  `longTermGoal` tinyint,
  `stayInvested` tinyint,
  `charitablegoals` integer,
  `dependent` integer,
  `riskIndex` double,
  `created` date,
  `lastUpdated` date, 
  PRIMARY KEY (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
