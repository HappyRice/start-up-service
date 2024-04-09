CREATE TABLE IF NOT EXISTS HandDeck
(
    handId       INT(11)        NOT NULL,
    card         VARCHAR(255)   NOT NULL,
    CONSTRAINT FK_HandDeck_Hand_Id FOREIGN KEY (handId) REFERENCES Hand(id),
    UNIQUE KEY UK_HandDeck_handId_card (handId, card)
);