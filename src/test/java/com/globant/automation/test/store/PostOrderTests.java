package com.globant.automation.test.store;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateOrderDTO;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Pruebas para el flujo de ventas de la tienda (/store).
 */
public class PostOrderTests extends TestRunner {

    /**
     * Simula el proceso de compra (orden) de una mascota.
     * Verifica que la API procese la orden correctamente con un status 200.
     */
    @Test(testName = "Create an order for a pet")
    public void createOrderForPetAssertion1() {

        CreateOrderDTO createOrderDTO = TestRunner.getCreateOrderDTO();

        Response response = RequestBuilder.postRequest(getBaseurl(), "/store/order", createOrderDTO, getApikey());

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
    }

}
