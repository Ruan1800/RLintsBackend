INSERT INTO tasks (id, title, description, status, created_at, updated_at) VALUES
  (gen_random_uuid(), 'Estudar Spring Boot', 'Aprender Clean Architecture com Spring Boot 3', 'PENDING', now(), now()),
  (gen_random_uuid(), 'Configurar Docker', 'Configurar docker-compose com PostgreSQL e API', 'DONE', now(), now()),
  (gen_random_uuid(), 'Implementar CRUD', 'Criar endpoints de criação, leitura, atualização e deleção de tasks', 'IN_PROGRESS', now(), now()),
  (gen_random_uuid(), 'Escrever testes', 'Implementar testes unitários e de integração', 'PENDING', now(), now()),
  (gen_random_uuid(), 'Criar frontend', 'Desenvolver interface React para o sistema de tasks', 'PENDING', now(), now());