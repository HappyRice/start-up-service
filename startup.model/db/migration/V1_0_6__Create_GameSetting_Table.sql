CREATE TABLE IF NOT EXISTS GameSetting
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL,
    modifiedDate DATETIME(3)    NOT NULL,
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    gameId       INT(11)        ,
    type         VARCHAR(255)   NOT NULL,
    winsRequired TINYINT(1)     NOT NULL,
    CONSTRAINT FK_GameSetting_Game_Id FOREIGN KEY (gameId) REFERENCES Game(id)
);