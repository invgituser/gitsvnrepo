DROP PROCEDURE IF EXISTS `sp_upload_position`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_position`(
)
BEGIN 

   begin
		delete from position
		where concat(`clientAccountID`,`symbol`,`reportDate`) in (select concat(`clientAccountID`,`symbol`,`reportDate`)
																  from tmp_position)
		;
   end;

   begin
	 INSERT INTO position
		(
			`clientAccountID`	,
			`accountAlias`	,
			`currencyPrimary`	,
			`assetClass`	,
			`fxRateToBase`	,
			`symbol`	,
			`description`	,
			`reportDate`	,
			`side`	,
			`quantity`	,
			`costBasisPrice`	,
			`CostBasisMoney`	,
			`markPrice`	,
			`positionValue`	,
			`fifoPnlUnrealized`	,
			`LevelOfDetail`	
		)
	  select
			`clientAccountID`	,
			`accountAlias`	,
			`currencyPrimary`	,
			`assetClass`	,
			`fxRateToBase`	,
			`symbol`	,
			`description`	,
			`reportDate`	,
			`side`	,
			`quantity`	,
			`costBasisPrice`	,
			`CostBasisMoney`	,
			`markPrice`	,
			`positionValue`	,
			`fifoPnlUnrealized`	,
			`LevelOfDetail`	
	  from tmp_position
	  ;
   end;
END$$
DELIMITER ;
