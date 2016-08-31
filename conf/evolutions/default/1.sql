# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table payment_history (
  id                        number(19) not null,
  receipt_id                varchar2(255),
  sales_man_id              number(19),
  sales_date                timestamp,
  prev_due                  number(19,4),
  paid                      number(19,4),
  current_due               number(19,4),
  constraint pk_payment_history primary key (id))
;

create table product (
  id                        number(19) not null,
  product_name              varchar2(255),
  product_code              varchar2(255),
  product_price             number(19,4),
  product_quantity          number(19,4),
  constraint pk_product primary key (id))
;

create table receipts (
  id                        number(19) not null,
  receipt_id                varchar2(255),
  product_id                number(19),
  product_quantity          number(19,4),
  sales_man_id              number(19),
  sales_date                timestamp,
  total_price               number(19,4),
  constraint pk_receipts primary key (id))
;

create table sales_man (
  id                        number(19) not null,
  sales_name                varchar2(255),
  sales_address             varchar2(255),
  sales_contact             varchar2(255),
  sales_tot_due             number(19,4),
  constraint pk_sales_man primary key (id))
;

create sequence payment_history_seq;

create sequence product_seq;

create sequence receipts_seq;

create sequence sales_man_seq;

alter table receipts add constraint fk_receipts_productId_1 foreign key (product_id) references product (id);
create index ix_receipts_productId_1 on receipts (product_id);



# --- !Downs

drop table payment_history cascade constraints purge;

drop table product cascade constraints purge;

drop table receipts cascade constraints purge;

drop table sales_man cascade constraints purge;

drop sequence payment_history_seq;

drop sequence product_seq;

drop sequence receipts_seq;

drop sequence sales_man_seq;

