DROP PROCEDURE IF EXISTS `sp_virtual_portfolio_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_virtual_portfolio_add_mod`(
    IN p_addmodflag      VARCHAR(1),
	IN p_acctnum bigint(20),
	IN p_itemnum int(10) unsigned,
	IN p_ticker varchar(20),
	IN p_active varchar(1),
	IN p_qty int(11),
	IN p_weightByAsset float,
	IN p_tradeprice float,
	IN p_investmentvalue double
)
BEGIN 

   DECLARE t_instrumentid bigint(10);

   IF (p_ticker is not NULL) then
		select instrumentid
		into t_instrumentid
		from sec_master
		where ticker = p_ticker;
   else
		SET t_instrumentid = 0;
		SET p_ticker = 'XXX';
   END IF;

   DELETE FROM virtual_portfolio
   WHERE acctnum = p_acctnum
   AND   ticker = p_ticker;

    INSERT INTO virtual_portfolio
         (
		acctnum,
		itemnum,
		instrumentid,
		ticker,
		active,
		qty,
		weight,
		tradeprice,
		investmentvalue,
		created,
		lastupdated
         )
    VALUES 
         ( 
		p_acctnum,
		p_itemnum,
		t_instrumentid,
		p_ticker,
		p_active,
		p_qty,
		p_weightByAsset,
		p_tradeprice,
		p_investmentvalue,
		now(),
		NULL
         ) ; 

END$$
DELIMITER ;
