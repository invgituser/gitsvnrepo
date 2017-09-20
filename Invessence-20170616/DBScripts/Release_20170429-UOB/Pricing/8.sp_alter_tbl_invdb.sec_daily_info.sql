## Alter table  Security daily information

ALTER TABLE  invdb.sec_daily_info  
DROP COLUMN  instrumentid ,
DROP INDEX  sdi_instrument  ,
ADD INDEX  sdi_instrument  ( businessdate  ASC);

##
ALTER TABLE  invdb.sec_daily_info  
CHANGE COLUMN  volume   volume  BIGINT(20) NULL DEFAULT NULL AFTER  adjusted_price ,
ADD COLUMN  prev_month_businessdate  DATE NULL AFTER  daily_return ,
ADD COLUMN  prev_monthly_adjusted  DOUBLE NULL AFTER  prev_month_businessdate ,
ADD COLUMN  exchange_symbol  VARCHAR(20) NULL AFTER  monthly_return ;
