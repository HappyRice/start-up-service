CREATE TABLE IF NOT EXISTS Game
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL,
    modifiedDate DATETIME(3)    NOT NULL,
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    startDate    DATETIME(3)    ,
    endDate      DATETIME(3)    ,
    type         VARCHAR(255)   NOT NULL,
    code         VARCHAR(255)   NOT NULL UNIQUE,
    winsRequired TINYINT(1)     NOT NULL DEFAULT 1
);