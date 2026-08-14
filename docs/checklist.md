# Checklist do Projeto - ImobFlow API

## Checklist do Projeto - ImobFlow API

## Setup inicial

* [x] Criar projeto Spring Boot com Gradle
* [x] Configurar Java 21
* [x] Abrir projeto no IntelliJ
* [x] Criar repositório no GitHub
* [x] Criar README inicial
* [x] Criar pasta docs
* [x] Configurar Docker
* [x] Configurar MySQL
* [x] Configurar application.yml

---

## Git e versionamento

* [x] Fazer primeiro commit
* [x] Criar branch develop
* [x] Criar primeira feature branch
* [x] Usar commits semânticos
* [x] Subir projeto no GitHub

---

## Documentação

* [x] Criar README.md
* [x] Criar checklist do projeto
* [ ] Criar documentação de visão geral
* [ ] Criar documentação da arquitetura
* [ ] Criar documentação de setup local
* [ ] Documentar decisões técnicas

---

## Banco de dados

* [x] Modelar tabela de imóveis
* [x] Configurar conexão com MySQL
* [x] Criar primeira entidade JPA
* [x] Criar repository
* [x] Testar persistência no banco
* [ ] Estudar migrations futuramente

---

## Arquitetura

* [x] Criar pacote domain
* [x] Criar pacote application
* [x] Criar pacote infrastructure
* [x] Criar pacote adapters
* [ ] Documentar arquitetura hexagonal
* [ ] Documentar arquitetura limpa
* [x] Separar regras de negócio do framework

---

## Feature - Imóvel

* [x] Criar entidade Imovel
* [x] Criar DTO de request
* [x] Criar DTO de response
* [x] Criar use case de cadastro
* [x] Criar controller
* [x] Criar endpoint POST /imoveis
* [x] Criar endpoint GET /imoveis
* [x] Criar endpoint GET /imoveis/{id}
* [x] Criar endpoint PUT /imoveis/{id}
* [x] Criar endpoint DELETE /imoveis/{id}
* [x] Adicionar Bean Validation
* [x] Adicionar tratamento global de erros
* [x] Adicionar tratamento de imóvel não encontrado
* [x] Testar endpoints no Insomnia

---

## Testes unitários

* [x] Criar primeiro teste unitário com JUnit
* [x] Criar teste com Mockito
* [x] Testar CadastrarImovelUseCase
* [x] Testar BuscarImovelPorIdUseCase
* [x] Testar cenário de imóvel não encontrado
* [x] Testar AtualizarImovelPorIdUseCase
* [ ] Testar DeletarImovelPorIdUseCase
* [ ] Testar ListarImoveisUseCase
* [ ] Criar teste de integração
* [ ] Configurar cobertura com JaCoCo
* [ ] Planejar testes E2E com Cypress futuramente

---

## CI/CD

* [ ] Configurar GitHub Actions
* [ ] Rodar build automático
* [ ] Rodar testes no pipeline
* [ ] Gerar relatório de cobertura
* [ ] Preparar pipeline para deploy futuro

---

## Feature - Interessado

* [ ] Modelar entidade Interessado
* [ ] Associar interessado a um imóvel
* [ ] Criar DTOs de request e response
* [ ] Criar repository
* [ ] Criar use cases
* [ ] Criar endpoints
* [ ] Adicionar validações
* [ ] Criar testes unitários

---

## Frontend - MVP

* [ ] Criar projeto frontend
* [ ] Criar página de listagem de imóveis
* [ ] Criar página de detalhes do imóvel
* [ ] Integrar frontend com backend
* [ ] Criar botão "Tenho interesse"
* [ ] Criar interface de atendimento

---

## Atendimento com IA

* [ ] Modelar atendimento
* [ ] Criar histórico de mensagens
* [ ] Criar contrato para integração com IA
* [ ] Integrar provedor de IA
* [ ] Enviar contexto do imóvel para o assistente
* [ ] Coletar dados do interessado
* [ ] Registrar interesse através do atendimento
* [ ] Criar testes do fluxo

---

## Integração com WhatsApp

* [ ] Definir estratégia de integração
* [ ] Enviar novo lead para atendimento humano
* [ ] Enviar dados do imóvel e do interessado
* [ ] Tratar falhas da integração

---

## Segurança

* [x] Estudar Spring Security
* [ ] Criar autenticação futuramente
* [ ] Criar login com JWT
* [ ] Definir perfis de usuário
* [ ] Proteger endpoints administrativos

---

## DDD

* [x] Definir entidades de domínio
* [ ] Criar Value Objects
* [x] Criar regras de negócio no domínio
* [x] Definir contratos de repositório
* [ ] Documentar decisões de domínio

---

## Observabilidade / SRE

* [ ] Configurar Spring Actuator
* [ ] Criar health check
* [ ] Adicionar logs estruturados
* [ ] Configurar métricas
* [ ] Estudar Prometheus e Grafana

---

## Mensageria e eventos - Futuro

* [ ] Estudar RabbitMQ
* [ ] Criar primeiro evento de domínio
* [ ] Criar producer
* [ ] Criar consumer
* [ ] Evoluir para arquitetura orientada a eventos

---

## Microsserviços - Futuro

* [x] Manter projeto inicialmente como monólito modular
* [ ] Identificar possíveis módulos
* [ ] Estudar separação em serviços
* [ ] Planejar futuro serviço de notificações
* [ ] Planejar futuro serviço de usuários

---

## AWS / Deploy

* [ ] Estudar AWS Free Tier
* [ ] Fazer deploy da aplicação
* [ ] Estudar EC2
* [ ] Estudar RDS para banco
* [ ] Estudar S3 para imagens
* [ ] Documentar processo de deploy

---

## Entrega V1

* [ ] Backend funcionando de ponta a ponta
* [ ] Testes unitários principais finalizados
* [ ] Pipeline passando
* [ ] Fluxo de interessado funcionando
* [ ] Frontend mínimo integrado
* [ ] Atendimento com IA funcionando
* [ ] Encaminhamento do lead funcionando
* [ ] Deploy realizado
* [ ] README final atualizado
* [ ] Adicionar diagrama da arquitetura
* [ ] Preparar demonstração do projeto
