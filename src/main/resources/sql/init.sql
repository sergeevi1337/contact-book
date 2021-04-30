-- CREATE DB
CREATE DATABASE contact_book;

-- CREATE USERS
CREATE USER contacts_db WITH ENCRYPTED PASSWORD 'contacts_db';
ALTER USER contacts_db WITH SUPERUSER;

-- ACCESS TO DB
GRANT CONNECT ON DATABASE contact_book TO contacts_db;

\c contact_book;

-- CREATE SCHEMAS
CREATE SCHEMA contacts;

-- ACCESS TO SCHEMAS
GRANT ALL ON SCHEMA contacts TO contacts_db;