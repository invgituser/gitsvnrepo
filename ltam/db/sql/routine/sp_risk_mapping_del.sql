delimiter $$
DROP PROCEDURE `sp_risk_mapping_del`
$$

CREATE PROCEDURE `sp_risk_mapping_del`(
        IN  p_risk              VARCHAR(45)    
)
BEGIN 

     DELETE FROM  risk_mapping
     WHERE
           risk = p_risk;
END
$$

