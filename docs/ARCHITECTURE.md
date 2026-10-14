# 🏗️ Arquitetura do ImobFlow API

Este documento detalha o desenho arquitetural do **ImobFlow API**, suas camadas, regras de dependência e padrões técnicos de implementação.

---

## 🎯 1. Princípios Arquiteturais

A arquitetura do ImobFlow é inspirada em **Clean Architecture** e **Arquitetura Hexagonal (Ports and Adapters)**, com foco em:
* **Independência de Frameworks:** As regras de negócio não dependem do Spring Boot, de bancos de dados ou de bibliotecas web.
* **Testabilidade:** Cada camada pode ser testada isoladamente com testes unitários rápidos e sem dependências externas.
* **Inversão de Dependência:** O domínio define as interfaces (portas) de que necessita; os adaptadores externos implementam essas interfaces.
* **Simplicidade Pragmática (KISS):** Evitar abstrações prematuras ou camadas vazias que não entreguem clareza de código.

---

## 🧩 2. Estrutura de Pacotes e Responsabilidades

```text
com.marcos.imobflow
│
├── domain                            # 🟢 NÚCLEO DO SISTEMA (Isolado)
│   ├── model                         # Entidades e Value Objects do negócio
│   └── repository                    # Portas de Saída (Interfaces de Repositório)
│
├── application                       # 🟡 CASOS DE USO E ORQUESTRAÇÃO
│   ├── dto                           # Objetos de transferência de dados (Requests / Responses)
│   ├── exception                     # Exceções de negócio (ex: ImovelNotFoundException)
│   └── usecase                       # Casos de uso especializados (um por ação do usuário)
│
├── adapters                          # 🔵 ADAPTADORES (Fronteira com o mundo externo)
│   ├── in                            # Adaptadores Primários (Driving / Conduzem o sistema)
│   │   ├── controller                # Controladores REST HTTP (Spring MVC)
│   │   └── exception                 # Tratamento global de exceções HTTP (ControllerAdvice)
│   └── out                           # Adaptadores Secundários (Driven / Conduzidos pelo sistema)
│       └── repository                # Implementações concretas de acesso ao banco (JPA, MySQL)
│
└── config                            # ⚪ CONFIGURAÇÕES TÉCNICAS
    ├── SecurityConfig.java           # Segurança e autorização de endpoints
    └── OpenApiConfig.java            # Configurações do Swagger / OpenAPI
```

---

## 📐 3. Regra de Dependência (Sentido das Setas)

Todas as dependências de código apontam para o centro (em direção ao domínio):

```text
[ adapters.in ]  ──(chama)──►  [ application.usecase ]  ──(opera)──►  [ domain.model ]
                                         │                                    ▲
                                      (usa)                                   │
                                         ▼                                    │
                               [ domain.repository ]  ◄──(implementa)──  [ adapters.out ]
```

* **`domain`:** Não importa nada de `application`, `adapters`, `infrastructure` ou bibliotecas do Spring.
* **`application`:** Depende do `domain` e de elementos internos da própria camada. Conhece as portas, mas não conhece implementações concretas (ex: não sabe se os dados vêm de MySQL ou memória). No baseline atual, são permitidos exclusivamente `org.springframework.stereotype.Service` nos casos de uso e `jakarta.validation` nos DTOs de entrada. Essas permissões são explícitas e restritas: não autorizam dependências da aplicação em Spring Web, Spring Data, Spring Security, Jakarta Persistence, `adapters` ou `config`. Qualquer ampliação exige decisão arquitetural própria.
* **`adapters.in`:** Depende apenas de `application` (use cases e DTOs).
* **`adapters.out`:** Implementa as interfaces de `domain.repository` e utiliza infraestrutura técnica (Spring Data JPA, Hibernate, Criteria API).

---

## 🧪 4. Estratégia de Testes Automatizados

O projeto adota a Pirâmide de Testes balanceada:

1. **Testes Unitários de Casos de Uso (`src/test/.../application/usecase`):**
   * Foco: Lógica de negócio, validações, cálculos e orquestração.
   * Ferramentas: JUnit 5 + Mockito (isolando as portas de repositório via `mock()`).
   * Execução instantânea (em memória).

2. **Testes de Slice Web de Controllers (`src/test/.../adapters/in/controller`):**
   * Foco: Mapeamento de rotas HTTP, conversão de JSON, códigos de status (200, 400, 404) e Bean Validation.
   * Ferramentas: `@WebMvcTest` + `MockMvc` + `@MockitoBean`.

3. **Testes de Integração de Repositório (`src/test/.../adapters/out/repository`):**
   * Foco: Persistência real, consultas dinâmicas (`Specifications`), mapeamentos relacionais e queries nativas.
   * Ferramentas: `@DataJpaTest` + **Testcontainers** (MySQL oficial em container Docker).

---

## 📌 5. Boas Práticas e Convenções de Código

* **DTOs Imutáveis:** Preferir Java 21 `record` para DTOs de Request e Response sempre que a deserialização permitir.
* **Nomenclatura Clara:**
  * Casos de Uso: Verbo no infinitivo + Ação + `UseCase` (ex: `CadastrarImovelUseCase`).
  * Repositórios de Domínio: Entidade + `Repository` (ex: `ImovelRepository`).
  * Implementações de Repositório: Tecnologia + Entidade + `Repository` (ex: `MySqlImovelRepository`).
* **Tratamento de Exceções:** Lançar exceções de negócio na camada de aplicação e capturá-las exclusivamente no `GlobalExceptionHandler` (`adapters.in.exception`) para traduzir em respostas HTTP sem vazar stacktraces.
