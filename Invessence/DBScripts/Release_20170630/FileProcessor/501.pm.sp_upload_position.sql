DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_position`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_upload_position`(
)
BEGIN 

   DECLARE tReportDate	VARCHAR(10);
      
   set tReportDate = `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE');
   
   begin
		delete from `ext_position`
		where concat(`clientAccountID`,`symbol`,`reportDate`) in (select concat(`clientAccountID`,`symbol`,IFNULL(`reportDate`,tReportDate))
																  from `invdb`.`tmp_position`)
		;
   end;

   begin
	 INSERT INTO `invdb`.`ext_position`
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
			IFNULL(`reportDate`,tReportDate),
			`side`	,
			`quantity`	,
			`costBasisPrice`	,
			`CostBasisMoney`	,
			`markPrice`	,
			`positionValue`	,
			`fifoPnlUnrealized`	,
			`LevelOfDetail`	
	  from `invdb`.`tmp_position`
	  ;
      
   end;
END$$
DELIMITER ;
