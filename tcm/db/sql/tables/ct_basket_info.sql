DROP TABLE IF EXISTS `basket_info`;

CREATE TABLE `basket_info` (
  `groupname`   varchar(20) NOT NULL,
  `theme`       varchar(30) NOT NULL,
  `basket`      varchar(30) DEFAULT NULL,
  `sortorder`   integer DEFAULT 0,
  `created`     date DEFAULT NULL,
  `lastupdated` date DEFAULT NULL,
  PRIMARY KEY (`groupname`, `theme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into `basket_info`
(
  `groupname`,
  `theme`,
  `basket`,
  `created`
)
select distinct
  `groupname`,
  `theme`,
  `theme`,
  now()
from sec_master_group
;

update sec_master_group
set theme = concat('62.',theme)
where groupname = 'Demo.Advisor'

select * from basket_info;
