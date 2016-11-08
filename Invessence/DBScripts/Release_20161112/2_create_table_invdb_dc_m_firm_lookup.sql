CREATE TABLE invdb.dc_m_firm_lookup (
  lookupSet varchar(45) NOT NULL,
  lookupCode varchar(45) NOT NULL,
  displayName varchar(100) DEFAULT NULL,
  parentLookupId varchar(45) DEFAULT NULL,
  value varchar(100) DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  sortOrder int(11) DEFAULT NULL,
  status char(1) DEFAULT NULL,
  isRequired char(1) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  phoneNumber varchar(20) DEFAULT NULL,
  created date DEFAULT NULL,
  createdBy varchar(45) DEFAULT NULL,
  updated date DEFAULT NULL,
  updatedBy varchar(45) DEFAULT NULL,
  PRIMARY KEY (displayName,lookupCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;