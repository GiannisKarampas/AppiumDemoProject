package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CredentialsLoader {
    public static Map<String, UserCredentials> load(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath),
                mapper.getTypeFactory().constructMapType(Map.class, String.class, UserCredentials.class));
    }
}
