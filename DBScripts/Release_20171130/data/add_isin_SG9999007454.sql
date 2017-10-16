INSERT INTO `invdb`.`sec_master` 
(`status`, `securityStatus`, `ticker`, `isin`, `ric`, `name`, `assetclass`, `subclass`, `exchange`, `base_currency`, `inception`) 
VALUES ('A', 'A', 'F00000MQNO.MSTA', 'SG9999007454', 'F00000MQNO.MSTA', 'Eastspring Investments Unit Trusts', 'Fixed Income', 'Singapore Bonds', 'MSTA', 'SGD', '20110408');

INSERT INTO `invdb`.`sec_source_mapping` 
(`sec_ticker`, `ticker_source_name`, `tickersource`, `pricing_required`, `exchange_required`, `base_currency`, `dest_currency`, `multiplying_factor`, `return_threshold`) 
VALUES ('F00000MQNO.MSTA', 'F00000MQNO.MSTA', 'FIS', 'Y', 'N', 'SGD', 'SGD', '1', '0.5');
