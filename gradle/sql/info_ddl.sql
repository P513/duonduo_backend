CREATE TABLE `test_db`.`nickname` (
`id` INT NOT NULL AUTO_INCREMENT,
`userId` INT NOT NULL,
`name` VARCHAR(45) NOT NULL,
`tier` INT NOT NULL,
`rank` INT NOT NULL,
`ment` VARCHAR(100) NOT NULL,
`selfPos` INT NOT NULL,
`duoPos` INT NOT NULL,
`playStyle` INT NOT NULL,
`voice` TINYINT NOT NULL,
`status` TINYINT NULL DEFAULT 1,
PRIMARY KEY (`id`),
UNIQUE INDEX `userId_UNIQUE` (`userId` ASC) INVISIBLE,
UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
CONSTRAINT `userId`
FOREIGN KEY (`userId`)
REFERENCES `test_db`.`user` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION);
ALTER TABLE `test_db`.`nickname`
    RENAME TO  `test_db`.`info` ;
ALTER TABLE `test_db`.`info`
    CHANGE COLUMN `voice` `voice` TINYINT NOT NULL DEFAULT 1 ;
ALTER TABLE `test_db`.`info`
    CHANGE COLUMN `status` `status` TINYINT NOT NULL DEFAULT '1' ;
