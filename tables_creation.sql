CREATE DATABASE  IF NOT EXISTS `buber`;
USE `buber`;

DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` tinyint(3) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `ban_scores` tinyint(4) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `account` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `drivers`;
CREATE TABLE `drivers` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `car_type` enum('ECONOMY','PREMIUM','LARGE') NOT NULL DEFAULT 'ECONOMY',
  `car_model` varchar(45) NOT NULL,
  `car_number` varchar(45) NOT NULL,
  `earned` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `street` varchar(255) NOT NULL,
  `dest_street` varchar(255) NOT NULL,
  `cost` decimal(8,2) NOT NULL,
  `status` enum('RECEIVED','ACCEPTED','DONE','CANCELED') NOT NULL DEFAULT 'RECEIVED',
  `car_type` enum('ECONOMY','PREMIUM','LARGE') NOT NULL DEFAULT 'ECONOMY',
  `driver_id` smallint(6) DEFAULT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `driver_id_idx` (`driver_id`),
  KEY `client_id_idx` (`client_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `driver_id` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `distance`;
CREATE TABLE `distance` (
  `id` tinyint(3) NOT NULL AUTO_INCREMENT,
  `distance` double NOT NULL,
  `first_district` varchar(255) NOT NULL,
  `second_district` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `first_district_idx` (`first_district`),
  KEY `second_district_idx` (`second_district`),
  CONSTRAINT `first_district` FOREIGN KEY (`first_district`) REFERENCES `districts` (`district`) ON UPDATE CASCADE,
  CONSTRAINT `second_district` FOREIGN KEY (`second_district`) REFERENCES `districts` (`district`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `districts`;
CREATE TABLE `districts` (
  `district` varchar(255) NOT NULL,
  PRIMARY KEY (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `streets`;
CREATE TABLE `streets` (
  `id` tinyint(3) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `district_idx` (`district`),
  CONSTRAINT `district` FOREIGN KEY (`district`) REFERENCES `districts` (`district`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
