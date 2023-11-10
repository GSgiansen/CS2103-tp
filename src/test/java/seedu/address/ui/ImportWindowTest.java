package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(ApplicationExtension.class)
public class ImportWindowTest {

    @Test
    public void testValidCardFormat() {
        // JSON string representing the card data
        String jsonInput = "{\n"
                + "  \"cards\": []\n"
                + "}";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON string
            JsonNode jsonNode = objectMapper.readTree(jsonInput);

            // Validate the structure
            assertTrue(jsonNode.has("cards"), "JSON should contain 'cards' key.");
            assertTrue(jsonNode.get("cards").isArray(), "'cards' should be an array.");

        } catch (Exception e) {
            fail("Invalid JSON format: " + e.getMessage());
        }
    }

}

