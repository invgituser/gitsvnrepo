DROP PROCEDURE IF EXISTS `invdb`.`del_inactive_acct`;

DELIMITER $$
CREATE PROCEDURE `del_inactive_acct`(
	IN p_days	INTEGER
)
BEGIN

	DROP TEMPORARY TABLE IF EXISTS cleanacct;
	CREATE TEMPORARY TABLE cleanacct(
		acctnum	BIGINT(20)
	);
    
    
    IF (IFNULL(p_days,1) = 0)
    THEN
		INSERT INTO cleanacct
		SELECT user_trade_profile.acctnum from invdb.user_trade_profile
		WHERE user_trade_profile.created >= DATE_FORMAT(now(),'%Y-%m-%d')
		AND  user_trade_profile.managed in ('N')
        AND  user_trade_profile.`status` in ('N', 'V');
    ELSE
		INSERT INTO cleanacct
		SELECT user_trade_profile.acctnum from invdb.user_trade_profile
		WHERE user_trade_profile.acctnum > 1000
		AND  DATE_ADD(now(),INTERVAL (-1 * IFNULL(p_days,30)) DAY) < user_trade_profile.created
		AND  user_trade_profile.managed in ('N');
    END IF;
    
--     SELECT * from invdb.user_trade_profile, cleanacct
--     WHERE cleanacct.acctnum = user_trade_profile.acctnum;
     
    DELETE FROM invdb.`acct_ben`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`acct_financial`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`acct_info`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`acct_risk_data`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`acct_trade_preference`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`actual_portfolio`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`advisor_notification`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`asset_alloc`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`clients_to_trade`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`dc_acct_owners_details_history`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`dc_acct_transfer_details`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`dc_benefiaciary_details`
    where acctnum in (select distinct acctnum from cleanacct);

    -- DELETE FROM invdb.`dc_decedent_information`
    -- where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_dup_doc_req_party`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_employment_details`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_ach_bank_details`
    where moveMoneyPayMethodID in (select distinct moveMoneyPayMethodID from cleanacct, invdb.dc_elecfund_transfer_details where cleanacct.acctnum = dc_elecfund_transfer_details.acctnum);

    DELETE FROM invdb.`dc_fedwire_acct_details`
    where moveMoneyPayMethodID in (select distinct moveMoneyPayMethodID from cleanacct, invdb.dc_elecfund_transfer_details where cleanacct.acctnum = dc_elecfund_transfer_details.acctnum);

    DELETE FROM invdb.`dc_internal_transfer_details`
    where moveMoneyPayMethodID in (select distinct moveMoneyPayMethodID from cleanacct, invdb.dc_elecfund_transfer_details where cleanacct.acctnum = dc_elecfund_transfer_details.acctnum);

    DELETE FROM invdb.`dc_elecfund_transfer_details`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_move_money_details`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`dc_mp_movemoney_paymethod`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_td_transfer_details`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_visa_details`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_request_audit`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_requests`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`dc_requests_final`
    where acctnum in (select distinct acctnum from cleanacct);


    DELETE FROM invdb.`cash_report`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);
    
    DELETE FROM invdb.`cash_transaction`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);
    
    DELETE FROM invdb.`commission`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);

    DELETE FROM invdb.`ext_nav`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);

    DELETE FROM invdb.`ext_position`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`ext_transaction`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`pretrade_details`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`rebalance_info`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`rebalance_trade`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`relAndunrel`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);

    DELETE FROM invdb.`sav_acct_financial`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`position`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);
    
    DELETE FROM invdb.`trades`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);
   
	DELETE FROM invdb.`unbundld_commission`
    where clientAccountID in (select distinct clientAccountID from cleanacct, invdb.ext_acct_info where cleanacct.acctnum = ext_acct_info.acctnum);

	DELETE FROM invdb.`user_exclude_subclass`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`user_notification`
    where logonid in (select distinct logonid from cleanacct, invdb.user_access_role where cleanacct.acctnum = user_access_role.acctnum);

	DELETE FROM invdb.`user_reports`
    where acctnum in (select distinct acctnum from cleanacct);
    
	DELETE FROM invdb.`user_risk_questions`
    where acctnum in (select distinct acctnum from cleanacct);
    
	DELETE FROM invdb.`user_risk_questions_audit`
    where acctnum in (select distinct acctnum from cleanacct);

	DELETE FROM invdb.`virtual_portfolio`
    where acctnum in (select distinct acctnum from cleanacct);

	DELETE FROM invdb.`visitor`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`ext_acct_info`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`user_trade_log`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`user_trade_profile_audit`
    where acctnum in (select distinct acctnum from cleanacct);

    DELETE FROM invdb.`user_trade_profile`
    where acctnum in (select distinct acctnum from cleanacct);

	DELETE FROM invdb.`user_logon`
    where logonid in (select distinct `user_access_role`.`logonid` from cleanacct, `user_access_role` where cleanacct.acctnum = `user_access_role`.acctnum);

	DELETE FROM invdb.`user_access_role`
    where acctnum in (select distinct acctnum from cleanacct);
    
    DELETE FROM invdb.`user_logon_exception`;
    

END$$
DELIMITER ;
