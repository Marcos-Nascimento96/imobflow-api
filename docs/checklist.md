# Checklist do Projeto - ImobFlow API

## Setup inicial
- [x] Criar projeto Spring Boot com Gradle
- [x] Configurar Java 21
- [x] Abrir projeto no IntelliJ
- [x] Criar repositório no GitHub
- [x] Criar README inicial
- [x] Criar pasta docs
- [ ] Configurar Docker
- [ ] Configurar MySQL
- [x] Configurar application.yml

---

## Git e versionamento
- [x] Fazer primeiro commit
- [ ] Criar branch develop
- [ ] Criar primeira feature branch
- [x] Usar commits semânticos
- [x] Subir projeto no GitHub

---

## Documentação
- [x] Criar README.md
- [x] Criar checklist do projeto
- [ ] Criar documentação de visão geral
- [ ] Criar documentação da arquitetura
- [ ] Criar documentação de setup local
- [ ] Documentar decisões técnicas

---

## Banco de dados
- [ ] Modelar tabela de imóveis
- [ ] Configurar conexão com MySQL
- [ ] Criar primeira entidade JPA
- [ ] Criar repository
- [ ] Testar persistência no banco
- [ ] Estudar migrations futuramente

---

## Arquitetura
- [ ] Criar pacote domain
- [ ] Criar pacote application
- [ ] Criar pacote infrastructure
- [ ] Criar pacote adapters
- [ ] Documentar arquitetura hexagonal
- [ ] Documentar arquitetura limpa
- [ ] Separar regras de negócio do framework

---

## Primeira feature - Imóvel
- [ ] Criar entidade Imovel
- [ ] Criar DTO de request
- [ ] Criar DTO de response
- [ ] Criar use case de cadastro
- [x] Criar controller
- [ ] Criar endpoint POST /imoveis
- [ ] Criar endpoint GET /imoveis
- [x] Testar no Insomnia

---

## Testes
- [ ] Criar primeiro teste unitário com JUnit
- [ ] Criar teste com Mockito
- [ ] Criar teste de integração
- [ ] Configurar cobertura com Jacoco
- [ ] Planejar testes E2E com Cypress futuramente

---

## Segurança
- [ ] Estudar Spring Security
- [ ] Criar autenticação futuramente
- [ ] Criar login com JWT
- [ ] Definir perfis de usuário
- [ ] Proteger endpoints administrativos

---

## DDD
- [ ] Definir entidades de domínio
- [ ] Criar Value Objects
- [ ] Criar regras de negócio no domínio
- [ ] Definir contratos de repositório
- [ ] Documentar decisões de domínio

---

## CI/CD
- [ ] Configurar GitHub Actions
- [ ] Rodar build automático
- [ ] Rodar testes no pipeline
- [ ] Gerar relatório de cobertura
- [ ] Preparar pipeline para deploy futuro

---

## Observabilidade / SRE
- [ ] Configurar Spring Actuator
- [ ] Criar health check
- [ ] Adicionar logs estruturados
- [ ] Configurar métricas
- [ ] Estudar Prometheus e Grafana

---

## Mensageria e eventos
- [ ] Estudar RabbitMQ
- [ ] Criar primeiro evento de domínio
- [ ] Criar producer
- [ ] Criar consumer
- [ ] Evoluir para arquitetura orientada a eventos

---

## Microsserviços
- [ ] Manter projeto inicialmente como monólito modular
- [ ] Identificar possíveis módulos
- [ ] Estudar separação em serviços
- [ ] Planejar futuro serviço de notificações
- [ ] Planejar futuro serviço de usuários

---

## AWS
- [ ] Estudar AWS Free Tier
- [ ] Fazer deploy em EC2
- [ ] Estudar RDS para banco
- [ ] Estudar S3 para imagens
- [ ] Documentar processo de deploy

---

## Evolução futura
- [ ] Criar frontend separado futuramente
- [ ] Integrar frontend com backend
- [ ] Criar testes E2E com Cypress
- [ ] Melhorar README com prints e exemplos
- [ ] Adicionar diagramas do projeto