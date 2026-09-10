# 🏠 ImobFlow — Visão do Projeto

## 1. Visão Geral

O **ImobFlow** é uma plataforma imobiliária em desenvolvimento que busca organizar diferentes etapas da jornada de um imóvel em uma única experiência.

O projeto começou como uma API Back-end para cadastro e gerenciamento de imóveis e está sendo desenvolvido de forma incremental, evoluindo conforme novas necessidades do produto são identificadas.

A visão de longo prazo é conectar:

- Proprietários
- Interessados em imóveis
- Inquilinos
- Prestadores de serviços
- Profissionais especializados

A plataforma pretende acompanhar o imóvel desde sua preparação para locação ou anúncio até visitas, contratação, manutenção e administração.

---

# 2. Objetivos

O ImobFlow tem como principais objetivos:

- Facilitar o cadastro e gerenciamento de imóveis
- Melhorar a experiência de busca por imóveis
- Permitir buscas baseadas em localização
- Facilitar o agendamento de visitas
- Organizar contratos e documentos
- Centralizar informações da locação
- Facilitar solicitações de manutenção
- Conectar usuários a prestadores de serviços
- Utilizar inteligência artificial para simplificar a interação com a plataforma

A proposta não é apenas criar um catálogo de imóveis, mas construir gradualmente uma plataforma para o **ciclo de vida do imóvel**.

---

# 3. Perfis de Usuário

Inicialmente, a plataforma considera três principais perfis.

## 👤 Interessado

Usuário que está procurando um imóvel.

Poderá:

- Pesquisar imóveis
- Aplicar filtros
- Visualizar fotos
- Assistir a vídeos
- Visualizar imóveis no mapa
- Pesquisar por distância
- Comparar imóveis
- Consultar informações da região
- Agendar visitas
- Demonstrar interesse
- Entrar em contato com o responsável pelo imóvel

No futuro, poderá utilizar um assistente inteligente para realizar buscas utilizando linguagem natural.

---

## 🏠 Proprietário

Usuário responsável por um ou mais imóveis.

Poderá:

- Cadastrar imóveis
- Atualizar informações
- Adicionar fotos
- Adicionar vídeos
- Gerenciar disponibilidade
- Receber solicitações de visitas
- Organizar documentos
- Gerenciar contratos
- Acompanhar inquilinos
- Registrar despesas relacionadas ao imóvel
- Receber solicitações de manutenção
- Solicitar serviços de profissionais

O proprietário também poderá utilizar a plataforma para organizar o processo de preparação de um imóvel antes da locação.

---

## 🔑 Inquilino

Usuário que possui uma locação ativa.

Poderá acessar uma área específica contendo informações relacionadas ao imóvel alugado.

Exemplos:

- Contrato
- Download de documentos
- Datas de vencimento
- Histórico de pagamentos
- Débitos
- Regras da locação
- Responsabilidades
- Informações importantes do imóvel
- Solicitações de manutenção
- Histórico de solicitações

Também poderão existir checklists relacionados ao início da locação.

Exemplo:

```text
Assinatura do contrato
        ↓
Vistoria de entrada
        ↓
Transferência de energia
        ↓
Transferência de água
        ↓
Entrega das chaves
        ↓
Início da locação
```

---

# 4. Busca e Filtros

A busca de imóveis será uma das funcionalidades centrais do ImobFlow.

Atualmente, o projeto já possui filtro por faixa de preço.

Exemplo:

```http
GET /imoveis?valorMin=1000&valorMax=2500
```

A evolução dos filtros poderá incluir:

- Cidade
- Bairro
- Tipo de imóvel
- Tipo de negociação
- Quantidade mínima de quartos
- Quantidade de banheiros
- Vagas de garagem
- Área mínima
- Valor mínimo
- Valor máximo

Os filtros poderão ser combinados.

Exemplo:

```http
GET /imoveis?cidade=SaoBernardo&quartosMin=2&valorMax=2500
```

---

# 5. Localização e Mapas

A localização terá papel importante na experiência de busca.

Ao cadastrar um imóvel, o endereço poderá ser convertido em coordenadas geográficas.

Exemplo:

```text
Endereço
   ↓
Geocodificação
   ↓
Latitude / Longitude
   ↓
Banco de dados
```

Essas coordenadas poderão ser utilizadas para:

- Mostrar imóveis no mapa
- Calcular distância
- Realizar busca por raio
- Encontrar imóveis próximos de pontos importantes

A implementação poderá utilizar serviços especializados de mapas e geolocalização.

---

# 6. Busca por Raio

O usuário poderá buscar imóveis próximos de determinada localização.

Exemplos de raio:

```text
500 m
1 km
3 km
5 km
10 km
```

A localização utilizada como centro da busca poderá ser:

- Localização atual
- Trabalho
- Faculdade
- Estação
- Bairro
- Endereço informado pelo usuário
- Outro ponto de interesse

Exemplo:

```text
Trabalho do usuário
        ↓
Raio de 5 km
        ↓
Imóveis disponíveis
        ↓
Filtros adicionais
        ↓
Resultado
```

A busca por raio poderá ser combinada com outros filtros.

Exemplo:

```text
Até 5 km do trabalho
+
Até R$ 2.500
+
2 ou mais quartos
+
Apartamento
```

---

# 7. Perto de Mim

A plataforma poderá oferecer uma funcionalidade de busca baseada na localização atual do usuário.

Exemplo:

```text
📍 Minha localização
        ↓
Imóveis próximos
```

O usuário poderá definir o raio da pesquisa e combinar a localização com filtros tradicionais.

---

# 8. Visualização no Mapa

Os imóveis poderão ser apresentados em um mapa interativo.

Cada imóvel poderá possuir um marcador contendo informações resumidas.

Exemplo:

```text
R$ 2.300
Apartamento
2 quartos
65 m²
1,8 km
```

Ao selecionar o marcador, poderá ser exibido um card contendo:

- Foto
- Valor
- Tipo de imóvel
- Quartos
- Área
- Bairro
- Distância
- Botão para visualizar detalhes

---

## 8.1 Mapa interativo e visão aérea

O usuário poderá alternar entre diferentes formas de visualização.

Exemplos:

- Mapa tradicional
- Visão aérea
- Visão por satélite

Isso poderá ajudar o interessado a compreender melhor a região onde o imóvel está localizado.

---

## 8.2 Mapa integrado aos filtros

Os filtros da busca poderão atualizar os imóveis apresentados no mapa.

Exemplo:

```text
Mapa
+
Até R$ 2.000
+
2 quartos
+
Raio de 3 km
```

O usuário também poderá alternar entre:

```text
Lista | Mapa
```

No futuro, poderá existir atualização dos resultados conforme o usuário movimenta o mapa.

---

## 8.3 Privacidade da localização

O endereço armazenado no sistema não precisa ser necessariamente o mesmo endereço apresentado publicamente.

Para aumentar a privacidade e segurança, a plataforma poderá apresentar uma localização aproximada.

Exemplo:

```text
Localização real
        ↓
Banco de dados
        ↓
Localização aproximada
        ↓
Mapa público
```

O endereço completo poderá ser disponibilizado conforme as regras de negócio da plataforma.

Por exemplo, após a confirmação de uma visita.

---

# 9. Agendamento de Visitas

O interessado poderá solicitar visitas diretamente pela plataforma.

Fluxo inicial:

```text
Escolher imóvel
        ↓
Consultar disponibilidade
        ↓
Selecionar data
        ↓
Selecionar horário
        ↓
Solicitar visita
        ↓
Confirmação
```

Evoluções futuras poderão incluir:

- Cancelamento
- Reagendamento
- Lembretes
- Notificações
- Histórico de visitas
- Integração com calendário

---

# 10. Fotos e Vídeos

Os imóveis poderão possuir conteúdo visual para ajudar o interessado antes de realizar uma visita.

Poderão existir:

- Fotos
- Vídeos
- Galeria
- Imagem principal
- Tour virtual futuramente

O objetivo é permitir que o interessado conheça melhor o imóvel antes de agendar uma visita presencial.

---

# 11. Manutenções

O ImobFlow poderá centralizar solicitações de manutenção relacionadas aos imóveis.

Exemplo de fluxo:

```text
Inquilino
   ↓
Abrir solicitação
   ↓
Descrição do problema
   ↓
Fotos
   ↓
Proprietário recebe
   ↓
Manutenção
   ↓
Atualização de status
   ↓
Conclusão
```

Possíveis status:

```text
ABERTA
EM_ANALISE
AGUARDANDO_PRESTADOR
EM_ANDAMENTO
CONCLUIDA
CANCELADA
```

O sistema poderá manter o histórico das manutenções realizadas em cada imóvel.

---

# 12. Contratos e Documentos

A plataforma poderá centralizar documentos relacionados aos imóveis e às locações.

Exemplos:

- Contrato de locação
- Laudo de vistoria
- Documentos do imóvel
- Recibos
- Documentos do proprietário
- Documentos do inquilino
- Comprovantes relacionados à locação

O objetivo é facilitar a organização e consulta dessas informações.

---

# 13. Serviços e Prestadores

O ImobFlow poderá conectar proprietários e inquilinos a profissionais e empresas relacionados à preparação e manutenção dos imóveis.

## Serviços para preparação do imóvel

Exemplos:

- Vistoria
- Pintura
- Limpeza
- Limpeza pós-obra
- Jardinagem
- Corte de grama
- Pequenos reparos
- Marcenaria
- Serralheria
- Vidraçaria
- Chaveiro
- Dedetização
- Limpeza de caixa d'água
- Instalações
- Montagem
- Retirada de entulho

---

## Serviços de manutenção

Exemplos:

- Eletricista
- Encanador
- Pintor
- Pedreiro
- Técnico de ar-condicionado
- Manutenção hidráulica
- Manutenção elétrica
- Telhado
- Portões
- Reparos gerais

---

## Serviços de vistoria

Poderão existir profissionais especializados em:

- Vistoria de entrada
- Vistoria de saída
- Registro fotográfico
- Relatório de conservação
- Identificação de danos
- Comparação entre entrada e saída

---

## Serviços especializados

Também poderão existir profissionais relacionados a:

- Serviços jurídicos
- Serviços contábeis
- Documentação imobiliária
- Regularizações

Quando uma situação exigir conhecimento jurídico, contábil ou tributário especializado, a plataforma deverá direcionar o usuário a profissionais adequados.

---

## Perfil do prestador

Prestadores poderão possuir perfis contendo:

- Categoria
- Região atendida
- Avaliações
- Fotos de trabalhos
- Disponibilidade
- Contato
- Histórico
- Solicitações de orçamento

---

## Fluxo de contratação de serviço

Uma evolução futura poderá permitir:

```text
Selecionar imóvel
        ↓
Selecionar serviço
        ↓
Descrever necessidade
        ↓
Adicionar fotos
        ↓
Consultar prestadores
        ↓
Solicitar orçamento
        ↓
Escolher profissional
        ↓
Agendar
        ↓
Acompanhar serviço
        ↓
Concluir
```

---

## Preparar para locação

Uma das jornadas importantes da plataforma poderá ser o processo **Preparar para locação**.

Exemplo:

```text
Vistoria
   ↓
Reparos necessários
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

Essa jornada permite que o proprietário acompanhe o que ainda precisa ser realizado antes de disponibilizar o imóvel.

---

# 14. Organização Financeira e Tributária

O proprietário poderá utilizar a plataforma para organizar informações financeiras relacionadas aos imóveis.

Exemplos:

- Recebimentos
- Despesas
- Manutenções
- Serviços contratados
- Histórico financeiro
- Documentos
- Comprovantes

Essas informações poderão ajudar na organização financeira e na preparação de informações necessárias para processos contábeis e tributários.

O ImobFlow não substitui profissionais especializados em contabilidade ou tributação.

---

# 15. Assistente Inteligente

Em uma etapa futura, o ImobFlow poderá possuir um assistente inteligente.

A inteligência artificial será utilizada como uma camada de interação sobre as regras e dados reais da plataforma.

Exemplo:

> "Tenho renda de R$ 5.000, quero um apartamento de 2 quartos em São Bernardo, até R$ 1.800 e no máximo 5 km do meu trabalho."

A aplicação poderá interpretar:

```text
Renda: R$ 5.000
Tipo: Apartamento
Quartos: 2
Cidade: São Bernardo
Valor máximo: R$ 1.800
Distância máxima: 5 km
Referência: Trabalho
```

Depois:

```text
Mensagem do usuário
        ↓
Interpretação
        ↓
Filtros estruturados
        ↓
Localização
        ↓
API ImobFlow
        ↓
Imóveis disponíveis
        ↓
Recomendações
```

A IA não será responsável por inventar informações sobre os imóveis.

Ela funcionará como interface para acessar e interpretar informações existentes na plataforma.

---

# 16. Recomendação Baseada em Perfil

O sistema poderá futuramente auxiliar o usuário a encontrar imóveis mais adequados ao seu perfil.

Possíveis informações consideradas:

- Faixa de preço
- Renda informada
- Localização
- Distância
- Quantidade de quartos
- Tipo de imóvel
- Preferências
- Histórico de buscas

As regras de recomendação deverão ser transparentes e configuráveis.

O objetivo será auxiliar na busca, e não tomar decisões pelo usuário.

---

# 17. Tecnologias

Tecnologias utilizadas atualmente no desenvolvimento:

## Backend

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle

## Testes

- JUnit
- Mockito
- Testcontainers
- JaCoCo

## Infraestrutura e desenvolvimento

- Docker
- Git
- GitHub
- GitHub Actions

A arquitetura utiliza conceitos inspirados em:

- Clean Architecture
- Arquitetura Hexagonal
- Separação de responsabilidades

Novas tecnologias serão adicionadas conforme necessidades reais do produto.

---

# 18. Estado Atual

Funcionalidades atualmente implementadas:

- [x] Cadastro de imóveis
- [x] Listagem de imóveis
- [x] Busca por ID
- [x] Atualização
- [x] Exclusão
- [x] Persistência MySQL
- [x] Spring Data JPA
- [x] Validação
- [x] Tratamento global de exceções
- [x] Testes unitários
- [x] Testes de controller
- [x] Testes de integração
- [x] Testcontainers
- [x] Docker
- [x] GitHub Actions
- [x] JaCoCo
- [x] Filtro por faixa de preço

O projeto continuará sendo desenvolvido incrementalmente.

---

# 19. Roadmap

## Fase 1 — Fundação Back-end

- [x] Estrutura inicial
- [x] CRUD de imóveis
- [x] Banco MySQL
- [x] JPA
- [x] Validações
- [x] Tratamento de erros
- [x] Testes
- [x] CI/CD
- [x] Cobertura com JaCoCo

---

## Fase 2 — Busca

- [x] Filtro por faixa de preço
- [ ] Filtros combinados
- [ ] Cidade
- [ ] Bairro
- [ ] Tipo de imóvel
- [ ] Tipo de negociação
- [ ] Quartos
- [ ] Paginação
- [ ] Ordenação
- [ ] Swagger / OpenAPI

---

## Fase 3 — Usuários e Segurança

- [ ] Cadastro de usuários
- [ ] Autenticação
- [ ] Autorização
- [ ] Proprietário
- [ ] Interessado
- [ ] Inquilino
- [ ] Controle de acesso
- [ ] JWT

---

## Fase 4 — Conteúdo dos Imóveis

- [ ] Upload de fotos
- [ ] Galeria
- [ ] Vídeos
- [ ] Organização do conteúdo visual

---

## Fase 5 — Localização e Mapas

- [ ] Geocodificação
- [ ] Latitude e longitude
- [ ] Mapa interativo
- [ ] Visão aérea
- [ ] Busca por raio
- [ ] Perto de mim
- [ ] Localização aproximada
- [ ] Busca próxima de pontos de interesse

---

## Fase 6 — Visitas

- [ ] Disponibilidade
- [ ] Agendamento
- [ ] Confirmação
- [ ] Cancelamento
- [ ] Reagendamento
- [ ] Histórico
- [ ] Notificações

---

## Fase 7 — Gestão da Locação

- [ ] Inquilinos
- [ ] Contratos
- [ ] Documentos
- [ ] Pagamentos
- [ ] Débitos
- [ ] Vistorias
- [ ] Manutenções

---

## Fase 8 — Front-end

- [ ] Interface web
- [ ] Busca visual
- [ ] Página do imóvel
- [ ] Área do proprietário
- [ ] Área do interessado
- [ ] Área do inquilino
- [ ] Mapa
- [ ] Agendamento

---

## Fase 9 — Inteligência Artificial

- [ ] Assistente
- [ ] Busca em linguagem natural
- [ ] Interpretação de preferências
- [ ] Recomendações
- [ ] Integração com localização

---

## Fase 10 — Serviços e Plataforma

- [ ] Cadastro de prestadores
- [ ] Categorias de serviços
- [ ] Avaliações
- [ ] Solicitação de orçamento
- [ ] Agendamento de serviços
- [ ] Preparar para locação
- [ ] Histórico de serviços
- [ ] Organização financeira
- [ ] Deploy em cloud

---

# 20. Princípio de Evolução

O ImobFlow será desenvolvido de forma incremental.

O objetivo não é implementar todas as funcionalidades de uma vez.

Cada nova tecnologia ou funcionalidade deverá resolver uma necessidade real do produto.

As prioridades durante a evolução serão:

1. Regras de negócio
2. Qualidade de código
3. Testes
4. Segurança
5. Documentação
6. CI/CD
7. Experiência do usuário
8. Evolução da arquitetura

A complexidade da arquitetura deverá crescer junto com a necessidade do sistema.

---

# 🌱 Visão de Longo Prazo

O objetivo do ImobFlow é evoluir de uma API de imóveis para uma plataforma capaz de acompanhar diferentes momentos da jornada imobiliária.

```text
PREPARAR
   ↓
ANUNCIAR
   ↓
ENCONTRAR
   ↓
VISITAR
   ↓
CONTRATAR
   ↓
MORAR
   ↓
MANTER
   ↓
ADMINISTRAR
```

Conectando:

```text
PROPRIETÁRIO
      ↕
INTERESSADO
      ↕
INQUILINO
      ↕
PRESTADORES E PROFISSIONAIS
```

**ImobFlow — a jornada do imóvel em um só lugar.**