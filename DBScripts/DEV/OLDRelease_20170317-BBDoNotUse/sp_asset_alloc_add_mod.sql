DROP PROCEDURE IF EXISTS `invdb`.`sp_asset_alloc_add_mod`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_asset_alloc_add_mod`(
	IN  p_addmodflag      VARCHAR(1),
	IN p_acctnum          bigint(20),
	IN p_assetclass       varchar(80),
	IN p_themecode        varchar(20),
	IN p_allocationmodel  varchar(1),
	IN p_assetyear        tinyint(3) unsigned,
	IN p_active           varchar(1),
	IN p_weight           float
)
BEGIN 

   DELETE FROM asset_alloc
   WHERE acctnum = p_acctnum
   AND   assetclass = p_assetclass
   AND   themecode = IFNULL(p_themecode,'ETF')
   AND   allocationmodel = IFNULL(p_allocationmodel,'D')
   AND   assetyear = IFNULL(p_assetyear,0)
   ;

	UPDATE asset_alloc
	set active = 'X'
	WHERE acctnum = p_acctnum
	AND   assetclass = p_assetclass
	AND   active = 'A'
	;

     INSERT INTO asset_alloc
         (
		acctnum,
		assetclass,
		themecode,
		allocationmodel,
		assetyear,
		active,
		weight,
		created,
		lastupdated
         )
    VALUES 
         ( 
		p_acctnum,
		p_assetclass,
		p_themecode,
		p_allocationmodel,
		IFNULL(p_assetyear,0),
		IFNULL(p_active,'A'),
		p_weight,
		now(),
		NULL
         ); 
 END$$
DELIMITER ;
