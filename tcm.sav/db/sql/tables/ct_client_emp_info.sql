CREATE  TABLE `invdb`.`client_emp_info` (
  `acctnum` BIGINT(20) NOT NULL ,
  `logonid` VARCHAR(20) NULL ,
  `empstatus` VARCHAR(45) NULL ,
  `employer` VARCHAR(80) NULL ,
  `natureofbusiness` VARCHAR(45) NULL ,
  `occupation` VARCHAR(45) NULL ,
  `address` VARCHAR(80) NULL ,
  `address2` VARCHAR(30) NULL ,
  `city` VARCHAR(25) NULL ,
  `state` VARCHAR(20) NULL ,
  `country` VARCHAR(20) NULL ,
  `zip` VARCHAR(9) NULL ,
  PRIMARY KEY (`acctnum`) );