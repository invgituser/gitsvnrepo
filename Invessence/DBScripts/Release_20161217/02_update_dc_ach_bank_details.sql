use invdb;
update dc_ach_bank_details a set acctnum=(select acctnum from dc_mp_movemoney_paymethod b where a.moveMoneyPayMethodID=b.moveMoneyPayMethId);
 