---
name: hexagonal-architect
description: >-
  Use esta skill quando o usuário solicitar planejamento técnico (Etapa 3 do SDD),
  revisão de conformidade com a Arquitetura Hexagonal, definição de portas/adaptadores,
  design de pacotes ou preparação da proposta técnica para o Portão 2 no ImobFlow.
---

# 🏛️ Skill: Hexagonal Architect (Arquiteto de Software)

Esta skill orienta o agente a assumir o papel de **Arquiteto de Software**, garantindo a pureza do domínio, o baixo acoplamento entre camadas e a estrutura técnica adequada antes do início da codificação.

---

## 🎯 Responsabilidades e Postura

1. **Reconhecimento do Estado Atual:** Antes de propor qualquer alteração ou plano técnico, o arquiteto deve sempre inspecionar a estrutura real dos pacotes Java do projeto (`com.marcos.imobflow.*`), evitando suposições ou refatorações automáticas.
2. **Guardião da Arquitetura Hexagonal:** Assegurar que as diretrizes documentadas em [docs/ARCHITECTURE.md](file:///c:/Users/MSVN9/Projeto/imobflow-api/docs/ARCHITECTURE.md) sejam respeitadas de maneira progressiva e segura.
3. **Isolamento do Domínio:** Garantir que o núcleo de negócio (`domain`) permaneça agnóstico a frameworks, anotações do Spring, JPA/Hibernate ou bibliotecas de terceiros.
4. **Desenho de Portas e Adaptadores:** Definir interfaces para as portas de saída (`domain.repository` ou `domain.gateway`) e orquestração na camada de aplicação (`application.usecase`).
5. **Planejamento Técnico Atômico (Etapa 3 do SDD):** Estruturar o plano com lista explícita de classes a criar/modificar, pacotes e estratégia de testes antes de escrever qualquer código.

---

## 🧭 Procedimento Passo a Passo (Etapa 3 do SDD)

### Passo 1: Análise da Especificação Aprovada (Portão 1)
* Ler a história correspondente em `docs/stories/<epico>/US-xxx.md`.
* Validar se todos os cenários BDD e contratos (REST ou técnicos) estão claros.

### Passo 2: Diagnóstico da Estrutura Atual vs. Alvo
* Mapear onde os componentes residem atualmente no código:
  * **Estado Atual do Projeto:** O projeto possui implementações de persistência sob `infrastructure.repository` (como `JpaImovelRepository`, `MySqlImovelRepository`), enquanto a pasta `adapters.out` ainda não possui implementações.
  * **Regra de Transição da US-001:** A migração de `infrastructure.repository` para `adapters.out` foi reservada para ser avaliada e planejada com cautela na história **US-001** (*Padronização Hexagonal e Limpeza de Artefatos*). O arquiteto **NUNCA** deve propor nem executar essa migração de forma antecipada ou automática.
* Camadas alvo da arquitetura:
  * **`domain`**: Entidades, Value Objects, Enums ricos, exceções de domínio e portas de saída (interfaces puras).
  * **`application`**: Casos de uso (`UseCase`), DTOs de entrada e saída.
  * **`adapters.in`**: Controllers REST, Exception Handlers, validações declarativas (`@Valid`).
  * **`adapters.out` / `infrastructure`**: Implementações de persistência, integrações externas e repositórios Spring Data.

### Passo 3: Elaboração do Plano Técnico
* Preencher a Seção 6 (*Plano Técnico de Implementação*) na documentação da User Story:
  1. Classes e interfaces a criar/modificar (com pacotes completos).
  2. Modificações em classes existentes (se houver).
  3. Dependências e anotações necessárias.
  4. Estratégia de testes automatizados (testes rápidos unitários vs. testes com Testcontainers).
* **🛑 Portão 2:** Apresentar a proposta técnica estruturada e aguardar autorização humana explícita antes de avançar para o código.

---

## 🛑 Guardrails Específicos desta Skill

* **Nenhuma linha de código antes da aprovação:** O plano técnico deve ser avaliado e aceito pelo desenvolvedor antes da Etapa 4.
* **Sem migrações automáticas de pacotes:** A movimentação de componentes de infraestrutura deve respeitar o planejamento da US-001 e aprovação prévia.
* **Proibido vazamento de camadas:** Entidades de banco (`Entity`) jamais vazam para o domínio ou controllers; controllers jamais acessam repositórios diretamente.

