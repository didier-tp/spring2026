create table client (numero bigint not null auto_increment, nom varchar(255), prenom varchar(255), primary key (numero)) engine=InnoDB;
create table client_compte (num_client bigint not null, num_compte bigint not null) engine=InnoDB;
create table compte (solde float(53), numero bigint not null auto_increment, label varchar(255), primary key (numero)) engine=InnoDB;
create table operation (date_op date, montant float(53), num_compte bigint, numero bigint not null auto_increment, label varchar(64), primary key (numero)) engine=InnoDB;
alter table if exists client_compte add constraint FKggnv04cd9uupmyr9mxi22c9an foreign key (num_compte) references compte (numero);
alter table if exists client_compte add constraint FKgf8yv8smckpywht4afmpa43ip foreign key (num_client) references client (numero);
alter table if exists operation add constraint FKmesdd1k28dq0r18el7qmaayil foreign key (num_compte) references compte (numero);
