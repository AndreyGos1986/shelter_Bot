-- liquibase formatted sql

-- changeset golenevav:1

CREATE TABLE bot_response
(
    id               BIGSERIAL    NOT NULL PRIMARY KEY,
    key_message      VARCHAR(255) NOT NULL,
    response_message VARCHAR(255) NOT NULL
);

ALTER TABLE bot_response
    owner to "Alexey";

CREATE TABLE knowledge
(
    id           BIGSERIAL    NOT NULL PRIMARY KEY,
    code_id      VARCHAR(255) NOT NULL,
    question     VARCHAR(255) NOT NULL,
    answer       VARCHAR(255) NOT NULL,
    version      BIGINT       NOT NULL,
    has_answered BOOLEAN      NOT NULL,
    has_approved BOOLEAN      NOT NULL
);


ALTER TABLE knowledge
    owner to "Alexey";

CREATE TABLE parent_user
(
    id                        BIGSERIAL                   NOT NULL PRIMARY KEY,
    parent_id                 BIGINT                      NOT NULL,
    shelter_user_id           BIGINT                      NOT NULL,
    name                      VARCHAR(255)                NOT NULL,
    surname                   VARCHAR(255)                NOT NULL,
    phone_number              VARCHAR(255)                NOT NULL,
    last_report_date          DATE                        NOT NULL,
    last_notice_date_and_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_of_probation          DATE                        NOT NULL
);

ALTER TABLE parent_user
    owner to "Alexey";

CREATE TABLE volunteer
(
    id             BIGSERIAL NOT NULL,
    firstname      VARCHAR(255),
    surname        VARCHAR(255),
    specialization INTEGER,
    CONSTRAINT pk_volunteer PRIMARY KEY (id)
);

ALTER TABLE parent_user
    owner to "Alexey";

CREATE TABLE pet
(
    id                        BIGSERIAL    NOT NULL PRIMARY KEY,
    nick_name                 VARCHAR(255),
    age                       INT          NOT NULL,
    ration                    VARCHAR(255) NOT NULL,
    behavior                  VARCHAR(255) NOT NULL,
    health                    VARCHAR(255) NOT NULL,
    type                      VARCHAR(255) NOT NULL,
    last_report_date          DATE         NOT NULL,
    last_notice_date_and_time TIMESTAMP    NOT NULL,
    end_of_probation          DATE         NOT NULL,
    volunteer_id              BIGINT
);


ALTER TABLE pet
    ADD CONSTRAINT FK_PET_ON_VOLUNTEER FOREIGN KEY (volunteer_id) REFERENCES volunteer (id);

ALTER TABLE pet
    owner to "Alexey";

CREATE TABLE report
(
    id        BIGSERIAL NOT NULL,
    parent_id BIGINT,
    photo     BYTEA,
    ration    VARCHAR(255),
    health    VARCHAR(255),
    behavior  VARCHAR(255),
    status    INTEGER,
    date      date,
    CONSTRAINT pk_report PRIMARY KEY (id)
);

ALTER TABLE pet
    owner to "Alexey";

CREATE TABLE shelter_user
(
    id           BIGSERIAL NOT NULL,
    telegram_id  BIGINT,
    name         VARCHAR(255),
    surname      VARCHAR(255),
    user_type    INTEGER,
    shelter_type INTEGER,
    CONSTRAINT pk_shelteruser PRIMARY KEY (id)
);

ALTER TABLE pet
    owner to "Alexey";

CREATE TABLE volunteer_calling
(
    id           BIGSERIAL NOT NULL,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    phone_number VARCHAR(255),
    parent_id    BIGINT,
    user_id      BIGINT,
    cause        VARCHAR(255),
    status       INTEGER,
    type         INTEGER,
    shelter_type INTEGER,
    date         date,
    CONSTRAINT pk_volunteercalling PRIMARY KEY (id)
);

ALTER TABLE pet
    owner to "Alexey";

