# code-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


You can modify the section about configuring keys for JWT to include the specific path for the public key. Here’s the updated version:

## Configuring Keys for JWT

To ensure that the application works correctly with JWT, you need to configure the signing keys. Please follow these steps:

1. **Generate the Keys**: If you haven't done so already, generate a public and private key pair for JWT. You can use tools like `openssl` for this. For example:
   ```bash
   openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048
   openssl rsa -pubout -in private_key.pem -out public_key.pem
   ```

2. **Store the Keys**: Keep the private key in a secure location and do not upload it to your repository. You can use environment variables or a secrets manager to store the private key. The public key can be shared and should be placed in the following directory:
   ```
   /GameVault/src/main/resources/META-INF/resources/
   ```

3. **Configure the Application**: Ensure that your application is set up to use the keys. This may involve modifying the `application.properties` or `application.yml` configuration file in Quarkus. For example:
   ```properties
   mp.jwt.verify.publickey=classpath:META-INF/resources/public_key.pem
   mp.jwt.sign.privatekey=classpath:META-INF/resources/private_key.pem
   ```

4. **Restart the Application**: After making the changes, restart your application for the new configuration to take effect.

With these configurations, your application will be ready to handle JWT securely.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Running the app with docker

**For Production with Base Configuration:**

```bash
docker-compose -f docker-compose.prod.yml up --build
```

**Explanation:**
- `-f docker-compose.prod.yml`: Specifies the production-specific file.
- `up --build`: Builds and starts the containers.

**For Development:**

```bash
docker-compose -f docker-compose.dev.yml up --build
```

**Explanation:**
- `-f docker-compose.dev.yml`: Specifies the production-specific file.
- `up --build`: Builds and starts the containers.

### **Stopping and Removing Containers**

To stop and remove containers and networks, you can use:

**For Development:**

```bash
docker-compose -f docker-compose.dev.yml down
```

**Explanation:**
- `-f docker-compose.dev.yml`: Specifies the development-specific Docker Compose file.
- `down`: Stops and removes containers, networks, and volumes.

```bash
docker-compose -f docker-compose.dev.yml down --volumes
```
- `down`: --volumes: Stops and removes containers, networks, and associated volumes.

**For Production:**

```bash
docker-compose -f docker-compose.prod.yml down
```

**Explanation:**
- `-f docker-compose.prod.yml`: Specifies the production-specific Docker Compose file.
- `down`: Stops and removes containers, networks, and volumes.

```bash
docker-compose -f docker-compose.prod.yml down --volumes
```
- `down`: --volumes: Stops and removes containers, networks, and associated volumes.

By using these commands and flags, you can efficiently manage your Docker containers and configurations for different environments.
- `-f docker-compose.dev


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
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)


### Test `login`

```bash
curl -X POST -H "Content-Type: application/json" -d '{"username": "player2", "password": "password1"}' http://localhost:8080/auth/login
```


### Test `myGames`

```bash
curl -X GET http://localhost:8080/player-videogames/mygames -H "Authorization: Bearer TOKEN"
```


### Test `testGetAllPlayers`

```java
@Test
public void testGetAllPlayers() {
    given()
      .when().get("/players")
      .then()
         .statusCode(200);
}
```

```bash
curl -X GET http://localhost:8080/players
```

### Test `testCreatePlayer`

```java
@Test
public void testCreatePlayer() {
    given()
      .header("Content-Type", "application/json")
      .body("{\"username\": \"" + testUsername + "\", \"email\": \"" + testEmail + "\", \"password\": \"password123\"}")
      .when().post("/players")
      .then()
         .statusCode(201);
}
```

```bash
curl -X POST -H "Content-Type: application/json" -d '{"username": "'"$testUsername"'", "email": "'"$testEmail"'", "password": "password123", "role": "user"}' http://localhost:8080/players
```

### Test `testGetPlayerById`

```java
@Test
public void testGetPlayerById() {
    given()
      .when().get("/players/{username}", testUsername)
      .then()
         .statusCode(200)
         .body("username", is(testUsername));
}
```

```bash
curl -X GET http://localhost:8080/players/$testUsername
```

### Test `testUpdatePlayer`

```java
@Test
public void testUpdatePlayer() {
    given()
      .header("Content-Type", "application/json")
      .body("{\"username\": \"updatedPlayer\", \"email\": \"updated@example.com\", \"password\": \"newpassword\"}")
      .when().put("/players/{username}", testUsername)
      .then()
         .statusCode(200);
}
```

```bash
curl -X PUT -H "Content-Type: application/json" -d '{"username": "updatedPlayer", "email": "updated@example.com", "password": "newpassword"}' http://localhost:8080/players/$testUsername
```

### Test `testDeletePlayer`

```java
@Test
public void testDeletePlayer() {
    given()
      .when().delete("/players/{username}", testUsername)
      .then()
         .statusCode(204);
}
```

```bash
curl -X DELETE http://localhost:8080/players/$testUsername
```


