CREATE DATABASE IF NOT EXISTS loan_pro;

-- Use the database
USE loan_pro;

-- Table structure for table `operation`
CREATE TABLE IF NOT EXISTS operation (id INT NOT NULL AUTO_INCREMENT,cost DECIMAL(38, 2) DEFAULT NULL,
    type ENUM('ADDITION', 'DIVISION', 'MULTIPLICATION', 'RANDOM_STRING', 'SQUARE_ROOT', 'SUBTRACTION') DEFAULT NULL,
    PRIMARY KEY (id));

-- Dumping data for table `operation`
INSERT INTO operation (id, cost, type)
VALUES
    (1, 1.00, 'ADDITION'),
    (2, 1.00, 'SUBTRACTION'),
    (3, 2.00, 'MULTIPLICATION'),
    (4, 2.00, 'DIVISION'),
    (5, 2.50, 'SQUARE_ROOT'),
    (6, 3.00, 'RANDOM_STRING');

-- Table structure for table `record`
CREATE TABLE IF NOT EXISTS record (
                                      id INT NOT NULL AUTO_INCREMENT,
                                      amount DECIMAL(38, 2) DEFAULT NULL,
    date DATETIME(6) DEFAULT NULL,
    operation_response VARCHAR(255) DEFAULT NULL,
    user_balance DECIMAL(38, 2) DEFAULT NULL,
    operation_id INT DEFAULT NULL,
    active BIT(1) DEFAULT NULL,
    user_id BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    KEY FKw9jt06n3r0bbig1iy99cce6t (operation_id),
    KEY FK44ctj7m4iik9qhcbaqt1aynka (user_id),
    CONSTRAINT FK44ctj7m4iik9qhcbaqt1aynka FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FKw9jt06n3r0bbig1iy99cce6t FOREIGN KEY (operation_id) REFERENCES operation (id)
    );

-- Dumping data for table `record`
INSERT INTO record (id, amount, date, operation_response, user_balance, operation_id, active, user_id)
VALUES
    (1, 1.00, NOW(), '3', 10.00, 1, 1, 1),
    (2, 1.00, NOW(), '-1', 9.00, 2, 1, 1),
    (3, 3.00, NOW(), 'eXm5BmefIp\n3gAg7BKyyd\n', 8.00, 6, 1, 1),
    (4, 1.00, NOW(), '3', 5.00, 1, 1, 1),
    (5, 2.00, NOW(), '2', 4.00, 3, 1, 1),
    (6, 2.50, NOW(), '1', 100.00, 5, 1, 1),
    (7, 2.50, NOW(), '3.2', 97.50, 5, 1, 1),
    (8, 2.50, NOW(), '4', 95.00, 5, 1, 1),
    (9, 2.50, NOW(), '13', 92.50, 5, 1, 1),
    (10, 2.50, NOW(), '40', 90.00, 5, 1, 1),
    (11, 2.50, NOW(), '40', 87.50, 5, 1, 1),
    (12, 2.50, NOW(), '40', 85.00, 5, 1, 1),
    (13, 2.00, NOW(), '2', 82.50, 3, 1, 1),
    (14, 2.00, NOW(), '3', 80.50, 3, 1, 1),
    (15, 2.00, NOW(), '0.5', 78.50, 4, 1, 1),
    (16, 1.00, NOW(), '-71', 76.50, 2, 1, 1),
    (17, 2.00, NOW(), '56', 75.50, 3, 1, 1),
    (18, 2.00, NOW(), '20', 73.50, 4, 1, 1),
    (19, 2.50, NOW(), '40', 71.50, 5, 1, 1),
    (20, 2.50, NOW(), '40.02', 69.00, 5, 1, 1),
    (21, 2.50, NOW(), '40.02499219', 66.50, 5, 1, 1),
    (22, 1.00, NOW(), '12', 64.00, 1, 1, 1),
    (23, 3.00, NOW(), 'N4FMjTaN6h\n', 63.00, 6, 1, 1),
    (24, 3.00, NOW(), 'JjjDU57FTE\n', 60.00, 6, 1, 1);

-- Table structure for table `roles`
CREATE TABLE IF NOT EXISTS roles (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     name ENUM('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_USER') DEFAULT NULL,
    PRIMARY KEY (id)
    );

-- Dumping data for table `roles`
INSERT INTO roles (id, name)
VALUES
    (1, 'ROLE_USER');

-- Table structure for table `user_roles`
CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT NOT NULL,
                                          role_id INT NOT NULL,
                                          PRIMARY KEY (user_id, role_id),
    KEY FKh8ciramu9cc9q3qcqiv4ue8a6 (role_id),
    CONSTRAINT FKh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT FKhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users (id)
    );

-- Dumping data for table `user_roles`
INSERT INTO user_roles (user_id, role_id)
VALUES
    (1, 1);

-- Table structure for table `users`
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     active BIT(1) DEFAULT NULL,
    email VARCHAR(255) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    user_balance DECIMAL(38, 2) DEFAULT NULL,
    username VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UKr43af9ap4edm43mmtq01oddj6 (username),
    UNIQUE KEY UK6dotkott2kjsp8vw4d0m25fb7 (email)
    );
