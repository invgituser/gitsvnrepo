DELIMITER $$
set @acctnum=-1;
CALL `sp_adv_user_trade_profile`
(62, 
@acctnum, 
'pmehta@comcast.net', 
'Prashant', 
'Mehta', 
'Retirement', 
'Non-taxable', 
35, 
30, 
100000, 
1000, 
5);
select @acctnum
$$