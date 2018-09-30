create database if not exists senla;

use senla;

create table if not exists products
(
  maker varchar(10) not null,
  model varchar(50) not null
    primary key,
  type  varchar(50) not null
);

create table if not exists laptops
(
  code   int         not null,
  model  varchar(50) not null,
  speed  smallint(6) not null,
  ram    int         not null,
  hd     double      not null,
  price  double      null,
  screen tinyint     not null,
  constraint laptop_product_model_fk
  foreign key (model) references products (model)

);

create table if not exists pcs
(
  code  int         not null
    primary key,
  model varchar(50) not null,
  speed smallint(6) not null,
  ram   smallint(6) not null,
  hd    double      not null,
  cd    varchar(10) not null,
  price double      null,
  constraint pc_product_model_fk
  foreign key (model) references products (model)
);

create table if not exists printers
(
  code  int         not null,
  model varchar(50) not null,
  color char        not null,
  type  varchar(10) not null,
  price double      null,
  constraint printer_product_model_fk
  foreign key (model) references products (model)
);


