package com.setur.se23.engine.resource;

import java.util.HashMap;
import java.util.Map;

public class ProcessedFileContent {

    private final Map<String, Object> _properties;

    public ProcessedFileContent(Map<String, Object> properties) {
        _properties = properties;
    }

    public static ProcessedFileContent Empty() {
        return new ProcessedFileContent(new HashMap<>());
    }

    public <T> T get(String key, Class<T> type) {

        if (!_properties.containsKey(key)) {
            throw new RuntimeException("Key not found: " + key);
        }

        var value = _properties.get(key);

        if (value == null) {
            throw new RuntimeException("Key " + key + " was found, but had to value");
        }

        try {
            return type.cast(value);
        } catch (ClassCastException e) {
            throw new RuntimeException("Cannot convert " + key + " to " + type.getName());
        }
    }

}
