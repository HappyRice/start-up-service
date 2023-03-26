CREATE TABLE IF NOT EXISTS User
(
    id           INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME     NOT NULL,
    modifiedDate DATETIME     NOT NULL,
    deletedBy    VARCHAR(255) DEFAULT NULL,
    deletedDate  DATETIME     DEFAULT NULL,
    guid         VARCHAR(255) NOT NULL,
    name         VARCHAR(128),
    email        VARCHAR(128),
    UNIQUE KEY UK_ValidationPolicy_guid (guid)
);