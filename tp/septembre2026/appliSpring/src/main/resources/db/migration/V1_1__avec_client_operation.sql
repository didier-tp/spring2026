create table client (numero bigint not null auto_increment, nom varchar(255), prenom varchar(255), primary key (numero));
create table client_compte (num_client bigint not null, num_compte bigint not null);
create table operation (date_op date, montant float(53), num_compte bigint, numero bigint not null auto_increment, label varchar(64), primary key (numero));
alter table if exists client_compte add constraint FK_client_compte_avec_compte_valide foreign key (num_compte) references compte (numero);
alter table if exists client_compte add constraint FK_client_compte_avec_client_valide foreign key (num_client) references client (numero);
alter table if exists operation add constraint FK_operation_avec_compte_valide foreign key (num_compte) references compte (numero);