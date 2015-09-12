-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nncloud
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nncloud
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nncloud` DEFAULT CHARACTER SET utf8 ;
USE `nncloud` ;

-- -----------------------------------------------------
-- Table `nncloud`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`users` (
  `id_user` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `activated` TINYINT(1) NOT NULL,
  `info_to_admin` VARCHAR(256) NULL DEFAULT 'No info provided',
  `session_id` VARCHAR(64) NULL DEFAULT NULL,
  `registered` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `session_id_UNIQUE` (`session_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`networks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`networks` (
  `id_network` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_user` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `creation` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_network`, `id_user`),
  INDEX `fk_networks_users_idx` (`id_user` ASC),
  CONSTRAINT `fk_networks_users`
    FOREIGN KEY (`id_user`)
    REFERENCES `nncloud`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`layers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`layers` (
  `id_layer` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_network` INT(10) UNSIGNED NOT NULL,
  `relative_number` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_layer`, `id_network`),
  INDEX `fk_layers_networks1_idx` (`id_network` ASC),
  CONSTRAINT `fk_layers_networks1`
    FOREIGN KEY (`id_network`)
    REFERENCES `nncloud`.`networks` (`id_network`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`neurons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`neurons` (
  `id_neuron` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_layer` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_neuron`, `id_layer`),
  INDEX `fk_neurons_layers1` (`id_layer` ASC),
  CONSTRAINT `fk_neurons_layers1`
    FOREIGN KEY (`id_layer`)
    REFERENCES `nncloud`.`layers` (`id_layer`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`activation_functions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`activation_functions` (
  `id_activation_function` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_neuron` INT(10) UNSIGNED NOT NULL,
  `function` VARCHAR(45) NULL DEFAULT NULL,
  `domain_rule` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_activation_function`, `id_neuron`),
  INDEX `fk_activation_functions_neurons1_idx` (`id_neuron` ASC),
  CONSTRAINT `fk_activation_functions_neurons1`
    FOREIGN KEY (`id_neuron`)
    REFERENCES `nncloud`.`neurons` (`id_neuron`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`connections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`connections` (
  `id_neuron` INT(10) UNSIGNED NOT NULL,
  `id_synapse` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_neuron`, `id_synapse`),
  INDEX `fk_neurons_has_synapses_neurons1_idx` (`id_neuron` ASC),
  CONSTRAINT `fk_neurons_has_synapses_neurons1`
    FOREIGN KEY (`id_neuron`)
    REFERENCES `nncloud`.`neurons` (`id_neuron`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`performance_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`performance_settings` (
  `visualisation` TINYINT(1) NULL DEFAULT NULL,
  `id_user` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_user`),
  INDEX `fk_performance_settings_users2_idx` (`id_user` ASC),
  CONSTRAINT `fk_performance_settings_users2`
    FOREIGN KEY (`id_user`)
    REFERENCES `nncloud`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nncloud`.`synapses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`synapses` (
  `id_synapse` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `weight` DOUBLE NULL,
  `id_neuron_out` INT(10) UNSIGNED NOT NULL,
  `id_neuron_in` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_synapse`, `id_neuron_out`, `id_neuron_in`),
  INDEX `fk_synapses_neurons1_idx` (`id_neuron_out` ASC),
  INDEX `fk_synapses_neurons2_idx` (`id_neuron_in` ASC),
  CONSTRAINT `fk_synapses_neurons1`
    FOREIGN KEY (`id_neuron_out`)
    REFERENCES `nncloud`.`neurons` (`id_neuron`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_synapses_neurons2`
    FOREIGN KEY (`id_neuron_in`)
    REFERENCES `nncloud`.`neurons` (`id_neuron`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
