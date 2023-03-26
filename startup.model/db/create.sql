CREATE DATABASE startup CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE startup;

CREATE USER IF NOT EXISTS 'testUser'@'%' IDENTIFIED BY 'testPassword';
GRANT ALL PRIVILEGES ON startup.* TO 'testUser'@'%';