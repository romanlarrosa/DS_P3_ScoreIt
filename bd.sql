create user 'si'@'localhost' identified by '1234';
create user 'si'@'%' identified by '1234';
CREATE DATABASE ScoreIt;


create table USUARIOS(
    id_usuario int AUTO_INCREMENT primary key,
    nombre varchar(200) not null,
    pass varchar(200) not null,
    tipo varchar(200),
    verificado BOOLEAN DEFAULT FALSE
);


CREATE TABLE LOCALES(
    id_local int AUTO_INCREMENT primary key,
    nombre_local varchar(200) not null,
    ubicacion varchar(200) not null,
    descripcion text not null,
    owner_local int,
    FOREIGN KEY (owner_local) references USUARIOS(id_usuario)
);

create table PUNTUACIONES(
    id_usuario int not null,
    id_local int not null,
    puntos int not null,
    FOREIGN KEY (id_usuario) references USUARIOS(id_usuario),
    FOREIGN KEY (id_local) references LOCALES(id_local),
    primary key (id_local, id_usuario)
);