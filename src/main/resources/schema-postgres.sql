alter table if exists producto drop constraint if exists fk_producto_categoria;
alter table if exists puntuacion drop constraint if exists fk_puntuacion_producto;
alter table if exists role drop constraint if exists uk_role_name;
alter table if exists user_role drop constraint if exists fk_user_role_role;
alter table if exists user_role drop constraint if exists fk_user_role_user;


drop table if exists categoria;
drop table if exists producto;
drop table if exists puntuacion;
drop table if exists role;
drop table if exists app_user;
drop table if exists user_role;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 100 increment by 1;

create table categoria (
	id bigint not null,
	destacada boolean not null,
	imagen varchar(512),
	nombre varchar(512),
	primary key (id)
);

create table producto (
	id bigint not null,
	descripcion text,
	descuento float not null,
	imagen varchar(512),
	nombre varchar(512),
	pvp float not null,
	categoria_id bigint,
	primary key (id)
);

create table puntuacion (
	id bigint not null,
	fecha timestamp,
	puntuacion integer not null,
	producto_id bigint,
	primary key (id)
);

create table role (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table app_user (
    id bigint not null,
    city varchar(255),
    created timestamp,
    email varchar(255),
    enabled boolean,
    name varchar(255),
    password varchar(255),
    updated timestamp,
    primary key (id)
);

create table user_role (
    users_id bigint not null,
    roles_id bigint not null,
    primary key (users_id, roles_id)
);

alter table producto add constraint fk_producto_categoria foreign key (categoria_id) references categoria;
alter table puntuacion add constraint fk_puntuacion_producto foreign key (producto_id) references producto;
alter table role add constraint uk_role_name unique (name);
alter table user_role add constraint fk_user_role_role foreign key (roles_id) references role (id);
alter table user_role add constraint fk_user_role_user foreign key (users_id) references app_user (id);