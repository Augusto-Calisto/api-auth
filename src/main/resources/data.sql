INSERT INTO USUARIOS(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$7br13fIA/jSiLAcfEMjgTuJpZEhKbz5H4XChdeJBEqO/sJ384L5xq'); -- 123456
INSERT INTO USUARIOS(nome, email, senha) VALUES('Adm', 'adm@email.com', '$2a$10$7br13fIA/jSiLAcfEMjgTuJpZEhKbz5H4XChdeJBEqO/sJ384L5xq'); -- 123456

INSERT INTO REGRAS(id, nome) VALUES (1, 'ALUNO');
INSERT INTO REGRAS(id, nome) VALUES (2, 'ADM');

INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (1, 1);
INSERT INTO USUARIOS_REGRAS(usuario_id, regra_id) VALUES (2, 2);