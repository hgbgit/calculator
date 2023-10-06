
CREATE TABLE `operation` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `cost` decimal(38,2) DEFAULT NULL,
                             `type` enum('ADDITION','DIVISION','MULTIPLICATION','RANDOM_STRING','SQUARE_ROOT','SUBTRACTION') DEFAULT NULL,
                             PRIMARY KEY (`id`)
)


INSERT INTO `operation` VALUES (1,1.00,'ADDITION'),(2,1.00,'SUBTRACTION'),(3,2.00,'MULTIPLICATION'),(4,2.00,'DIVISION'),(5,2.50,'SQUARE_ROOT'),(6,3.00,'RANDOM_STRING');

CREATE TABLE `record` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `amount` decimal(38,2) DEFAULT NULL,
                          `date` datetime(6) DEFAULT NULL,
                          `operation_response` varchar(255) DEFAULT NULL,
                          `user_balance` decimal(38,2) DEFAULT NULL,
                          `operation_id` int DEFAULT NULL,
                          `active` bit(1) DEFAULT NULL,
                          `user_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKw9jt06n3r0bbig1iy99cce6t` (`operation_id`),
                          KEY `FK44ctj7m4iik9qhcbaqt1aynka` (`user_id`),
                          CONSTRAINT `FK44ctj7m4iik9qhcbaqt1aynka` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                          CONSTRAINT `FKw9jt06n3r0bbig1iy99cce6t` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`)
)

INSERT INTO `record` VALUES (1,1.00,now(),'3',10.00,1,1,1),(2,1.00,now(),'-1',9.00,2,1,1),(3,3.00,now(),'eXm5BmefIp\n3gAg7BKyyd\n',8.00,6,1,1),(4,1.00,now(),'3',5.00,1,1,1),(5,2.00,now(),'2',4.00,3,1,1),(6,2.50,now(),'1',100.00,5,1,1),(7,2.50,now(),'3.2',97.50,5,1,1),(8,2.50,now(),'4',95.00,5,1,1),(9,2.50,now(),'13',92.50,5,1,1),(10,2.50,now(),'40',90.00,5,1,1),(11,2.50,now(),'40',87.50,5,1,1),(12,2.50,now(),'40',85.00,5,1,1),(13,2.00,now(),'2',82.50,3,1,1),(14,2.00,now(),'3',80.50,3,1,1),(15,2.00,now(),'0.5',78.50,4,1,1),(16,1.00,now(),'-71',76.50,2,1,1),(17,2.00,now(),'56',75.50,3,1,1),(18,2.00,now(),'20',73.50,4,1,1),(19,2.50,now(),'40',71.50,5,1,1),(20,2.50,now(),'40.02',69.00,5,1,1),(21,2.50,now(),'40.02499219',66.50,5,1,1),(22,1.00,now(),'12',64.00,1,1,1),(23,3.00,now(),'N4FMjTaN6h\n',63.00,6,1,1),(24,3.00,now(),'JjjDU57FTE\n',60.00,6,1,1);


DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` enum('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER') DEFAULT NULL,
                         PRIMARY KEY (`id`)
)

INSERT INTO `roles` VALUES (1,'ROLE_USER');


DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` int NOT NULL,
                              PRIMARY KEY (`user_id`,`role_id`),
                              KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
                              CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                              CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)





INSERT INTO `user_roles` VALUES (1,1);



CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `active` bit(1) DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `user_balance` decimal(38,2) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
                         UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
)





INSERT INTO `users` VALUES (1,_binary '','email@hugobarros.com.br','$2a$10$Py8jk2SdiwAtw7N0LyEQru/7QZHoeFqN9qKCyYWo658aGAbe4bnlm',57.00,'hgb');

