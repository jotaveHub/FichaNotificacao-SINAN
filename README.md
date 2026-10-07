# SINAN – API de Notificações (Ficha de Notificação/Conclusão)

Atividade prática de **Programação para a Web I**, com **Spring Boot**.

| | |
|---|---|
| **Instituição** | Instituto Federal de Educação, Ciência e Tecnologia da Paraíba (IFPB) – Campus Cajazeiras |
| **Curso** | Análise e Desenvolvimento de Sistemas |
| **Disciplina** | Programação para a Web I (4º período) |
| **Professor** | Renê Douglas Nobre de Morais |

## Integrantes

| Nome |
|---|
| João Victor Batista de Araújo Abrantes |

Atividade realizada individualmente.

## Sobre o projeto

API REST para o gerenciamento de casos notificados, baseada na [Ficha de Notificação/Conclusão](http://portalsinan.saude.gov.br/images/documentos/Agravos/NINDIV/Ficha_conclusao_v5.pdf) do SINAN (Ministério da Saúde) e nas respectivas [instruções de preenchimento](http://portalsinan.saude.gov.br/images/documentos/Agravos/NINDIV/Ficha_conclusao_v5_instr.pdf).

O projeto tem duas partes:

- **`backend-sinan`**: a API REST (Java e Spring Boot) com CRUD completo, validações, regras de negócio e erros no padrão Problem Detail.
- **`frontend-sinan`**: a interface web (HTML, CSS e JavaScript puros, sem frameworks) com uma página de consulta e uma de cadastro/alteração.

> **Armazenamento em memória.** O projeto **não usa banco de dados**. As notificações ficam em uma lista na memória da aplicação. Os dados permanecem disponíveis enquanto a aplicação estiver em execução e **são perdidos quando ela é reiniciada**.

## Tecnologias

- Java 21
- Spring Boot 4.1.1 (Spring Web MVC)
- Bean Validation (`spring-boot-starter-validation`)
- Maven (com o Maven Wrapper incluído, sem precisar instalar o Maven)
- HTML, CSS e JavaScript puros no front-end
- Padrão de erros **Problem Detail (RFC 9457)**

## Como executar

### Pré-requisitos

- **JDK 21** ou superior instalado (confira com `java -version`).
- Um navegador atual.
- Não é preciso instalar o Maven: o projeto traz o `mvnw`.

### 1. Obter o código

```bash
git clone https://github.com/jotaveHub/FichaNotificacao-SINAN.git
cd FichaNotificacao-SINAN
```

### 2. Executar o back-end

```bash
cd backend-sinan
./mvnw spring-boot:run
```

No Windows (CMD ou PowerShell), use `mvnw.cmd spring-boot:run`. No Git Bash, `./mvnw spring-boot:run` funciona normalmente.

A API sobe em **http://localhost:8080**. Aguarde a mensagem `Started FichaNotificacaoSinanApplication` no console. Para encerrar, use `Ctrl + C`.

Também é possível executar a classe `FichaNotificacaoSinanApplication` direto pelo IntelliJ.

### 3. Executar o front-end

Com a API em execução, abra no navegador o arquivo:

```
frontend-sinan/html/index.html
```

Basta dar um duplo clique nele, ou abri-lo por uma extensão como o *Live Server* do VS Code. Não há nada para instalar nem compilar. A página de consulta é a `index.html`, e a de cadastro/alteração é a `cadastro.html`, acessível pelo botão **Nova notificação** ou pelo botão **Alterar** da tabela.

O front-end conversa com a API em `http://localhost:8080` (endereço definido em `frontend-sinan/js/api.js`). O back-end já libera o CORS para a rota `/notificacao`, o que permite abrir o front-end direto do arquivo.

### Dados de exemplo

Ao iniciar, a aplicação carrega 7 notificações de exemplo, para facilitar a demonstração dos filtros e da duplicidade:

| N.º | Agravo | Paciente | Data | Observação |
|---|---|---|---|---|
| 001 e 002 | Dengue | Maria da Silva | 12/03 e 14/03/2026 | 2 dias de diferença: **duplicadas** |
| 003 e 004 | Dengue | José Souza | 01/04 e 09/04/2026 | 8 dias de diferença: não são duplicadas |
| 005 e 006 | Hepatites virais | Carlos Pereira | 05/05 e 06/05/2026 | Sem nome da mãe: não entram na verificação |
| 007 | Zika | Maria Oliveira | 13/03/2026 | Classificação final: confirmado |

Para iniciar sem esses dados, altere em `backend-sinan/src/main/resources/application.properties`:

```properties
sinan.dados-exemplo=false
```

### Solução de problemas

| Sintoma | Causa provável |
|---|---|
| `Port 8080 was already in use` | Já há uma instância da aplicação (ou outro programa) usando a porta 8080. Encerre-a e execute de novo. |
| Tabela vazia e mensagem "Não foi possível comunicar com a API" | O back-end não está em execução. |
| Página sem estilo ou sem dados | Abra a `index.html` a partir da pasta `frontend-sinan/html/`, sem mover os arquivos. As pastas `css`, `js` e `img` precisam continuar ao lado da pasta `html`. |

## Estrutura do repositório

```
FichaNotificacao-SINAN/
├── backend-sinan/
│   ├── pom.xml
│   ├── mvnw / mvnw.cmd
│   └── src/main/java/br/edu/ifpb/fichanotificacaosinan/
│       ├── controller/    NotificacaoController
│       ├── service/       NotificacaoService, DetectorDuplicidade, FiltroNotificacao
│       ├── repository/    NotificacaoRepository (armazenamento em memória)
│       ├── model/         Notificacao, DadosResidencia
│       ├── validation/    anotações e validadores (RN02, RN03, códigos da ficha)
│       ├── exception/     GlobalExceptionHandler (Problem Detail)
│       ├── config/        CorsConfig, DadosExemplo
│       └── util/          Texto (normalização de textos)
└── frontend-sinan/
    ├── html/              index.html (consulta), cadastro.html (cadastro e alteração)
    ├── css/               style.css
    ├── js/                api.js, consulta.js, cadastro.js
    └── img/               logo.png
```

## Modelo de dados

A API trabalha com duas entidades:

- **`Notificacao`**: dados gerais (campos N.º a 7), dados do paciente (8 a 16), conclusão (31 a 33 e 40 a 43), local provável da fonte de infecção (34 a 39), investigador e observações.
- **`DadosResidencia`**: dados de residência (campos 17 a 30). É um objeto aninhado dentro da notificação, no atributo `dadosResidencia`.

Os campos de código seguem a ficha (por exemplo, gestante: 1 a 6 ou 9; raça/cor: 1 a 5 ou 9; zona: 1, 2, 3 ou 9).

## Endpoints

URL base: `http://localhost:8080`

| Verbo | URI | Descrição | Sucesso | Erros |
|---|---|---|---|---|
| `POST` | `/notificacao` | Cadastra uma notificação | `201 Created` (com o cabeçalho `Location`) | `400` |
| `GET` | `/notificacao` | Lista as notificações, com filtros opcionais | `200 OK` | `400` |
| `GET` | `/notificacao/{id}` | Consulta uma notificação pelo ID | `200 OK` | `404` |
| `PUT` | `/notificacao/{id}` | Atualiza uma notificação (substitui o registro inteiro) | `200 OK` | `400`, `404` |
| `DELETE` | `/notificacao/{id}` | Exclui uma notificação | `204 No Content` | `404` |

### Filtros da consulta (`GET /notificacao`)

Todos os parâmetros são **opcionais** e podem ser **combinados** (a notificação precisa atender a todos os filtros informados). Filtros não informados são ignorados.

| Parâmetro | Comparação | Exemplo |
|---|---|---|
| `numeroNotificacao` | contém o trecho | `?numeroNotificacao=001` |
| `agravo` | contém o trecho | `?agravo=dengue` |
| `nomePaciente` | contém o trecho | `?nomePaciente=maria` |
| `ufResidencia` | sigla exata | `?ufResidencia=PB` |
| `municipioResidencia` | contém o trecho | `?municipioResidencia=cajaz` |
| `classificacaoFinal` | código exato (1 ou 2) | `?classificacaoFinal=1` |
| `dataNotificacaoInicio` | a partir da data (`aaaa-mm-dd`) | `?dataNotificacaoInicio=2026-03-01` |
| `dataNotificacaoFim` | até a data (`aaaa-mm-dd`) | `?dataNotificacaoFim=2026-03-31` |
| `duplicadas` | `true` lista só as notificações duplicadas (RN01) | `?duplicadas=true` |

As buscas por texto **não diferenciam maiúsculas de minúsculas nem acentos** (`jose` encontra `José`). Se nenhuma notificação for encontrada, a resposta é `200` com uma lista vazia (`[]`).

Exemplos:

```
GET /notificacao
GET /notificacao?nomePaciente=Maria
GET /notificacao?nomePaciente=Maria&ufResidencia=PB
GET /notificacao?duplicadas=true&dataNotificacaoInicio=2026-03-01
```

## Exemplos de requisições

### Cadastrar (`POST`)

```bash
curl -i -X POST http://localhost:8080/notificacao \
  -H "Content-Type: application/json" \
  -d '{
    "numeroNotificacao": "009",
    "agravo": "Dengue",
    "codigoCid10": "A90",
    "dataNotificacao": "2026-03-12",
    "ufNotificacao": "PB",
    "municipioNotificacao": "Cajazeiras",
    "unidadeSaude": "UBS Centro",
    "dataPrimeirosSintomas": "2026-03-10",
    "nomePaciente": "Maria da Silva",
    "dataNascimento": "1990-05-10",
    "sexo": "F",
    "gestante": 5,
    "nomeMae": "Ana da Silva",
    "dadosResidencia": {
      "uf": "PB",
      "municipio": "Cajazeiras",
      "pais": "Brasil"
    },
    "dataInvestigacao": "2026-03-13"
  }'
```

Resposta: `201 Created`, cabeçalho `Location: http://localhost:8080/notificacao/{id}` e o corpo com a notificação criada (incluindo o `id`).

> No **PowerShell**, as aspas do JSON dão problema. Salve o JSON em um arquivo (por exemplo, `notificacao.json`) e use `curl.exe -i -X POST http://localhost:8080/notificacao -H "Content-Type: application/json" --data "@notificacao.json"`.

### Consultar por ID (`GET`)

```bash
curl -i http://localhost:8080/notificacao/1
```

### Listar com filtros (`GET`)

```bash
curl -i "http://localhost:8080/notificacao?nomePaciente=maria&ufResidencia=PB"
curl -i "http://localhost:8080/notificacao?duplicadas=true"
```

As aspas na URL são necessárias por causa do `&`.

### Atualizar (`PUT`)

Envia a notificação **completa**, no mesmo formato do cadastro. O `id` vem da URL (um `id` no corpo é ignorado).

```bash
curl -i -X PUT http://localhost:8080/notificacao/1 \
  -H "Content-Type: application/json" \
  -d '{ ...notificação completa... }'
```

### Excluir (`DELETE`)

```bash
curl -i -X DELETE http://localhost:8080/notificacao/1
```

Resposta: `204 No Content`, sem corpo.

## Validações

Os dados recebidos no cadastro (`POST`) e na atualização (`PUT`) são validados antes de serem gravados.

- **Campos obrigatórios:** N.º da notificação, agravo, data da notificação, UF e município de notificação, unidade de saúde, data dos primeiros sintomas, nome do paciente, sexo, data da investigação e os dados de residência.
- **Formatos:**
	- UF: sigla válida, em maiúsculas (por exemplo, `PB`).
	- Sexo: `M`, `F` ou `I`.
	- Código CID10: formato `A90` ou `B15.9`.
	- Cartão SUS: 15 dígitos.
	- CEP: `58900-000` ou `58900000`.
	- Código IBGE: 7 dígitos.
- **Datas:** nenhuma data pode estar no futuro, e as datas devem estar no formato `aaaa-mm-dd`. Também são conferidas: a data de nascimento e a dos primeiros sintomas não podem ser posteriores à data da notificação, e a data de encerramento não pode ser anterior a ela.
- **Códigos da ficha:** idade (unidade 1 a 4), gestante, raça/cor, escolaridade, zona, classificação final, critério de confirmação, autóctone, doença relacionada ao trabalho e evolução do caso só aceitam os valores que constam na ficha.

Quando as instruções de preenchimento e a ficha impressa divergem, o projeto adota **a ficha**. Por exemplo, "Não se aplica" em gestante é o código `6`, e a doença relacionada ao trabalho aceita 1, 2 ou 9.

## Regras de negócio

### RN01: duplicidade de notificações

Duas notificações são consideradas **duplicadas** quando atendem, **ao mesmo tempo**, a todos os critérios:

1. mesmo agravo/doença;
2. mesmo nome do paciente;
3. mesma data de nascimento;
4. mesmo nome da mãe;
5. datas de notificação com diferença de **até 3 dias**, inclusive.

Detalhes da implementação (`DetectorDuplicidade`):

- A comparação de textos **ignora maiúsculas/minúsculas e espaços extras**.
- Notificações com **algum campo de comparação em branco** (por exemplo, sem nome da mãe) **não são consideradas**.
- Uma notificação nunca é comparada consigo mesma.
- A consulta com `?duplicadas=true` lista as notificações que são duplicadas de **ao menos uma outra**.
- A duplicidade é calculada sobre **todas** as notificações cadastradas e só depois combinada com os demais filtros. Por exemplo, `?duplicadas=true&dataNotificacaoInicio=2026-03-13` ainda mostra a notificação de 14/03, que é duplicada da de 12/03, mesmo que esta fique fora do período.

Exemplo: Maria da Silva, nascida em 10/05/1990, mãe Ana da Silva, notificada de dengue em 12/03/2026 e em 14/03/2026 (2 dias): **duplicadas**. Se a segunda fosse em 20/03/2026 (8 dias): **não duplicadas**.

### RN02: obrigatoriedade condicional de idade e gestante

Conforme os campos 10 e 12 das instruções de preenchimento:

- **Idade (valor e unidade):** obrigatória quando a **data de nascimento não é informada**. O valor e a unidade devem ser informados juntos.
- **Gestante:** obrigatória quando o **sexo é feminino** (`F`), exceto para pacientes com **menos de 7 anos**.
- Para pacientes do sexo masculino (ou menores de 7 anos), a gestante só pode ficar em branco ou valer `6` (não se aplica). Quando fica em branco, o sistema grava `6` automaticamente.

### RN03: residência

Conforme os campos 17, 18 e 30 das instruções de preenchimento:

- **UF de residência:** obrigatória quando o paciente reside no **Brasil**.
- **Município de residência:** obrigatório quando a **UF é informada**.
- **País de residência:** obrigatório quando o paciente reside em **outro país**.

O paciente é considerado residente no exterior quando informa um país diferente de "Brasil". Combinações incompatíveis são rejeitadas: UF junto com país estrangeiro, e município sem UF. Os dados de residência são sempre obrigatórios.

## Tratamento de erros (Problem Detail)

Todos os erros da API seguem o padrão **Problem Detail (RFC 9457)**, com o tipo de conteúdo `application/problem+json`.

| Situação | Status |
|---|---|
| Campos inválidos (validações e regras RN02/RN03) | `400 Bad Request` |
| Corpo da requisição malformado, ou data em formato inválido | `400 Bad Request` |
| Parâmetro inválido (por exemplo, `GET /notificacao/abc`) | `400 Bad Request` |
| Notificação inexistente | `404 Not Found` |
| Método HTTP não permitido | `405 Method Not Allowed` |
| Tipo de conteúdo não suportado | `415 Unsupported Media Type` |
| Erro inesperado | `500 Internal Server Error` |

Exemplo de erro de validação (`400`), em que a lista `erros` indica cada campo inválido e o motivo:

```json
{
  "detail": "Um ou mais campos são inválidos.",
  "instance": "/notificacao",
  "status": 400,
  "title": "Dados inválidos",
  "erros": [
    { "campo": "dadosResidencia.uf", "mensagem": "A UF é obrigatória quando o paciente reside no Brasil; se reside em outro país, informe o país" },
    { "campo": "gestante", "mensagem": "A gestante é obrigatória quando o sexo é feminino" }
  ]
}
```

Exemplo de notificação inexistente (`404`):

```json
{
  "detail": "Notificação com id 99 não encontrada.",
  "instance": "/notificacao/99",
  "status": 404,
  "title": "Notificação não encontrada"
}
```

## Boas práticas REST adotadas

- Uso coerente dos verbos HTTP: `POST` para criar, `GET` para consultar, `PUT` para atualizar e `DELETE` para excluir.
- Status HTTP apropriados em cada situação, como `201` com o cabeçalho `Location` na criação e `204` na exclusão.
- Recurso identificado na URL (`/notificacao/{id}`), com os filtros da consulta em parâmetros de URL.
- Respostas de erro padronizadas, com mensagens claras.

## Interface web

- **Consulta (`index.html`)**: campos de filtro (incluindo "Somente notificações duplicadas"), tabela com os resultados e colunas de **Alterar** e **Excluir**.
- **Cadastro e alteração (`cadastro.html`)**: formulário com os campos da ficha. Os erros da API são exibidos no topo e junto de cada campo inválido. Aberto pelo botão **Alterar**, o mesmo formulário carrega os dados da notificação e salva com `PUT`.

## Limitações e observações

- Os dados ficam apenas em memória e são perdidos ao reiniciar a aplicação.
- A paginação e a ordenação (desafio opcional do enunciado) não foram implementadas.
- O CORS está liberado para qualquer origem na rota `/notificacao`, o que é adequado para uso local e de demonstração. Em um sistema real, seria restrito aos endereços autorizados.
- A API não tem autenticação.