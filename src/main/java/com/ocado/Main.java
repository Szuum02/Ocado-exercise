package com.ocado;

import com.ocado.basket.BasketSplitter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basket = new BasketSplitter("/Users/user/Desktop/DO ROBOTY/Ocado/Zadanie/config.json");

//        List<String> items = List.of("Cocoa Butter", 	"Tart - Raisin And Pecan", "Table Cloth 54x72 White",
//                "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");
//        List<String> items = List.of("Pork - Hock And Feet Attached", "Haggis", "Mix - Cocktail Ice Cream",
//                "Fudge - Chocolate Fudge", "Flavouring - Rum", "Cake - Miini Cheesecake Cherry", "Mustard - Dry, Powder");
//        List<String> items = Arrays.asList("Pork Salted Bellies", "Yogurt - Cherry, 175 Gr", "Pork Ham Prager", "Otomegusa Dashi Konbu",
//                "Bread - Crumbs, Bulk", "Tea - Apple Green Tea", "Beer - Alexander Kieths, Pale Ale",
//                "Cheese Cloth", "Bagel - Whole White Sesame");

        List<String> items = List.of("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole",
                "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry", "Sauce - Mint",
                "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry",
                "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled", "Cabbage - Nappa",
                "Bagel - Whole White Sesame", "Tea - Apple Green Tea");
        Map<String, List<String>> res = basket.split(items);
        System.out.println(res);
    }
}