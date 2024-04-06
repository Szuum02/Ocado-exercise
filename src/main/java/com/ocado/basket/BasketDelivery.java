package com.ocado.basket;

import java.util.*;
import java.util.stream.Collectors;

public class BasketDelivery {
    private int maxProducts; // max products in one delivery
    private Map<String, List<String>> deliveries;
    private Map<String, Integer> productsInDelivery;

    public BasketDelivery(int maxProducts) {
        this.maxProducts = maxProducts;
        deliveries = new HashMap<>();
    }

    public BasketDelivery(List<String> items, Map<String, List<String>> productsDeliveryOption, List<String> deliveryTypes) {
        deliveries = new HashMap<>();
        for (String deliveryType : deliveryTypes) {
            deliveries.put(deliveryType, new ArrayList<>());
            for (String item : items) {
                if (productsDeliveryOption.get(item).contains(deliveryType)) {
                    deliveries.get(deliveryType).add(item);
                }
            }
            maxProducts = Math.max(maxProducts, deliveries.get(deliveryType).size());
        }
    }

    public static boolean checkDeliveries(List<String> items, Map<String, List<String>> productsDeliveryOption, List<String> deliveryTypes) {
        for (String item : items) {
            Set<String> possibleDeliveries = productsDeliveryOption.get(item).stream()
                    .distinct()
                    .filter(deliveryTypes::contains)
                    .collect(Collectors.toSet());
            if (possibleDeliveries.size() == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isBetter(BasketDelivery other) {
        if (other.maxProducts == Integer.MAX_VALUE) {
            return true;
        }
        return deliveries.keySet().size() == other.deliveries.keySet().size()
                ? maxProducts > other.maxProducts : deliveries.keySet().size() < other.deliveries.keySet().size();
    }

    public void addDelivery(Map<String, List<String>> deliveryOption, String deliveryType) {
        for (String product : deliveryOption.keySet()) {
            if (deliveryOption.get(product).contains(deliveryType)) {
                deliveries.get(deliveryType).add(product);
                maxProducts = Math.max(maxProducts, deliveries.get(deliveryType).size());
            }
        }
    }

    public int getDeliveriesNumber() {
        return deliveries.size();
    }

    public int getMaxProducts() {
        return maxProducts;
    }

    public Map<String, List<String>> getDeliveries() {
        return deliveries;
    }
}
