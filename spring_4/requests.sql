SET search_path TO boot;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost integer);

SELECT * FROM products;

INSERT INTO products VALUES (1,'book', 1600);
INSERT INTO products VALUES (2,'chair', 12300);
INSERT INTO products VALUES (3,'monitor', 9900);
INSERT INTO products VALUES (4,'sneakers', 4600);
INSERT INTO products VALUES (5,'t-shirt', 1500);