package tests;

import base.UITestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;


@Listeners(utility.TestListener.class)
public class UISmokeTest extends UITestBase {

    @Test
    public void sampleTestCase1(){
        // -- TEST DATA
        String firstName = faker().name().firstName();
        String lastName = faker().name().lastName();
        String zipCode = faker().address().zipCode();

        // -- TEST STEPS
        LOG("Adding items to the shopping cart");
        HomePage home = new HomePage();
        home.openHomePage();
        home.atcBackpack();
        home.atcBikeLight();
        home.atcBoltTShirt();
        home.clickShoppingCart();

        LOG("Checking out the item in the cart");
        CartPage cart = new CartPage();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage();
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterZipCode(zipCode);
        checkout.clickContinue();
        checkout.clickFinish();

        LOG("Asserting that user saw the confirmation message");
        boolean result1 = checkout.thankYouIsDisplayed();
        Assert.assertTrue(result1);
        IMG("Test Caputre");
    }

    @Test
    public void sampleTestCase2(){
        // -- TEST DATA
        String firstName = faker().name().firstName();
        String lastName = faker().name().lastName();
        String zipCode = faker().address().zipCode();

        // -- TEST STEPS
        HomePage home = new HomePage();
        home.openHomePage();
        home.atcBackpack();
        home.atcBoltTShirt();
        home.atcJacket();
        home.removeBackpack();
        home.clickShoppingCart();

        CartPage cart = new CartPage();
        cart.removeTshirt();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage();
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterZipCode(zipCode);
        checkout.clickContinue();

        boolean result1 = checkout.jacketDisplayed();
        Assert.assertTrue(result1);
    }

}
