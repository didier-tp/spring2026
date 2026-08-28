
create table purchase (amount float(53) not null CHECK (amount <= 1000), customer_id bigint, id BIGSERIAL PRIMARY KEY, order_id bigint, time_stamp timestamp );

