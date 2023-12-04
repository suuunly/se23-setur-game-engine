package com.setur.se23.engine.resource;

public interface ResourceParser {

    String getExtension();

    Object parse(ProcessedFileContent content);
}
