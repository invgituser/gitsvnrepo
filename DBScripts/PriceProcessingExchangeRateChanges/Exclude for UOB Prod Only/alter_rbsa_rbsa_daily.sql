ALTER TABLE rbsa.rbsa_daily
ADD COLUMN exchangeRate DOUBLE NULL AFTER adjusted_price,
ADD COLUMN base_currency VARCHAR(3) NULL AFTER monthly_return;
