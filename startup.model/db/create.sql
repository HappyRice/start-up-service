CREATE DATABASE flips CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE flips;

CREATE USER IF NOT EXISTS 'testUser'@'%' IDENTIFIED BY 'testPassword';
GRANT ALL PRIVILEGES ON flips.* TO 'testUser'@'%';