# Demo Validation

Projeto demo para testes validações personalizadas utilizando Hibernate Validator

## Libs/Dependências utilizadas

### Compilação e Runtime

- Arc [[Doc](https://quarkus.io/blog/quarkus-dependency-injection/)]
  - Container de injeção de dependência orientada para o tempo de construção baseada em CDI 2.0.
- RESTEasy [[Doc](https://quarkus.io/guides/resteasy)]
  - Estrutura de endpoints REST implementando JAX-RS.
- RESTEasy Jackson
  - Suporte de serialização/desserialização Jackson para RESTEasy
- Smallrye OpenAPI / Swagger [[Doc](https://quarkus.io/guides/openapi-swaggerui)]
  - Interface do usuário para renderizar visualmente a documentação de uma API definida com a especificação OpenAPI.
- Panache ORM [[Doc](https://quarkus.io/guides/hibernate-orm-panache)]
  - Implementação JPA que simplifica o código de persistência para Hibernate ORM por meio do `Active Record` ou `Repository Pattern`.
- JDBC Driver - H2
  - Conexão ao banco de dados H2 via JDBC
- Hibernate Validator [[Doc](https://quarkus.io/guides/validation)]
  - Validador de propriedades do objeto (campo, getter) e os parâmetros do método para seus beans (REST, CDI, JPA)
- Lombok [[Doc](https://projectlombok.org/features/all)]
  - Utilização de Annotations pra geração de código verboso/repetitivo (getter, setter, construtores, etc).
- Apache Commons Lang [[Doc](https://commons.apache.org/proper/commons-lang/)]
  - Classes utilitárias auxiliares Java para as classes que estão na hierarquia de `java.lang`, ou são consideradas tão padrão que justificam a existência em `java.lang`.

### Teste

- JUnit 5 [[Doc](https://junit.org/junit5/docs/current/user-guide/)]
  - Framework para criação de testes automatizados em Java.
- REST-assured [[Doc](https://rest-assured.io/)]
  - Biblioteca que permite testar serviços REST em Java.
- Mockito [[Doc](https://site.mockito.org/)]
  - Mock de objetos no contexto de testes.
- JaCoCo [[Doc](https://www.eclemma.org/index.html)]
  - Geração de relatório de cobertura de testes
- Java Faker [[Doc](https://github.com/DiUS/java-faker)]
  - Gerador de dados falsos para testes.

<br>
<br>
<br>
<br>
<br>

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/demo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, JPA)
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
