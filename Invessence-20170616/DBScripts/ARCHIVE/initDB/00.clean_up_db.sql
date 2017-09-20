delete from invdb.user_logon
where logonid > 100
AND upper(user_logon.access) in ('USER')
AND logonid not in (select distinct user_access_role.logonid from invdb.ext_acct_info, invdb.user_access_role 
					  where ext_acct_info.acctnum is not null
                      and   user_access_role.acctnum = ext_acct_info.acctnum
                      )
;

delete from invdb.user_logon_exception
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.visitor
where demoid not in (select acctnum from invdb.ext_acct_info where acctnum is not null);
                      
delete from invdb.user_trade_profile
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.user_risk_questions
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.asset_alloc
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.virtual_portfolio
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.user_access_role
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_acct_owners_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_acct_transfer_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_benefiaciary_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_dup_doc_req_party
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_employment_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_request_audit
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_td_transfer_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_visa_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_ach_bank_details
where dc_ach_bank_details.moveMoneyPayMethodID not in 
									(select dc_mp_movemoney_paymethod.moveMoneyPayMethId
									from invdb.`dc_mp_movemoney_paymethod`, invdb.ext_acct_info
									where dc_mp_movemoney_paymethod.acctnum = ext_acct_info.acctnum
									AND  ext_acct_info.acctnum is not null);


delete from invdb.dc_move_money_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_mp_movemoney_paymethod
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_elecfund_transfer_details
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.dc_requests
where acctnum not in (select acctnum from invdb.ext_acct_info where acctnum is not null);

delete from invdb.email_messages;

delete from invdb.email_alerts;
