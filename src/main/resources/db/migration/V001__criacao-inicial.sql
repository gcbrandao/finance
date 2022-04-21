CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Fornecedores');
INSERT INTO categoria (nome) values ('Impostos');
INSERT INTO categoria (nome) values ('Taxas');
INSERT INTO categoria (nome) values ('Despesas Fixas');
INSERT INTO categoria (nome) values ('Outros');