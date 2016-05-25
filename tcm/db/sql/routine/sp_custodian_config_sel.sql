delimiter $$

DROP PROCEDURE `sp_custodian_config_sel`
$$

CREATE PROCEDURE `sp_custodian_config_sel`()
BEGIN 

		 SELECT customerid,firm, correspondentid, correspondentofficeid,
               cryptkey, officecd, altbranch, registeredrep
         FROM custodian_config;

END
$$

