# TalkHub

🎙️ TalkHub é uma plataforma completa para gerenciamento e participação em palestras e eventos. Ideal para universidades, conferências, meetups e eventos online. Crie, organize, participe e interaja com outros participantes.
A idéia do projeto pessoal é desenvolver habilidades com java, principalmente com Spring Security.

## 🧩 Funcionalidades Principais

### 👥 Usuários

- Cadastro de usuários com dados pessoais (nome, email, CPF, senha, data de criação da conta).
- Upload de imagem de perfil (`imgUrl`) e imagem de capa (`imgCapa`).
- Sistema de seguidores:
    - Um usuário pode seguir outros usuários.
    - Cada usuário pode ter uma lista de seguidores e uma lista de pessoas que está seguindo.
- Atribuição de **papéis (roles)** ao usuário:
    - `ROLE_USER`: Usuário padrão.
    - `ROLE_PALESTRANTE`: Usuário que pode ministrar palestras.
    - `ROLE_ADMIN`: Usuário administrador com permissões extras.

---

### 🧑‍🏫 Palestrante

- Um palestrante está vinculado a um usuário.
- Informações do palestrante:
    - Mini biografia.
    - Área de especialidade.
    - Instituição.
    - Link do LinkedIn.
- Apenas usuários com `ROLE_PALESTRANTE` podem ser vinculados como palestrantes.
- Um palestrante poderá ser criado pelo ADM ou ser um usuário normal e solicitar ao ADM um "upgrade" para ser um palestrante

---

### 🗓️ Eventos

- Cadastro de eventos com:
    - Nome.
    - Descrição.
    - Data e hora de início e fim.
    - Status do evento: `planejado`, `em andamento`, `concluído`.
- Cada evento pode conter várias palestras.

---

### 🧑‍🏫 Palestras

- Cadastro de palestras com:
    - Título, descrição, data, horário de início e fim.
    - Capacidade total e número de vagas disponíveis.
    - Local e nível de dificuldade (`iniciante`, `intermediário`, `avançado`).
- Uma palestra pertence a:
    - Um único evento.
    - Uma única categoria (ex: Tecnologia, Marketing, etc.).
    - Um palestrante.
- Cada palestra pode ter:
    - Comentários dos usuários.
    - Inscrições de usuários interessados.

---

### 📥 Inscrição em Palestras

- Usuários podem se inscrever em qualquer palestra com vagas disponíveis.
- Campos da inscrição:
    - Data da inscrição.
    - Status: `confirmado`, `pendente`, `cancelado`.
    - Campo booleano indicando se a presença foi confirmada.
- Não é possível se inscrever duas vezes na mesma palestra.

---

### 💬 Comentários em Palestras

- Qualquer usuário pode comentar em uma palestra.
- Os comentários contêm:
    - Autor (usuário).
    - Texto.
    - Data do comentário.

---

### 📂 Categorias de Palestras

- Cada palestra pertence a uma **categoria** (ex: Inteligência Artificial, Design, Empreendedorismo).
- As categorias são cadastradas previamente com nome e descrição.
- Uma categoria pode conter várias palestras.

---

### 📝 Postagens

- Usuários podem criar postagens públicas, como em uma rede social.
- Campos da postagem:
    - Texto.
    - Data de publicação.
- Cada post tem um autor (usuário).

---

### 💬 Comentários em Postagens

- Qualquer usuário pode comentar em um post.
- Campos do comentário:
    - Autor (usuário).
    - Texto.
    - Data do comentário.
- Suporte a **respostas a comentários** (comentários aninhados).

---

## 🛡️ Controle de Acesso e Papéis

O sistema utiliza controle de acesso baseado em papéis:

| Papel | Permissões |
|-------|------------|
| `ROLE_USER` | Comentar, postar, seguir usuários, se inscrever em palestras. |
| `ROLE_PALESTRANTE` | Tudo que um usuário comum pode fazer **+ cadastrar palestras próprias**. |
| `ROLE_ADMIN` | Controle total da plataforma: gerenciar usuários, eventos, palestras e categorias. |

---
## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Spring Security
- Autenticação JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Docker + Docker Compose
- (Em breve: Integração com Google Calendar API e sistema de certificados)
- (Em breve: utilização da amazon S3)

## Modelagem das entidades

<img src="images/TalkHub.drawio%20(1).png" alt="Modelagem do banco">

## 📦 Como Rodar Localmente

1. Clone o repositório:
```
git clone https://github.com/Filipe-Samuel-p/TalkHub.git 
```
2. cd TalkHub
3. Configure o banco PostgreSQL (Usando Docker)
``` 
docker-compose up --build
```

