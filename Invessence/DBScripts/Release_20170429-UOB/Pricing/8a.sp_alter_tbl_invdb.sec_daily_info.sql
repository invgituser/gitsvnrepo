ALTER TABLE invdb.sec_daily_info
CHANGE COLUMN exchange_symbol dest_currency VARCHAR(20) NOT NULL FIRST;

ALTER TABLE invdb.sec_daily_info 
DROP PRIMARY KEY,
ADD PRIMARY KEY (dest_currency,ticker, businessdate);

ALTER TABLE invdb.sec_daily_info
ADD COLUMN converted_adjusted_price DOUBLE NULL AFTER adjusted_price;

ALTER TABLE invdb.sec_daily_info 
ADD COLUMN converted_prev_adjusted DOUBLE NULL AFTER monthly_return;