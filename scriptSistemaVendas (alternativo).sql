CREATE database sistemadevendas;

CREATE table sistemadevendas.clientes (
id bigint(20) not null auto_increment,
primary key (id),
nome varchar(200) not null,
cpf varchar(14),
telefone varchar(14)
) engine=InnoDB;

CREATE table sistemadevendas.produto (
id bigint(20) not null auto_increment,
primary key (id),
nomeDoProduto varchar(200) not null,
valorUnidade DECIMAL(10,2) not null,
estoque bigint(20) not null
) engine=InnoDB;


create table sistemadevendas.vendas (
id bigint(20) not null auto_increment,
primary key (id),
idCliente bigint(20) not null,
foreign key (idCliente) references sistemadevendas.clientes (id) on delete cascade,
idProduto bigint(20) not null,
foreign key (idProduto) references sistemadevendas.produto (id) on delete cascade,
nomeCliente varchar(20) not null,
telefoneCliente varchar(20) not null,
cpfCliente varchar(11) not null,
nomeProduto varchar(200) not null,
quant varchar(200) not null,
valorTot varchar(200) not null,
dataVenda date not null
) engine=InnoDB;
