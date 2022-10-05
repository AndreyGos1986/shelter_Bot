-- liquibase formatted sql

-- changeset golenevav:1
CREATE TABLE volunteer_calling
(
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    parent_id    BIGINT       NOT NULL,
    user_id      BIGINT       NOT NULL,
    cause        VARCHAR(255),
    status       VARCHAR(255),
    type         VARCHAR(255),
    date         TIMESTAMP
);

ALTER TABLE volunteer_calling
    owner to "Alexey";

