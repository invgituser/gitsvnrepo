DROP PROCEDURE if exists `sel_position`;

DELIMITER $$
CREATE PROCEDURE `sel_position`(
 	IN p_acctnum  bigint(20)
)
BEGIN
	select
		utp.`acctnum`,
		p.`clientAccountID`,
		p.`accountAlias`,
		p.`currencyPrimary`,
		p.`assetClass`,
		p.`fxRateToBase`,
		p.`symbol`,
		p.`description`,
		p.`reportDate`,
		p.`side`,
		p.`quantity`,
		p.`costBasisPrice`,
		p.`costBasisMoney`,
		p.`markPrice`,
		p.`positionValue`,
		p.`fifoPnlUnrealized`,
		p.`levelOfDetail`
    from user_trade_profile utp,
		 IB_Accounts ib,
		 position p
	WHERE utp.acctnum = ib.acctnum
	AND   ib.IB_acctnum = p.`clientAccountID`
    AND   utp.acctnum = p_acctnum
	AND   p.reportDate = (select value from invessence_switch where name = 'BROKER_BDATE');

END
$$
DELIMITER ;
