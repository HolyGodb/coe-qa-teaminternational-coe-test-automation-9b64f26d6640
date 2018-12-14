import com.codeborne.selenide.Condition;
//import com.github.javafaker.Faker;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static com.codeborne.selenide.Selenide.open;

public class Test {

    //private WebDriver driver;
    //private Wait<WebDriver> wait;
    @DataProvider
    public static Object[][] CredentialsWithAllData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "Products", 6}
        };
    }

    @BeforeTest
    public void setupTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-plugins-discovery");
        chromeOptions.addArguments("--start-maximized");
        DesiredCapabilities dcap = new DesiredCapabilities();
    }

    @AfterTest
    public void teardown() {

    }

    @org.testng.annotations.Test(dataProvider = "CredentialsWithAllData")
    public void SauceDemoTest(String username, String password, String inventoryPageProducts, int inventoryPageCountOfProducts) {
        //Faker faker = new Faker();
        String firstName = "fakir";//faker.gameOfThrones().character();
        String lastName = "of ";//+ faker.gameOfThrones().city();
        String postalCode = "23200";//faker.address().zipCode();

        open("https://www.saucedemo.com/");

        Page page = new Page();

        //Login page
        LoginPage loginPage = new LoginPage();
        loginPage.loginMethod(username, password);

        //Inventory page
        InventoryPage inventoryPage = new InventoryPage();
        Assert.assertEquals(inventoryPage.productsLabel.shouldBe(Condition.visible).getText(), inventoryPageProducts);
        Assert.assertEquals(inventoryPage.listOfProducts.size(), inventoryPageCountOfProducts);
        //Find element with lowest price, click on it and click on shopping cart button
        inventoryPage.clickOnAddToCartButtonOnElementWithLowestPrice();
        inventoryPage.cartButton.shouldBe(Condition.visible).click();

        //Cart page
        CartPage cartPage = new CartPage();
        //Verify that there is text "Your Cart"
        Assert.assertEquals(cartPage.cartCheckoutTitle.shouldBe(Condition.visible).getText(), "Your Cart");
        //Verify that there is a 1 element in a cart (on the cart button)
        Assert.assertEquals(page.cartButtonCountOfItems.shouldBe(Condition.visible).getText(), "1");
        //Verify name of item
        Assert.assertEquals(cartPage.itemName.shouldBe(Condition.visible).getText(), "Sauce Labs Onesie");
        //Verify description of item
        Assert.assertEquals(cartPage.itemDescription.shouldBe(Condition.visible).getText(), "Rib snap infant " +
                "onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle " +
                "hemmed sleeved and bottom won't unravel.");
        //Verify item price and click Checkout button
        Assert.assertEquals(cartPage.itemPrice.shouldBe(Condition.visible).getText(), "7.99");
        cartPage.checkoutButton.shouldBe(Condition.visible, Condition.enabled).click();

        //Checkout: Your Information page
        //Verify checkout page title
        Assert.assertEquals(cartPage.cartCheckoutTitle.shouldBe(Condition.visible).getText(), "Checkout: " +
                "Your Information");
        //Send firstName, lastName, zipPostalCode
        cartPage.InputDataAndClick(firstName, lastName, postalCode);

        //Checkout: Overview page
        //Verify checkout page title
        Assert.assertEquals(cartPage.cartCheckoutTitle.shouldBe(Condition.visible).getText(), "Checkout: Overview");
        //Verify that there is a 1 element in a cart (on the cart button)
        Assert.assertEquals(page.cartButtonCountOfItems.shouldBe(Condition.visible).getText(), "1");
        Assert.assertEquals(cartPage.itemName.shouldBe(Condition.visible).getText(), "Sauce Labs Onesie");
        Assert.assertEquals(cartPage.itemDescription.shouldBe(Condition.visible).getText(), "Rib snap infant " +
                "onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed " +
                "sleeved and bottom won't unravel.");
        Assert.assertEquals(cartPage.itemPrice.shouldBe(Condition.visible).getText(), "$7.99");

        //Verify payment information
        Assert.assertEquals(cartPage.paymentInformationLabel.shouldBe(Condition.visible).getText(), "SauceCard #31337");
        Assert.assertEquals(cartPage.shippingInformation.shouldBe(Condition.visible).getText(), "FREE PONY EXPRESS DELIVERY!");
        Assert.assertEquals(cartPage.itemTotalAmount.shouldBe(Condition.visible).getText(), "Item total: $7.99");
        Assert.assertEquals(cartPage.taxAmount.shouldBe(Condition.visible).getText(), "Tax: $0.64");
        Assert.assertEquals(cartPage.totalAmount.shouldBe(Condition.visible).getText(), "Total: $8.63");

        //Click on "Finish" button
        cartPage.checkoutButton.shouldBe(Condition.visible, Condition.enabled).click();

        //Checkout: Complete!
        //Verify values on the page
        Assert.assertEquals(cartPage.cartCheckoutTitle.shouldBe(Condition.visible).getText(), "Checkout: Complete!");
        Assert.assertEquals(cartPage.completeHeader.shouldBe(Condition.visible).getText(), "THANK YOU FOR YOUR ORDER");
        Assert.assertEquals(cartPage.completeText.shouldBe(Condition.visible).getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        Assert.assertTrue(cartPage.completeImg.shouldBe(Condition.visible).isDisplayed());


    }

}
