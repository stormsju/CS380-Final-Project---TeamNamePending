-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`CryptographyLevel1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CryptographyLevel1` (
  `Password` VARCHAR(20) NOT NULL,
  `LinearComboPublic` INT NOT NULL,
  `PersonID` INT NOT NULL,
  PRIMARY KEY (`PersonID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Security`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Security` (
  `PersonID` INT NOT NULL,
  `Flag` INT NOT NULL,
  `ConnectionsFlag` INT NOT NULL,
  PRIMARY KEY (`PersonID`),
  CONSTRAINT `securityFKcryptlvl1`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`CryptographyLevel1` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Person` (
  `PersonID` INT NOT NULL AUTO_INCREMENT,
  `JoinDate` DATE NOT NULL,
  `ConnectionID` INT NOT NULL,
  PRIMARY KEY (`PersonID`),
  CONSTRAINT `personFKsecurity`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`Security` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Profile` (
  `Fname` VARCHAR(25) NOT NULL,
  `Lname` VARCHAR(25) NOT NULL,
  `PersonID` INT NOT NULL,
  `AboutMe` VARCHAR(1024) NOT NULL,
  `BirthDate` DATE NOT NULL,
  `City` VARCHAR(25) NULL,
  `State` VARCHAR(25) NULL,
  `Country` VARCHAR(25) NULL,
  `Zip` VARCHAR(9) NULL,
  `Phone` VARCHAR(11) NULL,
  `Email` VARCHAR(45) NULL,
  PRIMARY KEY (`PersonID`),
  CONSTRAINT `profileFKperson`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`Person` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`BlockList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BlockList` (
  `PersonID` INT NOT NULL,
  `BlockedID` INT NOT NULL,
  `BlockFlag` INT NOT NULL,
  PRIMARY KEY (`PersonID`, `BlockedID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Connections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Connections` (
  `ConnectionID` INT NOT NULL AUTO_INCREMENT,
  `PersonID` INT NOT NULL,
  PRIMARY KEY (`ConnectionID`, `PersonID`),
  CONSTRAINT `connectionsFKperson`
    FOREIGN KEY (`ConnectionID`)
    REFERENCES `mydb`.`Person` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `connectionsFKblocklist`
    FOREIGN KEY (`ConnectionID`)
    REFERENCES `mydb`.`BlockList` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pictures`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pictures` (
  `PictureID` INT NOT NULL AUTO_INCREMENT,
  `PersonID` INT NOT NULL,
  `PictureFileName` NVARCHAR(100) NOT NULL,
  `PictureName` VARCHAR(45) NOT NULL,
  `Picture` VARBINARY(8000) NOT NULL,
  `Date` DATE NOT NULL,
  PRIMARY KEY (`PictureID`, `PersonID`),
  INDEX `picturesFKconnections_idx` (`PersonID` ASC) VISIBLE,
  CONSTRAINT `picturesFKperson`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`Person` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `picturesFKconnections`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`Connections` (`ConnectionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Posts` (
  `PostID` INT NOT NULL AUTO_INCREMENT,
  `PersonID` INT NOT NULL,
  `Date` DATE NOT NULL,
  `PostText` VARCHAR(45) NOT NULL,
  `ConnectionID` INT NULL,
  PRIMARY KEY (`PostID`, `PersonID`, `Date`),
  CONSTRAINT `postsFKperson`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`Person` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `postsFKconnections`
    FOREIGN KEY (`PersonID`)
    REFERENCES `mydb`.`Connections` (`ConnectionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
