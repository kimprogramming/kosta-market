CREATE DATABASE IF NOT EXISTS kosta_market;
ALTER DATABASE kosta_market DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE kosta_market;

DROP TABLE IF EXISTS TBL_ORDER CASCADE;
DROP TABLE IF EXISTS TBL_USER_ORDER CASCADE;
DROP TABLE IF EXISTS TBL_CATEGORY_PRODUCT CASCADE;
DROP TABLE IF EXISTS TBL_CATEGORY CASCADE;
DROP TABLE IF EXISTS TBL_SELLER_PRODUCT CASCADE;
DROP TABLE IF EXISTS TBL_PRODUCT CASCADE;
DROP TABLE IF EXISTS TBL_SELLER CASCADE;
DROP TABLE IF EXISTS TBL_DELIVERY CASCADE;
DROP TABLE IF EXISTS TBL_USER CASCADE;

CREATE TABLE IF NOT EXISTS TBL_USER (
  user_no int primary key AUTO_INCREMENT,
  username varchar(255),
  password varchar(255),
  name varchar(255),
  contact varchar(255)
);

CREATE TABLE IF NOT EXISTS TBL_DELIVERY (
  delivery_no int primary key AUTO_INCREMENT,
  user_no int,
  delivery_place varchar(255),
  FOREIGN KEY (user_no) references TBL_USER(user_no)
);

CREATE TABLE TBL_SELLER (
  seller_no int primary key AUTO_INCREMENT,
  user_no int,
  business_no varchar(255),
  FOREIGN KEY (user_no) references TBL_USER(user_no)
);

CREATE TABLE TBL_PRODUCT (
  product_no int primary key AUTO_INCREMENT,
  product_name varchar(255),
  product_price int,
  product_img_file_name varchar(255),
  product_img_path varchar(255),
  product_description varchar(255),
  product_quantity int
);

CREATE TABLE IF NOT EXISTS TBL_SELLER_PRODUCT (
  seller_no int,
  product_no int,
  FOREIGN KEY (seller_no) references TBL_SELLER(seller_no),
  FOREIGN KEY (product_no) references TBL_PRODUCT(product_no)
);

CREATE TABLE IF NOT EXISTS TBL_CATEGORY (
  category_no int primary key AUTO_INCREMENT,
  category_name varchar(255)
);

CREATE TABLE IF NOT EXISTS TBL_CATEGORY_PRODUCT (
  category_no int,
  product_no int,
  FOREIGN KEY (category_no) references TBL_CATEGORY(category_no),
  FOREIGN KEY (product_no) references TBL_PRODUCT(product_no)
);

CREATE TABLE TBL_ORDER (
  order_no int primary key AUTO_INCREMENT,
  order_quantity int,
  order_price int,
  order_date date,
  payment_method int,
  order_state int
);

CREATE TABLE TBL_USER_ORDER (
  user_no int,
  product_no int,
  FOREIGN KEY (user_no) references TBL_USER(user_no),
  FOREIGN KEY (product_no) references TBL_PRODUCT(product_no)
);