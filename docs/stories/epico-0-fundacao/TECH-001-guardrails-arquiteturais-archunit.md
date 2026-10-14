# 🛡️ TECH-001: Guardrails Arquiteturais Automatizados com ArchUnit

## 1. Metadados & Rastreabilidade

* **ID:** TECH-001
* **Épico:** Épico 0 — Fundação Técnica & Governança SDD
* **Status:** 🟢 `CONCLUIDA`
* **Dependências:** US-001
* **Sequenciamento:** Deve ser concluída antes do início da implementação da US-002.
* **Branch Git Sugerida:** `test/TECH-001-archunit-guardrails`

---

## 2. Descrição (História Técnica)

**Como** engenheiro responsável pela evolução do ImobFlow

**Quero** automatizar a verificação das fronteiras arquiteturais essenciais

**Para que** violações da Arquitetura Hexagonal sejam detectadas pela suíte de testes antes de serem integradas ao projeto.

### 2.1 Objetivo

Transformar as regras arquiteturais essenciais, hoje protegidas por documentação e revisão manual, em testes automatizados com ArchUnit, preservando o baseline aprovado pela US-001 e sem alterar o comportamento da aplicação.

### 2.2 Escopo Incluído

* Adicionar futuramente o ArchUnit como dependência exclusiva de testes.
* Criar testes arquiteturais para as fronteiras consolidadas no projeto.
* Proteger a pureza do domínio e a direção das dependências entre camadas.
* Validar a localização das entidades JPA e a natureza das portas de repositório.
* Executar os testes arquiteturais no fluxo padrão de testes e no CI existente.
* Documentar as permissões conscientes de `@Service` e Jakarta Validation na camada `application`.

### 2.3 Fora do Escopo

* Refatorar ou alterar classes de produção.
* Remover `@Service` dos casos de uso ou mover Bean Validation para outra camada.
* Alterar endpoints, contratos JSON ou regras de negócio.
* Introduzir Flyway ou iniciar qualquer atividade da US-002.
* Criar regras extensas de estilo, nomenclatura ou visibilidade.
* Estabelecer limite mínimo de cobertura JaCoCo ou reestruturar o pipeline de CI.

---

## 3. Conceitos Técnicos Estudados

* **ArchUnit:** Verificação automatizada de dependências e convenções arquiteturais a partir do bytecode Java.
* **Fitness Functions:** Testes executáveis que informam continuamente se a arquitetura permanece dentro das restrições acordadas.
* **Arquitetura Hexagonal:** Proteção da direção das dependências entre domínio, aplicação, adaptadores e configuração.
* **Baseline Arquitetural:** Registro explícito das integrações técnicas atuais que são permitidas sem ampliar silenciosamente o acoplamento.

---

## 4. Critérios de Aceite e Cenários BDD

### Cenário 1: Arquitetura atual permanece válida

* **Dado que** a estrutura resultante da US-001 representa o baseline arquitetural aprovado,
* **Quando** a suíte de testes arquiteturais for executada,
* **Então** todas as regras ArchUnit deverão passar sem exigir alteração nas classes de produção.

### Cenário 2: Dependência de framework no domínio é rejeitada

* **Dado que** o domínio deve permanecer independente de frameworks,
* **Quando** uma classe em `domain..` depender de Spring, Jakarta Persistence, Jakarta Validation, `application`, `adapters` ou `config`,
* **Então** a suíte arquitetural deverá falhar identificando a classe e a dependência proibida.

### Cenário 3: Aplicação acessa um adaptador ou configuração

* **Dado que** a aplicação deve orquestrar o domínio por meio de portas,
* **Quando** uma classe em `application..` depender de `adapters..` ou `config..`,
* **Então** a suíte arquitetural deverá falhar.

### Cenário 4: Dependências atuais da aplicação são preservadas conscientemente

* **Dado que** os casos de uso atuais utilizam `org.springframework.stereotype.Service`,
* **E** o DTO de entrada utiliza `jakarta.validation`,
* **Quando** as regras de dependência da camada `application` forem executadas,
* **Então** essas dependências específicas deverão ser aceitas,
* **E** dependências em Spring Web, Spring Data, Spring Security ou Jakarta Persistence deverão continuar proibidas.

### Cenário 5: Adaptador de entrada acessa persistência

* **Dado que** controllers e tratadores HTTP devem delegar para a aplicação,
* **Quando** uma classe em `adapters.in..` depender de `adapters.out..` ou Spring Data,
* **Então** a suíte arquitetural deverá falhar.

### Cenário 6: Entidade JPA fora do adaptador de saída

* **Dado que** o domínio não representa diretamente a persistência,
* **Quando** uma classe anotada com `jakarta.persistence.Entity` estiver fora de `adapters.out..`,
* **Então** a suíte arquitetural deverá falhar.

### Cenário 7: Portas de repositório permanecem abstratas

* **Dado que** `domain.repository` define portas de saída,
* **Quando** os tipos desse pacote forem analisados,
* **Então** eles deverão ser interfaces sem dependências de implementação externa.

### Cenário 8: Integração com a validação existente

* **Dado que** os testes unitários, MockMvc e Testcontainers já protegem o comportamento,
* **Quando** `./gradlew test` for executado,
* **Então** os testes arquiteturais e os testes existentes deverão formar uma única validação verde,
* **E** uma violação arquitetural deverá tornar a mesma execução inválida.

### Critérios Verificáveis Complementares

1. ArchUnit deve existir apenas no classpath de testes.
2. A estrutura atual deve passar sem alteração de produção.
3. As permissões de `application` devem ser específicas, sem liberar genericamente Spring ou Jakarta.
4. Os 38 testes comportamentais existentes devem continuar passando junto aos novos testes arquiteturais.
5. Nenhum endpoint ou contrato REST pode ser alterado.

---

## 5. Especificação Funcional & Contratos

### 5.1 Contratos REST

**Não se aplica.** Esta é uma história técnica de proteção arquitetural e não cria nem altera interfaces HTTP.

### 5.2 Regras Arquiteturais Mínimas

1. **Pureza do domínio:** `domain..` pode depender apenas do JDK e do próprio domínio; não pode depender de Spring, Jakarta, `application`, `adapters` ou `config`.
2. **Direção da aplicação:** `application..` pode depender do próprio pacote e de `domain..`; não pode depender de `adapters..`, `config..`, Spring Web, Spring Data, Spring Security ou Jakarta Persistence.
3. **Permissões explícitas da aplicação:** somente `org.springframework.stereotype.Service` nos casos de uso e `jakarta.validation..` nos DTOs são admitidos no baseline atual.
4. **Isolamento da entrada:** `adapters.in..` não pode depender de `adapters.out..` nem de Spring Data/JPA.
5. **Isolamento da saída:** `adapters.out..` não pode depender de `adapters.in..`.
6. **Localização de entidades:** tipos anotados com `@Entity` devem residir em `adapters.out..`.
7. **Portas de saída:** tipos em `domain.repository..` devem ser interfaces e permanecer livres de implementações externas.

Não será incluída inicialmente uma proibição absoluta de `adapters.in..` depender de `domain..`. Embora não exista esse acoplamento no baseline, sua adoção como regra adicional exige discussão arquitetural específica para evitar rigidez não aprovada.

---

## 6. Plano Técnico de Implementação — Proposta para o Portão 2

### 6.1 Dependência Gradle

Após aprovação do Portão 2, adicionar ao bloco `dependencies` do `build.gradle`:

```groovy
testImplementation 'com.tngtech.archunit:archunit-junit5:1.5.1'
```

A dependência será restrita a testes e utilizará a integração oficial com JUnit 5. A versão deverá ser reconfirmada no momento da implementação caso o plano permaneça pendente por período prolongado.

### 6.2 Organização dos Testes

Criar uma única classe inicial:

```text
src/test/java/com/marcos/imobflow/architecture/HexagonalArchitectureTest.java
```

Responsabilidades planejadas:

* importar `com.marcos.imobflow` com `@AnalyzeClasses`;
* excluir classes de teste da análise quando necessário;
* declarar regras independentes com `@ArchTest` e descrições legíveis;
* manter as permissões do baseline próximas às regras correspondentes;
* evitar abstrações auxiliares até que repetição real justifique sua criação.

### 6.3 Regras a Implementar

1. Dependências permitidas e proibidas para `domain..`.
2. Proibição de `application..` acessar `adapters..` ou `config..`.
3. Proibição específica, em `application..`, de Spring Web, Spring Data, Spring Security e Jakarta Persistence.
4. Permissão limitada de `@Service` nos casos de uso e Jakarta Validation nos DTOs.
5. Proibição de `adapters.in..` acessar `adapters.out..` e tecnologias de persistência.
6. Proibição de `adapters.out..` acessar `adapters.in..`.
7. Restrição de classes anotadas com `@Entity` ao adaptador de saída.
8. Exigência de que os tipos em `domain.repository..` sejam interfaces.

### 6.4 Estratégia para Demonstrar Detecção de Violações

A validação será feita sem deixar código inválido no repositório:

1. Executar a nova classe contra o baseline e registrar resultado verde.
2. Durante o ciclo TDD local, introduzir uma violação temporária e mínima em uma fixture exclusiva de teste ou em classes sintéticas importadas deliberadamente pela regra.
3. Executar apenas a regra correspondente e registrar que ela falha pela causa esperada.
4. Remover a fixture/violação temporária ou convertê-la em teste de metateste isolado, conforme a API do ArchUnit permitir sem fragilidade.
5. Reexecutar a classe arquitetural e depois a suíte completa, mantendo somente artefatos verdes no estado final.

Não será criada uma violação temporária em código de produção. O plano técnico deverá preferir fixtures sob `src/test/java`; caso isso torne a regra artificial ou acople o teste à sua implementação interna, a evidência Red/Green poderá ser registrada no relatório da história sem conservar a fixture.

### 6.5 Integração com Suíte e CI

* A classe seguirá a convenção `*Test`, sendo descoberta automaticamente pela tarefa Gradle `test`.
* Não será criada tarefa Gradle separada na primeira versão.
* O workflow atual já executa `./gradlew clean test jacocoTestReport`; portanto, os testes arquiteturais entrarão no CI sem alteração obrigatória do YAML.
* Durante o desenvolvimento, usar execução direcionada da classe arquitetural para feedback rápido.
* Antes da conclusão, executar a suíte completa, incluindo os testes com Testcontainers e Docker disponível.

### 6.6 Etapas Atômicas

1. Adicionar a dependência de teste e confirmar resolução/compilação.
2. Criar a classe arquitetural com a regra de pureza do domínio.
3. Demonstrar o ciclo de detecção da primeira violação.
4. Adicionar, uma a uma, as regras de aplicação, adaptadores, entidades e portas.
5. Executar a classe arquitetural após cada regra.
6. Executar a suíte completa e conferir que os 38 testes preexistentes continuam verdes.
7. Registrar evidências, riscos residuais e resultado final na Seção 7.

### 6.7 Arquivos Previstos na Implementação

* **Modificar:** `build.gradle`, exclusivamente para a dependência ArchUnit de teste.
* **Criar:** `src/test/java/com/marcos/imobflow/architecture/HexagonalArchitectureTest.java`.
* **Atualizar ao final:** este documento e `docs/backlog/BACKLOG.md`, conforme o rito das Etapas 5 e 6.
* **Código de produção:** nenhuma alteração prevista.
* **CI:** nenhuma alteração prevista em `.github/workflows/ci.yml`.

### 6.8 Critérios Técnicos de Conclusão

* Todas as regras mínimas estão implementadas e possuem mensagens compreensíveis.
* Há evidência de que pelo menos uma violação representativa foi detectada no ciclo Red/Green.
* O baseline de produção passa sem refatoração.
* `./gradlew test` executa os testes arquiteturais automaticamente.
* Os 38 testes preexistentes e os novos testes passam sem falhas ou testes ignorados.
* O CI requer apenas seu fluxo normal para bloquear uma violação.
* Nenhuma dependência ArchUnit aparece no classpath de produção.

### 6.9 Riscos Técnicos e Mitigações

* **Regras rígidas demais:** começar apenas com fronteiras essenciais e discutir novas restrições em histórias próprias.
* **Exceções amplas anularem a proteção:** permitir classes, anotações ou pacotes específicos, nunca frameworks completos.
* **Regras sem classes correspondentes passarem silenciosamente:** configurar as regras relevantes para não aceitar avaliações vazias e verificar a cobertura dos pacotes importados.
* **Fixtures produzirem falso sinal:** mantê-las isoladas e importá-las apenas quando a demonstração exigir.
* **Mensagens difíceis de interpretar:** dar nomes e descrições de negócio às regras.
* **Testcontainers indisponível:** validar rapidamente a classe ArchUnit sem Docker, mas exigir a suíte completa antes do Portão 3.
* **Versão da biblioteca ficar desatualizada:** reconfirmar a versão oficial antes da alteração do Gradle.

---

## 7. Registro de Conclusão & Evidências

### 7.1 Arquivos da Implementação

* `build.gradle`: inclusão de `com.tngtech.archunit:archunit-junit5:1.5.1` com escopo `testImplementation`.
* `src/test/java/com/marcos/imobflow/architecture/HexagonalArchitectureTest.java`: nove checks, sendo oito regras arquiteturais e uma verificação explícita de que os pacotes e a entidade JPA esperados participam da análise.
* Nenhuma classe de produção, endpoint, contrato REST ou configuração de CI foi alterada.

### 7.2 Evidência Red/Green

Em 14/10/2026 foi criada temporariamente, apenas em `src/test/java`, a fixture `com.marcos.imobflow.domain.ArchUnitViolationFixture`, anotada com `@Service`, e um teste de prova que submeteu essa classe à mesma regra de pureza do domínio usada pela suíte.

* **Red:** a execução direcionada falhou com uma violação, identificando que `ArchUnitViolationFixture` estava anotada com `org.springframework.stereotype.Service` dentro de `domain`.
* **Green:** os dois arquivos temporários foram removidos e a execução direcionada de `HexagonalArchitectureTest` voltou a passar.
* **Estado final:** nenhuma fixture ou violação artificial permaneceu no projeto.

### 7.3 Suíte Completa

Comando executado em 14/10/2026:

```powershell
./gradlew test --rerun-tasks --no-daemon --console=plain
```

Resultado: **47 testes**, **0 falhas**, **0 erros** e **0 ignorados**, distribuídos em 38 testes preexistentes e 9 checks arquiteturais. A execução incluiu testes unitários, MockMvc, integração com MySQL/Testcontainers e ArchUnit.

### 7.4 Isolamento da Dependência

As consultas `dependencyInsight` confirmaram:

* `runtimeClasspath`: nenhuma dependência ArchUnit encontrada.
* `testRuntimeClasspath`: `archunit-junit5:1.5.1` e seus módulos transitivos presentes.

ArchUnit permanece, portanto, restrito ao classpath de testes.

### 7.5 Integridade e Riscos Residuais

* `git diff --check` executado sem erros após a implementação.
* As permissões de `@Service` e Jakarta Validation permanecem específicas; nenhuma permissão genérica de framework foi adicionada.
* A suíte completa continua dependente de Docker para os testes Testcontainers.
* Novas integrações técnicas na camada `application` exigirão decisão arquitetural explícita antes de alterar as regras.

### 7.6 Aceite Final

O Portão 3 foi aprovado formalmente pelo desenvolvedor em 14/10/2026. Foram aceitas as evidências do ciclo Red/Green, a execução bem-sucedida dos 47 testes, o isolamento do ArchUnit no classpath de testes e a ausência de alterações em código de produção. A TECH-001 está concluída documentalmente e preparada para versionamento, permanecendo commit, push e integração condicionados a autorização posterior.

---

## 8. Estado dos Portões

* **Portão 1:** Aprovado pelo desenvolvedor antes da formalização deste documento.
* **Portão 2:** Aprovado pelo desenvolvedor antes do início da implementação.
* **Portão 3:** Aprovado formalmente em 14/10/2026; fechamento documental concluído.
