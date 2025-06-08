package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonWriter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeToJson(Object obj, String filePath) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), obj);
    }
}
