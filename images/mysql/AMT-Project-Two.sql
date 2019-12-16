DROP DATABASE IF EXISTS api;
CREATE DATABASE api;
USE api;

CREATE TABLE `videogame_entity` (
  `id` INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `kind` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `supported_on` varchar(255) NOT NULL
);