DROP DATABASE IF EXISTS api;
CREATE DATABASE api;
USE api;

CREATE TABLE `videogame_entity` (
  `id` INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `kind` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `supported_on` varchar(255) NOT NULL
);



CREATE TABLE `user` (
  `username` varchar(255) UNIQUE NOT NULL,
  `passsword` varchar(255),
  `first_name` varchar(255),
  `last_name` varchar(255),
  `isAdmin` boolean,
  CONSTRAINT FK_Coach_username_ID PRIMARY KEY (username)

);

CREATE TABLE `amt_teams` (
  `name` varchar(255) UNIQUE  NOT NULL,
  `creationDate` date,
  `location` varchar(255),
  CONSTRAINT FK_Teams_name_ID PRIMARY KEY (name)

);

CREATE TABLE `amt_players` (
  `id` INTEGER UNSIGNED AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `position` varchar(255),
  `number` int,
  `name_teams`varchar(255) NOT NULL,

  CONSTRAINT FK_Player_id_ID PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS `amt_teams_coach` (
  `coach_id` varchar(255)  NOT NULL,
  `team_id` varchar(255) NOT NULL,
  PRIMARY KEY (`coach_id`, `team_id`),
  INDEX `fk_coach_team_team` (`team_id` ASC),
  INDEX `fk_coach_team_coach` (`coach_id` ASC),
  CONSTRAINT `fk_coach_team_coach`
    FOREIGN KEY (`coach_id`)
    REFERENCES `amt_coaches` (`username`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_coach_team_team`
    FOREIGN KEY (`team_id`)
    REFERENCES `amt_teams` (`name`)
    ON DELETE CASCADE
    )
ENGINE = InnoDB;


ALTER TABLE amt_players ADD CONSTRAINT FK_player_team
  FOREIGN KEY (name_teams) REFERENCES amt_teams (name) ON DELETE CASCADE;