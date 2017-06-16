ALTER TABLE rbsa.tmp_rbsa_daily 
CHANGE COLUMN exchange_symbol dest_currency VARCHAR(20) NOT NULL FIRST;

ALTER TABLE rbsa.tmp_rbsa_daily 
DROP PRIMARY KEY,
ADD PRIMARY KEY (dest_currency,ticker, businessdate);

ALTER TABLE rbsa.tmp_rbsa_daily 
ADD COLUMN converted_adjusted_price DOUBLE NULL AFTER adjusted_price;

ALTER TABLE rbsa.tmp_rbsa_daily 
ADD COLUMN converted_prev_adjusted DOUBLE NULL AFTER monthly_return;

ALTER TABLE rbsa.tmp_rbsa_daily
CHANGE COLUMN converted_prev_adjusted converted_prev_adjusted DOUBLE NULL DEFAULT NULL AFTER prev_close_price,
ADD COLUMN converted_prev_monthly_adjusted DOUBLE NULL AFTER prev_monthly_adjusted;

