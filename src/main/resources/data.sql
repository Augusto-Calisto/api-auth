-- ADM's
INSERT INTO USUARIOS(nome, email, senha) VALUES('Augusto Calisto', 'augusto_ti@email.com', '$2a$10$Rd.aUC5UEyZaUEWZYhmQDONJNKDm9ypYNhvcs4glE2h6xybIOLwB6'); -- palmeiras
INSERT INTO USUARIOS(nome, email, senha) VALUES('Oscar Oliveira', 'oscar_ti@email.com', '$2a$10$sD7sbSUTBusNp.31DbGNSOH4NeN/ezJRF9778UqF0PGCXWBovKMY2'); -- labfer
INSERT INTO USUARIOS(nome, email, senha) VALUES('Luiz Diniz', 'luiz_ti@email.com', '$2a$10$cPc6rav5gN/0GHauwB1GA.VfG/HjUJBLYb3ZYadzYhBeGq/Smz8gi'); -- megadeth

-- Alunos
INSERT INTO USUARIOS(nome, email, senha) VALUES('Matheus', 'matheus@email.com', '$2a$10$gTMD8xZnztvEDFWDVzDKv.c0rVT0578r2W9Akmuw4rf2pKTCbjj2y'); -- guitarra
INSERT INTO USUARIOS(nome, email, senha) VALUES('João', 'joao@email.com', '$2a$10$v5R9kSYmeog.CvKlPYU39uDo.AxCc/jq5vqhg/B744DS2HRP15kA2'); -- java
INSERT INTO USUARIOS(nome, email, senha) VALUES('Maria', 'maria@email.com', '$2a$10$Np/tMBkmOR464bZNMrRBZeOw/tL5gWk42cFBK9b43beN.sAe/KSeW'); -- 123456

-- Regras
INSERT INTO REGRAS(id, nome) VALUES (1, 'ALUNO');
INSERT INTO REGRAS(id, nome) VALUES (2, 'ADM');

-- Regras de cada usuário
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (1, 2);
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (2, 2);
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (3, 2);
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (4, 1);
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (5, 1);
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (6, 1);