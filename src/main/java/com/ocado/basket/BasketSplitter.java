package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* ... */
public class BasketSplitter {
    private Map<String, List<String>> deliveryOptions;

    public BasketSplitter(String absolutePathToConfigFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.deliveryOptions = mapper.readValue(new File(absolutePathToConfigFile), new TypeReference<HashMap<String, List<String>>>() {});
        } catch (IOException e) {
            System.err.println("Error while reading config file: " + e.getMessage());
        }
    }

//    public Map<String, List<String>> split(List<String> items) {
//        /* ... */
//    }
//    /* ... */


    public static void main(String[] args) {
        String path = "/Users/user/Desktop/DO ROBOTY/Ocado/Zadanie/config.json";

        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> res;
        try {
            res = mapper.readValue(new File(path), new TypeReference<HashMap<String, List<String>>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(res.get("Tart - Raisin And Pecan"));
    }

    public Map<String, List<String>> getDeliveryOptions() {
        return deliveryOptions;
    }
}