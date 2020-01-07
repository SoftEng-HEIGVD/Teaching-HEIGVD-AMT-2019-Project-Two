USE `authentication_api`;

INSERT INTO `User` (`email`, `firstname`, `lastname`, `password`, `role`) VALUES ('admin@admin.ch', 'Admin', 'Istrator', '$2a$10$hUvnaEMrHdFH2qo8Q0IjqOG3UpCquW1Zaypw7VnJes.4ALVt2P7he', 'admin');
INSERT INTO `User` (`email`, `firstname`, `lastname`, `password`, `role`) VALUES ('user@user.ch', 'User', 'User', '$2a$10$Isct.0ROCG.X01dkHKrueePP6FeGUnOSZc2uv0ztQAQOJWzC.PJ3q', 'user');
    