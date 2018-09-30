create database if not exists bookshop4;

use bookshop4;

create table if not exists books
(
  id                   bigint auto_increment
    primary key,
  dateAddedBookToStore datetime      null,
  dateOfPublication    datetime      null,
  description          varchar(255)  null,
  isAvailable          bit           null,
  isOld                bit           null,
  nameBook             varchar(255)  null,
  price                double        null
);


create table if not exists orders
(
  id                   bigint auto_increment
    primary key,
  dateOfStartedOrder   datetime null,
  dateOfCompletedOrder datetime null,
  isCompletedOrder     bit      null,
  book_id              bigint   null,
  constraint orders_books_id_fk
  foreign key (book_id) references books (id)
);

create table if not exists request
(
  id                 bigint auto_increment
    primary key,
  requireNameBook    varchar(30) null,
  requireIsCompleted bit         null,
  requireQuantity    int         null
);