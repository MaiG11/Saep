CREATE DATABASE IF NOT EXISTS padoca;

CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `estoque_minimo` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `movimentacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `funcionario_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_movimentacao_produto FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  CONSTRAINT fk_movimentacao_funcionario FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`)
) 

INSERT INTO funcionario (nome, login, senha) VALUES
("Neymar Junior", "neymar","1234"),
("Endrick", "endrick", "4567"),
("Luiz Henrique", "luiz", "1234"),
("Weverton", "weverton","4567");

INSERT INTO produto (nome, quantidade, estoque_minimo, descricao, categoria) VALUES
("Pão Doce", 10, 5,"Pão é muito doce", "Pães");

INSERT INTO movimentacao (tipo, quantidade, data, funcionario_id, produto_id) VALUES
("Entrada",10, "2026-05-18", 3,1);