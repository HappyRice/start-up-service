CREATE TABLE IF NOT EXISTS Game
(
    id            INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate   DATETIME(3)    NOT NULL,
    modifiedDate  DATETIME(3)    NOT NULL,
    deletedBy     VARCHAR(255)   DEFAULT NULL,
    deletedDate   DATETIME(3)    DEFAULT NULL,
    guid          VARCHAR(255)   NOT NULL UNIQUE,
    activeDate    DATETIME(3)    ,
    state         VARCHAR(255)   NOT NULL,
    winnerId      INT(11)        ,
    currentHandId INT(11)        ,
    code          VARCHAR(255)   NOT NULL UNIQUE
);