use afisha;
ALTER TABLE `AppUser` CHANGE `password` `password` VARCHAR(129) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;



-- -----------------------------------------------------
-- Table `afisha`.`appuser_has_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `afisha`.`appuser_has_place` (
  `appuser_id` INT(11) NOT NULL,
  `place_id` INT(11) NOT NULL,
  PRIMARY KEY (`appuser_id`, `place_id`),
  INDEX `fk_appuser_has_place_place1_idx` (`place_id` ASC),
  INDEX `fk_appuser_has_place_appuser1_idx` (`appuser_id` ASC),
  CONSTRAINT `fk_appuser_has_place_appuser1`
    FOREIGN KEY (`appuser_id`)
    REFERENCES `afisha`.`appuser` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appuser_has_place_place1`
    FOREIGN KEY (`place_id`)
    REFERENCES `afisha`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
