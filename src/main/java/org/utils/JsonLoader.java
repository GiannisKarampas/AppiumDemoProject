package org.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <K, V> Map<K,V> load( String filePath, Class<K> keyClass, Class<V> valueClass) throws IOException {
        return mapper.readValue(new File(filePath),
                mapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
    }

    public static <T> T load(String filePath, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(filePath),
                mapper.getTypeFactory().constructMapType(Map.class, String.class, clazz));
    }

    public static <T> T load(String filePath, TypeReference<T> typeReference) throws IOException {
        return mapper.readValue(new File(filePath), typeReference);
    }
}
