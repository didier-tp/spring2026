l'application withJta (sans partie REST) est une petite application de démonstration
sur les transactions ditribuées de springBoot/SpringData_JPA en mode JTA (XA, commit à 2 phases)
----------------
Cette application utilise Atomikos comme implémentation de JTA.
----------------
Deux bases de données sont utilisées:
  * base "product_customer_order_db" gérée par serveur MariaDB
     avec tables product , customer , t_order , orderline
  * base "purchase_db" gérée soit par MariaDB mais idéalement par Postgresql
     avec table purchase
-----
cette application comporte la classe de test tp.service.TestPurchaseOrderService
   avec métodes testSavePurchaseOrderWithAcceptedAmount() et testRejectSavePurchaseOrderWithInvalidAmount()
   Ces deux méthodes déclenchent :
     - un ajout de commande ("order") dans les tables t_order et orderline de la base product_customer_order_db
     - un ajout d'achat effectué ("purchase") dans la table purchase de la base purchase_db

     point clef : la colonne amount de la table purchase n'accepte que des valeurs inférieures ou égales à 1000
     create table purchase (amount float(53) not null CHECK (amount <= 1000), ...)

   Ainsi si la méthode savePurchaseOrder() de PurchaseOrderServiceImpl comporte une sélection de produit dont le montant global
   est inférieur ou égal à 1000 , tout est ajouté définitivement (commit global complet sur les deux bases)
   Si par contre le montant global est supérieur à 1000 , la commande temporairement ajoutée dans les tables "t_order" et "orderline" de la base
   product_customer_order_db se retrouve annulée via un rollback automatique intervenant (en seconde phase) une fois que l'autre base "purchase_db"
   participant à la transaction globale aura remonté l'information "echec d'insert suite à amount invalide"

On a bien un comportement "tout ou rien" distribué sur plusieurs bases de données (éventuellement de marques différentes mais respectant toutes le protocole XA) .
===========
Pour préparer les bases de données nécessaires à l'exécution du test, il faudra prévoir :
- une éventuelle installation du serveur mariaDB (root/root)
- une éventuelle installation du serveur postgresql (postgres/root) avec ce paramétrage
  dans postgresql.conf (dans PostgreSQL\18\data ou ailleurs)
      max_prepared_transactions = 64  + rédémarrage du service postgresql
- une création de la base vide "purchase_db" (via PgAdmin)
- une initialisation des tables dans les bases via
   mvn flyway:migrate
   mvn flyway:migrate -Ppurchase (avec profile purchase)
  ou bien des déclenchement direct des scripts de src/main/resources/db/migration
-------
Une fois le test lancé via junit/java on pourra visualiser le contenu des tables via HeidiSQL et/ou PgAdmin

