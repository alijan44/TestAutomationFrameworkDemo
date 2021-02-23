package pages;

import base.UIActions;
import org.openqa.selenium.By;


public class CartPage extends UIActions {
    // --- List of Element Locations ---
    private By checkout_button = By.xpath("//div[@id='cart_contents_container']//div[@class='cart_footer']/a[@href='./checkout-step-one.html']");
    private By remove_tshirt = By.cssSelector(".cart_list > div:nth-of-type(3) .btn_secondary.cart_button");

    // --- List of Methods (User Actions) ---
    public void clickCheckout(){
        click(checkout_button);
        waitfor(2);
    }

    public void removeTshirt(){
        click(remove_tshirt);
        waitfor(2);
    }
}
