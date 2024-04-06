package com.ocado;

import com.ocado.basket.BasketSplitter;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basket = new BasketSplitter("/Users/user/Desktop/DO ROBOTY/Ocado/Zadanie/config.json");

//        List<String> items = List.of("Cocoa Butter", 	"Tart - Raisin And Pecan", "Table Cloth 54x72 White",
//                "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");
        List<String> items = List.of("Pork - Hock And Feet Attached", "Haggis", "Mix - Cocktail Ice Cream",
                "Fudge - Chocolate Fudge", "Flavouring - Rum", "Cake - Miini Cheesecake Cherry", "Mustard - Dry, Powder");
        Map<String, List<String>> res = basket.split(items);
        System.out.println(res);
    }
}