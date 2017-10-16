/* 
 DAY 0;
 Insert Cash Funding for Account#1
*/
INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3201', '20170913', '12000');


/* 
 DAY 1;
 Trade File for Account#1,
 Insert Trust for Accont#1
 Insert Cash Funding for Account#2
*/

INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3201', '20170913', '445.83');

INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3206', '20170913', '15000');

INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', '2821.HK', 'B', '', '10', '12.156', '121.56', '0.97248', '0.03039', '20170918', '', 'HKD', 'SGD', '1.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'IPXJ.L', 'B', '', '15', '17.897', '268.455', '2.14764', '0.06711375', '20170918', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'EIMI.L', 'B', '', '20', '20.897', '417.94', '3.34352', '0.104485', '20170918', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'IHYG.L', 'B', '', '25', '100.895', '2522.375', '20.179', '0.63059375', '20170918', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'IHYU.L', 'B', '', '75', '5.2345', '392.5875', '3.1407', '0.098146875', '20170918', '', 'SGD', 'SGD', '1', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'SJPA.L', 'B', '', '5', '120.987', '604.935', '4.83948', '0.15123375', '20170918', '', 'SGD', 'SGD', '1', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'IMEU.L', 'B', '', '2', '300.125', '600.25', '4.802', '0.1500625', '20170918', '', 'USD', 'SGD', '1.45', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170914', 'IUAG.L', 'B', '', '1', '2000.98', '2000.98', '16.00784', '0.500245', '20170918', '', 'USD', 'SGD', '1.45', '');

call `temp`.`sp_upload_trades`();

/* 
 DAY 2
 Trade File for Account#2,
 Insert Additional Fund for Account#1
*/

truncate table `temp`.`tmp_transaction`;
truncate table `temp`.`uob_cash`;

INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3201', '20170913', '1000');

INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3206', '20170913', '3445.832598');


INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', '2821.HK', 'B', '', '10', '12.156', '121.56', '0.97248', '0.03039', '20170919', '', 'HKD', 'SGD', '1.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'IPXJ.L', 'B', '', '15', '17.897', '268.455', '2.14764', '0.06711375', '20170919', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'EIMI.L', 'B', '', '20', '20.897', '417.94', '3.34352', '0.104485', '20170919', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'IHYG.L', 'B', '', '25', '100.895', '2522.375', '20.179', '0.63059375', '20170919', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'IHYU.L', 'B', '', '75', '5.2345', '392.5875', '3.1407', '0.098146875', '20170919', '', 'SGD', 'SGD', '1', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'SJPA.L', 'B', '', '5', '120.987', '604.935', '4.83948', '0.15123375', '20170919', '', 'SGD', 'SGD', '1', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'IMEU.L', 'B', '', '2', '300.125', '600.25', '4.802', '0.1500625', '20170919', '', 'USD', 'SGD', '1.45', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3206', '20170915', 'IUAG.L', 'B', '', '1', '2000.98', '2000.98', '16.00784', '0.500245', '20170919', '', 'USD', 'SGD', '1.45', '');

call `temp`.`sp_upload_trades`();

/* 
 DAY 3
 Trade File for Account#1,
 Insert Additional Fund for Account#1
*/
truncate table `temp`.`tmp_transaction`;
truncate table `temp`.`uob_cash`;

INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3201', '20170913', '550');

INSERT INTO `temp`.`uob_cash` (`clientAccountID`, `reportDate`, `value`) VALUES ('TST3206', '20170913', '3445.832598');

INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', '2821.HK', 'S', '', '2', '15.678', '31.356', '0.250848', '0.007839', '20170920', '', 'HKD', 'SGD', '1.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'IPXJ.L', 'B', '', '1', '20.897', '20.897', '0.167176', '0.00522425', '20170920', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'EIMI.L', 'B', '', '1', '22.789', '22.789', '0.182312', '0.00569725', '20170920', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'IHYG.L', 'B', '', '1', '98.367', '98.367', '0.786936', '0.02459175', '20170920', '', 'GBP', 'SGD', '2.132', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'IHYU.L', 'B', '', '10', '3.3445', '33.445', '0.26756', '0.00836125', '20170920', '', 'SGD', 'SGD', '1', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'SJPA.L', 'B', '', '1', '120.987', '120.987', '0.967896', '0.03024675', '20170920', '', 'SGD', 'SGD', '1', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'IMEU.L', 'B', '', '0', '300.125', '0', '0', '0', '20170920', '', 'USD', 'SGD', '1.45', '');
INSERT INTO `temp`.`tmp_transaction` (`clientAccountID`, `tradeDate`, `symbolSIN`, `transactionType`, `statusFlag`, `quantity`, `price`, `netAmount`, `brokerFee`, `otherFees`, `settleDate`, `comments`, `executionCurrency`, `localCurrency`, `exchangeRate`, `confirmNumber`) VALUES ('TST3201', '20170918', 'IUAG.L', 'B', '', '0', '2000.98', '0', '0', '0', '20170920', '', 'USD', 'SGD', '1.45', '');

call `temp`.`sp_upload_trades`();

