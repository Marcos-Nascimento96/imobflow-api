# 📖 US-001: Padronização Hexagonal e Limpeza de Artefatos

## 1. Metadados & Rastreabilidade
* **ID:** US-001
* **Épico:** Épico 0 — Fundação Técnica & Governança SDD
* **Status:** 🟢 `CONCLUIDA`
* **Dependências:** US-000
* **Branch Git Sugerida:** `refactor/US-001-padronizacao-hexagonal`

---

## 2. Descrição (User Story)
**Como** engenheiro de software e desenvolvedor do ImobFlow

**Quero** padronizar a estrutura de pacotes da Arquitetura Hexagonal, isolar o modelo de domínio do Jakarta Persistence (JPA), remover artefatos obsoletos e proteger as saídas da API com DTOs dedicados

**Para que** a base de código seja manutenível, puramente desacoplada de frameworks de persistência no domínio, livre de código morto e com comportamento 100% preservado para suportar as próximas evoluções (Flyway e Testcontainers na US-002).

---

## 3. 📚 Conceitos Técnicos Estudados
* **Arquitetura Hexagonal & Pureza do Domínio:** Garantir que o núcleo de negócio (`domain.model`) seja composto por POJOs puros, sem nenhuma dependência ou anotação do JPA/Hibernate (`@Entity`, `@Table`, `@Id`).
* **Padrão Ports & Adapters e Inversão de Controle:** Reorganização correta dos adaptadores secundários (orientados a banco) em `adapters.out.repository`, implementando a porta pura `domain.repository.ImovelRepository`.
* **Mapeamento de Persistência (Entity vs. Domain Model):** Criação de `ImovelEntity` em `adapters.out` com conversor/mapper bidirecional para transitar entre a tabela relacional e a entidade de domínio.
* **DTOs de Proteção de Fronteira:** Utilização de DTO de saída (`ImovelResponse`) para impedir que entidades internas de negócio vazem nos controladores REST HTTP.
* **Testes de Regressão com Testcontainers:** Implementação de teste real de persistência em container Docker MySQL antes da refatoração de infraestrutura para garantir segurança contra regressões.

---

## 4. Critérios de Aceite (BDD)

### Cenário 1: Teste de integração real de persistência pré-refatoração
* **Dado que** o repositório MySQL é o adaptador oficial de persistência,
* **Quando** o teste de integração com Testcontainers (`MySqlImovelRepositoryIntegrationTest`) for executado contra o MySQL em container Docker,
* **Então** ele deve salvar, consultar por ID e filtrar registros de fato, validando o comportamento do banco antes das alterações estruturais de classes.

### Cenário 2: Reorganização de adaptadores secundários em `adapters.out.repository`
* **Dado que** as classes de persistência residem temporariamente em `com.marcos.imobflow.infrastructure.repository`,
* **Quando** a reorganização estrutural for aplicada,
* **Então** as classes `MySqlImovelRepository`, `JpaImovelRepository` e `ImovelSpecification` devem residir exclusivamente no pacote `com.marcos.imobflow.adapters.out.repository`,
* **E** o pacote legado `infrastructure` deve ser descontinuado.

### Cenário 3: Isolamento estrito do modelo de domínio em relação ao JPA
* **Dado que** a classe `domain.model.Imovel` representará o negócio puro,
* **Quando** for inspecionada a estrutura da classe `Imovel`,
* **Então** ela não deve conter nenhuma anotação do pacote `jakarta.persistence.*`,
* **E** uma entidade JPA dedicada `ImovelEntity` deve ser criada sob `adapters.out.repository` para manter a tabela `imoveis` com os mesmos tipos e nomes de colunas,
* **E** o mapeamento bidirecional entre `ImovelEntity` e `Imovel` deve ser realizado exclusivamente dentro de `adapters.out.repository`.

### Cenário 4: Exposição de dados na API protegida por DTO (`ImovelResponse`)
* **Dado que** os endpoints `GET /imoveis`, `GET /imoveis/{id}` e `PUT /imoveis/{id}` retornam dados de imóveis,
* **Quando** um cliente consumir esses endpoints,
* **Então** o controlador `ImovelController` deve retornar o DTO de resposta `ImovelResponse`,
* **E** a entidade de domínio `Imovel` não deve ser exposta na assinatura dos métodos do controlador nem no retorno REST,
* **E** a estrutura, nomes e tipos de campos no JSON de resposta devem ser rigorosamente preservados.

### Cenário 5: Remoção segura de artefatos obsoletos e código morto
* **Dado que** `InMemoryImovelRepository` e o método `listarPorFaixaDeValor` não são referenciados nem utilizados na aplicação,
* **Quando** for realizada a limpeza de código morto,
* **Então** a classe `InMemoryImovelRepository` deve ser excluída após verificação de ausência de referências,
* **E** o método `listarPorFaixaDeValor` deve ser removido de `ImovelRepository` e de suas implementações sem afetar o método `listarPorFiltros`.

### Cenário 6: Reorganização do endpoint de smoke test
* **Dado que** o `HelloController` reside na raiz do pacote `adapters.in`,
* **Quando** a padronização for concluída,
* **Então** ele deve residir no subpacote `com.marcos.imobflow.adapters.in.controller`,
* **E** a rota `GET /hello` deve continuar respondendo normalmente com a mensagem `"Hello World 🚀"`.

### Cenário 7: Preservação de compatibilidade e suíte de testes 100% verde
* **Dado que** todas as refatorações atômicas da US-001 foram executadas,
* **Quando** a suíte de testes completa for executada com `./gradlew test`,
* **Então** todos os testes unitários de casos de uso, testes de controller (MockMvc) e testes de integração com Testcontainers devem passar com sucesso.

---

## 5. Especificação Funcional & Contratos (SDD)

### 5.1 Endpoints REST e Contratos de Apresentação (Preservação Rigorosa)

Todos os contratos JSON existentes serão **100% preservados**, sem nenhuma alteração nos campos de requisição ou resposta:

#### 1. `GET /hello`
* **Status HTTP:** `200 OK`
* **Body:** `"Hello World 🚀"` (texto puro)

#### 2. `POST /imoveis`
* **Status HTTP:** `200 OK` (preservando o comportamento atual do controller)
* **Payload Entrada (CreateImovelRequest):**
  ```json
  {
    "titulo": "Apartamento no Centro",
    "descricao": "Apartamento bem localizado",
    "cidade": "São Bernardo do Campo",
    "bairro": "Centro",
    "endereco": "Rua das Flores, 100",
    "valor": 450000.0,
    "tipoNegociacao": "VENDA",
    "tipoImovel": "APARTAMENTO",
    "quartos": 2,
    "banheiros": 1,
    "vagasGaragem": 1,
    "areaM2": 65.5
  }
  ```
* **Payload Saída (ImovelResponse):**
  ```json
  {
    "id": 1,
    "titulo": "Apartamento no Centro",
    "descricao": "Apartamento bem localizado",
    "cidade": "São Bernardo do Campo",
    "bairro": "Centro",
    "endereco": "Rua das Flores, 100",
    "valor": 450000.0,
    "tipoNegociacao": "VENDA",
    "tipoImovel": "APARTAMENTO",
    "quartos": 2,
    "banheiros": 1,
    "vagasGaragem": 1,
    "areaM2": 65.5
  }
  ```

#### 3. `GET /imoveis`
* **Query Params Opcionais:** `valorMin` (Double), `valorMax` (Double), `cidade` (String)
* **Status HTTP:** `200 OK`
* **Payload Saída (List<ImovelResponse>):**
  ```json
  [
    {
      "id": 1,
      "titulo": "Apartamento no Centro",
      "descricao": "Apartamento bem localizado",
      "cidade": "São Bernardo do Campo",
      "bairro": "Centro",
      "endereco": "Rua das Flores, 100",
      "valor": 450000.0,
      "tipoNegociacao": "VENDA",
      "tipoImovel": "APARTAMENTO",
      "quartos": 2,
      "banheiros": 1,
      "vagasGaragem": 1,
      "areaM2": 65.5
    }
  ]
  ```

#### 4. `GET /imoveis/{id}`
* **Status HTTP:** `200 OK` (se encontrado) ou `404 Not Found` (se não encontrado)
* **Payload Saída Sucesso (ImovelResponse):** Mesmo formato do item 2.
* **Payload Saída Erro 404 (SimpleErrorResponse):**
  ```json
  {
    "status": 404,
    "message": "Imóvel não encontrado"
  }
  ```

#### 5. `PUT /imoveis/{id}`
* **Status HTTP:** `200 OK`
* **Payload Entrada (CreateImovelRequest):** Mesmo formato do POST.
* **Payload Saída (ImovelResponse):** Dados atualizados do imóvel.

#### 6. `DELETE /imoveis/{id}`
* **Status HTTP:** `200 OK`
* **Body:** vazio

---

### 5.2 Contratos Técnicos e Escopo Estrutural

1. **Camada `domain`:**
   * `com.marcos.imobflow.domain.model.Imovel`: POJO Java puro sem nenhuma dependência externa.
   * `com.marcos.imobflow.domain.repository.ImovelRepository`: Interface da porta de saída sem o método obsoleto `listarPorFaixaDeValor`.
2. **Camada `application`:**
   * `com.marcos.imobflow.application.dto.ImovelResponse`: DTO dedicado de saída, substituindo o vazamento de `Imovel` e alinhando semanticamente a resposta de criação, listagem, busca e atualização.
   * `CreateImovelRequest` e `ImovelFiltro`: Preservados como POJOs conforme diretriz de evitar refatoração prematura de records apenas por estética.
3. **Camada `adapters.in`:**
   * Mover `HelloController` para `com.marcos.imobflow.adapters.in.controller`.
   * Atualizar `ImovelController` para retornar `ImovelResponse`.
4. **Camada `adapters.out`:**
   * Criar pacote `com.marcos.imobflow.adapters.out.repository`.
   * Criar `ImovelEntity` anotada com `@Entity`, `@Table(name = "imoveis")`, `@Id`, etc.
   * Criar `ImovelEntityMapper` para conversões `toDomain(ImovelEntity)` e `toEntity(Imovel)`.
   * Mover `MySqlImovelRepository`, `JpaImovelRepository` e `ImovelSpecification` para `adapters.out.repository`.
   * Excluir com segurança `InMemoryImovelRepository` e descontinuar o pacote `infrastructure`.
5. **Estratégia de Execução em Etapas Atômicas:**
   * **Fase 1:** Implementar teste real de integração em `MySqlImovelRepositoryIntegrationTest` com Testcontainers.
   * **Fase 2:** Mover `HelloController` para `adapters.in.controller` e validar suíte.
   * **Fase 3:** Remover código morto (`InMemoryImovelRepository` e `listarPorFaixaDeValor`) e validar suíte.
   * **Fase 4:** Introduzir `ImovelResponse` no controller e nos use cases de consulta/atualização e validar suíte.
   * **Fase 5:** Migrar persistência para `adapters.out.repository`, criar `ImovelEntity` + Mapper, isolar `Imovel` no domínio e validar suíte.

---

## 6. Plano Técnico de Implementação

### Fase 1: Rede de Segurança com Teste Real de Integração (Testcontainers MySQL)
* **Objetivo:** Preencher `MySqlImovelRepositoryIntegrationTest` com testes reais de ciclo de vida (salvar, buscar por ID, atualizar, deletar e filtrar por cidade/valor) antes da refatoração JPA.
* **Classes Envolvidas:** `src/test/java/com/marcos/imobflow/infrastructure/repository/MySqlImovelRepositoryIntegrationTest.java`.
* **Configuração:** `@DataJpaTest`, `@Testcontainers`, `@ServiceConnection`, `@Import(MySqlImovelRepository.class)`.

### Fase 2: Reposicionamento do `HelloController` para `adapters.in.controller`
* **Objetivo:** Mover `HelloController` da raiz de `adapters.in` para o subpacote de controladores `com.marcos.imobflow.adapters.in.controller`.
* **Classes Envolvidas:** Criar `adapters.in.controller.HelloController` e remover `adapters.in.HelloController`. Preservar rota `GET /hello`.

### Fase 3: Limpeza de Código Morto (`InMemoryImovelRepository` e `listarPorFaixaDeValor`)
* **Objetivo:** Remover artefato obsoleto `InMemoryImovelRepository` e método não utilizado `listarPorFaixaDeValor` de `ImovelRepository`, `MySqlImovelRepository` e `JpaImovelRepository`.
* **Classes Envolvidas:** Remover `InMemoryImovelRepository.java`; alterar `ImovelRepository.java`, `MySqlImovelRepository.java` e `JpaImovelRepository.java`.

### Fase 4: Proteção da Camada REST com DTO de Saída (`ImovelResponse`)
* **Objetivo:** Desacoplar a apresentação da entidade de domínio `Imovel`, retornando `ImovelResponse` em `GET /imoveis`, `GET /imoveis/{id}` e `PUT /imoveis/{id}`, preservando integralmente o formato JSON.
* **Classes Envolvidas:** Criar `ImovelResponse.java`; ajustar `ImovelController.java`, `BuscarImovelPorIdUseCase.java`, `ListarImoveisUseCase.java`, `AtualizarImovelPorIdUseCase.java` e respectivos testes.

### Fase 5: Isolamento Estrito do Domínio vs. JPA & Migração para `adapters.out.repository`
* **Objetivo:** Tornar `domain.model.Imovel` um POJO 100% puro; criar `ImovelEntity` JPA + `ImovelEntityMapper` em `adapters.out.repository`; migrar `MySqlImovelRepository`, `JpaImovelRepository`, `ImovelSpecification` e o teste de integração para `adapters.out.repository`; remover o pacote `infrastructure`.
* **Classes Envolvidas:** Criar `ImovelEntity.java`, `ImovelEntityMapper.java`, `JpaImovelRepository.java`, `MySqlImovelRepository.java`, `ImovelSpecification.java` em `adapters.out.repository`; limpar anotações JPA em `domain.model.Imovel.java`; excluir pacote `infrastructure.repository`.

---

## 7. Registro de Conclusão & Evidências

### 7.1 Resumo das Fases Implementadas

1. **Rede de segurança da persistência:** implementação de testes reais com MySQL 8 via Testcontainers para registrar o comportamento anterior à refatoração.
2. **Padronização do adaptador de entrada:** movimentação do `HelloController` para `adapters.in.controller`, preservando `GET /hello` e seu corpo de resposta.
3. **Limpeza de código obsoleto:** remoção de `InMemoryImovelRepository` e de `listarPorFaixaDeValor`, mantendo `listarPorFiltros` como operação oficial de consulta combinada.
4. **Proteção da fronteira REST:** criação do record imutável `ImovelResponse` e substituição da exposição direta de `Imovel` nos retornos da API, sem alteração do contrato JSON.
5. **Separação entre domínio e persistência:** remoção completa do JPA de `domain.model.Imovel`; criação de `ImovelEntity` e `ImovelEntityMapper`; migração dos repositórios e Specifications para `adapters.out.repository`.

### 7.2 Evidências Arquiteturais

* `domain.model.Imovel` não possui imports ou anotações de Spring, Hibernate ou Jakarta Persistence.
* `ImovelEntity` é a única entidade JPA mapeada para a tabela `imoveis`.
* O pacote legado `infrastructure.repository` foi descontinuado e não possui referências ativas.
* Os casos de uso dependem da porta `domain.repository.ImovelRepository`, enquanto `MySqlImovelRepository` fornece a implementação no adaptador de saída.
* O esquema MySQL foi preservado: tabela, 13 colunas, tipos, nulabilidade, chave primária e geração `AUTO_INCREMENT` permaneceram equivalentes ao DDL anterior.

### 7.3 Evidências de Testes Automatizados

* **Testes unitários:** casos de uso, conversão bidirecional dos 13 atributos pelo `ImovelEntityMapper` e comportamento do `MySqlImovelRepository` com Spring Data mockado.
* **Testes MockMvc:** contratos JSON dos endpoints de imóveis, validação HTTP, tratamento de erro e contrato público de `GET /hello` sob a configuração real de segurança.
* **Testes Testcontainers:** criação, geração de ID, busca, atualização sem registro adicional, exclusão, listagem, filtros individuais e combinados, limites inclusivos, ciclo completo dos 13 atributos e metadados reais do MySQL 8.
* **Suíte completa:** `./gradlew test --rerun-tasks --no-daemon --console=plain` executada com **38 testes**, **0 falhas**, **0 erros** e **0 ignorados**.
* **Integridade do diff:** `git diff --check` concluído sem erros.

### 7.4 Riscos Técnicos Restantes

* `spring.jpa.hibernate.ddl-auto=update` permanece ativo e deverá ser substituído por migrações versionadas em história posterior.
* Valores monetários continuam representados por `Double` para preservar o contrato atual; uma futura evolução pode avaliar `BigDecimal` para maior precisão decimal.
* A validação de integração depende de Docker disponível para execução do Testcontainers.

### 7.5 Estado do Portão 3

Implementação e evidências preparadas em 13/10/2026. O aceite final do desenvolvedor foi concedido em 13/10/2026, concluindo o Portão 3 e autorizando o fechamento documental e o commit da US-001. A entrega foi posteriormente integrada em `develop` pelo commit `a70c799` e está registrada no histórico de conclusões do backlog.

A dependência documental da US-001 em relação à US-000 é preservada. Os artefatos técnicos e de governança necessários estavam disponíveis durante a execução, embora o fechamento administrativo do Portão 3 da US-000 permaneça pendente; essa pendência não deve ser interpretada como conclusão implícita da US-000.
