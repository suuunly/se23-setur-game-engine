package com.setur.se23.engine.resource.process;

import com.setur.se23.engine.resource.FileProcessor;
import com.setur.se23.engine.resource.ProcessedFileContent;

import java.util.HashMap;
import java.util.Map;

public class JsonFileProcessor implements FileProcessor {

    @Override
    public ProcessedFileContent processString(String unprocessedData) {
        Map<String, Object> jsonData = new HashMap<>();

        // Custom logic to parse the JSON content and populate the map
        // We assume a simple key-value JSON structure without nested objects or arrays

        // Remove whitespace characters (if present)
        unprocessedData = unprocessedData.replaceAll("\\s", "");

        // Remove curly braces at the beginning and end of the JSON content
        unprocessedData = unprocessedData.substring(1, unprocessedData.length() - 1);

        // Split the content by commas to separate key-value pairs
        String[] keyValuePairs = unprocessedData.split(",");

        // Iterate over key-value pairs and populate the map
        for (String keyValuePair : keyValuePairs) {
            // Split each key-value pair by the colon separator
            String[] pair = keyValuePair.split(":");

            // Trim whitespace and remove surrounding quotation marks
            String key = pair[0].trim().replaceAll("\"", "");
            String value = pair[1].trim();

            // Determine the type of the value and store it in the map accordingly
            if (value.startsWith("\"") && value.endsWith("\"")) {
                // String value
                value = value.substring(1, value.length() - 1);
                jsonData.put(key, value);
            } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                // Boolean value
                boolean boolValue = Boolean.parseBoolean(value);
                jsonData.put(key, boolValue);
            } else {
                // Numeric value (assumed to be an integer in this example)
                int intValue = Integer.parseInt(value);
                jsonData.put(key, intValue);
            }
        }

        return new ProcessedFileContent(jsonData);
    }
}
