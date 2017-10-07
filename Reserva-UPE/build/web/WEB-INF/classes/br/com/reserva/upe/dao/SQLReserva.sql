/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Hilton
 * Created: 15/09/2017
 */


create table pessoa(
id    int auto_increment, 
nome  varchar(70), 
email varchar(60),
senha varchar(30), 
tipo  varchar(9), 
primary key(id),
unique(email));


create table reserva(
id            int auto_increment, 
idPessoa      int, 
dataDaReserva varchar(30), 
turno         varchar(30), 
horario       varchar(30),
laboratorio   varchar(30),
descricao     varchar(30), 
primary key (id), 
foreign key (idPessoa)
references pessoa(id));