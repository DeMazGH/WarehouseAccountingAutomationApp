-- liquibase formatted sql

-- changeset demaz:1
create table if not exists socks(
    id              bigint generated by default as identity primary key,
    color           varchar(40),
    cotton_part     int,
    quantity        bigint
);