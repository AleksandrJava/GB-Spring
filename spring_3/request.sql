CREATE TABLE customers (id serial, name varchar(255), PRIMARY KEY (id));

CREATE TABLE goods (id serial, title varchar(255), cost int, PRIMARY KEY (id));

CREATE TABLE purchases (id serial, customer_id integer REFERENCES customers (id), good_id integer REFERENCES goods (id), cost int);

SELECT * FROM customers;

SELECT * FROM goods;

SELECT * FROM purchases;