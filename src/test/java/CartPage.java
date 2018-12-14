import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends Page{
    SelenideElement cartCheckoutTitle = $(".subheader_label");
    SelenideElement itemName = $(".inventory_item_name");
    SelenideElement itemDescription = $(".inventory_item_desc");
    SelenideElement itemPrice = $(".inventory_item_price");
    SelenideElement checkoutButton = $(".cart_checkout_link");

    //Checkout your information
    SelenideElement firstNameInputField = $("[data-test=firstName]");
    SelenideElement lastNameInputField = $("[data-test=lastName]");
    SelenideElement zipPostalCodeInputField = $("[data-test=postalCode]");
    SelenideElement submitButton = $(".cart_checkout_link");

    //Checkout Overview
    SelenideElement paymentInformationLabel = $x("//div[@class='summary_info_label']" +
            "[contains(text(),'Payment Information')]/following-sibling::div[1]");
    SelenideElement shippingInformation = $x("//div[@class='summary_info_label']" +
                            "[contains(text(),'Shipping Information')]/following-sibling::div[1]");
    SelenideElement itemTotalAmount = $(".summary_subtotal_label");
    SelenideElement taxAmount = $(".summary_tax_label");
    SelenideElement totalAmount = $(".summary_total_label");

    //Checkout Complete!
    SelenideElement completeHeader = $(".complete-header");
    SelenideElement completeText = $(".complete-text");
    SelenideElement completeImg = $(".checkout_complete_container > img");

    public void InputDataAndClick(String firstName, String lastName, String postalCode)
    {
        firstNameInputField.shouldBe(Condition.visible).setValue(firstName);
        lastNameInputField.shouldBe(Condition.visible).setValue(lastName);
        zipPostalCodeInputField.shouldBe(Condition.visible).setValue(postalCode);
        submitButton.shouldBe(Condition.visible, Condition.enabled).click();
    }
}
