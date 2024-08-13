package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PlayerResourceTest {

    private String testUsername;
    private String testEmail;

    @BeforeEach
    public void setup() {
        // Generar datos únicos antes de cada prueba
        testUsername = "playerJUnit_" + System.currentTimeMillis();
        testEmail = testUsername + "@example.com";
    }

    @AfterEach
    public void teardown() {
        // Limpiar datos después de cada prueba
        // Implementa la lógica para eliminar el usuario creado
        // Esto puede ser una llamada a un servicio REST DELETE o una operación en la base de datos
        // Aquí se asume que sabes cómo eliminar un usuario por su ID o nombre de usuario
        given()
            .when().delete("/players/{username}", testUsername)
            .then()
                .statusCode(204); // Suponiendo que 204 es el código para una eliminación exitosa
    }

    @Test
    public void testGetAllPlayers() {
        given()
          .when().get("/players")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCreatePlayer() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"username\": \"" + testUsername + "\", \"email\": \"" + testEmail + "\", \"password\": \"password123\"}")
          .when().post("/players")
          .then()
             .statusCode(201);
    }

    @Test
    public void testGetPlayerById() {
        given()
          .when().get("/players/{username}", testUsername)
          .then()
             .statusCode(200)
             .body("username", is(testUsername));
    }

    @Test
    public void testUpdatePlayer() {
        given()
          .header("Content-Type", "application/json")
          .body("{\"username\": \"updatedPlayer\", \"email\": \"updated@example.com\", \"password\": \"newpassword\"}")
          .when().put("/players/{username}", testUsername)
          .then()
             .statusCode(200);
    }

    @Test
    public void testDeletePlayer() {
        given()
          .when().delete("/players/{username}", testUsername)
          .then()
             .statusCode(204);
    }
}
