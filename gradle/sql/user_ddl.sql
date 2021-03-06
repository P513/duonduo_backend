CREATE TABLE `test_db`.`user` (
`id` INT NOT NULL AUTO_INCREMENT,
`nicknameId` INT NULL,
`email` VARCHAR(45) NULL,
`naverOAuth` VARCHAR(45) NULL,
`kakaoOAuth` VARCHAR(45) NULL,
`evalCnt` INT NOT NULL DEFAULT 0,
`evalSum` INT NOT NULL DEFAULT 0,
`createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`deletedAt` DATETIME NULL,
PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `test_db`.`user`
    ADD UNIQUE INDEX `nicknameId_UNIQUE` (`nicknameId` ASC) VISIBLE;
;

ALTER TABLE `test_db`.`user`
    ADD COLUMN `password` VARCHAR(128) NOT NULL AFTER `nicknameId`;

ALTER TABLE `test_db`.`user`
    CHANGE COLUMN `nicknameId` `nicknameId` INT NULL ;

ALTER TABLE `test_db`.`user`
    ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE;
;

ALTER TABLE `test_db`.`user`
    ADD COLUMN `role` VARCHAR(45) NOT NULL DEFAULT 'USER' AFTER `nicknameId`;

ALTER TABLE `test_db`.`user`
    CHANGE COLUMN `id` `id` BIGINT NOT NULL ,
    CHANGE COLUMN `nicknameId` `nicknameId` BIGINT NULL DEFAULT NULL ;
ALTER TABLE `test_db`.`user`
    CHANGE COLUMN `nicknameId` `infoId` BIGINT NULL DEFAULT NULL ;
