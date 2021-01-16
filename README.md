## Descrição

Cria, Atualiza, Visualiza e Remove Contatos usando JSP, Servlet, JDBC e MySQL com Paginação utilizando Bootstrap 4.5

## Ferramentas e tecnologias usadas

* Eclipse neon.3
* Tomcat 8.5
* Java 8
* JSP 2.3
* Servlet 3.1
* JSTL 1.2
* MySQL Connector/J 8.0.22

## Estrutura do Projeto

![Estrutura projeto](https://github.com/adevecchi/jsp-servlet-jdbc-pagination/blob/main/WebContent/assets/images/estrutura.png)

## Configuração Banco de Dados MySQL

```sql
CREATE DATABASE contatos_jsp_servlet;

USE contatos_jsp_servlet;

CREATE TABLE contatos (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  telefone VARCHAR(15) NOT NULL,
  PRIMARY KEY (id)
);
```

- No arquivo ***dev.lab.dao.ContatoDao.java*** atualizar nome de usuário e senha do Banco de Dados Mysql usados para acesso, conforme mostrado abaixo:

```java
  private static final String url = "jdbc:mysql://localhost:3306/contatos_jsp_servlet?useSSL=false&useTimezone=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
  private static final String user = "root";
  private static final String pass = "password";
```

## Captura de Tela

![Home page](https://github.com/adevecchi/jsp-servlet-jdbc-pagination/blob/main/WebContent/assets/images/home.png)

---

![Home page](https://github.com/adevecchi/jsp-servlet-jdbc-pagination/blob/main/WebContent/assets/images/contatos-home.png)

---

![Home page](https://github.com/adevecchi/jsp-servlet-jdbc-pagination/blob/main/WebContent/assets/images/contatos-novo.png)

---

![Home page](https://github.com/adevecchi/jsp-servlet-jdbc-pagination/blob/main/WebContent/assets/images/contatos-editar.png)

---

![Home page](https://github.com/adevecchi/jsp-servlet-jdbc-pagination/blob/main/WebContent/assets/images/contatos-excluir.png)