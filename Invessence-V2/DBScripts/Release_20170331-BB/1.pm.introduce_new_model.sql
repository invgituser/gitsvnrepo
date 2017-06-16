DELETE FROM `invdb`.`sec_fixedmodel` WHERE `theme` in ('0.TA', 'T.0.TA');
DELETE FROM `invdb`.`sec_fixedmodel_asset` WHERE `theme` in ('0.TA', 'T.0.TA');
DELETE FROM `invdb`.`sec_fixedmodel_subasset` WHERE `theme` in ('0.TA', 'T.0.TA');
DELETE FROM `invdb`.`sec_fixed_projectionchart` WHERE `theme` in ('0.TA', 'T.0.TA');
DELETE FROM `invdb`.`sec_fixed_performancechart` WHERE `theme` in ('0.TA', 'T.0.TA');

INSERT INTO `invdb`.`sec_fixedmodel`
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
`created`,
`updated`)
SELECT REPLACE(`sec_fixedmodel`.`theme`,'BB','TA'),
    `sec_fixedmodel`.`level`,
    `sec_fixedmodel`.`sortorder`,
    `sec_fixedmodel`.`status`,
    `sec_fixedmodel`.`displayname`,
    `sec_fixedmodel`.`description`,
    `sec_fixedmodel`.`lowRisk`,
    `sec_fixedmodel`.`highRisk`,
    `sec_fixedmodel`.`expectedreturn`,
    `sec_fixedmodel`.`expectedrisk`,
    `sec_fixedmodel`.`created`,
    `sec_fixedmodel`.`updated`
FROM `invdb`.`sec_fixedmodel`
WHERE `sec_fixedmodel`.`theme` like '%BB';

INSERT INTO `invdb`.`sec_fixedmodel_asset`
(`theme`,
`level`,
`asset`,
`assetname`,
`status`,
`allocation`,
`color`,
`sortorder`,
`created`,
`updated`)
SELECT REPLACE(`sec_fixedmodel_asset`.`theme`,'BB','TA'),
    `sec_fixedmodel_asset`.`level`,
    `sec_fixedmodel_asset`.`asset`,
    `sec_fixedmodel_asset`.`assetname`,
    `sec_fixedmodel_asset`.`status`,
    `sec_fixedmodel_asset`.`allocation`,
    `sec_fixedmodel_asset`.`color`,
    `sec_fixedmodel_asset`.`sortorder`,
    `sec_fixedmodel_asset`.`created`,
    `sec_fixedmodel_asset`.`updated`
FROM `invdb`.`sec_fixedmodel_asset`
WHERE `sec_fixedmodel_asset`.`theme` like '%BB';

INSERT INTO `invdb`.`sec_fixedmodel_subasset`
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
SELECT REPLACE(`sec_fixedmodel_subasset_20160116`.`theme`,'BB','TA'),
    `sec_fixedmodel_subasset_20160116`.`level`,
    `sec_fixedmodel_subasset_20160116`.`asset`,
    `sec_fixedmodel_subasset_20160116`.`keyname`,
    `sec_fixedmodel_subasset_20160116`.`keydescription`,
    `sec_fixedmodel_subasset_20160116`.`status`,
    `sec_fixedmodel_subasset_20160116`.`allocation`,
    `sec_fixedmodel_subasset_20160116`.`sortorder`,
    `sec_fixedmodel_subasset_20160116`.`created`,
    `sec_fixedmodel_subasset_20160116`.`updated`
FROM `invdb`.`sec_fixedmodel_subasset_20160116`
WHERE `sec_fixedmodel_subasset_20160116`.`theme` like '%BB';

INSERT INTO `invdb`.`sec_fixed_projectionchart`
(`theme`,
`model`,
`year`,
`5percent`,
`25percent`,
`50percent`,
`75percent`,
`95percent`)
SELECT REPLACE(`sec_fixed_projectionchart`.`theme`,'BB','TA'),
    `sec_fixed_projectionchart`.`model`,
    `sec_fixed_projectionchart`.`year`,
    `sec_fixed_projectionchart`.`5percent`,
    `sec_fixed_projectionchart`.`25percent`,
    `sec_fixed_projectionchart`.`50percent`,
    `sec_fixed_projectionchart`.`75percent`,
    `sec_fixed_projectionchart`.`95percent`
FROM `invdb`.`sec_fixed_projectionchart`
WHERE `sec_fixed_projectionchart`.`theme` like '%BB';

INSERT INTO `invdb`.`sec_fixed_performancechart`
(`theme`,
`model`,
`year`,
`5percent`,
`25percent`,
`50percent`,
`75percent`,
`95percent`)
SELECT REPLACE(`sec_fixed_performancechart`.`theme`,'BB','TA'),
    `sec_fixed_performancechart`.`model`,
    `sec_fixed_performancechart`.`year`,
    `sec_fixed_performancechart`.`5percent`,
    `sec_fixed_performancechart`.`25percent`,
    `sec_fixed_performancechart`.`50percent`,
    `sec_fixed_performancechart`.`75percent`,
    `sec_fixed_performancechart`.`95percent`
FROM `invdb`.`sec_fixed_performancechart`
WHERE `sec_fixed_performancechart`.`theme` like '%BB';

select * from invdb.sec_fixed_performancechart;






