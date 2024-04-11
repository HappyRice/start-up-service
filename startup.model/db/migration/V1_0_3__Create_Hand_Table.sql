CREATE TABLE IF NOT EXISTS Hand
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL,
    modifiedDate DATETIME(3)    NOT NULL,
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    gameId       INT(11)        NOT NULL,
    state        VARCHAR(255)   NOT NULL,
    winnerId     INT(11)        ,
    CONSTRAINT FK_Hand_Game_Id FOREIGN KEY (gameId) REFERENCES Game(id),
    CONSTRAINT FK_Hand_Player_Id FOREIGN KEY (winnerId) REFERENCES Player(id)
);