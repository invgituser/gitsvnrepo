delimiter $$
DROP PROCEDURE `sp_risk_mapping_add_mod`
$$

CREATE PROCEDURE `sp_risk_mapping_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
        IN  p_risktype        VARCHAR(45), 
        IN  p_charvalue       VARCHAR(60), 
        IN  p_numvalue        DOUBLE(21,8),
        IN  p_datevalue       DATE

)
BEGIN 

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
   BEGIN
    INSERT INTO risk_mapping
         (
            risktype,
            charvalue,
            numvalue,
            datevalue
         )
    VALUES 
         ( 
           p_risktype,
           p_charvalue,
           p_numvalue,
           p_datevalue
         ) ; 
   END;
   ELSE
   BEGIN
     UPDATE  risk_mapping
     SET 
           charvalue = p_charvalue,
           numvalue  = p_numvalue,
           datevalue = p_datevalue
     WHERE
           risktype = p_risktype;
   END;
   END IF;
END
$$

