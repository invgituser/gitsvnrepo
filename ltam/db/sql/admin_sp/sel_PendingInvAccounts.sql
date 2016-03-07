DROP PROCEDURE IF EXISTS `sel_PendingInvAccounts`;

DELIMITER $$
CREATE PROCEDURE `sel_PendingInvAccounts`(
)
BEGIN
    select 
        logonid,
        acctnum,
        email,
        name,
		firstname,
		lastname,
        address,
        phone,
        accttype,
        age,
        horizon,
        riskIndex,
        initialInvestment,
        recurringInvestment,
        longTermGoal,
        stayInvested
    from
        vw_pending_user_info
;
END;

