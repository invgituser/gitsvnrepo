CREATE 
VIEW `temp`.`vw_invessence_switch` AS
    SELECT 
        `invessence_switch`.`name` AS `name`,
        `invessence_switch`.`description` AS `description`,
        `invessence_switch`.`value` AS `value`,
        `invessence_switch`.`format` AS `format`,
        `invessence_switch`.`created` AS `created`,
        `invessence_switch`.`lastupdated` AS `lastupdated`
    FROM
        `invdb`.`invessence_switch`;
