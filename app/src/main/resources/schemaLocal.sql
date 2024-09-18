DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255),
   created_at TIMESTAMP
);

CREATE TABLE url_checks(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status_code INT,
    title VARCHAR(255),
    h1 VARCHAR(255),
    description VARCHAR(255),
    url_id INT REFERENCES urls(id),
    created_at TIMESTAMP
);