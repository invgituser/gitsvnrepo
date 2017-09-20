## Create Table for Security and Source Mapping

drop table if exists invdb.sec_source_mapping;

CREATE TABLE invdb.sec_source_mapping (
  ticker varchar(20) NOT NULL,
  tickersource varchar(50) NOT NULL,
  pricing_required varchar(1) DEFAULT 'N',
  currency_exchange_cd varchar(45) DEFAULT NULL,
  PRIMARY KEY (ticker)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
