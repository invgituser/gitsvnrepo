use service;
update service.dc_template_mapping a set a.dbColumn='clientAccountID' where lable='AccountNumber' and dbcolumn is null; 