# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                        bigint auto_increment not null,
  product_name              varchar(255),
  product_code              varchar(255),
  product_price             float,
  product_quantity          float,
  constraint pk_product primary key (id))
;

create table receipts (
  id                        bigint auto_increment not null,
  product_id                bigint,
  product_quantity          float,
  sales_man_id              bigint,
  sales_date                datetime,
  total_due                 float,
  total_price               float,
  constraint pk_receipts primary key (id))
;

create table sales_man (
  id                        bigint auto_increment not null,
  sales_name                varchar(255),
  sales_address             varchar(255),
  sales_contact             varchar(255),
  sales_tot_due             float,
  constraint pk_sales_man primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table product;

drop table receipts;

drop table sales_man;

SET FOREIGN_KEY_CHECKS=1;

