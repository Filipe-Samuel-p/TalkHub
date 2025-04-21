INSERT INTO tb_user(id,name, email, password, cpf, creation_Date,biography,img_Url,img_Background,num_following,num_followers)
VALUES ('957e87ba-27df-46a1-aa03-96622cc0ba50','Filipe Samuel', 'felipepires.p125@gmail.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS', '18864561781', CURRENT_TIMESTAMP,'sou Dev','jahashas','hahsshhsa',0,0);

INSERT INTO tb_speaker( user_id,resume,specialties,institution) VALUES (   '957e87ba-27df-46a1-aa03-96622cc0ba50','la ola','backend','UENF');

INSERT INTO tb_role(role_id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO tb_role(role_id,authority) VALUES (2,'ROLE_USER');

INSERT INTO tb_user_role(role_id, user_id) VALUES (1,'957e87ba-27df-46a1-aa03-96622cc0ba50');