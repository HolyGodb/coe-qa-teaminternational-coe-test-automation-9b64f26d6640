package com.teaminternational.coe.pages.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends Page {
    public SelenideElement cartCheckoutTitle = $(".subheader_label"),
    itemName = $(".inventory_item_name"),
    itemDescription = $(".inventory_item_desc"),
    itemPrice = $(".inventory_item_price"),
    checkoutButton = $(".cart_checkout_link"),

    //Checkout your information
    firstNameInputField = $("[data-test=firstName]"),
    lastNameInputField = $("[data-test=lastName]"),
    zipPostalCodeInputField = $("[data-test=postalCode]"),
    submitButton = $(".cart_checkout_link"),

    //Checkout Overview
    paymentInformationLabel = $x("//div[@class='summary_info_label']" +
            "[contains(text(),'Payment Information')]/following-sibling::div[1]"),
    shippingInformation = $x("//div[@class='summary_info_label']" +
            "[contains(text(),'Shipping Information')]/following-sibling::div[1]"),
    itemTotalAmount = $(".summary_subtotal_label"),
    taxAmount = $(".summary_tax_label"),
    totalAmount = $(".summary_total_label"),

    //Checkout Complete!
    completeHeader = $(".complete-header"),
    completeText = $(".complete-text"),
    completeImg = $(".checkout_complete_container > img");

    public void InputDataAndClick(String firstName, String lastName, String postalCode) {
        firstNameInputField.shouldBe(Condition.visible).setValue(firstName);
        lastNameInputField.shouldBe(Condition.visible).setValue(lastName);
        zipPostalCodeInputField.shouldBe(Condition.visible).setValue(postalCode);
        submitButton.shouldBe(Condition.visible, Condition.enabled).click();
    }
}
