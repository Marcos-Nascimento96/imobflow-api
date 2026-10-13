# 📋 Quadro de Backlog e Roadmap — ImobFlow API

Este documento é a fonte única da verdade para o andamento das histórias de usuário do **ImobFlow API**.

> 💡 **Nota de Flexibilidade:** Este backlog é vivo e revisável. As histórias são especificadas e refinadas uma a uma sob demanda, garantindo entregas incrementais com validação humana.

---

## 🚦 Legenda de Status
* ⚪ `BACKLOG`: História mapeada, aguardando momento de especificação.
* 🟡 `EM_ESPECIFICACAO`: Espec do SDD em elaboração no arquivo individual.
* 🔵 `ESPECIFICADA`: Especificação concluída e aprovada, pronta para implementação técnica.
* 🟠 `EM_DESENVOLVIMENTO`: Implementação de código e testes em andamento.
* 🟣 `EM_VALIDACAO`: Código e testes prontos, em revisão e momento didático de mentoria.
* 🟢 `CONCLUIDA`: Validada, aprovada pelo desenvolvedor e integrada.

---

## 📊 Tabela de Acompanhamento das Histórias

| ID | Épico | Título da História | Dependências | Status | Conceitos Técnicos Estudados |
| :---: | :--- | :--- | :---: | :---: | :--- |
| **US-000** | Épico 0: Fundação | Setup de Governança, `AGENTS.md`, Skills e Rastreabilidade | Nenhuma | 🟣 `EM_VALIDACAO` | Governança de IA, Antigravity Customizations/Skills, SDD, Git. |
| **US-001** | Épico 0: Fundação | Padronização Hexagonal e Limpeza de Artefatos | US-000 | 🟢 `CONCLUIDA` | Arquitetura Hexagonal, Ports & Adapters, Refatoração segura. |
| **US-002** | Épico 0: Fundação | Migrações de Banco com Flyway & Testcontainers MySQL | US-001 | ⚪ `BACKLOG` | Versionamento de banco (Flyway), Testcontainers, `@DataJpaTest`. |
| **US-003** | Épico 1: Core Imóveis | Tipagem Rica com Enums no Domínio Imóvel | US-002 | ⚪ `BACKLOG` | Domain-Driven Design (Enums ricos), Bean Validation, Jackson. |
| **US-004** | Épico 1: Core Imóveis | Paginação e Ordenação na Listagem de Imóveis | US-003 | ⚪ `BACKLOG` | `Pageable`, `Page<T>` no Spring Data, queries paginadas. |
| **US-005** | Épico 1: Core Imóveis | Documentação Viva da API com OpenAPI / Swagger | US-004 | ⚪ `BACKLOG` | OpenAPI 3, `springdoc-openapi`, Swagger UI, contratos REST. |
| **US-006** | Épico 2: Deploy Antecipado | Deploy Experimental da API com Healthcheck em Nuvem | US-005 | ⚪ `BACKLOG` | Dockerfile multi-stage, PaaS (Render/Railway), Smoke Testing, variáveis de ambiente. |
| **US-007** | Épico 3: Segurança | Cadastro de Usuários com Perfis (`Roles`) | US-006 | ⚪ `BACKLOG` | Criptografia com `BCryptPasswordEncoder`, modelagem de entidades de autenticação. |
| **US-008** | Épico 3: Segurança | Autenticação Stateless com Spring Security & JWT | US-007 | ⚪ `BACKLOG` | JWT (JSON Web Tokens), `OncePerRequestFilter`, Spring Security Filter Chain. |
| **US-009** | Épico 3: Segurança | Vínculo de Imóveis ao Proprietário e Controle de Acesso | US-008 | ⚪ `BACKLOG` | Autorização em nível de método (`@PreAuthorize`), regras de posse de recursos. |
| **US-010** | Épico 4: Mídia | Upload e Armazenamento de Fotos do Imóvel | US-009 | ⚪ `BACKLOG` | Manipulação de `MultipartFile`, persistência de arquivos, validação de mimetype. |
| **US-011** | Épico 4: Mídia | Galeria de Fotos e Definição de Foto de Capa | US-010 | ⚪ `BACKLOG` | Relacionamento 1:N JPA, integridade referencial, regras de agregação. |
| **US-012** | Épico 5: Visitas | Solicitação de Agendamento de Visita pelo Interessado | US-009 | ⚪ `BACKLOG` | Validações temporais (`@Future`), máquina de estados de agendamento. |
| **US-013** | Épico 5: Visitas | Gestão de Visitas pelo Proprietário (Aprovar/Recusar) | US-012 | ⚪ `BACKLOG` | Transações (`@Transactional`), consistência e notificações/status. |
| **US-014** | Épico 6: Front-end | Setup do Front-end Web e Catálogo de Imóveis | US-006 | ⚪ `BACKLOG` | Consumo de API REST, CORS, renderização dinâmica, paginação visual. |
| **US-015** | Épico 6: Front-end | Interface Web de Detalhes e Agendamento de Visita | US-014 | ⚪ `BACKLOG` | Formulários web, autenticação com armazenamento de token JWT, UX de lead. |
| **US-016** | Épico 7: DevOps Final | Publicação Completa em Produção & Pipeline de CD | US-015 | ⚪ `BACKLOG` | Pipeline GitHub Actions para deploy contínuo, monitoramento básico. |

---

## 📈 Histórico de Conclusões

| ID | Data de Conclusão | Descrição Resumida da Entrega |
| :---: | :---: | :--- |
| *Nenhuma história concluída ainda* | - | - |
