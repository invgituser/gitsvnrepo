DROP PROCEDURE IF EXISTS `invdb`.`sel_tddc_ACATFirm_list`;

DELIMITER $$
CREATE  PROCEDURE `invdb`.`sel_tddc_ACATFirm_list`(
)
BEGIN
	SELECT 
		`dc_m_firm_lookup`.`lookupSet`,
		`dc_m_firm_lookup`.`lookupCode`,
		`dc_m_firm_lookup`.`displayName`,
		`dc_m_firm_lookup`.`parentLookupId`,
		`dc_m_firm_lookup`.`value`,
		`dc_m_firm_lookup`.`remark`,
		`dc_m_firm_lookup`.`sortOrder`,
		`dc_m_firm_lookup`.`status`,
		`dc_m_firm_lookup`.`isRequired`,
        `dc_m_firm_lookup`.`address`,
        `dc_m_firm_lookup`.`phoneNumber`
	FROM `dc_m_firm_lookup`
    WHERE `dc_m_firm_lookup`.`status` = 'A';
	  
END$$
DELIMITER ;