# Sistema de Gerenciamento de ONG

Este é um projeto Java para gerenciamento de ONGs, utilizando Hibernate e JPA para persistência de dados em um banco de dados MySQL. O sistema permite cadastrar doadores, itens de doação, agendamentos e realizar operações CRUD no banco de dados.

## Funcionalidades

- Cadastrar doadores com informações pessoais.
- Gerenciar itens de doação com categorias e descrições.
- Agendar entregas ou retiradas de doações.
- Operações CRUD:
  - Criar, ler, atualizar e excluir doadores e itens de doação.
- Persistência de dados em banco de dados MySQL com Hibernate.

## Tecnologias Utilizadas

- **Java 8**
- **Hibernate** (versão 5.6.15)
- **JPA**
- **MySQL** (versão 8.x)
- **JUnit** (para testes)
- **Maven** (gerenciador de dependências)

## Configuração do Ambiente
### Pré-requisitos

1. **Java 8** ou superior instalado.
2. **MySQL Server** instalado e rodando.
3. **Maven** instalado e configurado.

## Instalação e Configuração

### 1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio
```

### 2. Configuração do Banco de Dados

1. Crie um banco de dados chamado ong no MySQL:
```sql
CREATE DATABASE ong;
```

2. Atualize as credenciais do banco de dados no arquivo persistence.xml:
Localização: src/main/resources/META-INF/persistence.xml

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/ong?useSSL=false&amp;serverTimezone=UTC" />
<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
<property name="javax.persistence.jdbc.user" value="SEU_USUARIO" />
<property name="javax.persistence.jdbc.password" value="SUA_SENHA" />
```

### 3. Compilar e Executar
1. Compile o projeto com Maven:

```bash
mvn clean install
```

2. Execute a classe principal:
```bash
mvn exec:java -Dexec.mainClass="utils.Main"
```

## Estrutura do Projeto
```plaintext
src/
├── main/java/
│   ├── model/                # Entidades JPA (Doador, ItemDoacao, etc.)
│   ├── service/              # Lógica de negócios (CRUD, agendamentos)
│   ├── utils/                # Classes utilitárias (JPAUtil, Main)
│   ├── test/                 # Testes unitários
└── resources/
    └── META-INF/
        └── persistence.xml   # Configuração do banco de dados
```

## Principais Classes
### Entidades

1. Doador
  - Representa um doador cadastrado no sistema.
  - Atributos: id, nome, cpf, email, telefone, endereco.

2. ItemDoacao
  - Representa um item de doação.
  - Atributos: id, nome, descricao, categoria.
    
### Serviços
1. DoadorService
  - Operações CRUD para doadores:
    - Inserir: inserirDoador(Doador doador)
    - Buscar: encontrarDoadorPorCpf(String cpf)
    - Excluir: excluirDoador(String cpf)

2. JPAUtil
  - Gerencia o EntityManager para transações com o banco de dados.

## Como Usar
1. Ao executar a aplicação, você verá o menu no terminal:
   
![image](https://github.com/user-attachments/assets/0ce5c17b-6e82-46b3-9d0b-1160322fb8f5)

3. Escolha uma operação:
   
  1 - Inserir Doador
  2 - Encontrar Doador por CPF
  3 - Excluir Doador
  0 - Sair

5. Escolha a operação desejada e siga as instruções fornecidas no terminal.

## Testes
1. Para executar os testes, use o comando:

```bash
mvn test
```
Testes estão localizados na pasta src/test/java/.

## Dicas e Logs

1. Ative os logs SQL para depuração, adicionando essas propriedades no arquivo persistence.xml:
```xml
<property name="hibernate.show_sql" value="true" />
<property name="hibernate.format_sql" value="true" />
```

2. Verifique os dados diretamente no banco com comandos SQL no terminal:
```sql
SELECT * FROM Doador;
SELECT * FROM ItemDoacao;
```

## Possíveis Erros

1. Erro de conexão com o banco de dados:
  - Verifique se o MySQL está rodando.
  - Confirme as credenciais no persistence.xml.

2. Tabela não encontrada:
  - Certifique-se de que o Hibernate está configurado para criar tabelas automaticamente:
```xml
<property name="hibernate.hbm2ddl.auto" value="update" />
```

## Contribuições
Contribuições são bem-vindas! Siga os passos abaixo:

1. Faça um fork do repositório.
2. Crie uma branch para suas alterações:
```bash
git checkout -b minha-feature
```
3. Faça commit das suas alterações:
```bash
git commit -m "Descrição do que foi alterado"
```
4. Envie para sua branch:
```bash
git push origin minha-feature
```
5. Abra um pull request.
   
## Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Contato
Para dúvidas ou sugestões, entre em contato:
  - Nome: Luiz Antônio de Souza
  - Email: luiz.antoniodesouza004@gmail.com
