-- authorities: table
CREATE TABLE `authorities` (
  `username`  VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`, `authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- group_authorities: table
CREATE TABLE `group_authorities` (
  `group_id`  BIGINT(20)  NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  KEY `fk_group_authorities_group` (`group_id`),
  CONSTRAINT `fk_group_authorities_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- group_members: table
CREATE TABLE `group_members` (
  `id`       BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
    `group_id` BIGINT(20)  NOT NULL,
    PRIMARY KEY (`id`),
  KEY `fk_group_members_group` (`group_id`),
  CONSTRAINT `fk_group_members_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- groups: table
CREATE TABLE `groups` (
  `id`         BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- users: table
CREATE TABLE `users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `enabled`  TINYINT(1)  NOT NULL,
  PRIMARY KEY (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


