DROP VIEW IF EXISTS `vw_assetmapping_list`;

CREATE 
VIEW `vw_assetmapping_list` AS
    select 
        `asset_mapping`.`assetclass` AS `assetclass`,
        `asset_mapping`.`description` AS `description`,
        `asset_mapping`.`asset_level` AS `asset_level`,
        `asset_mapping`.`sortorder` AS `sortorder`,
        `asset_mapping`.`index_fund` AS `indexFund`,
        `asset_mapping`.`lower_bound` AS `lowerBound`,
        `asset_mapping`.`upper_bound` AS `upperBound`,
        `asset_mapping`.`color` AS `color`,
        `asset_mapping`.`index_lower_bound` AS `indexlowerBound`,
        `asset_mapping`.`index_upper_bound` AS `indexupperBound`,
        `asset_mapping`.`avg_performace` AS `averageReturn`,
        `asset_mapping`.`risk_adjustment` AS `risk_adjustment`,
        `asset_mapping`.`end_allocation` AS `end_allocation`
    from
        `asset_mapping`
    where
        (`asset_mapping`.`asset_level` in (1));
