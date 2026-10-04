
**Visão Geral**
- **Descrição:** Projeto exemplo para gerenciar notificações compatíveis com o fluxo do SINAN.
- **Objetivo:** fornecer uma API REST em Java (Spring Boot) e um frontend estático que consome a API para cadastro, consulta e validação de notificações do Sistema de Informação de Agravos de Notificação(SINAN).

**Aluno responsável**
- João Victor Batista de Araújo Abrantes - 202512010022

**Tecnologias**
- **Backend:** Java 21, Spring Boot, Maven
- **Frontend:** HTML/CSS/JS (estático)

**Estrutura do Projeto**
- **Backend:** [backend-sinan/pom.xml](backend-sinan/pom.xml) e código em [backend-sinan/src/main/java](backend-sinan/src/main/java)
	- Controller: [backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/controller/NotificacaoController.java](backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/controller/NotificacaoController.java)
	- Service: [backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/service/NotificacaoService.java](backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/service/NotificacaoService.java)
	- Model: [backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/model/Notificacao.java](backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/model/Notificacao.java)
- **Frontend:** arquivos estáticos em [frontend-sinan/html](frontend-sinan/html) e scripts em [frontend-sinan/js](frontend-sinan/js)

**Pré-requisitos**
- Java 21 ou superior instalado
- Maven (ou usar o `mvnw` incluso)
- Navegador web para abrir o frontend

**Rodando o Backend (desenvolvimento)**
1. Abra um terminal na pasta `backend-sinan`.
2. Para compilar e executar com o wrapper (Windows PowerShell):

```powershell
cd backend-sinan
.\mvnw spring-boot:run
```

3. A aplicação por padrão roda em `http://localhost:8080` (verifique `application.properties`).

**Rodando o Frontend**
- O frontend é estático. Basta abrir o arquivo [frontend-sinan/html/index.html](frontend-sinan/html/index.html) no navegador.
- Para servir localmente via servidor HTTP simples (recomendado durante desenvolvimento):

```bash
# usando Python 3
cd frontend-sinan/html
python -m http.server 8000
# então abra http://localhost:8000
```

**API — Endpoints principais**
- Os endpoints estão implementados em `NotificacaoController`. Principais rotas (exemplo):

- `GET /notificacoes` : lista todas as notificações
- `GET /notificacoes/{id}` : obtém notificação por id
- `POST /notificacoes` : cria uma nova notificação (JSON)
- `PUT /notificacoes/{id}` : atualiza notificação
- `DELETE /notificacoes/{id}` : remove notificação

Consulte o arquivo [backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/controller/NotificacaoController.java](backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/controller/NotificacaoController.java) para detalhes de parâmetros e respostas.

**Exemplos de requisições (curl)**

- Criar notificação:

```bash
curl -X POST http://localhost:8080/notificacoes \
	-H "Content-Type: application/json" \
	-d @docs/Notificacao.json
```

- Buscar todas:

```bash
curl http://localhost:8080/notificacoes
```

**Validações e Regras**
- O backend contém validações customizadas em `validation/` (ex.: `IdadeEGestanteValidator`). Veja [backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/validation](backend-sinan/src/main/java/br/edu/ifpb/fichanotificacaosinan/validation) para regras específicas.
- Há uma classe `DetectorDuplicidade` em `service/` que exemplifica checagem de duplicidade.

**Testes**
- Para rodar os testes unitários:

```powershell
cd backend-sinan
.\mvnw test
```

**Como contribuir**
- Abra uma issue descrevendo o problema ou a feature.
- Faça um fork/branch, implemente a mudança e envie um pull request com descrição clara.
- Siga as convenções de código do projeto e adicione testes quando aplicável.

**Notas de Desenvolvimento**
- Configurações e propriedades estão em [backend-sinan/src/main/resources/application.properties](backend-sinan/src/main/resources/application.properties).
- Exemplos de payload estão em [docs](docs) (ex.: `Notificacao.json`, `notificacao-invalida.json`).


---
