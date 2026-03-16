DROP TABLE IF EXISTS Compte;

CREATE TABLE Compte(
    numero integer auto_increment NOT NULL,
	label VARCHAR(64),
	solde double,
	PRIMARY KEY(numero));	 

INSERT INTO Compte (label,solde) VALUES ('compte courant',100);
INSERT INTO Compte (label,solde) VALUES ('compte codevi',50);    
INSERT INTO Compte (label,solde) VALUES ('compte 3',150); 

SELECT * FROM Compte;


