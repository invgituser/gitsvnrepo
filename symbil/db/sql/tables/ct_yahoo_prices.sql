DROP TABLE IF exists `yahoo_prices`;

CREATE TABLE `yahoo_prices` (
  `ticker`      varchar(20) DEFAULT NULL,
  `trade_date`  varchar(15) NOT NULL,
  `open_price`  varchar(20) DEFAULT NULL,
  `high_price`  varchar(20) DEFAULT NULL,
  `low_price`   varchar(20) DEFAULT NULL,
  `close_price` varchar(20) DEFAULT NULL,
  `volume`      varchar(20) DEFAULT NULL,
  `prev_close`  varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
