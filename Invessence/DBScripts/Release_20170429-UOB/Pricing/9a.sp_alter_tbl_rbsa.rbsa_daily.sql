ALTER TABLE rbsa.rbsa_daily 
CHANGE COLUMN exchange_symbol dest_currency VARCHAR(20) NOT NULL FIRST;

ALTER TABLE rbsa.rbsa_daily 
DROP PRIMARY KEY,
ADD PRIMARY KEY (dest_currency,ticker, businessdate);

ALTER TABLE rbsa.rbsa_daily 
ADD COLUMN converted_adjusted_price DOUBLE NULL AFTER adjusted_price;

ALTER TABLE rbsa.rbsa_daily 
ADD COLUMN converted_prev_adjusted DOUBLE NULL AFTER monthly_return;