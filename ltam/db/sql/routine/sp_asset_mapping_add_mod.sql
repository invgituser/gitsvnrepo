delimiter $$

DROP PROCEDURE `sp_asset_mapping_add_mod`
$$

CREATE PROCEDURE `sp_asset_mapping_add_mod`(
        IN  p_addmodflag       varchar(1),
        IN  p_assetclass       varchar(20), 
        IN  p_description      varchar(60),
		IN  p_asset_level      int(3),
        IN  p_sortorder        int(10),
		IN  p_index_fund       varchar(20),
		IN  p_lower_bound      float,
        IN  p_upper_bound      float,
		IN  p_color            varchar(20),
		IN  p_indx_lower_bound float,
 		IN  p_indx_upper_bound float,
        IN  p_avg_performace   float
)
BEGIN 

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
	   BEGIN
		INSERT INTO `asset_mapping`
				(`assetclass`,
				`description`,
				`asset_level`,
				`sortorder`,
				`index_fund`,
				`lower_bound`,
				`upper_bound`,
				`color`,
				`index_lower_bound`,
				`index_upper_bound`,
				`avg_performace`)
		VALUES 
			 ( 
				p_assetclass, 
				p_description ,
				p_asset_level,
				p_sortorder,
				p_index_fund ,
				p_lower_bound,
				p_upper_bound,
				p_color,
				p_indx_lower_bound,
				p_indx_upper_bound,
				p_avg_performace
			 ) ; 
	   END;
   ELSE
	   BEGIN
		 UPDATE  asset_mapping
		 SET 
				`description` = p_description
				,`asset_level` = p_asset_level
				,`sortorder` = p_sortorder
				,`index_fund` = p_index_fund
				,`lower_bound` = p_lower_bound
				,`upper_bound` = p_upper_bound
				,`color` = p_color
				,`index_lower_bound` = p_indx_lower_bound
				,`index_upper_bound` = p_indx_upper_bound
				,`avg_performace` = p_avg_performace
		 WHERE
			   assetclass = p_assetclass;
	   END;
   END IF;
END$$

