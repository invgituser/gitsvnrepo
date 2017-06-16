## Create table for RBSA Security Exchange Rate Data

drop table if exists rbsa.sec_exchangerate_data;

CREATE TABLE rbsa.sec_exchangerate_data (
   symbol varchar(20) NOT NULL,
   exchangeDate varchar(10) NOT NULL,
   exchangeRate double DEFAULT NULL,
   reverseExchangeRate double DEFAULT NULL,
   created date DEFAULT NULL,
   updated date DEFAULT NULL,
   PRIMARY KEY (symbol,exchangeDate)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 