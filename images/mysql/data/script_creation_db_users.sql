CREATE DATABASE IF NOT EXISTS users_amt CHARACTER SET utf8 COLLATE utf8_general_ci;

USE users_amt;

CREATE TABLE IF NOT EXISTS user_entity
( 
    email VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL, 
    last_name VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    administrator SMALLINT NOT NULL DEFAULT 0
) CHARACTER SET=utf8 COLLATE utf8_general_ci;



INSERT INTO user_entity(`email`, `first_name`, `last_name`, `password`, `administrator`) VALUES
('john.doe@boozify.ch', 'john','doe', '$2a$10$91tXEkxOx1HPNbGIibif.O7t462t.nFAwymUOOwBtKQnCH.4o6FN6', 1),
('user@boozify.ch', 'user', 'user', '$2a$10$91tXEkxOx1HPNbGIibif.O7t462t.nFAwymUOOwBtKQnCH.4o6FN6', 0);

