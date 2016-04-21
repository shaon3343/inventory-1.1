# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                        number(19) not null,
  product_name              varchar2(255),
  product_code              varchar2(255),
  product_price             number(19,4),
  product_quantity          number(19,4),
  constraint pk_product primary key (id))
;

create table sales_man (
  id                        number(19) not null,
  sales_name                varchar2(255),
  sales_address             varchar2(255),
  sales_contact             varchar2(255),
  sales_tot_due             number(19,4),
  constraint pk_sales_man primary key (id))
;

create sequence product_seq;

create sequence sales_man_seq;




# --- !Downs

drop table product cascade constraints purge;

drop table sales_man cascade constraints purge;

drop sequence product_seq;

drop sequence sales_man_seq;

