INSERT INTO service.dc_template_details VALUES ('UAT','BUILDINGBENJAMINS','DOCUSIGN-SERVICES','BB_ACAT_OTHER','f3d35d40-d093-4ae2-841a-12f1cac49432','Account Transfer Form Other','Y','A',NULL,NULL,NULL,NULL,NULL,NULL),
('PROD','BUILDINGBENJAMINS','DOCUSIGN-SERVICES','BB_ACAT_OTHER','0A6AC9B2-E603-47D2-AC35-379E256E968F','Account Transfer Form Other','Y','A',NULL,NULL,NULL,NULL,NULL,NULL);


INSERT INTO service.dc_template_mapping VALUES
('BB_ACAT_OTHER','Textbox','Contra Firm List','contraFirmList','Client'),
('BB_ACAT_OTHER','Radiobox','AccountType','accountType','Client'),
('BB_ACAT_OTHER','Radiobox','FromAccountType','fromAccountType','Client'),
('BB_ACAT_OTHER','Radiobox','SimpleFunded','simpleFunded','Client'),
('BB_ACAT_OTHER','Radiobox','TransferType','transferTypeId','Client'),
('BB_ACAT_OTHER','Textbox','AccountNumber',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AccountNumber2','accountNumber2','Client'),
('BB_ACAT_OTHER','Textbox','AccountTitle 122','accountTitle','Client'),
('BB_ACAT_OTHER','Textbox','AssetName1',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName10',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName2',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName3',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName4',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName5',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName6',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName7',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName8',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','AssetName9',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','CaseNumber','caseNumber','Client'),
('BB_ACAT_OTHER','Textbox','EEPlanType','eePlanType','Client'),
('BB_ACAT_OTHER','Textbox','FromAccountTitle','fromAccountTitle','Client'),
('BB_ACAT_OTHER','Textbox','FromEEPlanType','fromEEPlanType','Client'),
('BB_ACAT_OTHER','Textbox','FromFirmAddress','fromFirmAddress','Client'),
('BB_ACAT_OTHER','Textbox','FromFirmPhoneNumber','fromFirmPhoneNumber','Client'),
('BB_ACAT_OTHER','Textbox','FromOtherAccountType','fromOtherAccountType','Client'),
('BB_ACAT_OTHER','Textbox','FullName','fullName','Client'),
('BB_ACAT_OTHER','Textbox','OtherAccountType','otherAccountType','Client'),
('BB_ACAT_OTHER','Textbox','repcode','advisorCode','Client'),
('BB_ACAT_OTHER','Textbox','ShareQty1',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty10',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty2',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty3',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty4',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty5',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty6',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty7',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty8',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','ShareQty9',NULL,'Client'),
('BB_ACAT_OTHER','Textbox','SSN','ssn','Client');



INSERT INTO service.service_operation_details VALUES
('BUILDINGBENJAMINS','DOCUSIGN-SERVICES','ACAT_OTHER_NEW','DOCUSIGN','A',0,'BB_ACAT_OTHER');