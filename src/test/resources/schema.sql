CREATE TABLE contract (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    text VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    role VARCHAR(128) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE contract_user (
    contract_id   INTEGER,
    user_id   INTEGER
);
