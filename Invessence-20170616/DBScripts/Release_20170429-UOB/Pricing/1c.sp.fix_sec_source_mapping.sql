ALTER TABLE invdb.sec_source_mapping 
CHANGE COLUMN ticker ticker_source_name VARCHAR(20) NOT NULL ,
CHANGE COLUMN dest_currency dest_currency VARCHAR(3) NOT NULL DEFAULT 'USD' ,
ADD COLUMN multiplying_factor DOUBLE NOT NULL DEFAULT '1' AFTER currency_exchange_cd,
ADD COLUMN return_threshold DOUBLE NOT NULL DEFAULT '0.5' AFTER multiplying_factor,
DROP PRIMARY KEY,
ADD PRIMARY KEY (sec_ticker, ticker_source_name, dest_currency);

