--liquibase formatted sql

--changeset hottabych04:1
CREATE TABLE IF NOT EXISTS file
(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR NOT NULL ,
    description VARCHAR NOT NULL DEFAULT '',
    creation_date DATE NOT NULL,
    data TEXT NOT NULL
);