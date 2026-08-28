DROP TABLE IF EXISTS pays;
DROP TABLE IF EXISTS devise;

CREATE TABLE devise(
                       code VARCHAR(8),
                       monnaie VARCHAR(64),
                       d_change double,
                       PRIMARY KEY(code));

CREATE TABLE pays(
                     code VARCHAR(8),
                     capitale VARCHAR(64),
                     nom VARCHAR(64),
                     ref_devise VARCHAR(64),
                     PRIMARY KEY(code));

ALTER TABLE pays ADD CONSTRAINT pays_avec_devise_valide
    FOREIGN KEY (ref_devise) REFERENCES devise(code);