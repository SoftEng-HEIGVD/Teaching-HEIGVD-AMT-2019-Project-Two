CREATE DATABASE IF NOT EXISTS users_amt CHARACTER SET utf8 COLLATE utf8_general_ci;

USE users_amt;

CREATE TABLE IF NOT EXISTS users
( 
    email VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL, 
    last_name VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    administrator SMALLINT NOT NULL DEFAULT 0
) CHARACTER SET=utf8 COLLATE utf8_general_ci;



INSERT INTO users(`email`, `first_name`, `last_name`, `password`, `administrator`) VALUES
('julien@boozify.ch', 'julien','huguet', 'test123', 1),
('test@boozify.ch', 'test', 'test', '1234', 0);

