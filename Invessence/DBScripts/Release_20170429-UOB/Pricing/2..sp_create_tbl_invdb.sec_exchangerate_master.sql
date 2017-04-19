## Create Table for Security Exchange Master

drop table if exists invdb.sec_exchangerate_master;

CREATE TABLE invdb.sec_exchangerate_master (
   symbol varchar(20) NOT NULL,
   status varchar(1) DEFAULT 'A' COMMENT 'A - Active,I - Inactive',
   fromCurrency varchar(20) NOT NULL,
   toCurrency varchar(20) NOT NULL,
   description varchar(100) NOT NULL,
   created date DEFAULT NULL,
   updated date DEFAULT NULL,
   PRIMARY KEY (symbol)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
