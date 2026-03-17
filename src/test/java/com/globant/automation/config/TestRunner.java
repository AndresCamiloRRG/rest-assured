package com.globant.automation.config;

import com.globant.automation.model.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;

/**
 * Clase base para la ejecución de pruebas.
 * Se encarga de cargar la configuración y los datos (DTOs).
 */

@Slf4j
public class TestRunner {

    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    @Getter
    private static String baseurl;

    @Getter
    private static CreateUserDTO createUserDTO;

    @Getter
    private static CreateOrderDTO createOrderDTO;

    @Getter
    private static PetDTO petDTO;

    @Getter
    private static String apikey;

    /**
     * Configuración inicial antes de ejecutar la suite de pruebas.
     * Inicializa los modelos de datos para User, Pet y Order.
     */

    @BeforeSuite
    public void setupEnvironment() {
        loadProperties();

        //Base url
        baseurl = PROPERTIES.getProperty("url.base");

        //Api key
        apikey = PROPERTIES.getProperty("apikey");

        //User

        int id = Integer.parseInt(PROPERTIES.getProperty("data.user.id"));
        String username = PROPERTIES.getProperty("data.user.username");
        String firstname = PROPERTIES.getProperty("data.user.firstname");
        String lastname = PROPERTIES.getProperty("data.user.lastname");
        String email = PROPERTIES.getProperty("data.user.email");
        String password = PROPERTIES.getProperty("data.user.password");
        String phone = PROPERTIES.getProperty("data.user.phone");
        int userStatus = Integer.parseInt(PROPERTIES.getProperty("data.user.userStatus"));

        createUserDTO = new CreateUserDTO(id, username, firstname, lastname, email, password, phone, userStatus);

        //Pet

        String petId = PROPERTIES.getProperty("data.pet.id");

        String categoryId = PROPERTIES.getProperty("data.pet.category.id");
        String categoryName = PROPERTIES.getProperty("data.pet.category.name");

        String name = PROPERTIES.getProperty("data.pet.name");
        String photoUrl = PROPERTIES.getProperty("data.pet.photoUrl");
        String tagId = PROPERTIES.getProperty("data.pet.tag.id");
        String tagName = PROPERTIES.getProperty("data.pet.tag.name");
        String petStatus = PROPERTIES.getProperty("data.pet.status");

        petDTO = new PetDTO(petId, new CategoryDTO(categoryId, categoryName), name,
                new String[]{photoUrl}, new TagDTO[]{new TagDTO(tagId, tagName)}, petStatus);

        // Order

        String orderId = PROPERTIES.getProperty("data.store.id");
        int quantity = Integer.parseInt(PROPERTIES.getProperty("data.store.quantity"));
        Instant shipInstant = Instant.parse(PROPERTIES.getProperty("data.store.shipDate"));
        Date shipDate = Date.from(shipInstant);
        String status = PROPERTIES.getProperty("data.store.status");
        boolean complete = Boolean.parseBoolean(PROPERTIES.getProperty("data.store.complete"));

        createOrderDTO = new CreateOrderDTO(orderId, petId, quantity, shipDate, status, complete);
    }

    private void loadProperties() {
        try {
            FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
