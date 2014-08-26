CREATE TABLE `htmlText` (
  `tag` VARCHAR(30) NOT NULL,
  `title` VARCHAR(80) NULL,
  `htmlText` LONGTEXT NULL,
  `created` DATE NULL,
  `last_updated` DATE NULL,
  PRIMARY KEY (`tag`))
ENGINE = InnoDB;
