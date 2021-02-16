package pages;

import base.UIActions;
import org.openqa.selenium.By;


public class CartPage extends UIActions {
    // --- List of Element Locations ---
    private By checkout_button = By.xpath("//div[@id='cart_contents_container']//div[@class='cart_footer']/a[@href='./checkout-step-one.html']");

    // --- List of Methods (User Actions) ---
    public void clickCheckout(){
        click(checkout_button);
        waitfor(2);
    }



}
