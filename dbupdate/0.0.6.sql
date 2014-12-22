use afisha;

CREATE TABLE `afisha`.`page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uriName` VARCHAR(45) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `text` TEXT NOT NULL,
  `Creator_AppUser_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modifed` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));
	
  
  CREATE TABLE `afisha`.`blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `text` TEXT NOT NULL,
  `Creator_AppUser_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modifed` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));
	