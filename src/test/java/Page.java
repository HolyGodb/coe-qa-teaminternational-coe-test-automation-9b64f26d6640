import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Page {
    SelenideElement cartButton = $(".fa-shopping-cart");
    SelenideElement cartButtonCountOfItems = $(".fa-layers-counter");
}
