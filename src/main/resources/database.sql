create table tables
(
    id      serial not null
        constraint table_pk
            primary key,
    n_guest integer
);

alter table tables
    owner to postgres;

create table dish
(
    id         serial not null
        constraint dish_pk
            primary key,
    item       varchar,
    unit_price double precision
);

alter table dish
    owner to postgres;

create table server
(
    id         serial not null
        constraint server_pk
            primary key,
    first_name varchar,
    last_name  varchar
);

alter table server
    owner to postgres;

create table bill
(
    id         serial not null
        constraint bill_pk
            primary key,
    table_idx  integer
        constraint table_fk
            references tables,
    server_idx integer
        constraint serveur_fk
            references server
);

alter table bill
    owner to postgres;

create table dish_bill
(
    dish_idx integer not null
        constraint dish_fk
            references dish,
    bill_idx integer not null
        constraint bill_fk
            references bill,
    quantity integer,
    constraint dish_bill_pk
        primary key (dish_idx, bill_idx)
);

alter table dish_bill
    owner to postgres;

