package com.teaminternational.coe.pages.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Page {
    public SelenideElement cartButton = $(".fa-shopping-cart"),
    cartButtonCountOfItems = $(".fa-layers-counter");
}
