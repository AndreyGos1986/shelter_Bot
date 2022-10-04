-- liquibase formatted sql

-- changeset golenevav:1
CREATE TABLE call_volunteer
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  INTEGER      NOT NULL
);

ALTER TABLE call_volunteer
    owner to "Alexey";

