package com.globant.automation.test.pet;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.PetDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Pruebas funcionales sobre el inventario de mascotas (/pet).
 */
public class GetPetByIdTests extends TestRunner {

    /**
     * Pre-condición: Crea la mascota para asegurarnos que exista antes de buscarla.
     */
    @BeforeClass
    public void createUser() {

        PetDTO petDTO = TestRunner.getPetDTO();
        RequestBuilder.postRequest(getBaseurl(), "/pet", petDTO, getApikey());
    }

    /**
     * Valida la consulta de una mascota por su ID único.
     * Comprueba que la respuesta no sea nula y que el nombre coincida con el registrado.
     */
    @Test(testName = "Get pet by id")
    public void getPetByIdAssertion() {

        PetDTO petDTO = TestRunner.getPetDTO();

        Response response = RequestBuilder.getRequest(getBaseurl(), "/pet/" + petDTO.getId(), getApikey());

        PetDTO responsePetDTO = response.as(PetDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");

        assertNotNull(responsePetDTO);

        assertEquals(responsePetDTO.getName(), petDTO.getName());
    }

}
