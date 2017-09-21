use service;
ALTER TABLE `service`.`dc_template_mapping` 
ADD COLUMN `isDisabled` CHAR(1) NOT NULL DEFAULT 'N' AFTER `role` ;

update service.dc_template_mapping set dbcolumn='feesCheck' ,isDisabled='Y' where tempCode='BB_ACCT_APPLI'	and tab='Checkbox' and role='Client' and lable like '%fee%';
update service.dc_template_mapping set dbcolumn='feesCheck' ,isDisabled='Y'where tempCode='BB_ACCT_APPLI'	and tab='Checkbox' and role='Joint' and lable like '%fee%';
update service.dc_template_mapping set dbcolumn='feesCheck' ,isDisabled='Y'where tempCode='BB_IRA_APPLI'	and tab='Checkbox' and role='Client' and lable like '%fee%';
update service.dc_template_mapping set dbcolumn='feesCheck' ,isDisabled='Y'where tempCode='BB_IRAQRP_BENE'	and tab='Checkbox' and role='Client' and lable like '%fee%';
