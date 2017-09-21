DROP PROCEDURE IF EXISTS `temp`.`sp_upload_fixedmodel_robo_loader`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_fixedmodel_robo_loader`()
BEGIN

	delete from `temp`.`tmp_sec_fixedmodel`;
	delete from `temp`.`tmp_sec_fixedmodel_asset`;
	delete from `temp`.`tmp_sec_fixedmodel_subasset`;
    
    INSERT INTO `temp`.`tmp_sec_fixedmodel_subasset`
	(`theme`,
	`level`,
	`asset`,
	`keyname`,
	`keydescription`,
	`status`,
	`allocation`,
	`sortorder`,
	`created`,
	`updated`)
	select
    `mapping`.`theme`,
    `mapping`.`level`,
    `sec_master`.`assetclass`,
    `loader`.`ticker`,
    `sec_master`.`name`,
    'A',
    `loader`.`allocation`/100,
    null,
    now(),
    now()
    from `temp`.`tmp_fixedmodel_robo_loader` `loader`, `temp`.`tmp_fixedmodel_robo_mapping` `mapping`, `invdb`.`sec_master`
    where `loader`.`modelName` = `mapping`.`modelName`
    and   `loader`.`ticker` = `sec_master`.`ticker`;
    
	INSERT INTO `temp`.`tmp_sec_fixedmodel`
	(`theme`,
	`level`,
	`sortorder`,
	`status`,
	`displayname`,
	`description`,
	`lowRisk`,
	`highRisk`,
	`expectedreturn`,
	`expectedrisk`,
	`taxable`,
	`created`,
	`updated`)
	select distinct
    `mapping`.`theme`,
    `mapping`.`level`,
    null,
    'A',
    `mapping`.`displayname`,
    `mapping`.`displayname`,
    null,
    null,
    null,
    null,
    'N',
    now(),
    now()
    from `temp`.`tmp_fixedmodel_robo_loader` `loader`, `temp`.`tmp_fixedmodel_robo_mapping` `mapping`
    where `loader`.`modelName` = `mapping`.`modelName`;
    
    INSERT INTO `temp`.`tmp_sec_fixedmodel_asset`
	(`theme`,
	`level`,
	`asset`,
	`assetname`,
	`status`,
	`alloc`,
	`color`,
	`sortorder`,
	`created`,
	`updated`)
	select
    `mapping`.`theme`,
    `mapping`.`level`,
    `sec_master`.`assetclass`,
    `sec_master`.`assetclass`,
    'A',
    sum(`loader`.`allocation`/100),
    null,
    null,
    now(),
    now()
    from `temp`.`tmp_fixedmodel_robo_loader` `loader`, `temp`.`tmp_fixedmodel_robo_mapping` `mapping`, `invdb`.`sec_master`
    where `loader`.`modelName` = `mapping`.`modelName`
    and   `loader`.`ticker` = `sec_master`.`ticker`
    group by
    `mapping`.`theme`,
    `mapping`.`level`,
    `sec_master`.`assetclass`
    ;
    

END$$
DELIMITER ;
