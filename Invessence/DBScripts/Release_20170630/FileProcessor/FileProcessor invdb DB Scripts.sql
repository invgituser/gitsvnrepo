USE `invdb`;
CREATE TABLE `trade_process_identifier` (
  `acctnum` bigint(20) NOT NULL,
  `tradeStatus` varchar(1) DEFAULT NULL COMMENT 'N = New\nA = Allocation\nD = DateBreak\nV = View',
  `processStatus` varchar(1) DEFAULT NULL COMMENT 'N : Not Processed\nY : User Selected\nR : ReBalance\nP : Processed\nS : Sent',
  `reason` varchar(100) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `file_process_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product` varchar(45) NOT NULL,
  `mode` varchar(45) NOT NULL,
  `process` varchar(45) NOT NULL,
  `processId` varchar(45) NOT NULL,
  `fileName` varchar(100) NOT NULL,
  `status` varchar(45) NOT NULL,
  `remarks` varchar(500) NOT NULL,
  `executionTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



USE `invdb`;
DROP procedure IF EXISTS `file_process_auditrial`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `file_process_auditrial`(
in p_id	bigint(20),
in p_product	varchar(45),
in p_mode	varchar(45),
in p_process	varchar(45),
in p_processId	varchar(45),
in p_fileName	varchar(100),
in p_status	varchar(45),
in p_remarks	varchar(500),
in p_executionTime	datetime,
in p_opt varchar(20),
out op_msgCode int(3),out op_msg varchar(20))
BEGIN

	Insert into invdb.file_process_audit(
product,
mode,
process,
processId,
fileName,
status,
remarks,
executionTime)
    value(p_product,
p_mode,
p_process,
p_processId,
p_fileName,
p_status,
p_remarks,
now());

    
	SELECT 'MSG', max(id) INTO op_msg , op_msgCode from invdb.file_process_audit;

END$$

DELIMITER ;
