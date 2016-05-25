DROP PROCEDURE IF Exists `sp_acct_risk_data_sel`;

DELIMITER $$
CREATE PROCEDURE `sp_acct_risk_data_sel`(
		IN p_acctnum            BIGINT(20),
        IN  p_attributename     VARCHAR(45)    
)
BEGIN 

if (p_attributename is NULL) 
then
begin
SELECT `acct_risk_data`.`acctnum`,
    `acct_risk_data`.`atttributename`,
	case 
		when (`acct_risk_data`.`datevalue` is not null)
			then funct_convert_timestamp(`acct_risk_data`.`charvalue`)
	    when (`acct_risk_data`.`numvalue` is not NULL)
			then `acct_risk_data`.`numvalue`
		else `acct_risk_data`.`charvalue`
	end as datavalue 
FROM `acct_risk_data`
WHERE `acct_risk_data`.`acctnum` = p_acctnum
;
end;
else
begin
SELECT `acct_risk_data`.`acctnum`,
    `acct_risk_data`.`atttributename`,
	case 
		when (`acct_risk_data`.`datevalue` is not null)
			then funct_convert_timestamp(`acct_risk_data`.`charvalue`)
	    when (`acct_risk_data`.`numvalue` is not NULL)
			then `acct_risk_data`.`numvalue`
		else `acct_risk_data`.`charvalue`
	end as datavalue
FROM `acct_risk_data`
WHERE `acct_risk_data`.`acctnum` = p_acctnum
AND `acct_risk_data`.`atttributename` = p_attributename
;
end;
END IF;
END$$
DELIMITER ;
