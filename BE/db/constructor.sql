-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema nncloud
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nncloud
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nncloud` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `nncloud` ;

-- -----------------------------------------------------
-- Table `nncloud`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`users` (
  `id_user` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `activated` TINYINT(1) NOT NULL,
  `info_to_admin` VARCHAR(256) NULL DEFAULT 'No info provided',
  `session_id` VARCHAR(64) NULL,
  `registered` DATETIME NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `session_id_UNIQUE` (`session_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`networks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`networks` (
  `id_network` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_user` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `creation` DATETIME NULL,
  PRIMARY KEY (`id_network`, `id_user`),
  INDEX `fk_networks_users_idx` (`id_user` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`performance_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`performance_settings` (
  `id_user` INT UNSIGNED NOT NULL,
  `visualisation` TINYINT(1) NULL,
  PRIMARY KEY (`id_user`),
  INDEX `fk_performance_settings_users1_idx` (`id_user` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`layers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`layers` (
  `id_layer` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_network` INT UNSIGNED NOT NULL,
  `relative_number` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_layer`, `id_network`),
  INDEX `fk_layers_networks1_idx` (`id_network` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`synapses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`synapses` (
  `id_synapse` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `weight` DOUBLE NULL,
  PRIMARY KEY (`id_synapse`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`neurons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`neurons` (
  `id_neuron` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_layer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_neuron`, `id_layer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`connections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`connections` (
  `id_neuron` INT UNSIGNED NOT NULL,
  `id_synapse` INT UNSIGNED NOT NULL,
  INDEX `fk_neurons_has_synapses_neurons1_idx` (`id_neuron` ASC),
  PRIMARY KEY (`id_neuron`, `id_synapse`),
  INDEX `fk_neurons_has_synapses_synapses1_idx` (`id_synapse` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nncloud`.`activation_functions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nncloud`.`activation_functions` (
  `id_activation_function` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_neuron` INT UNSIGNED NOT NULL,
  `function` VARCHAR(45) NULL,
  `domain_rule` VARCHAR(45) NULL,
  PRIMARY KEY (`id_activation_function`, `id_neuron`),
  INDEX `fk_activation_functions_neurons1_idx` (`id_neuron` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
