CREATE TABLE `test_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `age` TINYINT NULL,
  `salary` INT NULL,
  PRIMARY KEY (`id`));