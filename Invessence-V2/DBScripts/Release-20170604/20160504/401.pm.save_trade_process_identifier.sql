DROP PROCEDURE IF EXISTS `invdb`.`save_trade_process_identifier`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`save_trade_process_identifier`(
   `p_acctnum` 		bigint(20)
  ,`p_tradeStatus`	varchar(1) --  COMMENT 'N = New\nA = Allocation\nD = DateBreak\nV = View',
  ,`p_processStatus`	varchar(1) -- COMMENT 'N : Not Processed\nY : User Selected\nR : ReBalance\nP : Processed\nS : Sent',
  ,`p_reason`			varchar(100)
)
BEGIN
	begin
    
		INSERT INTO `invdb`.`trade_process_identifier`
		(`acctnum`,
		`tradeStatus`,
		`processStatus`,
		`reason`,
		`created`,
		`updated`)
		VALUES
		(`p_acctnum`
		,`p_tradeStatus`
		,`p_processStatus`
		,`p_reason`
		,now()
		,null)
        ON duplicate key update
			`processStatus` = `p_processStatus`
            ,`updated` = now()
		;
    
    end;
END$$
DELIMITER ;
