## Create Table for Security Exchange Source

drop table if exists invdb.sec_exchangerate_source;

CREATE TABLE invdb.sec_exchangerate_source (
   symbol varchar(20) NOT NULL,
   status varchar(1) NOT NULL COMMENT 'A - Active,I - Inactive',
   source varchar(20) NOT NULL,
   created date DEFAULT NULL,
   updated date DEFAULT NULL,
   PRIMARY KEY (symbol,status)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;