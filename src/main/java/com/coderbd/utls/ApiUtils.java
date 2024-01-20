package com.coderbd.utls;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtils {
    public static String convertObjectToJson(String obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
