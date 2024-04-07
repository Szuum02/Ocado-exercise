package com.ocado.basket;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class BasketDeliveryTest {
    private final List<String> items = List.of("Item1", "Item2", "Item3", "Item4", "Item5");
    private final Map<String, List<String>> itemsDelivery = new HashMap<>(Map.of(
            "Item1", List.of("Delivery1", "Delivery3"),
            "Item2", List.of("Delivery1", "Delivery2", "Delivery3"),
            "Item3", List.of("Delivery2", "Delivery4"),
            "Item4", List.of("Delivery1", "Delivery2"),
            "Item5", List.of("Delivery1", "Delivery3", "Delivery4")));
    private final List<String> deliveryTypes = List.of("Delivery1", "Delivery2", "Delivery3", "Delivery4");

    @Test
    public void givenItemsAndDeliveryTypes_whenCreatingBasketDelivery_thenReturnBasketDeliveryObject() {
        BasketDelivery basket = new BasketDelivery(items, itemsDelivery, deliveryTypes);

        Map<String, List<String>> actualDeliveries = basket.getDeliveries();
        Map<String, List<String>> expectedDeliveries = Map.of(
                "Delivery1", List.of("Item1", "Item2", "Item4", "Item5"),
                "Delivery2", List.of("Item2", "Item3", "Item4"),
                "Delivery3", List.of("Item1", "Item2", "Item5"),
                "Delivery4", List.of("Item3", "Item5")
        );
        Assert.assertEquals(expectedDeliveries, actualDeliveries);

        int actualMaxItems = basket.getMaxItems();
        int expectedMaxItems = 4;
        Assert.assertEquals(expectedMaxItems, actualMaxItems);
    }

    @Test
    public void givenItemsAndDelivery_whenCheckingDelivery_thenReturnResult() {
        List<String> myDeliveryTypes = List.of("Delivery1", "Delivery3");
        boolean actualResult = BasketDelivery.checkDeliveries(items, itemsDelivery, myDeliveryTypes);
        Assert.assertFalse(actualResult);

        myDeliveryTypes = List.of("Delivery1", "Delivery2");
        actualResult = BasketDelivery.checkDeliveries(items, itemsDelivery, myDeliveryTypes);
        Assert.assertTrue(actualResult);
    }

    @Test
    public void givenTwoSplit_whenCompareThem_thenReturnBetterSplit() {
        List<String> deliveryTypes1 = List.of("Deliver1", "Delivery2", "Deliver3");
        List<String> deliveryTypes2 = List.of("Deliver1", "Delivery2");
        BasketDelivery basket1 = new BasketDelivery(items, itemsDelivery, deliveryTypes1);
        BasketDelivery basket2 = new BasketDelivery(items, itemsDelivery, deliveryTypes2);
        Assert.assertFalse(basket1.isBetter(basket2));

        deliveryTypes1 = List.of("Delivery1", "Delivery4");
        deliveryTypes2 = List.of("Delivery2", "Delivery3");
        basket1 = new BasketDelivery(items, itemsDelivery, deliveryTypes1);
        basket2 = new BasketDelivery(items, itemsDelivery, deliveryTypes2);
        Assert.assertTrue(basket1.isBetter(basket2));
    }

    @Test
    public void givenBasketSplit_whenFindOptimalSolution_thenReturnBestSplit() {
        List<String> myDeliveryTypes = List.of("Delivery1", "Delivery2");
        BasketDelivery basket = new BasketDelivery(items, itemsDelivery, myDeliveryTypes);
        Map<String, List<String>> actualBestSplit = basket.getSortedDeliveries(items);
        Map<String, List<String>> expectedBestSplit = Map.of(
                "Delivery1", List.of("Item1", "Item2", "Item4", "Item5"),
                "Delivery2", List.of("Item3")
        );
        Assert.assertEquals(expectedBestSplit, actualBestSplit);
    }
}
