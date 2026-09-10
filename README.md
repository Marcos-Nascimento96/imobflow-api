# 🏠 ImobFlow API

API backend para uma plataforma de gestão e busca imobiliária.

O **ImobFlow** nasceu como um projeto de evolução em desenvolvimento Back-end e está sendo construído de forma incremental, aplicando conceitos de engenharia de software em um domínio imobiliário real.

A proposta é evoluir desde o cadastro e busca de imóveis até uma plataforma capaz de conectar **proprietários, interessados, inquilinos e prestadores de serviços**.

> 📚 A visão completa do produto e o roadmap estão disponíveis em:
> [PROJECT_VISION.md](docs/PROJECT_VISION.md)

---

## 🚀 Funcionalidades atuais

- ✅ Cadastro de imóveis
- ✅ Listagem de imóveis
- ✅ Busca de imóvel por ID
- ✅ Atualização de imóvel
- ✅ Exclusão de imóvel
- ✅ Persistência com MySQL
- ✅ Validação de dados
- ✅ Tratamento global de exceções
- ✅ Filtro por faixa de preço
- ✅ Testes unitários
- ✅ Testes de integração
- ✅ Testcontainers
- ✅ Pipeline de CI
- ✅ Relatório de cobertura com JaCoCo

---

## 🔎 Exemplo de busca

O ImobFlow já permite filtrar imóveis por faixa de preço.

```http
GET /imoveis?valorMin=1000&valorMax=2500
```

Exemplo de evolução planejada:

```http
GET /imoveis?valorMax=2500&cidade=SaoBernardo&quartosMin=2
```

---

## 🛠️ Tecnologias

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle

### Testes e qualidade

- JUnit
- Mockito
- Testcontainers
- JaCoCo

### DevOps

- Git
- GitHub
- GitHub Actions
- Docker

---

## 🏗️ Arquitetura

O projeto está organizado buscando separação de responsabilidades e baixo acoplamento entre regras de negócio e infraestrutura.

```text
src/main/java/com/marcos/imobflow
│
├── domain
│   ├── model
│   └── repository
│
├── application
│   ├── dto
│   ├── exception
│   └── usecase
│
├── adapters
│   └── in
│       ├── controller
│       └── exception
│
├── infrastructure
│   └── repository
│
└── config
```

A estrutura utiliza conceitos inspirados em **Clean Architecture e Arquitetura Hexagonal**, mantendo as regras de negócio separadas das implementações de infraestrutura.

---

## 🧪 Testes

O projeto possui diferentes níveis de testes:

- Testes unitários dos casos de uso
- Testes de controller
- Testes de integração
- Testes de persistência com MySQL utilizando Testcontainers

Para executar os testes:

```bash
./gradlew test
```

Para executar os testes e gerar o relatório de cobertura:

```bash
./gradlew clean test jacocoTestReport
```

O relatório pode ser encontrado em:

```text
build/reports/jacoco/test/html/index.html
```

---

## 🔄 Integração Contínua

O projeto utiliza **GitHub Actions** para executar verificações automáticas durante o desenvolvimento.

Fluxo simplificado:

```text
Push / Pull Request
        ↓
     Checkout
        ↓
      Java 21
        ↓
       Gradle
        ↓
       Testes
        ↓
      JaCoCo
        ↓
       Build
```

Isso permite validar as alterações antes de integrá-las à branch de desenvolvimento.

---

## 🐳 Banco de dados

A aplicação utiliza **MySQL** para persistência dos dados.

Docker também faz parte do ambiente do projeto e o **Testcontainers** é utilizado nos testes de integração para criar um banco isolado durante a execução dos testes.

---

## 🗺️ Roadmap

### 🔨 Em desenvolvimento

- 🔎 Evolução dos filtros de imóveis
- 📄 Paginação
- ↕️ Ordenação
- 📚 Swagger / OpenAPI

### 📌 Próximas etapas

- 👤 Usuários e autenticação
- 🔐 Controle de acesso
- 📷 Fotos e vídeos dos imóveis
- 📍 Geolocalização
- 🗺️ Mapa interativo
- 📡 Busca de imóveis por raio
- 📅 Agendamento de visitas
- 📑 Contratos e documentos
- 🔧 Solicitações de manutenção
- 🧰 Prestadores e serviços

### 🔮 Visão futura

- 🤖 Assistente inteligente
- 🧠 Busca em linguagem natural
- 🏠 Recomendação de imóveis
- ☁️ Deploy em cloud

> O roadmap completo está disponível em:
> [PROJECT_VISION.md](docs/PROJECT_VISION.md)

---

## 💡 Visão do produto

A visão do ImobFlow é acompanhar diferentes momentos da jornada de um imóvel:

**PREPARAR → ANUNCIAR → ENCONTRAR → VISITAR → CONTRATAR → MORAR → MANTER → ADMINISTRAR**

A plataforma poderá futuramente reunir busca de imóveis, mapas, agendamento de visitas, contratos, manutenção, serviços e ferramentas para auxiliar proprietários, interessados e inquilinos.

---

## 🧰 Serviços e prestadores

Uma das evoluções planejadas para o ImobFlow é conectar proprietários e inquilinos a profissionais e empresas relacionados à preparação e manutenção dos imóveis.

Entre os serviços previstos estão:

- 🔍 Vistoria
- 🎨 Pintura
- ⚡ Elétrica
- 🚰 Hidráulica
- 🧹 Limpeza
- 🌱 Jardinagem e corte de grama
- 🔑 Chaveiro
- 🪚 Marcenaria
- 🔩 Serralheria
- 🪟 Vidraçaria
- 🐜 Dedetização
- 🧱 Reparos gerais
- 📑 Serviços documentais
- ⚖️ Serviços jurídicos
- 📊 Serviços contábeis

Uma das jornadas previstas é o processo de **Preparar para locação**:

```text
Vistoria
   ↓
Reparos
   ↓
Elétrica / Hidráulica
   ↓
Pintura
   ↓
Jardinagem
   ↓
Limpeza
   ↓
Documentação
   ↓
Fotos / Vídeos
   ↓
Anúncio
   ↓
Visitas
   ↓
Contrato
   ↓
Locação
```

---

## 🤖 Inteligência Artificial

Em uma etapa futura, o ImobFlow poderá utilizar inteligência artificial como interface para facilitar a busca e interação com a plataforma.

Por exemplo:

> "Tenho renda de R 5.000, quero um apartamento com 2 quartos, até R$ 1.800 e no máximo 5 km do meu trabalho."

A plataforma poderá interpretar a solicitação, transformar as preferências em critérios de busca e consultar os imóveis disponíveis.

A inteligência artificial será utilizada como uma camada de interação sobre as regras e dados reais da plataforma.

---

## 📍 Localização e mapas

A evolução do ImobFlow também prevê recursos baseados em localização.

O usuário poderá pesquisar imóveis próximos de:

- Localização atual
- Trabalho
- Faculdade
- Estação
- Bairro
- Outro endereço informado

Exemplos de raio de busca:

```text
500 m
1 km
3 km
5 km
10 km
```

A visualização poderá combinar:

- Mapa interativo
- Visão aérea/satélite
- Marcadores dos imóveis
- Informações de preço
- Fotos
- Distância
- Filtros
- Alternância entre lista e mapa

Por questões de privacidade e segurança, a localização pública do imóvel poderá ser apresentada de forma aproximada dependendo das regras da plataforma.

---

## 📚 Documentação

A documentação do projeto está organizada na pasta `docs`.

- [Visão completa do projeto e roadmap](docs/PROJECT_VISION.md)
- [Apresentação de negócio](docs/BUSINESS_PRESENTATION.md)

---

## 🌱 Evolução do projeto

O ImobFlow é desenvolvido de forma incremental.

Cada nova tecnologia ou funcionalidade deve resolver uma necessidade real do produto.

As prioridades durante a evolução são:

- Qualidade de código
- Regras de negócio
- Testes
- Segurança
- Documentação
- CI/CD
- Experiência do usuário
- Evolução da arquitetura

---

## 👨‍💻 Autor

**Marcos Nascimento**

Estudante de **Análise e Desenvolvimento de Sistemas**, com foco em desenvolvimento **Back-end Java e Engenharia de Software**.

🔗 GitHub: [Marcos-Nascimento96](https://github.com/Marcos-Nascimento96)