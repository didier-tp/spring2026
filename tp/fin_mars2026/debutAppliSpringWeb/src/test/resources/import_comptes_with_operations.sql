INSERT INTO Compte (numero,label,solde) VALUES (1001,'compteA',200);
INSERT INTO Operation (date_op,label,montant,num_compte)  VALUES ('2011-01-20','achat 1a' , -15.0,1001);
INSERT INTO Operation (date_op,label,montant,num_compte)  VALUES ('2012-01-20','achat 2a' , -16.0,1001);
INSERT INTO Compte (numero,label,solde) VALUES (1002,'compteB',200);
INSERT INTO Operation (date_op,label,montant,num_compte)  VALUES ('2025-01-23','achat 1b_sql' , -12.0,1002);
INSERT INTO Operation (date_op,label,montant,num_compte)  VALUES ('2025-02-12','achat 2b_sql' , -13.0,1002);
INSERT INTO Operation (date_op,label,montant,num_compte)  VALUES ('2025-03-11','achat 2c_sql' , -17.0,1002);
INSERT INTO Operation (date_op,label,montant,num_compte)  VALUES ('2025-03-18','achat 2d_sql' , -9.0,1002);