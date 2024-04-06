package com.ocado.basket;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasketSplitterTest {

    @Test
    public void givenAbsolutePathToConfigFile_whenReadingInputs_thenReturnMapProductsDelivery() {
        BasketSplitter basket = new BasketSplitter("/Users/user/Desktop/DO ROBOTY/Ocado/Zadanie/config.json");
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
        BasketSplitter basket = new BasketSplitter("/Users/user/Desktop/DO ROBOTY/Ocado/Zadanie/config.json");

        List<String> actualDeliveryTypes = basket.getDeliveryTypes();
        Assert.assertTrue(actualDeliveryTypes.contains("Pick-up point"));
        Assert.assertTrue(actualDeliveryTypes.contains("Express Collection"));
    }
}
