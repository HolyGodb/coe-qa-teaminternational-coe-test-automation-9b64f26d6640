package com.teaminternational.coe.pages.Pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage extends Page{
    public SelenideElement productsLabel = $(".product_label");
    public List<SelenideElement> listOfProducts = $$(".inventory_list > .inventory_item");
    public List<SelenideElement> listOfPrices = $$(".inventory_item_price");

    //method finds id of element with lowest price in list and click on button "Add to cart"
    public void clickOnAddToCartButtonOnElementWithLowestPrice() {
        float k = Float.MAX_VALUE;
        int numberOfElement = 0;
        for (int i = 0; i < listOfPrices.size(); i++) {
            if(k > Float.valueOf(listOfPrices.get(i).getText().replace("$",""))) {
                numberOfElement = i;
                k = Float.valueOf(listOfPrices.get(i).getText().replace("$",""));
            }
        }
        listOfPrices.get(numberOfElement).parent().lastChild().click();
    }
}

