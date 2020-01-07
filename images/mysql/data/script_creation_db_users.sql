CREATE DATABASE IF NOT EXISTS users_amt CHARACTER SET utf8 COLLATE utf8_general_ci;

USE users_amt;

CREATE TABLE IF NOT EXISTS users
( 
    first_name VARCHAR(255) NOT NULL, 
    last_name VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    email VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL, 
    role SMALLINT NOT NULL DEFAULT 0
) CHARACTER SET=utf8 COLLATE utf8_general_ci;



INSERT INTO users(`first_name`, `last_name`, `password`, `email`, `role`) VALUES
('julien','huguet', 'test123', 'julien@boozify.ch', 1),
('test', 'test', '1234', 'test@boozify.ch', 0);

