use `invdb`;
INSERT INTO `invdb`.`dc_m_firm_lookup` (`lookupSet`, `lookupCode`, `displayName`, `parentLookupId`, `value`, `status`, `isRequired`) VALUES ('CONTRAFIRM', 'WEDBSECINC', 'Wedbush Securities Inc.', '', 'Wedbush Securities Inc.', 'A', 'Y');
UPDATE `invdb`.`dc_m_firm_lookup` SET `value`='OptionsXpress/BrokersXpress', `isRequired`='Y' WHERE `lookupCode`='OPTIINC' and`lookupSet`='CONTRAFIRM';
UPDATE `invdb`.`dc_m_firm_lookup` SET `value`='Merrill Lynch', `isRequired`='Y' WHERE `lookupCode`='MERRLYNCPIER' and`lookupSet`='CONTRAFIRM';
INSERT INTO `invdb`.`dc_m_firm_lookup` (`lookupSet`, `lookupCode`, `displayName`, `parentLookupId`, `value`, `status`, `isRequired`, `address`, `phoneNumber`) VALUES ('CONTRAFIRM', 'WELLFARG', 'Wells Fargo', '', 'Wachovia SecuritiesFirst Clearing Corp(Wachovia & Wells Fargo)', 'A', 'Y', 'One North Jefferson, St. Louis, MO 63103', '(866) 224-5708');
