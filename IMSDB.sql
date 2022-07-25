CREATE DATABASE IF NOT EXISTS IMSDB;
USE IMSDB;

CREATE TABLE Items(
item_id int not null,
item_name varchar(50),
value_ double not null,
PRIMARY KEY(item_id));

CREATE TABLE Customers(
customer_id int NOT NULL AUTO_INCREMENT,
first_name varchar(20) NOT NULL,
last_name varchar(20) not null,
PRIMARY KEY (customer_id)
);

CREATE TABLE Orders(
  order_id int NOT NULL AUTO_INCREMENT,
  customer_id int NOT NULL,
  item_id int NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
  FOREIGN KEY (item_id) REFERENCES Items(item_id)
);

SHOW DATABASES