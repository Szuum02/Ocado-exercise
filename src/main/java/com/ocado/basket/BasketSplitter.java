package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BasketSplitter {
    private Map<String, List<String>> deliveryOptions;
    private final List<String> deliveryTypes;
    private final int MAX_DELIVERY_TYPES = 10;

    public BasketSplitter(String absolutePathToConfigFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            deliveryOptions = mapper.readValue(new File(absolutePathToConfigFile), new TypeReference<HashMap<String, List<String>>>() {});
        } catch (IOException e) {
            System.err.println("Error while reading config file: " + e.getMessage());
        }

        Set<String> tmp = new HashSet<>();
        for (String product : deliveryOptions.keySet()) {
            tmp.addAll(deliveryOptions.get(product));
            if (tmp.size() == MAX_DELIVERY_TYPES) {
                break;
            }
        }
        deliveryTypes = tmp.stream().toList();
    }

    public Map<String, List<String>> split(List<String> items) {
        BasketDelivery delivery = splitItems(items, 0, new ArrayList<>());
        return delivery.getSortedDeliveries(items);
    }

    private BasketDelivery splitItems(List<String> items, int deliveryIdx, List<String> usedDeliveries) {
        if (deliveryIdx == deliveryTypes.size()) {
            return new BasketDelivery(Integer.MAX_VALUE);
        }
        BasketDelivery bestSplit = splitItems(items, deliveryIdx+1, usedDeliveries);

        usedDeliveries.add(deliveryTypes.get(deliveryIdx));
        BasketDelivery split;
        if (BasketDelivery.checkDeliveries(items, deliveryOptions, usedDeliveries)) {
            split = new BasketDelivery(items, deliveryOptions, usedDeliveries);
        }
        else {
            split = splitItems(items, deliveryIdx+1, usedDeliveries);
        }
        usedDeliveries.remove(deliveryTypes.get(deliveryIdx));

        if (split.isBetter(bestSplit)) {
            bestSplit = split;
        }
        return bestSplit;
    }

    public Map<String, List<String>> getDeliveryOptions() {
        return deliveryOptions;
    }

    public List<String> getDeliveryTypes() {
        return deliveryTypes;
    }
}