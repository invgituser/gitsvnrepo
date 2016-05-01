CREATE TABLE `survey_passwords` (
  `source`		varchar(10),
  `passwordID`	Integer,
  `passwrd`		varchar(30),
  `created`		datetime,
  `lastupdated`	datetime,
  PRIMARY KEY (`source`,`passwordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
