# 📖 US-000: Setup de Documentação, AGENTS.md e Rastreabilidade

## 1. Metadados & Rastreabilidade
* **ID:** US-000
* **Épico:** Épico 0 — Fundação Técnica & Governança SDD
* **Status:** 🟣 `EM_VALIDACAO`
* **Dependências:** Nenhuma
* **Branch Git:** `feature/US-000-setup-agentes`

---

## 2. Descrição (User Story)
**Como** engenheiro de software e desenvolvedor do projeto  
**Quero** estabelecer as diretrizes de governança de agentes de IA, a arquitetura do sistema e o painel de rastreamento do backlog  
**Para que** o projeto evolua de forma previsível, orientada a especificações (SDD), com aprendizado técnico contínuo e sem risco de alterações desordenadas no código.

---

## 3. 📚 Conceitos Técnicos Estudados
* **Governança de Agentes de IA & Guardrails:** Como definir restrições e instruções operacionais para que LLMs atuem como pair programmers confiáveis sem gerar alucinações ou quebrar o código.
* **Specification-Driven Development (SDD):** A disciplina de transformar requisitos em especificações formais antes de qualquer linha de código de produção ser escrita.
* **Context Engineering:** Organização de documentação em markdown para que novos agentes ou colaboradores recuperem o contexto do sistema de forma instantânea e sem ruídos.

---

## 4. Critérios de Aceite (BDD)

### Cenário 1: Instruções e Guardrails de IA Estabelecidos
* **Dado que** um agente de IA acessa o repositório `imobflow-api`,
* **Quando** ele inspecionar a raiz do projeto,
* **Então** ele deve encontrar o arquivo `AGENTS.md` definindo seus papéis, as regras inegociáveis de aprovação prévia e o ciclo de vida do SDD.

### Cenário 2: Arquitetura Hexagonal Formalizada
* **Dado que** o desenvolvedor ou agente precisa entender as camadas do sistema,
* **Quando** ele consultar a pasta `docs/`,
* **Então** deve existir um arquivo `ARCHITECTURE.md` explicando a separação entre `domain`, `application`, `adapters.in`, `adapters.out` e a pirâmide de testes.

### Cenário 3: Painel de Controle e Histórias Mapeadas
* **Dado que** o time precisa acompanhar o progresso das entregas,
* **Quando** ele consultar o painel de backlog,
* **Então** deve encontrar o arquivo `docs/backlog/BACKLOG.md` contendo as 17 histórias mapeadas com status, dependências e conceitos técnicos associados.

### Cenário 4: Skills Especializadas Configuradas e Reconhecidas
* **Dado que** o projeto utiliza o ecossistema Antigravity IDE para desenvolvimento assistido por IA,
* **Quando** for inspecionado o diretório `.agents/skills/`,
* **Então** devem existir as Skills modulares `sdd-story-analyst`, `hexagonal-architect` e `java-tdd-mentor` devidamente documentadas com frontmatter YAML e instruções de ativação progressiva.

---

## 5. Especificação Funcional & Contratos (SDD)
* **Arquivos Documentais Criados/Atualizados:**
  * `AGENTS.md` (na raiz do projeto, com guardrails e catálogo de skills).
  * `docs/ARCHITECTURE.md` (diretrizes de arquitetura hexagonal).
  * `docs/backlog/BACKLOG.md` (quadro kanban / rastreador de histórias).
  * `docs/stories/epico-0-fundacao/US-000-setup-documentacao-agentes.md` (esta especificação).
  * `.agents/skills/sdd-story-analyst/SKILL.md` e template `us-template.md`.
  * `.agents/skills/hexagonal-architect/SKILL.md`.
  * `.agents/skills/java-tdd-mentor/SKILL.md`.
* **Restrição Técnica:** Nenhuma classe Java, configuração de banco de dados ou dependência do Gradle deve ser alterada durante esta história.

---

## 6. Plano Técnico de Implementação
* **Passo 1:** Preencher `AGENTS.md` com diretrizes de governança, guardrails, fluxo SDD e rito didático.
* **Passo 2:** Criar `docs/ARCHITECTURE.md` sintetizando pacotes, inversão de controle e regras de dependência.
* **Passo 3:** Criar `docs/backlog/BACKLOG.md` com as 17 histórias e conceitos técnicos.
* **Passo 4:** Criar a especificação individual da US-000 para inaugurar a pasta de histórias.
* **Passo 5:** Estruturar a pasta `.agents/skills/` com as três Skills especializadas para o ciclo de vida SDD/Hexagonal/TDD.

---

## 7. Registro de Conclusão & Evidências
* **Arquivos Criados/Alterados:**
  * [`AGENTS.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/AGENTS.md)
  * [`docs/ARCHITECTURE.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/docs/ARCHITECTURE.md)
  * [`docs/backlog/BACKLOG.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/docs/backlog/BACKLOG.md)
  * [`docs/stories/epico-0-fundacao/US-000-setup-documentacao-agentes.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/docs/stories/epico-0-fundacao/US-000-setup-documentacao-agentes.md)
  * [`.agents/skills/sdd-story-analyst/SKILL.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/.agents/skills/sdd-story-analyst/SKILL.md)
  * [`.agents/skills/sdd-story-analyst/resources/us-template.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/.agents/skills/sdd-story-analyst/resources/us-template.md)
  * [`.agents/skills/hexagonal-architect/SKILL.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/.agents/skills/hexagonal-architect/SKILL.md)
  * [`.agents/skills/java-tdd-mentor/SKILL.md`](file:///c:/Users/MSVN9/Projeto/imobflow-api/.agents/skills/java-tdd-mentor/SKILL.md)
* **Status da Suíte de Testes:** A suíte de código permaneceu 100% intocada, sem nenhuma modificação de código Java.

