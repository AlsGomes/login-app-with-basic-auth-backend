set foreign_key_checks = 0;

truncate table user;

set foreign_key_checks = 1;

INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (1, 'João', 'joao@gmail.com', '123', utc_timestamp, utc_timestamp);
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (2, 'Maria', 'maria@gmail.com', '123', utc_timestamp, utc_timestamp);
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (3, 'Alisson', 'alisson@gmail.com', '123', utc_timestamp, utc_timestamp);
INSERT INTO user (id, name, email, password, created_at, last_updated) VALUES (4, 'Cristina', 'cristina@gmail.com', '123', utc_timestamp, utc_timestamp);
