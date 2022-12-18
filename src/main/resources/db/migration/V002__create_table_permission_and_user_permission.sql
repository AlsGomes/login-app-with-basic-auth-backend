CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL UNIQUE,
  `description` varchar(70) NOT NULL,
  `created_at` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_permission` (
  `id_user` bigint NOT NULL,
  `id_permission` bigint NOT NULL,
  FOREIGN KEY `fk_user` (`id_user`) REFERENCES user(`id`),
  FOREIGN KEY `fk_permission` (`id_permission`) REFERENCES permission(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
