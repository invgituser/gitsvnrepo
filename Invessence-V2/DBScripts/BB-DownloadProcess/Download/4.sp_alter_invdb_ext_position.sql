## Script for altering invdb.ext_position

ALTER TABLE invdb.ext_position
CHANGE COLUMN levelOfDetail levelOfDetail VARCHAR(40) NOT NULL DEFAULT 0 ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (clientAccountID, reportDate, symbol, purchaseDate, costBasisPrice, levelOfDetail);