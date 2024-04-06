CREATE TABLE IF NOT EXISTS Player
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL,
    modifiedDate DATETIME(3)    NOT NULL,
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    name         VARCHAR(128)   NOT NULL,
    gameId       INT(11)        NOT NULL,
    winCounter   TINYINT(1)     NOT NULL DEFAULT 0,
    CONSTRAINT FK_GameId FOREIGN KEY (gameId) REFERENCES Game(id)
);