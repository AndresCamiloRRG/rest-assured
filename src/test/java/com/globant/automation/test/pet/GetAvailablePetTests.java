package com.globant.automation.test.pet;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.PetDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Pruebas funcionales sobre el inventario de mascotas (/pet).
 */
public class GetAvailablePetTests extends TestRunner {

    /**
     * Valida la consulta de las mascotas disponibles
     */
    @Test(testName = "Get all available pets")
    public void getAllAvailablePets() {

        Response response = RequestBuilder.getRequest(getBaseurl(), "/pet/findByStatus?status=available", getApikey());

        PetDTO[] petDTOS = response.as(PetDTO[].class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");

        assertNotNull(petDTOS);

        assertEquals(petDTOS[0].getStatus(), "available");
    }

}
