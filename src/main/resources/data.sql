INSERT INTO tb_user(id,name, email, password, cpf, creation_Date,biography,img_Url,img_Background,num_following,num_followers)
VALUES ('957e87ba-27df-46a1-aa03-96622cc0ba50','Filipe Samuel',
        'felipepires.p125@gmail.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '18864561781', CURRENT_TIMESTAMP,'sou Dev','jahashas','hahsshhsa',0,0);

INSERT INTO tb_user(id,name, email, password, cpf, creation_Date,biography,img_Url,img_Background,num_following,num_followers)
VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19','Marina Alves',
        'marina.alves@gmail.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '29376154890', CURRENT_TIMESTAMP,'Designer UX/UI','img_marina','bg_marina',0,0);

INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 'Lucas Pereira',
        'lucas.pereira@hotmail.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '41587236901', CURRENT_TIMESTAMP, 'Desenvolvedor Full Stack', 'img_lucas', 'bg_lucas', 0, 0);


INSERT INTO tb_user(id,name, email, password, cpf, creation_Date,biography,img_Url,img_Background,num_following,num_followers)
VALUES ('c4e98d25-61a3-47bf-85f1-4d7b9c632a8e','Rafael Costa',
        'rafael.costa@outlook.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '37689512340', CURRENT_TIMESTAMP,'Engenheiro de Software | Backend','img_rafael','bg_rafael',0,0);


INSERT INTO tb_speaker( user_id,resume,specialties,institution) VALUES (   '957e87ba-27df-46a1-aa03-96622cc0ba50','la ola','backend','UENF');
INSERT INTO tb_speaker( user_id,resume,specialties,institution) VALUES (   'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19','Sou design','UX/UI','Globo');
INSERT INTO tb_speaker( user_id,resume,specialties,institution) VALUES (   'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2','Sou Full Stack','Java e Angular','Itaú');

INSERT INTO tb_role(role_id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO tb_role(role_id,authority) VALUES (2,'ROLE_USER');
INSERT INTO tb_role(role_id,authority) VALUES (3,'ROLE_SPEAKER');

INSERT INTO tb_user_role(role_id, user_id) VALUES (1,'957e87ba-27df-46a1-aa03-96622cc0ba50');
INSERT INTO tb_user_role(role_id, user_id) VALUES (2,'d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3,'d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3,'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_role(role_id, user_id) VALUES (2,'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_role(role_id, user_id) VALUES (2,'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');

INSERT INTO tb_user_following(user_id,following_id) VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19','c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');

INSERT INTO tb_speaker_request (id, user_id, status, request_date)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 'PENDING', CURRENT_TIMESTAMP),
    ('123e4567-e89b-12d3-a456-426614174001', 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 'PENDING', CURRENT_TIMESTAMP);


INSERT INTO tb_category(name) VALUES ('Inteligência Artificial'),
                                     ('Ciência de Dados'),
                                     ('Matemática'),
                                     ('Mercado de TI');

INSERT INTO tb_talk ( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level,category_id,speaker_id)
VALUES (
           'Introdução ao Spring Boot',
           'Uma palestra voltada para desenvolvedores iniciantes que desejam aprender como criar aplicações web com o framework Spring Boot.',
           '2025-05-10T09:00:00Z',
           120, -- 2 horas em minutos
           100, 100, 'Auditório A', 'BASIC',2,'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19'
       );

INSERT INTO tb_talk ( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level,category_id,speaker_id)
VALUES (
           'Java para Profissionais',
           'Uma palestra voltada para desenvolvedores iniciantes que desejam aprender como criar aplicações web com o framework Spring Boot.',
           '2025-05-10T09:00:00Z',
           120, -- 2 horas em minutos
           100, 100, 'Auditório A', 'BASIC',1,'d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2'
       );


INSERT INTO tb_event (name, description, start_Date, end_Date, place) VALUES
    ('Congresso de Tecnologia 2025', 'Evento voltado para inovações tecnológicas e tendências em TI.', '2025-06-15T09:00:00Z', '2025-06-15T18:00:00Z', 'Centro de Convenções, São Paulo');

INSERT INTO tb_event(name, description, start_Date, end_Date, place) VALUES
    ('Feira de Ciências Universitária', 'Apresentação de projetos científicos e experimentos de alunos.', '2025-09-10T10:00:00Z', '2025-09-10T16:00:00Z', 'Campus Principal, Auditório 2');
