CREATE TABLE contract (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    text VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE participant (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE contract_participant (
    contract_id   INTEGER,
    participant_id   INTEGER
);
