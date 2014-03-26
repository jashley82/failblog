SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `failblog` DEFAULT CHARACTER SET utf8 ;
USE `failblog` ;

-- -----------------------------------------------------
-- Table `failblog`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `failblog`.`users` (
  `userid` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `username` VARCHAR(25) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `salt` BLOB NULL DEFAULT NULL,
  `password` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `failblog`.`blogs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `failblog`.`blogs` (
  `blogid` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `content` BLOB NULL DEFAULT NULL,
  `meta` BLOB NULL DEFAULT NULL,
  `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` INT(11) NULL DEFAULT NULL,
  `deleted` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`blogid`),
  INDEX `userid_idx` (`userid` ASC),
  CONSTRAINT `userid`
    FOREIGN KEY (`userid`)
    REFERENCES `failblog`.`users` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `failblog`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `failblog`.`comments` (
  `commentid` INT(11) NOT NULL AUTO_INCREMENT,
  `blogid` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(25) NULL DEFAULT NULL,
  `content` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`commentid`),
  INDEX `blogid` (`blogid` ASC),
  CONSTRAINT `comments_ibfk_1`
    FOREIGN KEY (`blogid`)
    REFERENCES `failblog`.`blogs` (`blogid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
