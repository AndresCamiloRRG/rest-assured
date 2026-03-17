package com.globant.automation.test.user;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.LoginResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Pruebas de autenticación de usuario.
 */
public class LoginUserTests extends TestRunner {

    /**
     * Pre-condición: Crea un usuario antes de intentar el login.
     */
    @BeforeClass
    public void createUser() {

        CreateUserDTO createUserDTO = TestRunner.getCreateUserDTO();
        RequestBuilder.postRequest(getBaseurl(), "/user", createUserDTO, getApikey());
    }

    /**
     * Valida que un usuario recién creado pueda autenticarse exitosamente.
     */
    @Test(testName = "Login as user")
    public void loginUserAssertion() {

        CreateUserDTO createUserDTO = TestRunner.getCreateUserDTO();

        Response response = RestAssured.given()
                .baseUri(getBaseurl())
                .queryParam("username", createUserDTO.getUsername())
                .queryParam("password", createUserDTO.getPassword())
                .header("api_key", getApikey())
                .get("/user/login");

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");

        LoginResponseDTO loginResponseDTO = response.as(LoginResponseDTO.class);

        assertTrue(loginResponseDTO.getMessage().contains("logged in user session"));
    }

}
