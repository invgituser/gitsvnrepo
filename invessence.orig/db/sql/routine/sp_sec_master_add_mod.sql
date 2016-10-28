delimiter $$
DROP PROCEDURE `sp_sec_master_add_mod`
$$

CREATE PROCEDURE `sp_sec_master_add_mod`(
    IN  p_addmodflag      VARCHAR(1),
	IN p_instrumentid bigint(20),
	IN p_status varchar(1),
	IN p_ticker varchar(20),
	IN p_cusip varchar(12),
	IN p_isin varchar(15),
	IN p_name varchar(60),
	IN p_primarymarket varchar(10),
	IN p_type varchar(12),
	IN p_subtype varchar(20),
	IN p_assetclass varchar(20),
	IN p_subclass varchar(20)
)
BEGIN 

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO sec_master
			 (
		instrumentid,
		status,
		ticker,
		cusip,
		isin,
		name,
		primarymarket,
		type,
		subtype,
		assetclass,
		subclass
			 )
		VALUES 
			 ( 
		p_instrumentid,
		p_status,
		p_ticker,
		p_cusip,
		p_isin,
		p_name,
		p_primarymarket,
		p_type,
		p_subtype,
		p_assetclass,
		p_subclass
			 ) ; 
	   END;
	ELSE
	   BEGIN
		 UPDATE  sec_master
		 SET 
			status = p_status,
			ticker = p_ticker,
			cusip = p_cusip,
			isin = p_isin,
			name = p_name,
			primarymarket = p_primarymarket,
			type = p_type,
			subtype = p_subtype,
			assetclass = p_assetclass,
			subclass = p_subclass
		 WHERE
			instrumentid = p_instrumentid;
	   END;
   END IF;
END
$$

