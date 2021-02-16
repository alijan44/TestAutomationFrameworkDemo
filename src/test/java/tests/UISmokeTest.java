package tests;

import base.UITestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;

public class UISmokeTest extends UITestBase {

    @Test
    public void sampleTestCase1(){
        // -- TEST DATA
        String firstName = faker().name().firstName();
        String lastName = faker().name().lastName();
        String zipCode = faker().address().zipCode();

        // -- TEST STEPS
        HomePage home = new HomePage();
        home.openHomePage();
        home.atcBackpack();
        home.atcBikeLight();
        home.atcBoltTShirt();
        home.clickShoppingCart();

        CartPage cart = new CartPage();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage();
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterZipCode(zipCode);
        checkout.clickContinue();
        checkout.clickFinish();

        boolean result1 = checkout.thankYouIsDisplayed();
        Assert.assertTrue(result1);

    }





}
