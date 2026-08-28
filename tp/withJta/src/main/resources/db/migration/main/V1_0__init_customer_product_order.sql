create table customer (id bigint not null auto_increment, email varchar(255), primary key (id));
create table order_line (quantity integer, order_id bigint not null, product_id bigint not null, primary key (order_id, product_id));
create table product (price float(53) not null, id bigint not null auto_increment, label varchar(255), primary key (id));
create table t_order (customer_id bigint, id bigint not null auto_increment, time_stamp datetime(6), primary key (id)) ;
alter table if exists order_line add constraint orderLineWithValidOrderId foreign key (order_id) references t_order (id);
