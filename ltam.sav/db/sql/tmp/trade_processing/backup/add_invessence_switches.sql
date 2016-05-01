DELETE FROM `invdb`.`invessence_switch`
where name in ('ALLOC_MAX_OFFSET', 'LAST_REBALANCED', 'NEXT_REBALANCE','DAYS_TO_REBALANCE','MIN_FEE');

INSERT INTO `invdb`.`invessence_switch` (`name`, `description`, `value`, `format`, `created`, `lastupdated`) 
	VALUES ('ALLOC_MAX_OFFSET', 'max allocation offset to rebalance', '5.00', 'N', '2014-04-10 10:00:54', NULL);
INSERT INTO `invdb`.`invessence_switch` (`name`, `description`, `value`, `format`, `created`, `lastupdated`) 
	VALUES ('LAST_REBALANCED', 'Last Date of Rebalance', '20140215', 'D', '2014-04-08 21:30:54', NULL);
INSERT INTO `invdb`.`invessence_switch` (`name`, `description`, `value`, `format`, `created`, `lastupdated`) 
	VALUES ('NEXT_REBALANCE', 'Next Business date of Rebalance', '20140415', 'D', '2014-04-08 21:30:54', NULL);
INSERT INTO `invdb`.`invessence_switch` (`name`, `description`, `value`, `format`, `created`) 
	VALUES ('DAYS_TO_REBALANCE', 'Number of Days to use to rebalance', '90', 'N', '2014-04-10 10:00:54');
INSERT INTO `invdb`.`invessence_switch` (`name`, `description`, `value`, `format`, `created`) 
	VALUES ('MIN_FEE', 'Minimum Fee to charge', '250', 'N', '2014-04-14 10:00:54');


