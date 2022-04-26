CREATE TABLE lancamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Salário mensal', '2021-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('teste', '2021-02-10', '2021-02-10', 100.32, null, 'DESPESA', 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Top pro', '2021-06-10', null, 120, null, 'RECEITA', 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('SABESP', '2021-02-10', '2021-02-10', 110.44, 'Geração', 'RECEITA', 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Eleticidade', '2021-06-10', null, 200.30, null, 'DESPESA', 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Extra', '2021-03-10', '2021-03-10', 1010.32, null, 'RECEITA', 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Vendas', '2021-06-10', null, 500, null, 'RECEITA', 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Top procx', '2021-03-10', '2021-03-10', 400.32, null, 'DESPESA', 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Despachante', '2021-06-10', null, 123.64, 'Multas', 'DESPESA', 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Pneus', '2021-04-10', '2021-04-10', 665.33, null, 'RECEITA', 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Café', '2021-06-10', null, 8.32, null, 'DESPESA', 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Eletrônicos', '2021-04-10', '2021-04-10', 2100.32, null, 'DESPESA', 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Instrumentos', '2021-06-10', null, 1040.32, null, 'DESPESA', 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Café', '2021-04-10', '2021-04-10', 4.32, null, 'DESPESA', 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria) values ('Lanche', '2021-06-10', null, 10.20, null, 'DESPESA', 4);
