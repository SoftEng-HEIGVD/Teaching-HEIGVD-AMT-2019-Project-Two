USE `db_rest_api`;

INSERT INTO user_entity(username, email, first_name, is_admin, last_name, password) VALUES ('admin',
'admin@email.com', 'Stephane', true, 'Selim', 'root');
INSERT INTO user_entity(username, email, first_name, is_admin, last_name, password) VALUES ('user1',
'Dylan@email.com', 'Bob', false, 'Dylan', 'password');

