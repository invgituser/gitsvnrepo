delimiter $$

DROP PROCEDURE `sp_asset_mapping_del`
$$

CREATE PROCEDURE `sp_asset_mapping_del`(
        IN  p_assetclass    varchar(20) 
)
BEGIN 
     DELETE  FROM asset_mapping
     WHERE
           assetclass = p_assetclass;
END
$$

