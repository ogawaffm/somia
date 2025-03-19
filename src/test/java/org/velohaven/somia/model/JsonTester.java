package org.velohaven.somia.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.Map;

@Getter
public class JsonTester {

    private Object json;

    public void loadJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.json = objectMapper.readValue(jsonString, Object.class);
        } catch (Exception e) {
            if (jsonString.trim().isEmpty()) {
                throw new RuntimeException("Empty JSON string", e);
            }
            throw new RuntimeException("Failed to load JSON string:\n" + jsonString, e);
        }

    }

    @SneakyThrows
    public String saveJson(boolean prettyPrint) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = prettyPrint ? objectMapper.writerWithDefaultPrettyPrinter() : objectMapper.writer();
        return writer.writeValueAsString(this.json);
    }

    @SneakyThrows
    public void assertEquals(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        JsonNode actualJson = objectMapper.valueToTree(this.json);
        compareJsonNodes(expectedJson, actualJson, "");
    }

    private void compareJsonNodes(JsonNode expected, JsonNode actual, String path) {
        if (!expected.equals(actual)) {
            if (expected.isObject() && actual.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields = expected.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    String fieldPath = path.isEmpty() ? field.getKey() : path + "." + field.getKey();
                    compareJsonNodes(field.getValue(), actual.get(field.getKey()), fieldPath);
                }
            } else if (expected.isArray() && actual.isArray()) {
                for (int i = 0; i < expected.size(); i++) {
                    compareJsonNodes(expected.get(i), actual.get(i), path + "[" + i + "]");
                }
            } else {
                Assertions.assertEquals(expected, actual, "Mismatch at path: " + path);
            }
        }
    }

}