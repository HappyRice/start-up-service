CREATE TABLE IF NOT EXISTS Board
(
    id           INT(11)        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    createdDate  DATETIME(3)    NOT NULL,
    modifiedDate DATETIME(3)    NOT NULL,
    deletedBy    VARCHAR(255)   DEFAULT NULL,
    deletedDate  DATETIME(3)    DEFAULT NULL,
    guid         VARCHAR(255)   NOT NULL UNIQUE,
    handId       INT(11)        ,
    flop1        VARCHAR(255)   ,
    flop2        VARCHAR(255)   ,
    flop3        VARCHAR(255)   ,
    turn         VARCHAR(255)   ,
    river        VARCHAR(255)   ,
    CONSTRAINT FK_Board_Hand_Id FOREIGN KEY (handId) REFERENCES Hand(id)
);