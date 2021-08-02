INSERT INTO TUTOR (EMAIL,NOME, TELEFONE) VALUES ('rafael.gomes@gmail.com', 'Rafael Gomes','11987765612');
INSERT INTO TUTOR (EMAIL,NOME, TELEFONE) VALUES ('samuel.sousa@outlook.com', 'Samuel Sousa','11934847399');
INSERT INTO TUTOR (EMAIL,NOME, TELEFONE) VALUES ('oliveira.renata@gmail.com', 'Renata Oliveira','119484738342');

INSERT INTO ANIMAL (DATA_NASCIMENTO,ESPECIE, NOME, RACA, TUTOR) VALUES (TO_DATE('15/02/2018','DD/MM/YYYY'), 'CACHORRO', 'BOB', 'GOLDEN RETRIEVER', 1);
INSERT INTO ANIMAL (DATA_NASCIMENTO,ESPECIE, NOME, RACA, TUTOR) VALUES (TO_DATE('08/04/2019','DD/MM/YYYY'), 'GATO', 'TOM', 'PERSA', 2);
INSERT INTO ANIMAL (DATA_NASCIMENTO,ESPECIE, NOME, RACA, TUTOR) VALUES (TO_DATE('22/09/2008','DD/MM/YYYY'), 'PASSARO', 'AMORA', 'CALOPSITA', 3);

INSERT INTO VETERINARIO (EMAIL,NOME, TELEFONE) VALUES ('fernanda.oliveira@petcare.com', 'Fernanda Oliveira','11998766143');
INSERT INTO VETERINARIO (EMAIL,NOME, TELEFONE) VALUES ('ricardo.bittencourt@petcare.com', 'Ricardo Bittencourt','11934847399');
INSERT INTO VETERINARIO (EMAIL,NOME, TELEFONE) VALUES ('juliana.mendonca@petcare.com', 'Juliana Mendonça','11987665343');

INSERT INTO CONSULTA (DATA_CONSULTA, STATUS, ANIMAL, VETERINARIO ) VALUES ( TO_DATE('15/08/2021','DD/MM/YYYY'), 'AGENDADA', 1, 1 );
INSERT INTO CONSULTA (DATA_CONSULTA, STATUS, ANIMAL, VETERINARIO ) VALUES ( TO_DATE('22/08/2021','DD/MM/YYYY'), 'AGENDADA', 2, 2 );
INSERT INTO CONSULTA (DATA_CONSULTA, STATUS, ANIMAL, VETERINARIO ) VALUES ( TO_DATE('31/07/2021','DD/MM/YYYY'), 'CANCELADA', 3, 3 );