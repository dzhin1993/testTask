DROP TABLE cities IF EXISTS;

CREATE TABLE cities (
  id        INTEGER IDENTITY PRIMARY KEY,
  city      VARCHAR(255) NOT NULL,
  message   VARCHAR(255) NOT NULL
);