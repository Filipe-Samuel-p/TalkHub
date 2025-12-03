
INSERT INTO tb_role(role_id, authority) VALUES
                                            (1, 'ROLE_ADMIN'),
                                            (2, 'ROLE_USER'),
                                            (3, 'ROLE_SPEAKER');

-- ============================================
-- 2. CATEGORIAS
-- ============================================
INSERT INTO tb_category(name) VALUES
                                  ('Inteligência Artificial'),
                                  ('Desenvolvimento Web'),
                                  ('DevOps e Cloud'),
                                  ('Ciência de Dados'),
                                  ('Mobile'),
                                  ('Segurança da Informação'),
                                  ('Arquitetura de Software'),
                                  ('UX/UI Design');

-- ============================================
-- 3. USUÁRIOS
-- ============================================

-- ADMIN
INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('957e87ba-27df-46a1-aa03-96622cc0ba50', 'Filipe Samuel',
        'admin@talkhub.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '18864561781', CURRENT_TIMESTAMP, 'Administrador do TalkHub | Desenvolvedor Full Stack', 'img_admin', 'bg_admin', 2, 5);

-- PALESTRANTES
INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 'Marina Alves',
        'marina.alves@talkhub.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '29376154890', CURRENT_TIMESTAMP, 'Especialista em UX/UI Design | Google Design Sprint Facilitator | 8 anos de experiência', 'img_marina', 'bg_marina', 3, 12);

INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 'Lucas Pereira',
        'lucas.pereira@talkhub.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '41587236901', CURRENT_TIMESTAMP, 'Desenvolvedor Full Stack Sênior | Java & Angular Expert | Tech Lead no Itaú', 'img_lucas', 'bg_lucas', 2, 18);

INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 'Rafael Costa',
        'rafael.costa@talkhub.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '37689512340', CURRENT_TIMESTAMP, 'Engenheiro de Software Sênior | Especialista em Backend e Microsserviços | AWS Certified', 'img_rafael', 'bg_rafael', 1, 15);

INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Juliana Santos',
        'juliana.santos@talkhub.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '52698741032', CURRENT_TIMESTAMP, 'Cientista de Dados | Machine Learning Engineer | Doutora em IA pela USP', 'img_juliana', 'bg_juliana', 4, 22);

INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('f9e8d7c6-b5a4-3210-fedc-ba0987654321', 'Carlos Mendes',
        'carlos.mendes@talkhub.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '63741852096', CURRENT_TIMESTAMP, 'DevOps Engineer | Kubernetes & Docker Expert | Cloud Architect na AWS', 'img_carlos', 'bg_carlos', 3, 14);

-- USUÁRIOS COMUNS
INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 'Ana Carolina Silva',
        'ana.silva@email.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '78945612303', CURRENT_TIMESTAMP, 'Desenvolvedora Júnior | Apaixonada por tecnologia | Estudante de Ciência da Computação', 'img_ana', 'bg_ana', 5, 3);

INSERT INTO tb_user(id, name, email, password, cpf, creation_Date, biography, img_Url, img_Background, num_following, num_followers)
VALUES ('1d64d6e2-71cf-45e4-8b69-344350afa2fc', 'Pedro Henrique Oliveira',
        'pedro.oliveira@email.com', '$2a$10$YjNEj18ApFMUmdjAIzw0KuxRiSxpVKxvhTrY8P7vTC8pd63P8qiGS',
        '85236974101', CURRENT_TIMESTAMP, 'Entusiasta de tecnologia | Iniciante em programação | Buscando primeira oportunidade', 'img_pedro', 'bg_pedro', 6, 2);

-- ============================================
-- 4. USER ROLES
-- ============================================

-- Admin
INSERT INTO tb_user_role(role_id, user_id) VALUES (1, '957e87ba-27df-46a1-aa03-96622cc0ba50');
INSERT INTO tb_user_role(role_id, user_id) VALUES (2, '957e87ba-27df-46a1-aa03-96622cc0ba50');

-- Palestrantes (USER + SPEAKER)
INSERT INTO tb_user_role(role_id, user_id) VALUES (2, 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3, 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');

INSERT INTO tb_user_role(role_id, user_id) VALUES (2, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');

INSERT INTO tb_user_role(role_id, user_id) VALUES (2, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');

INSERT INTO tb_user_role(role_id, user_id) VALUES (2, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');

INSERT INTO tb_user_role(role_id, user_id) VALUES (2, 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');
INSERT INTO tb_user_role(role_id, user_id) VALUES (3, 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');

-- Usuários comuns (só USER)
INSERT INTO tb_user_role(role_id, user_id) VALUES (2, 'cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0');
INSERT INTO tb_user_role(role_id, user_id) VALUES (2, '1d64d6e2-71cf-45e4-8b69-344350afa2fc');

-- ============================================
-- 5. SPEAKERS (Dados adicionais dos palestrantes)
-- ============================================

INSERT INTO tb_speaker(user_id, resume, specialties, institution)
VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19',
        'Especialista em Design de Experiência do Usuário com mais de 8 anos de experiência em empresas como Globo e Nubank. Formada em Design Digital pela ESPM.',
        'UX/UI Design, Design Systems, Figma, User Research, Prototipação',
        'Globo');

INSERT INTO tb_speaker(user_id, resume, specialties, institution)
VALUES ('d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2',
        'Desenvolvedor Full Stack com 10 anos de experiência, especializado em arquiteturas escaláveis. Tech Lead em projetos críticos no setor bancário.',
        'Java, Spring Boot, Angular, Microserviços, Kafka, PostgreSQL',
        'Itaú Unibanco');

INSERT INTO tb_speaker(user_id, resume, specialties, institution)
VALUES ('c4e98d25-61a3-47bf-85f1-4d7b9c632a8e',
        'Engenheiro de Software com foco em backend e arquitetura de microsserviços. Certificado AWS Solutions Architect. Contribuidor open-source.',
        'Java, Spring Framework, AWS, Docker, Kubernetes, APIs REST',
        'Magazine Luiza');

INSERT INTO tb_speaker(user_id, resume, specialties, institution)
VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890',
        'Doutora em Inteligência Artificial pela USP, especialista em Machine Learning e Deep Learning. Pesquisadora e palestrante internacional.',
        'Python, TensorFlow, PyTorch, Machine Learning, Deep Learning, NLP',
        'Universidade de São Paulo');

INSERT INTO tb_speaker(user_id, resume, specialties, institution)
VALUES ('f9e8d7c6-b5a4-3210-fedc-ba0987654321',
        'DevOps Engineer com experiência em automação de infraestrutura e CI/CD. Especialista em cloud computing e containers.',
        'Kubernetes, Docker, Terraform, Jenkins, AWS, Azure, GitLab CI',
        'Amazon Web Services');

-- ============================================
-- 6. EVENTOS
-- ============================================

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ( 'Tech Summit 2025',
        'O maior evento de tecnologia do Brasil, reunindo especialistas, desenvolvedores e entusiastas para discutir as últimas tendências em IA, Cloud, DevOps e desenvolvimento de software.',
        '2025-06-15T08:00:00Z', '2025-06-17T18:00:00Z',
        'Centro de Convenções Frei Caneca, São Paulo - SP');

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ( 'DevOps Experience',
        'Evento focado em DevOps, Cloud Computing e Infraestrutura como Código. Workshops práticos e palestras com cases reais de empresas líderes do mercado.',
        '2025-07-20T09:00:00Z', '2025-07-21T17:00:00Z',
        'Hotel Renaissance, Rio de Janeiro - RJ');

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ('AI & Data Science Conference',
        'Conferência dedicada à Inteligência Artificial e Ciência de Dados. Apresentações de cases práticos, pesquisas acadêmicas e networking com profissionais da área.',
        '2025-08-10T09:00:00Z', '2025-08-12T18:00:00Z',
        'Campus USP, São Paulo - SP');

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ('Mobile Dev Week',
        'Semana intensiva de desenvolvimento mobile com foco em Flutter, React Native e Kotlin. Ideal para quem quer dominar o desenvolvimento de aplicativos.',
        '2025-09-05T09:00:00Z', '2025-09-09T17:00:00Z',
        'Campus Google, Belo Horizonte - MG');

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ('Design & Frontend Meetup',
        'Encontro para designers e desenvolvedores frontend. Discussões sobre UX/UI, acessibilidade, performance e as melhores práticas do mercado.',
        '2025-10-15T14:00:00Z', '2025-10-15T20:00:00Z',
        'WeWork Paulista, São Paulo - SP');

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ('Security Summit',
        'Evento focado em Segurança da Informação, Ethical Hacking e práticas de segurança em desenvolvimento de software.',
        '2025-11-20T09:00:00Z', '2025-11-20T18:00:00Z',
        'Centro de Eventos Online - YouTube Live');

INSERT INTO tb_event(name, description, start_Date, end_Date, place)
VALUES ( 'Tech Talks December',
        'Série de palestras online gratuitas sobre diversos temas de tecnologia. Ideal para quem quer aprender no final do ano.',
        '2025-12-05T18:00:00Z', '2025-12-10T21:00:00Z',
        'Online - Zoom & YouTube');

-- ============================================
-- 7. PALESTRAS (TODAS COM EVENTOS)
-- ============================================

-- Palestras do Tech Summit 2025 (evento e1111111)
INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('Introdução ao Spring Boot 3',
        'Aprenda os fundamentos do Spring Boot 3, o framework Java mais popular para desenvolvimento de aplicações web e microsserviços. Ideal para desenvolvedores que estão começando.',
        '2025-06-15T09:00:00Z', 90, 150, 145,
        'Auditório Principal', 'BASIC', 2, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 1);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Arquitetura de Microsserviços na Prática',
        'Como implementar uma arquitetura de microsserviços escalável e resiliente. Cases reais do setor bancário, incluindo comunicação assíncrona com Kafka e service mesh.',
        '2025-06-15T14:00:00Z', 120, 100, 92,
        'Sala 201', 'ADVANCED', 7, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 1);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Machine Learning para Iniciantes',
        'Introdução prática ao Machine Learning com Python. Aprenda os conceitos fundamentais e implemente seu primeiro modelo de classificação usando scikit-learn.',
        '2025-06-16T10:00:00Z', 120, 120, 115,
        'Lab de Computação 1', 'BASIC', 1, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 1);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('Design Systems: Da Teoria à Prática',
        'Como criar e manter um Design System escalável. Ferramentas, processos e boas práticas utilizadas na Globo para garantir consistência em produtos digitais.',
        '2025-06-16T15:00:00Z', 90, 80, 72,
        'Sala 105', 'INTERMEDIATE', 8, 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 1);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Java para Profissionais: Boas Práticas e Clean Code',
        'Aprofunde seus conhecimentos em Java aplicando princípios SOLID, design patterns e técnicas de Clean Code. Exemplos práticos e code reviews ao vivo.',
        '2025-06-17T09:00:00Z', 150, 130, 120,
        'Auditório Principal', 'INTERMEDIATE', 2, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 1);

-- Palestras do DevOps Experience (evento e2222222)
INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('Kubernetes do Zero ao Deploy',
        'Workshop hands-on de Kubernetes. Da instalação local com Minikube até o deploy em produção na AWS. Inclui configuração de clusters, deployments, services e ingress.',
        '2025-07-20T09:00:00Z', 240, 60, 52,
        'Lab de DevOps', 'INTERMEDIATE', 3, 'f9e8d7c6-b5a4-3210-fedc-ba0987654321', 2);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'CI/CD Avançado com GitLab',
        'Construa pipelines de CI/CD profissionais com GitLab. Automação de testes, builds, deploys e estratégias de release como blue-green e canary deployments.',
        '2025-07-20T14:00:00Z', 120, 80, 75,
        'Sala DevOps', 'ADVANCED', 3, 'f9e8d7c6-b5a4-3210-fedc-ba0987654321', 2);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Monitoramento e Observabilidade com Prometheus',
        'Implemente uma stack completa de observabilidade com Prometheus, Grafana e Loki. Métricas, logs e traces em aplicações distribuídas.',
        '2025-07-21T10:00:00Z', 120, 70, 63,
        'Sala Cloud', 'INTERMEDIATE', 3, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 2);

-- Palestras do AI & Data Science Conference (evento e3333333)
INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('Deep Learning com PyTorch',
        'Construa redes neurais profundas com PyTorch. CNN, RNN, Transfer Learning e aplicações práticas em visão computacional e processamento de linguagem natural.',
        '2025-08-10T09:00:00Z', 180, 100, 88,
        'Auditório de IA', 'ADVANCED', 1, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 3);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Análise de Dados com Python e Pandas',
        'Domine a análise exploratória de dados com Pandas, NumPy e Matplotlib. Limpeza, transformação e visualização de dados para insights de negócio.',
        '2025-08-10T14:00:00Z', 120, 90, 82,
        'Lab Python', 'BASIC', 4, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 3);

INSERT INTO tb_talk( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'MLOps: Machine Learning em Produção',
        'Como levar modelos de ML para produção de forma confiável. Versionamento de modelos, monitoramento de drift, A/B testing e retraining automático.',
        '2025-08-11T10:00:00Z', 150, 80, 71,
        'Sala MLOps', 'ADVANCED', 4, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 3);

-- Palestras do Mobile Dev Week (evento e4444444)
INSERT INTO tb_talk( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('React Native: Desenvolvimento Mobile Multiplataforma',
        'Crie aplicativos nativos para iOS e Android com uma única base de código. Navegação, estado global, APIs e publicação nas stores.',
        '2025-09-05T14:00:00Z', 180, 70, 65,
        'Sala Mobile 1', 'INTERMEDIATE', 5, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 4);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Flutter: Do Básico ao Avançado',
        'Aprenda Flutter do zero e construa apps incríveis. Widgets, navegação, animações, APIs REST e deploy na Play Store e App Store.',
        '2025-09-06T09:00:00Z', 240, 80, 74,
        'Lab Mobile', 'BASIC', 5, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 4);

-- Palestras do Design & Frontend Meetup (evento e5555555)
INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('UX Research: Entendendo seu Usuário',
        'Métodos práticos de pesquisa com usuários: entrevistas, testes de usabilidade, card sorting e análise de dados. Como transformar insights em melhorias de produto.',
        '2025-10-15T14:00:00Z', 120, 50, 42,
        'Sala Principal', 'BASIC', 8, 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 5);

INSERT INTO tb_talk( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Acessibilidade Web: Construindo para Todos',
        'Aprenda a criar interfaces acessíveis que funcionam para todas as pessoas. WCAG, ARIA, ferramentas de teste e boas práticas de desenvolvimento.',
        '2025-10-15T16:30:00Z', 90, 50, 45,
        'Sala Principal', 'INTERMEDIATE', 8, 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 5);

-- Palestras do Security Summit (evento e6666666)
INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ('Segurança em APIs REST',
        'Aprenda a proteger suas APIs REST com OAuth 2.0, JWT, rate limiting e outras técnicas essenciais de segurança. Exemplos práticos com Spring Security.',
        '2025-11-20T09:00:00Z', 120, 100, 90,
        'Online - YouTube Live', 'INTERMEDIATE', 6, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 6);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'OWASP Top 10: Vulnerabilidades Críticas',
        'Conheça as 10 vulnerabilidades de segurança mais críticas em aplicações web segundo a OWASP. Como identificar, explorar e corrigir cada uma delas.',
        '2025-11-20T14:00:00Z', 150, 100, 85,
        'Online - YouTube Live', 'ADVANCED', 6, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 6);

-- Palestras do Tech Talks December (evento e7777777)
INSERT INTO tb_talk( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Introdução ao Git e GitHub',
        'Aprenda versionamento de código com Git e GitHub. Comandos essenciais, branches, merge, pull requests e workflows colaborativos.',
        '2025-12-05T18:00:00Z', 90, 200, 185,
        'Online - Zoom', 'BASIC', 2, 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 7);

INSERT INTO tb_talk(title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Carreira em Tech: Do Júnior ao Sênior',
        'Dicas práticas para crescer na carreira de tecnologia. Habilidades técnicas, soft skills, networking e como se destacar no mercado.',
        '2025-12-07T19:00:00Z', 60, 300, 275,
        'Online - YouTube Live', 'BASIC', 2, 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 7);

INSERT INTO tb_talk( title, description, start_time, duration, total_capacity, number_available, local, difficulty_level, category_id, speaker_id, event_id)
VALUES ( 'Python para Análise de Dados',
        'Introdução ao Python focado em análise de dados. Bibliotecas essenciais: Pandas, NumPy, Matplotlib e cases práticos de mercado.',
        '2025-12-10T20:00:00Z', 120, 150, 138,
        'Online - Zoom', 'BASIC', 4, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 7);

-- ============================================
-- 8. INSCRIÇÕES (REGISTRATIONS)
-- ============================================

-- Ana Carolina se inscreveu em várias palestras para iniciantes
INSERT INTO tb_registration(id, user_id, talk_id, registration_date, payment_status)
VALUES (1, 'cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 1, CURRENT_TIMESTAMP, 'CONFIRMED');

INSERT INTO tb_registration(id, user_id, talk_id, registration_date, payment_status)
VALUES (2, 'cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 3, CURRENT_TIMESTAMP, 'CONFIRMED');


-- Pedro Henrique também se inscreveu em palestras
INSERT INTO tb_registration(id, user_id, talk_id, registration_date, payment_status)
VALUES (6, '1d64d6e2-71cf-45e4-8b69-344350afa2fc', 1, CURRENT_TIMESTAMP, 'CONFIRMED');

INSERT INTO tb_registration(id, user_id, talk_id, registration_date, payment_status)
VALUES (7, '1d64d6e2-71cf-45e4-8b69-344350afa2fc', 3, CURRENT_TIMESTAMP, 'CONFIRMED');

INSERT INTO tb_registration(id, user_id, talk_id, registration_date, payment_status)
VALUES (8, '1d64d6e2-71cf-45e4-8b69-344350afa2fc', 5, CURRENT_TIMESTAMP, 'PENDING');


-- ============================================
-- 9. SEGUINDO USUÁRIOS
-- ============================================

-- Ana segue palestrantes
INSERT INTO tb_user_following(user_id, following_id) VALUES ('cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('cab5f392-6f2e-4ebe-a33b-c5b5c04b1be0', 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');

-- Pedro também segue palestrantes
INSERT INTO tb_user_following(user_id, following_id) VALUES ('1d64d6e2-71cf-45e4-8b69-344350afa2fc', 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('1d64d6e2-71cf-45e4-8b69-344350afa2fc', 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('1d64d6e2-71cf-45e4-8b69-344350afa2fc', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('1d64d6e2-71cf-45e4-8b69-344350afa2fc', 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('1d64d6e2-71cf-45e4-8b69-344350afa2fc', 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');


-- Admin segue alguns
INSERT INTO tb_user_following(user_id, following_id) VALUES ('957e87ba-27df-46a1-aa03-96622cc0ba50', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('957e87ba-27df-46a1-aa03-96622cc0ba50', 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');

-- Palestrantes seguem uns aos outros
INSERT INTO tb_user_following(user_id, following_id) VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('b8c12a93-45f7-42d8-9e3b-67a2c5d84e19', 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');

INSERT INTO tb_user_following(user_id, following_id) VALUES ('d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('d4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');

INSERT INTO tb_user_following(user_id, following_id) VALUES ('c4e98d25-61a3-47bf-85f1-4d7b9c632a8e', 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');

INSERT INTO tb_user_following(user_id, following_id) VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'f9e8d7c6-b5a4-3210-fedc-ba0987654321');

INSERT INTO tb_user_following(user_id, following_id) VALUES ('f9e8d7c6-b5a4-3210-fedc-ba0987654321', 'b8c12a93-45f7-42d8-9e3b-67a2c5d84e19');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('f9e8d7c6-b5a4-3210-fedc-ba0987654321', 'd4e91f12-7c3f-4d88-bc2a-73a6a9cbf0c2');
INSERT INTO tb_user_following(user_id, following_id) VALUES ('f9e8d7c6-b5a4-3210-fedc-ba0987654321', 'c4e98d25-61a3-47bf-85f1-4d7b9c632a8e');

