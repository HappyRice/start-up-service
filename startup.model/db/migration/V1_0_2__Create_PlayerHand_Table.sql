CREATE TABLE IF NOT EXISTS PlayerHand
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    modifiedDate DATETIME(3)    NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    playerId     INT(11)        NOT NULL,
    card1        VARCHAR(255)   NOT NULL,
    card2        VARCHAR(255)   NOT NULL,
    CONSTRAINT FK_PlayerId FOREIGN KEY (playerId) REFERENCES Player(id)
);