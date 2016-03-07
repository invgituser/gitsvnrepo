DELIMITER $$
DROP PROCEDURE `sp_acct_risk_data_add_mod`$$

CREATE PROCEDURE `sp_acct_risk_data_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
        IN  p_acctnum         BIGINT(20),
        IN  p_attributename   VARCHAR(45), 
        IN  p_charvalue       VARCHAR(60), 
        IN  p_numvalue        DOUBLE(21,8),
        IN  p_datevalue       DATE

)
BEGIN 

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
   BEGIN
    INSERT INTO acct_risk_data
         (
			acctnum,
            attributename,
            charvalue,
            numvalue,
            datevalue
         )
    VALUES 
         (
		   p_acctnum,
           p_attributename,
           p_charvalue,
           p_numvalue,
           p_datevalue
         ) ; 
   END;
   ELSE
   BEGIN
     UPDATE  acct_risk_data
     SET 
           charvalue = p_charvalue,
           numvalue  = p_numvalue,
           datevalue = p_datevalue
     WHERE
		   acctnum = p_acctnum
	AND    attributename = p_attributename;
   END;
   END IF;
END 
$$
