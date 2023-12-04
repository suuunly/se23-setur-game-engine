package com.setur.se23.engine.resource;

import com.setur.se23.engine.resource.parser.MaterialResourceParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Resources {

    private static final String _resourcesPath = "src/main/resources/";

    private final HashMap<String, ResourceParser> _parsers = new HashMap<>();
    private final FileProcessor _processor;

    public Resources(FileProcessor processor) {
        _processor = processor;

        registerParser(new MaterialResourceParser());
    }

    public String loadPath(String path) {
        return _resourcesPath + path;
    }

    public <T> T load(String path, Class<T> type) {

        File file = new File(loadPath(path));

        var filePath = file.getAbsolutePath();
        var content = processContent(filePath);

        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);

        ResourceParser parser = _parsers.get(extension);
        if (parser == null) {
            throw new RuntimeException("No parser found for extension: " + extension);
        }

        var parsedResult = parser.parse(content);
        if (!type.isInstance(parser.parse(content))) {
            throw new RuntimeException("Parser does not return correct type");
        }

        //noinspection unchecked
        return (T) parsedResult;
    }

    private ProcessedFileContent processContent(String filePath) {
        try {
            var reader = new java.io.FileReader(filePath);
            var bufferedReader = new BufferedReader(reader);
            var stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String content = stringBuilder.toString();


            return _processor.processString(content);
        } catch (IOException e) {

            // todo: replace with logger
            System.out.println(e.getMessage());

            return ProcessedFileContent.Empty();
        }

    }

    public void registerParser(ResourceParser parser) {
        _parsers.put(parser.getExtension(), parser);
    }

}
