
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS product;

CREATE TABLE category (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (category_id)
        REFERENCES category (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
