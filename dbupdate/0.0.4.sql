use afisha;

-- -----------------------------------------------------
-- Table `afisha`.`appuser_like_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `afisha`.`appuser_like_event` (
  `appuser_id` INT(11) NOT NULL,
  `event_id` INT(11) NOT NULL,
  `STATUS` INT NOT NULL,
  PRIMARY KEY (`appuser_id`, `event_id`),
  INDEX `fk_appuser_has_event1_event1_idx` (`event_id` ASC),
  INDEX `fk_appuser_has_event1_appuser1_idx` (`appuser_id` ASC),
  CONSTRAINT `fk_appuser_has_event1_appuser1`
    FOREIGN KEY (`appuser_id`)
    REFERENCES `afisha`.`AppUser` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appuser_has_event1_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `afisha`.`Event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


ALTER TABLE `FileTable` CHANGE `id` `id` INT(11) NOT NULL AUTO_INCREMENT;