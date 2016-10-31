delimiter $$

DROP PROCEDURE `sp_virtual_portfolio_sel`
$$

CREATE PROCEDURE `sp_virtual_portfolio_sel`(
	IN p_acctnum bigint(20)
)
BEGIN
SELECT
	`virtual_portfolio`.`acctnum`,
	`virtual_portfolio`.`itemnum`,
	`virtual_portfolio`.`instrumentid`,
	`virtual_portfolio`.`active`,
	`virtual_portfolio`.`qty`,
	`virtual_portfolio`.`tradeprice`,
	`virtual_portfolio`.`investmentvalue`,
	`virtual_portfolio`.`created`,
	`virtual_portfolio`.`lastupdated`
FROM `virtual_portfolio`
WHERE `virtual_portfolio`.`acctnum` = p_acctnum;

END
$$
