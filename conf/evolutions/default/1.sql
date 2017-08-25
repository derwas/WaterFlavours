# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cost (
  id                        bigint not null,
  country                   varchar(255),
  unit                      varchar(255),
  value                     varchar(255),
  currency                  varchar(255),
  service                   varchar(255),
  reference                 varchar(255),
  description               varchar(255),
  constraint pk_cost primary key (id))
;

create table footprint (
  id                        bigint not null,
  wqt                       varchar(255),
  wunit                     varchar(255),
  value                     varchar(255),
  unit                      varchar(255),
  label                     varchar(255),
  reference                 varchar(255),
  image                     varchar(255),
  description               varchar(255),
  constraint pk_footprint primary key (id))
;

create table metaphor (
  id                        bigint not null,
  qt                        varchar(255),
  v_water                   varchar(255),
  value                     varchar(255),
  unit                      varchar(255),
  label                     varchar(255),
  reference                 varchar(255),
  image                     varchar(255),
  description               varchar(255),
  constraint pk_metaphor primary key (id))
;

create table use (
  id                        bigint not null,
  activity                  varchar(255),
  wqt                       varchar(255),
  wunit                     varchar(255),
  reference                 varchar(255),
  image                     varchar(255),
  description               varchar(255),
  label                     varchar(255),
  unit                      varchar(255),
  value                     varchar(255),
  constraint pk_use primary key (id))
;

create table user (
  name                      varchar(255) not null,
  password                  varchar(255),
  email                     varchar(255),
  constraint pk_user primary key (name))
;

create sequence cost_seq;

create sequence footprint_seq;

create sequence metaphor_seq;

create sequence use_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cost;

drop table if exists footprint;

drop table if exists metaphor;

drop table if exists use;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cost_seq;

drop sequence if exists footprint_seq;

drop sequence if exists metaphor_seq;

drop sequence if exists use_seq;

drop sequence if exists user_seq;

