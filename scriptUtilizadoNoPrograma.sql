create database sistemaVendas;

CREATE table sistemaVendas.vendas (
id bigint(20) not null auto_increment,
primary key (id),
nomeCliente varchar(200) not null,
nomeProduto varchar(200) not null,
precoProduto Double(10,2) not null,
quantidadeVendida bigint(20) not null,
totalVenda Double(10,2) not null
) engine=InnoDB;