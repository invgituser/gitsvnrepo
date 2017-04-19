## Update data invdb.sec_master

UPDATE invdb.sec_master SET ticker='IPXJ.L' WHERE instrumentid='101';
UPDATE invdb.sec_master SET ticker='IMEU.L' WHERE instrumentid='106';
UPDATE invdb.sec_master SET ticker='EIMI.L' WHERE instrumentid='102';
UPDATE invdb.sec_master SET ticker='SJPA.L' WHERE instrumentid='105';
UPDATE invdb.sec_master SET ticker='IHYG.L' WHERE instrumentid='103';
UPDATE invdb.sec_master SET ticker='KV4.SI' WHERE instrumentid='119';
UPDATE invdb.sec_master SET ticker='ES3.SI' WHERE instrumentid='118';
UPDATE invdb.sec_master SET ticker='VUSD.L' WHERE instrumentid='117';
UPDATE invdb.sec_master SET ticker='IHYU.L' WHERE instrumentid='104';
UPDATE invdb.sec_master SET ticker='IUAG.L' WHERE instrumentid='107';

## Insert data invdb.sec_exchangerate_master

INSERT INTO invdb.sec_exchangerate_master (symbol, status, fromCurrency, toCurrency, description, created) VALUES ('EURSGD', 'I', 'EUR', 'SGD', 'Euro to SGD', now());
INSERT INTO invdb.sec_exchangerate_master (symbol, status, fromCurrency, toCurrency, description, created) VALUES ('GBPSGD', 'I', 'GBP', 'SGD', 'GBP to SGD', now());
INSERT INTO invdb.sec_exchangerate_master (symbol, status, fromCurrency, toCurrency, description, created) VALUES ('HKDUSD', 'I', 'HKD', 'USD', 'HKD to USD', now());
INSERT INTO invdb.sec_exchangerate_master (symbol, status, fromCurrency, toCurrency, description, created) VALUES ('SGDUSD', 'I', 'SGD', 'USD', 'Singapore Dollar to USD', now());
INSERT INTO invdb.sec_exchangerate_master (symbol, status, fromCurrency, toCurrency, description, created) VALUES ('USDEUR', 'I', 'USD', 'EUR', 'USD to EURO', now());
INSERT INTO invdb.sec_exchangerate_master (symbol, status, fromCurrency, toCurrency, description, created) VALUES ('USDGBP', 'A', 'USD', 'GBP', 'USD to GBP', now());

## Insert data invdb.sec_exchangerate_source

INSERT INTO invdb.sec_exchangerate_source (symbol, status, source, created) VALUES ('EURSGD', 'A', 'FIS', now());
INSERT INTO invdb.sec_exchangerate_source (symbol, status, source, created) VALUES ('GBPSGD', 'A', 'FIS', now());
INSERT INTO invdb.sec_exchangerate_source (symbol, status, source, created) VALUES ('HKDUSD', 'A', 'FIS', now());
INSERT INTO invdb.sec_exchangerate_source (symbol, status, source, created) VALUES ('SGDUSD', 'A', 'FIS', now());
INSERT INTO invdb.sec_exchangerate_source (symbol, status, source, created) VALUES ('USDEUR', 'A', 'FIS', now());
INSERT INTO invdb.sec_exchangerate_source (symbol, status, source, created) VALUES ('USDGBP', 'A', 'FIS', now());

## Insert data invdb.sec_source_mapping

INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('IPXJ.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('IMEU.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('EIMI.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('SJPA.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('IHYG.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('KV4.SI', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('ES3.SI', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('VUSD.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('2821.HK', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('IHYU.L', 'FIS', 'Y', 'GBPSGD');
INSERT INTO invdb.sec_source_mapping (ticker, tickersource, pricing_required, currency_exchange_cd) VALUES ('IUAG.L', 'FIS', 'Y', 'GBPSGD');
