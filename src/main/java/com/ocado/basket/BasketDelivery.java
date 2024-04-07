package com.ocado.basket;

import java.util.*;
import java.util.stream.Collectors;

public class BasketDelivery {
    private int maxItems; // max items in one delivery
    private final Map<String, List<String>> deliveries;

    public BasketDelivery(int maxItems) {
        this.maxItems = maxItems;
        deliveries = new HashMap<>();
    }

    public BasketDelivery(List<String> items, Map<String, List<String>> itemsDeliveryOption, List<String> deliveryTypes) {
        deliveries = new HashMap<>();
        for (String deliveryType : deliveryTypes) {
            deliveries.put(deliveryType, new ArrayList<>());
            for (String item : items) {
                if (itemsDeliveryOption.get(item).contains(deliveryType)) {
                    deliveries.get(deliveryType).add(item);
                }
            }
            maxItems = Math.max(maxItems, deliveries.get(deliveryType).size());
        }
    }

    public static boolean checkDeliveries(List<String> items, Map<String, List<String>> itemsDeliveryOption, List<String> deliveryTypes) {
        for (String item : items) {
            List<String> possibleDeliveries = getIntersection(itemsDeliveryOption.get(item), deliveryTypes);
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
        if (other.maxItems == Integer.MAX_VALUE) {
            return true;
        }
        return deliveries.keySet().size() == other.deliveries.keySet().size()
                ? maxItems > other.maxItems : deliveries.keySet().size() < other.deliveries.keySet().size();
    }

    public Map<String, List<String>> getSortedDeliveries(List<String> originalItems) {
        List<String> itemsCopy = new ArrayList<>(originalItems); // if original items are immutable
        Map<String, List<String>> result = new HashMap<>();
        while (!itemsCopy.isEmpty()) {
            String bestDeliveryType = getBestDelivery(itemsCopy);
            List<String> deliveredItems = getIntersection(deliveries.get(bestDeliveryType), itemsCopy);
            result.put(bestDeliveryType, deliveredItems.stream().toList());
            itemsCopy.removeAll(deliveredItems);
        }
        return result;
    }

    private String getBestDelivery(List<String> items) {
        String bestDeliveryType = "";
        int maxItemsNumber = 0;
        for (String deliveryType : deliveries.keySet()) {
            List<String> deliveredItems = getIntersection(deliveries.get(deliveryType), items);
            if (deliveredItems.size() > maxItemsNumber) {
                bestDeliveryType = deliveryType;
                maxItemsNumber = deliveredItems.size();
            }
        }
        return bestDeliveryType;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public Map<String, List<String>> getDeliveries() {
        return deliveries;
    }
}
