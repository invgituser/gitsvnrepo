DELIMITER $$

DROP PROCEDURE `sp_acct_risk_data_sel` $$

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
    `acct_risk_data`.`charvalue`,
    `acct_risk_data`.`numvalue`,
    `acct_risk_data`.`datevalue`
FROM `acct_risk_data`
WHERE `acct_risk_data`.`acctnum` = p_acctnum
;
end;
else
begin
SELECT `acct_risk_data`.`acctnum`,
    `acct_risk_data`.`atttributename`,
    `acct_risk_data`.`charvalue`,
    `acct_risk_data`.`numvalue`,
    `acct_risk_data`.`datevalue`
FROM `acct_risk_data`
WHERE `acct_risk_data`.`acctnum` = p_acctnum
AND `acct_risk_data`.`atttributename` = p_attributename
;
end;
END IF;
END;
$$
DELIMITER ;
