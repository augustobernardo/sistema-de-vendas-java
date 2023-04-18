CREATE database sistemadevendas;

CREATE table sistemadevendas.clientes (
id bigint(20) not null auto_increment,
primary key (id),
nome varchar(200) not null,
cpf VARCHAR(14),
telefone VARCHAR(11)
) engine=InnoDB;

CREATE table sistemadevendas.produto (
id bigint(20) not null auto_increment,
primary key (id),
nomeDoProduto varchar(200) not null,
valorUnit varchar(200) not null,
estoque bigint(20) not null,
sku VARCHAR(250)
) engine=InnoDB;


create table sistemadevendas.vendas (
id bigint(20) not null auto_increment,
primary key (id),
idCliente bigint(20) not null,
idProduto bigint(20) not null,
nomeCliente varchar(20) not null,
telefoneCliente varchar(20) not null,
cpfCliente varchar(11) not null,
nomeProduto varchar(200) not null,
quant varchar(200) not null,
valorTot varchar(200) not null,
dataVenda date not null
) engine=InnoDB;

alter table sistemadevendas.vendas add foreign key(idCliente) references sistemadevendas.clientes(id);

alter table sistemadevendas.vendas add foreign key(idProduto) references sistemadevendas.produto(id);