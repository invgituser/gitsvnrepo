DROP PROCEDURE if exists `sel_position`;

DELIMITER $$
CREATE PROCEDURE `sel_position`(
 	IN p_acctnum  bigint(20)
)
BEGIN
	select
		utp.`acctnum`,
		p.`clientAccountID`,
		ib.`lastname`,
		ib.`firstname`,
		ib.`dateOpened`,
		p.`accountAlias`,
		p.`currencyPrimary`,
		amg.`classname` as assetClass,
		amg.`color` as color,
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
		 position p,
		 sec_master sec,
		 asset_mapping_group amg
	WHERE utp.acctnum = ib.acctnum
	AND   ib.IB_acctnum = p.`clientAccountID`
    AND   utp.acctnum = p_acctnum
	AND   p.`symbol` = sec.`ticker`
	AND   sec.`assetclass` = amg.`classname`
	AND   amg.assetLevel = 1
	AND   amg.groupname = 'Invessence'
	AND   p.reportDate = (select value from invessence_switch where name = 'BROKER_BDATE')
	ORDER BY amg.`sortOrder`;

END
$$
DELIMITER ;
