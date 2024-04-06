package com.ocado.basket;

import java.util.*;
import java.util.stream.Collectors;

public class BasketDelivery {
    private int maxProducts; // max products in one delivery
    private final Map<String, List<String>> deliveries;
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
            List<String> possibleDeliveries = getIntersection(productsDeliveryOption.get(item), deliveryTypes);
            if (possibleDeliveries.size() == 0) {
                return false;
            }
        }
        return true;
    }

    private static List<String> getIntersection(List<String> list1, List<String> list2) {
        return list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
    }

    public boolean isBetter(BasketDelivery other) {
        if (other.maxProducts == Integer.MAX_VALUE) {
            return true;
        }
        return deliveries.keySet().size() == other.deliveries.keySet().size()
                ? maxProducts > other.maxProducts : deliveries.keySet().size() < other.deliveries.keySet().size();
    }

    public Map<String, List<String>> getSortedDeliveries(List<String> originalItems) {
        List<String> items = new ArrayList<>(originalItems); // if original items are immutable
        Map<String, List<String>> result = new HashMap<>();
        while (!items.isEmpty()) {
            String bestDeliveryType = getBestDelivery(items);
            List<String> products = getIntersection(deliveries.get(bestDeliveryType), items);
            result.put(bestDeliveryType, products.stream().toList());
            items.removeAll(products);
        }
        return result;
    }

    private String getBestDelivery(List<String> items) {
        String bestDeliveryType = "";
        int maxProductsNumber = 0;
//        Set<String> bestDeliveryProducts = new HashSet<>();
        for (String deliveryType : deliveries.keySet()) {
            List<String> products = getIntersection(deliveries.get(deliveryType), items);
            if (products.size() > maxProductsNumber) {
                bestDeliveryType = deliveryType;
                maxProductsNumber = products.size();
//                bestDeliveryProducts = products;
            }
        }
//        items.removeAll(bestDeliveryProducts);
        return bestDeliveryType;
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
