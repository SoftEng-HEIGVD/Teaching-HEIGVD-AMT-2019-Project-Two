CREATE DATABASE IF NOT EXISTS users_amt CHARACTER SET utf8 COLLATE utf8_general_ci;

USE users_amt;

CREATE TABLE IF NOT EXISTS users
( 
    id_user INT UNIQUE NOT NULL AUTO_INCREMENT, 
    username VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL UNIQUE, 
    `password` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    email VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
    role SMALLINT NOT NULL DEFAULT 0
) CHARACTER SET=utf8 COLLATE utf8_general_ci;



INSERT INTO users(`username`, `password`, `email`, `role`) VALUES
('admin', 'test123', 'admin@boozify.ch', 1),
('user','1234', 'user@boozify.ch', 0);

