DROP TABLE IF EXISTS client;

CREATE TABLE client(
                       nom VARCHAR(64),
                       prenom VARCHAR(64),
                       num_client integer auto_increment NOT NULL,
                       date_naissance DATE,
                       telephone VARCHAR(16),
                       email VARCHAR(64),
                       PRIMARY KEY(num_client));

