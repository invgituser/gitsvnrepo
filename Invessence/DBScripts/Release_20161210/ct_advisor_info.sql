CREATE TABLE `invdb`.`advisor_info` (
  `advisor` 		varchar(20) NOT NULL,
  `suportemail` 	varchar(60) NULL,
  `supportphone`	varchar(15) NULL,
  `logo`			varchar(40) NULL,
  `minInvestment`	Integer DEFAULT NULL,
  `created`			datetime,
  `updated`			datetime,
  primary key(`advisor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
