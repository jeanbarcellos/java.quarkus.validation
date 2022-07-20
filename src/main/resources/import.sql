

INSERT INTO "category" (id, name, description) VALUES ('824ca30a-a80b-4829-8af5-333b35c177b1', 'Categoria 001', 'Descrição 001');
INSERT INTO "category" (id, name, description) VALUES ('d61ef95b-ff40-4c25-93d5-5caaae732494', 'Categoria 002', 'Descrição 002');
INSERT INTO "category" (id, name, description) VALUES ('03324f77-322d-452a-8e84-5ad48c68a85f', 'Categoria 003', 'Descrição 003');


INSERT INTO "type" (id, parent, name) VALUES (1, 1, 'Público');
INSERT INTO "type" (id, parent, name) VALUES (2, 1, 'Privado');
INSERT INTO "type" (id, parent, name) VALUES (3, 1, 'Não listado');
INSERT INTO "type" (id, parent, name) VALUES (4, 2, 'Física');
INSERT INTO "type" (id, parent, name) VALUES (5, 2, 'Jurídica');