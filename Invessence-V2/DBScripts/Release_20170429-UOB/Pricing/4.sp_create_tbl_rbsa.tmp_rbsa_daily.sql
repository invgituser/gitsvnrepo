## Create tabel for rbsa daily Temporary

drop table if exists rbsa.tmp_rbsa_daily;

CREATE TABLE rbsa.tmp_rbsa_daily (
   ticker varchar(20) NOT NULL,
   businessdate varchar(10) NOT NULL,
   open_price double DEFAULT NULL,
   close_price double DEFAULT NULL,
   high_price double DEFAULT NULL,
   low_price double DEFAULT NULL,
   adjusted_price double DEFAULT NULL,
   volume bigint(20) DEFAULT NULL,
   prev_businessdate date DEFAULT NULL,
   prev_close_price double DEFAULT NULL,
   daily_return double DEFAULT NULL,
   prev_month_businessdate date DEFAULT NULL,
   prev_monthly_adjusted double DEFAULT NULL,
   monthly_return double DEFAULT NULL,
   exchange_symbol varchar(20) DEFAULT NULL,
   PRIMARY KEY (ticker,businessdate)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;