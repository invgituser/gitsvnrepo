## Alter Table rbsa.rbsa_daily

ALTER TABLE  rbsa.rbsa_daily  
CHANGE COLUMN  volume   volume  BIGINT(20) NULL DEFAULT NULL AFTER  adjusted_price ,
ADD COLUMN  prev_month_businessdate  DATE NULL AFTER  daily_return ,
ADD COLUMN  prev_monthly_adjusted  DOUBLE NULL AFTER  prev_month_businessdate ,
ADD COLUMN  exchange_symbol  VARCHAR(20) NULL AFTER  monthly_return ;