
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');



INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username ) VALUES ('zambrano','castillo de oropesa', 'steven.zslp@gmail.com', 1, 'Steven','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq', 'admin');

INSERT INTO `usuarios` (apellido, direccion, email, enable, nombre, password, username) VALUES ('Gonzales','castillo de la mota', 'marvin@gmail.com', 1, 'Marvin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'Marvin80');


INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (1, 1);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 2);

INSERT INTO `usuarios_roles` (usuario_id, roles_id) VALUES (2, 1);


INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Arroz', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (7, 100, 'Almendras', 100, 25, 350);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Lechuga', 200, 10, 150);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Patata', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Pollo', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Ternera', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Cerdo', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Pescado', 200, 10, 250);
INSERT INTO `alimentos`(carbohidratos, grasas, nombre, peso, proteinas, valor_energetico) VALUES (80, 7, 'Pimiento', 200, 10, 250);



INSERT INTO `usuarios_alimentos`(usuario_id, alimentos_id) VALUES (2,1);
INSERT INTO `usuarios_alimentos`(usuario_id, alimentos_id) VALUES (2,3);
INSERT INTO `usuarios_alimentos`(usuario_id, alimentos_id) VALUES (2,5);
INSERT INTO `usuarios_alimentos`(usuario_id, alimentos_id) VALUES (2,2);
INSERT INTO `usuarios_alimentos`(usuario_id, alimentos_id) VALUES (2,6);
INSERT INTO `usuarios_alimentos`(usuario_id, alimentos_id) VALUES (2,8);



