package org.poc.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class RequestUtils {

    public static Map<String, String> parseQuery(String query) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(query,Map.class);

        } catch (Exception e){
            throw new Exception("Error on query parse");
        }

    }
}
