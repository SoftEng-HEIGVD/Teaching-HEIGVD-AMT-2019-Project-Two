DROP DATABASE IF EXISTS firstApi;
CREATE DATABASE firstApi;
USE firstApi;


CREATE TABLE `user_entity` (
  `email` varchar(255) UNIQUE NOT NULL,
  `username` varchar(255),
  `password` varchar(255),
  `first_name` varchar(255),
  `last_name` varchar(255),
  `is_admin` boolean,
  CONSTRAINT FK_Coach_username_ID PRIMARY KEY (username)

);


INSERT INTO user_entity (`email`,`username`,`password`, `first_name`, `last_name`,`is_admin`) VALUES ("robel_eteeete@ererer.com", "robelinho", "ok", "robel","test",true);
INSERT INTO user_entity (`email`,`username`,`password`, `first_name`, `last_name`,`is_admin`) VALUES ("nair_erere@erer.com", "naireee", "ok", "nair","test",true);
INSERT INTO user_entity (`email`,`username`,`password`, `first_name`, `last_name`,`is_admin`) VALUES ("volkan_julien@gameway.com", "waye", "ok", "waye","test",false);

