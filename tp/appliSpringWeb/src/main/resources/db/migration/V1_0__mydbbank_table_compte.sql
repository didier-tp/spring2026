DROP TABLE IF EXISTS compte;

CREATE TABLE compte(
                       numero integer auto_increment NOT NULL,
                       label VARCHAR(64),
                       solde double,
                       PRIMARY KEY(numero));

