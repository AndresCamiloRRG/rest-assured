package com.globant.automation.test.user;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Clase de prueba para validar el Logout.
 */
public class LogoutUserTests extends TestRunner {

    /**
     * Pre-condición: Crea un usuario y se logea antes de intentar el logout
     */

    @BeforeClass
    public void createAndLoginUser() {

        CreateUserDTO createUserDTO = TestRunner.getCreateUserDTO();
        RequestBuilder.postRequest(getBaseurl(), "/user", createUserDTO, getApikey());

        RequestBuilder.getRequest(getBaseurl(), "/user/login?username=" +
                createUserDTO.getUsername() + "&password=" + createUserDTO.getPassword(), getApikey());
    }

    /**
     * Mandar una petición de logout y valida que el código de respuesta sea 200
     */
    @Test(testName = "Logout as user")
    public void logoutUserAssertion1() {

        Response response = RequestBuilder.getRequest(getBaseurl(), "/user/logout", getApikey());

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
    }

}
