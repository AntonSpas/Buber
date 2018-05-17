CREATE TABLE `buber`.`rides` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `driver_id` INT NOT NULL,
  `distance` INT NOT NULL,
  `cost` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `driver_id_idx` (`driver_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `buber`.`users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `driver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `buber`.`users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `buber`.`distance` (
  `distance` INT(10) NOT NULL,
  `first_district` VARCHAR(255) NOT NULL,
  `second_district` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`distance`),
  INDEX `first_district_idx` (`first_district` ASC),
  INDEX `second_district_idx` (`second_district` ASC),
  CONSTRAINT `first_district`
    FOREIGN KEY (`first_district`)
    REFERENCES `buber`.`districts` (`district`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `second_district`
    FOREIGN KEY (`second_district`)
    REFERENCES `buber`.`districts` (`district`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `buber`.`streets` (
  `street` VARCHAR(255) NOT NULL,
  `district` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`street`),
  INDEX `district_idx` (`district` ASC),
  CONSTRAINT `district`
  FOREIGN KEY (`district`)
  REFERENCES `buber`.`districts` (`district`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

UPDATE drivers SET password=SHA1('1010ab') WHERE id=1;
UPDATE drivers SET password=SHA1('2048cc') WHERE id=2;