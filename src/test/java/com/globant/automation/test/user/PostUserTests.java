package com.globant.automation.test.user;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.CreateUserResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Pruebas unitarias para el endpoint de gestión de usuarios (/user).
 */
public class PostUserTests extends TestRunner {

    /**
     * Valida la creación exitosa de un usuario.
     * Verifica que el status code sea 200 y el mensaje de respuesta coincida con el ID enviado.
     */
    @Test(testName = "Create user")
    public void createUserAssertion1() {

        CreateUserDTO createUserDTO = TestRunner.getCreateUserDTO();

        Response response = RequestBuilder.postRequest(getBaseurl(), "/user", createUserDTO, getApikey());

        CreateUserResponseDTO createUserResponseDTO = response.as(CreateUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(createUserResponseDTO.getMessage(),  createUserDTO.getId()+"", "The message doesn't contain the user id");
    }

}
