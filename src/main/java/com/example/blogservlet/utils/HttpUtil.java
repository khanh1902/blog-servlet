package com.example.blogservlet.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class HttpUtil {
    private String value;

    // convert json to BufferedReader
    public static HttpUtil of(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while (((line = reader.readLine()) != null))
                sb.append(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new HttpUtil(sb.toString());
    }

    // convert BufferedReader to Model
    public <T> T toModel(Class<T> tClass){
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
