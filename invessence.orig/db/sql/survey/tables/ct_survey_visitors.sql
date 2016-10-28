DROP TABLE IF EXISTS `survey_visitors`;

CREATE TABLE `survey_visitors` (
  email			varchar(60),
  lastname		varchar(30),
  firstname		varchar(30),
  leadsource	varchar(15),
  passwrd		varchar(15),
  followup		varchar(1),
  surveylink	varchar(150),
  emailMimeType	varchar(4),
  attempts 		INT DEFAULT 0,
  created		datetime,
  lastupdated	datetime,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
