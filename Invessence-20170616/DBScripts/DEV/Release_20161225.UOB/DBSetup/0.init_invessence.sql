-- User Logon tables
delete from invdb.user_logon;
delete from invdb.user_access_role;
delete from `invdb`.`role`;


-- Advisor Tables
delete from invdb.user_advisor_info;
delete from invdb.`user_advisor_access`;
delete from invdb.advisor_notification;

-- Data Collector tables During portfolio Creation
delete from `invdb`.`user_trade_profile`;
delete from invdb.acct_info;
delete from invdb.acct_ben;
delete from invdb.acct_financial;
delete from invdb.acct_risk_data;
delete from invdb.acct_trade_preference;

-- Emails and alerts
delete from invdb.email_alerts;
delete from invdb.email_messages;

-- External Data
delete from invdb.ext_nav;
delete from invdb.performance_info;
delete from invdb.pretrade_details;
delete from invdb.cash_report;
delete from invdb.clients_to_trade;
delete from `invdb`.`commission`;
delete from `invdb`.`ext_acct_info`;
delete from `invdb`.`ext_position`;
delete from `invdb`.`rebalance_info`;
delete from `invdb`.`rebalance_positions`;
delete from `invdb`.`rebalance_trade`;
delete from `invdb`.`relAndunrel`;
delete from `invdb`.`trades`;
delete from `invdb`.`unbundld_commission`;
delete from `invdb`.`user_trade_log`;

-- Security Master Info (Be careful, might want to keep securities from restore)
delete from `invdb`.`sec_master`;
delete from `invdb`.`sec_theme`;
delete from `invdb`.`sec_asset_master`;
delete from invdb.daily_returns;
delete from invdb.daily_date_table;
delete from invdb.inv_date_table;





