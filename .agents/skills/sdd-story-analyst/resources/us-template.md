# 📖 US-XXX: [Título da História]

## 1. Metadados & Rastreabilidade
* **ID:** US-XXX
* **Épico:** Épico X — [Nome do Épico]
* **Status:** 🟡 `EM_ESPECIFICACAO`
* **Dependências:** [Ex: US-000 ou Nenhuma]
* **Branch Git Sugerida:** `feature/US-XXX-[slug-da-historia]`

---

## 2. Descrição (User Story)
**Como** [persona: Proprietário / Interessado / Inquilino / Engenheiro]  
**Quero** [ação ou capacidade desejada]  
**Para que** [benefício real de negócio ou técnico gerado].

---

## 3. 📚 Conceitos Técnicos Estudados
* **[Conceito 1]:** [Breve explicação do foco de estudo].
* **[Conceito 2]:** [Breve explicação do foco de estudo].

---

## 4. Critérios de Aceite (BDD)

### Cenário 1: [Nome do Cenário Principal - Caminho Feliz]
* **Dado que** [contexto inicial / pré-condição],
* **Quando** [ação executada / requisição HTTP recebida],
* **Então** [resultado esperado / status HTTP / estado persistido].

### Cenário 2: [Nome do Cenário Alternativo / Validação]
* **Dado que** [contexto com dados inválidos ou não autorizados],
* **Quando** [ação executada],
* **Então** [erro tratado / status HTTP esperado / mensagem de validação].

---

## 5. Especificação Funcional & Contratos (SDD)
* **Endpoints REST Planejados:**
  * *(Caso a história seja puramente técnica, de refatoração ou infraestrutura sem interface HTTP, marque como "Não se aplica" e detalhe o contrato técnico abaixo)*
  * **Status do Endpoint:** `[Disponível / Não se aplica (História Técnica / Refatoração)]`
  * **Endpoint:** `[MÉTODO] /api/v1/[recurso]`
  * **Payload Entrada (Request):**
    ```json
    {}
    ```
  * **Payload Saída (Response):**
    ```json
    {}
    ```
* **Contratos Técnicos e Escopo Estrutural (se aplicável):**
  * *(Ex.: Tabelas de banco, migrações Flyway, contratos de interfaces/portas, reorganização de pacotes)*
* **Regras de Negócio e Restrições:**
  * [Regra 1]
  * [Regra 2]


---

## 6. Plano Técnico de Implementação
*(Preenchido na Etapa 3 pelo Arquiteto Hexagonal após aprovação do Portão 1)*

---

## 7. Registro de Conclusão & Evidências
*(Preenchido na Etapa 6 pelo Mentor/Dev após aprovação do Portão 3)*
