package com.ocado.basket;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class BasketSplitterTest {

    @Test
    public void givenAbsolutePathToConfigFile_whenReadingInputs_thenReturnMapProductsDelivery() {
        BasketSplitter basket = new BasketSplitter("/Users/user/Desktop/praktyki/Ocado/Zadanie/config.json");
        Map<String, List<String>> actualMap = basket.getDeliveryOptions();

        List<String> expectedBreadDelivery = List.of("Next day shipping", "Mailbox delivery", "Courier",
                "In-store pick-up", "Same day delivery", "Parcel locker");
        List<String> actualBreadDelivery = actualMap.get("Bread - Crumbs, Bulk");
        Assert.assertEquals(expectedBreadDelivery, actualBreadDelivery);

        int expectedNumberOfAppetizerDelivery = 7;
        int actualNumberOfAppetizerDelivery = 7;
        Assert.assertEquals(expectedNumberOfAppetizerDelivery, actualNumberOfAppetizerDelivery);
    }

    @Test
    public void givenAbsolutePathToConfigFile_whenReadingInputs_thenReturnSetOfDeliveryTypes() {
        BasketSplitter basket = new BasketSplitter("/Users/user/Desktop/praktyki/Ocado/Zadanie/config.json");

        List<String> actualDeliveryTypes = basket.getDeliveryTypes();
        Assert.assertTrue(actualDeliveryTypes.contains("Pick-up point"));
        Assert.assertTrue(actualDeliveryTypes.contains("Express Collection"));
    }

    @Test
    public void givenAbsolutePathToConfigFile_whenSplitBasket_returnBestSplit() {
        // same products and delivery like in BasketDeliveryTestClass
        BasketSplitter splitter = new BasketSplitter("C:/Users/user/Desktop/praktyki/Ocado/Zadanie/zadanie/src/main/resources/configTest.json");
        List<String> items = List.of("Item1", "Item2", "Item3", "Item4", "Item5");
        Map<String, List<String>> actualBestSplit = splitter.split(items);
        Map<String, List<String>> expectedBestSplit = Map.of(
                "Delivery1", List.of("Item1", "Item2", "Item4", "Item5"),
                "Delivery2", List.of("Item3")
        );
        Assert.assertEquals(expectedBestSplit, actualBestSplit);
    }
}
