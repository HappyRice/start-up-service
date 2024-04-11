CREATE TABLE IF NOT EXISTS PlayerHand
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL,
    modifiedDate DATETIME(3)    NOT NULL,
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    playerId     INT(11)        NOT NULL,
    handId       INT(11)        NOT NULL,
    card1        VARCHAR(255)   NOT NULL,
    card2        VARCHAR(255)   NOT NULL,
    CONSTRAINT FK_PlayerHand_Player_Id FOREIGN KEY (playerId) REFERENCES Player(id),
    CONSTRAINT FK_PlayerHand_Hand_Id FOREIGN KEY (handId) REFERENCES Hand(id)
);