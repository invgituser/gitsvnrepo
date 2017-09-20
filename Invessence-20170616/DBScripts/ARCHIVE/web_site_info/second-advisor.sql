insert into invdb.user_basket_access
SELECT 'BB-TCM',
    `user_basket_access`.`theme`,
    `user_basket_access`.`status`,
    `user_basket_access`.`displayname`,
    `user_basket_access`.`sortorder`,
    `user_basket_access`.`primary`,
    `user_basket_access`.`taxable`,
    `user_basket_access`.`created`,
    `user_basket_access`.`lastupdated`
FROM `invdb`.`user_basket_access`
where `user_basket_access`.`advisor` = 'BB';

insert into invdb.dc_advisor_details
SELECT 2,
    `dc_advisor_details`.`advisorCode`,
    `dc_advisor_details`.`firmName`,
    `dc_advisor_details`.`primaryContact`,
    `dc_advisor_details`.`email`,
    `dc_advisor_details`.`created`,
    `dc_advisor_details`.`createdBy`,
    `dc_advisor_details`.`updated`,
    `dc_advisor_details`.`updatedBy`
FROM `invdb`.`dc_advisor_details`
where `dc_advisor_details`.`id` = 1;

