 # Desafio Dev Jr - Target Sistemas 
 Download do desafio: [desafio_dev.docx](https://github.com/user-attachments/files/23966170/desafio_dev.docx)


API desenvolvida para o desafio técnico da Target Sistemas, contendo três exercícios independentes implementados em uma única aplicação Spring Boot.
Mesmo com um enunciado simples, foram aplicadas boas práticas de arquitetura REST, validação, DTOs, relacionamento entre as tabelas e persistência com JPA/H2.

**Tecnologias Utilizadas:**
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Jakarta Validation

 **Estrutura Geral:**
<br> A aplicação foi organizada em três domínios:
- Comissões → LojaTargetController
- Estoque → LojaTargetEstoqueController
- Juros → LojaTargetJurosController

Todos os endpoints compartilham a raiz:
`/api/target `

📌**1) Comissão de Vendas**
<br> Regras de comissão (conforme anexo):
```
Valor < R$100,00 → 0%
≥ R$100 e < R$500 → 1%
≥ R$500 → 5%
```

OBS: Foram usados DTOs pois o exercício forneceu um JSON específico.

🔹 1.1 - Calcular comissão por venda
- Endpoint: **GET /api/target/comissaoPorVenda**

Request Body (modelo completo em anexo no exercício):
```
{
  "vendas": [
    { "vendedor": "João Silva", "valor": 1200.50 },
    { "vendedor": "Maria", "valor": 80.00 }
  ]
}
```   

🔹 1.2 - Calcular comissão total por vendedor
- Endpoint: **GET /api/target/comissaoPorVendedor?vendedor=João Silva**
<p> A busca é feita pelo nome do vendedor, basta editar no nome do final da URL - se estiver usando Postman ou similares precisa passar o nome no Params:
<img width="795" height="253" alt="image" src="https://github.com/user-attachments/assets/fedaa5a5-92e3-4715-9b6a-fb45ab6d6fec" />

Request Body (modelo completo em anexo no exercício):
```
{
  "vendas": [
    { "vendedor": "João Silva", "valor": 1200.50 },
    { "vendedor": "Maria", "valor": 80.00 }
  ]
}
``` 

🔹 1.3 - Salvar vendas no banco (opcional)
- Endpoint: **POST /api/target/salvarVendas**

Salva as vendas no H2 para testes.

📌**2) Controle de Estoque**
<br>Foi criado um fluxo completo com:
- Entidade Estoque
- Entidade MovimentacaoEstoque
- Relacionamento OneToMany
- Histórico de movimentações

O exercício era aberto, então implementei banco + validações.

🔹 **2.1 - Salvar estoque inicial** - Necessário para realizar as movimentações.
- Endpoint: **POST /api/target/salvarEstoque**

**Request Body (modelo completo em anexo no exercício):**
```
{
  "estoque":[
  {"codigoProduto": 101, "descricaoProduto": "Caneta Azul", "estoque": 150},
  {"codigoProduto": 102, "descricaoProduto": "Caderno Universitário", "estoque": 75},
  {"codigoProduto": 105, "descricaoProduto": "Marcador de Texto Amarelo", "estoque": 90}
  ]
}
```
🔹 2.2 - Movimentar estoque (entrada ou saída)
- Endpoint: **GET /api/target/estoque**
<br>Query Params:

Param |	Tipo | Descrição
------|------|---------
codigoProduto |	Long	| Código do produto
quantidade |	Integer	| Quantidade a movimentar
descricao	| Enum	| ENTRADA ou SAIDA

Exemplo: GET /api/target/estoque?codigoProduto=1&quantidade=5&descricao=SAIDA
<br> ou via Postman:
<img width="880" height="322" alt="image" src="https://github.com/user-attachments/assets/e24a6a33-a268-4a96-86c6-16ea32f5b195" />

💰**3) Cálculo de Juros**
<br> Cálculo de juros (conforme exercício em anexo (2,5% ao dia)

Sem persistência, conforme o enunciado.

🔹 3.1 - Calcular juros
- Endpoint: **GET /api/target/juros**

Request Body (modelo):
```
{
  "vencimento": "2025-12-01",
  "valor": 150.50
}
```

Retorno inclui dias de atraso, juros calculado e valor final.

🗄️ **Banco de Dados (H2)**
<br>Console disponível em:
<http://localhost:8080/h2>

🧪**Como Rodar**
<br>mvn spring-boot:run
<br>ou execute a classe principal no STS/IntelliJ.
