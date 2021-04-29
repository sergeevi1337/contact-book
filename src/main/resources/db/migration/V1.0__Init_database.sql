CREATE TABLE usr
(
    id          serial PRIMARY KEY,
    username    VARCHAR(64),
    password    VARCHAR(64),
    active      boolean,
    deleted     boolean,
    UNIQUE (username)
);

CREATE TABLE user_role
(
    user_id INTEGER REFERENCES usr (id),
    roles   VARCHAR(255)
);

CREATE TABLE contacts
(
    id            serial PRIMARY KEY,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    phone_number  VARCHAR(255),
    email         VARCHAR(255),
    path_to_photo VARCHAR(512),
    deleted       boolean,
    UNIQUE (phone_number)
);

