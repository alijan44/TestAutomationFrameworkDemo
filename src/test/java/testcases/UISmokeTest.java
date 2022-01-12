package testcases;

import base.UITestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;


@Listeners(utility.TestListener.class)
public class UISmokeTest extends UITestBase {

    @Test
    public void sampleTestCase1(){
        //(testing to ensure confirmation message is displayed after purchase)
        // -- TEST DATA
        String firstName = faker().name().firstName();
        String lastName = faker().name().lastName();
        String zipCode = faker().address().zipCode();

        // -- TEST STEPS
        LOG("Logging into the website");
        LoginPage login = new LoginPage();
        login.openLoginPage();
        login.login();

        LOG("Adding items to the shopping cart");
        HomePage home = new HomePage();
        home.atcBackpack();
        home.atcBikeLight();
        home.atcBoltTShirt();
        home.clickShoppingCart();

        LOG("Checking out the item in the cart");
        CartPage cart = new CartPage();
        cart.clickCheckout();

        LOG("Entering shipping information");
        CheckoutPage checkout = new CheckoutPage();
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterZipCode(zipCode);
        checkout.clickContinue();
        checkout.clickFinish();

        LOG("Asserting that user saw the confirmation message");
        boolean result1 = checkout.thankYouIsDisplayed();
        Assert.assertTrue(result1);
        IMG("Test Capture");
    }

    @Test
    public void sampleTestCase2(){
        // --(testing to ensure that fleece jacket is displayed on final checkout page)
        // -- TEST DATA
        String firstName = faker().name().firstName();
        String lastName = faker().name().lastName();
        String zipCode = faker().address().zipCode();

        // -- TEST STEPS

        LOG("Logging into the website");
        LoginPage login = new LoginPage();
        login.openLoginPage();
        login.login();

        LOG("Adding/removing items from cart");
        HomePage home = new HomePage();
        home.atcBackpack();
        home.atcBoltTShirt();
        home.atcJacket();
        home.removeBackpack();
        home.clickShoppingCart();

        LOG("Removing Tshirt from cart on cart page");
        CartPage cart = new CartPage();
        cart.removeTshirt();
        cart.clickCheckout();

        LOG("Entering shipping information");
        CheckoutPage checkout = new CheckoutPage();
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterZipCode(zipCode);
        checkout.clickContinue();

        LOG("Asserting that jacket is displayed on final checkout page");
        boolean result1 = checkout.jacketDisplayed();
        Assert.assertTrue(result1);
        IMG("Jacket is Displayed");
    }

}
