set foreign_key_checks = 0;

truncate table user;
truncate table permission;
truncate table user_permission;

set foreign_key_checks = 1;

-- user
-- Password = 12345678
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (1, 'João', 'joao@gmail.com', '$2a$10$q1SAVDm1vbE8adpQNjYMDOWryY1lnIolxzmFNaRSJjssUD5LOncx6', utc_timestamp, utc_timestamp);
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (2, 'Maria', 'maria@gmail.com', '$2a$10$q1SAVDm1vbE8adpQNjYMDOWryY1lnIolxzmFNaRSJjssUD5LOncx6', utc_timestamp, utc_timestamp);
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (3, 'Alisson', 'alisson@gmail.com', '$2a$10$q1SAVDm1vbE8adpQNjYMDOWryY1lnIolxzmFNaRSJjssUD5LOncx6', utc_timestamp, utc_timestamp);
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (4, 'Cristina', 'cristina@gmail.com', '$2a$10$q1SAVDm1vbE8adpQNjYMDOWryY1lnIolxzmFNaRSJjssUD5LOncx6', utc_timestamp, utc_timestamp);

-- permission
INSERT INTO permission (id, name, description, created_at, last_updated) VALUES (1, 'ROLE_USER_ADMIN', 'Usuário administrador', utc_timestamp, utc_timestamp);
INSERT INTO permission (id, name, description, created_at, last_updated) VALUES (2, 'ROLE_USER_BASIC', 'Usuário comum', utc_timestamp, utc_timestamp);

-- user_permission
INSERT INTO user_permission (id_user, id_permission) VALUES (1, 2);
INSERT INTO user_permission (id_user, id_permission) VALUES (2, 2);
INSERT INTO user_permission (id_user, id_permission) VALUES (3, 1);
INSERT INTO user_permission (id_user, id_permission) VALUES (4, 1);
