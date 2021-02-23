-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tienda_videojuegos_android
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tienda_videojuegos_android
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tienda_videojuegos_android` DEFAULT CHARACTER SET utf8 ;
USE `tienda_videojuegos_android` ;

-- -----------------------------------------------------
-- Table `tienda_videojuegos_android`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda_videojuegos_android`.`empleado` (
  `id_empleado` INT NOT NULL AUTO_INCREMENT,
  `nombre_empleado` VARCHAR(200) NOT NULL,
  `apellidos_empleado` VARCHAR(200) NOT NULL,
  `domicilio_empleado` VARCHAR(200) NOT NULL,
  `telefono_empleado` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id_empleado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tienda_videojuegos_android`.`videojuego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda_videojuegos_android`.`videojuego` (
  `id_videojuego` INT NOT NULL AUTO_INCREMENT,
  `título_videojuego` VARCHAR(200) NOT NULL,
  `pegi_videojuego` INT NOT NULL,
  `genero_videojuego` VARCHAR(200) NOT NULL,
  `logo_videojuego` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id_videojuego`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tienda_videojuegos_android`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda_videojuegos_android`.`venta` (
  `id_venta` INT NOT NULL AUTO_INCREMENT,
  `numero_venta` INT NOT NULL,
  `EMPLEADO_id_empleado` INT NOT NULL,
  `VIDEOJUEGO_id_videojuego` INT NOT NULL,
  PRIMARY KEY (`id_venta`, `EMPLEADO_id_empleado`, `VIDEOJUEGO_id_videojuego`),
  UNIQUE INDEX `numero_venta_UNIQUE` (`numero_venta` ASC) VISIBLE,
  INDEX `fk_VENTA_EMPLEADO_idx` (`EMPLEADO_id_empleado` ASC) VISIBLE,
  INDEX `fk_VENTA_VIDEOJUEGO1_idx` (`VIDEOJUEGO_id_videojuego` ASC) VISIBLE,
  CONSTRAINT `fk_VENTA_EMPLEADO`
    FOREIGN KEY (`EMPLEADO_id_empleado`)
    REFERENCES `tienda_videojuegos_android`.`empleado` (`id_empleado`),
  CONSTRAINT `fk_VENTA_VIDEOJUEGO1`
    FOREIGN KEY (`VIDEOJUEGO_id_videojuego`)
    REFERENCES `tienda_videojuegos_android`.`videojuego` (`id_videojuego`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
