--liquibase formatted sql

--changeset hottabych04:1
CREATE TABLE IF NOT EXISTS file
(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR NOT NULL ,
    description VARCHAR NOT NULL ,
    creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    data TEXT NOT NULL
);