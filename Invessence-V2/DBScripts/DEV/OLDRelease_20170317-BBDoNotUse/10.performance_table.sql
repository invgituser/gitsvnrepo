create table invdb.sec_fixed_projectionchart as
select * from invdb.sec_fixed_performancechart;

ALTER TABLE `invdb`.`sec_fixed_performancechart` 
CHANGE COLUMN `model` `level` VARCHAR(20) NOT NULL ;

delete from `invdb`.`sec_fixed_performancechart`
where theme not like '%BB';
update `invdb`.`sec_fixed_performancechart`
set `level` = 'S1'
where `level` = '1';
update `invdb`.`sec_fixed_performancechart`
set `level` = 'S2'
where `level` = '2';
update `invdb`.`sec_fixed_performancechart`
set `level` = 'S3'
where `level` = '3';
update `invdb`.`sec_fixed_performancechart`
set `level` = 'S4'
where `level` = '4';
update `invdb`.`sec_fixed_performancechart`
set `level` = 'S5'
where `level` = '5';
insert into invdb.sec_fixed_performancechart
SELECT `sec_fixed_performancechart`.`theme`,
    'S6',
    `sec_fixed_performancechart`.`year`,
    `sec_fixed_performancechart`.`5percent`,
    `sec_fixed_performancechart`.`25percent`,
    `sec_fixed_performancechart`.`50percent`,
    `sec_fixed_performancechart`.`75percent`,
    `sec_fixed_performancechart`.`95percent`
FROM `invdb`.`sec_fixed_performancechart`
where `level` = 'S3';
insert into invdb.sec_fixed_performancechart
SELECT 'T.0.BB',
    `sec_fixed_performancechart`.`level`,
    `sec_fixed_performancechart`.`year`,
    `sec_fixed_performancechart`.`5percent`,
    `sec_fixed_performancechart`.`25percent`,
    `sec_fixed_performancechart`.`50percent`,
    `sec_fixed_performancechart`.`75percent`,
    `sec_fixed_performancechart`.`95percent`
FROM `invdb`.`sec_fixed_performancechart`
where `sec_fixed_performancechart`.`theme` = '0.BB';


