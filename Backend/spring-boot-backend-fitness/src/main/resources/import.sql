
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');



INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username ) VALUES ('zambrano','castillo de oropesa', 'steven.zslp@gmail.com', 1, 'Steven','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq', 'admin');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Gonzales','castillo de la mota', 'marvin@gmail.com', 1, 'Marvin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'Marvin80');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Bello','Amposta', 'diego@gmail.com', 1, 'Diego','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'diego69');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Gonzales','Miguel Yuste', 'Volo@gmail.com', 1, 'Volo','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'volo22');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Gonzales','Pedraza del rio', 'Ivan@gmail.com', 1, 'Ivan','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'ivan10');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Gonzales','Ezquiel solano', 'Laura@gmail.com', 1, 'Laura','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'lauraBd');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Gonzales','Vicálvaro', 'vaal@gmail.com', 1, 'Valeria','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'vaal');



INSERT INTO `objetivos` (altura, edad, nivel, peso, sexo, usuario) VALUES (174, 22, 1.55, 70, 'Hombre', 2);

INSERT INTO `objetivos` (altura, edad, nivel, peso, sexo, usuario) VALUES (171, 22, 1.2, 65, 'Hombre', 3);



INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (1, 1);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 2);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 1);


INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (3, 2);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (4, 2);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (5, 2);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (6, 2);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (7, 2);


INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Arroz', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (7, 100, 'Almendras', 100, 25, 350);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Lechuga', 200, 10, 150);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Patata', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Pollo', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Ternera', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Cerdo', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Pescado', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Pimiento', 200, 10, 250);



INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (2,1);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (2,3);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (2,5);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (2,2);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (2,6);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (2,8);


INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (3,9);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (3,8);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (3,7);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (3,4);
INSERT INTO `usuarios_alimentos`(usuarios_id, alimentos_id) VALUES (3,5);




