
ALTER TABLE client
    ADD COLUMN password VARCHAR(64);

DROP TABLE IF EXISTS adresse;
CREATE TABLE adresse(
                        code_postal VARCHAR(64),
                        ville VARCHAR(64),
                        rue VARCHAR(64),
                        id integer auto_increment NOT NULL,
                        PRIMARY KEY(id));

ALTER TABLE client
    ADD COLUMN id_adresse_principale integer;

ALTER TABLE client ADD CONSTRAINT client_avec_adresse_principale_valide
    FOREIGN KEY (id_adresse_principale) REFERENCES adresse(id);