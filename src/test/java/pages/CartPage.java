package pages;

import base.PageActions;
import org.openqa.selenium.By;


public class CartPage extends PageActions {
    // --- List of Element Locations ---
    private By checkout_button = By.xpath("/html//button[@id='checkout']");
    private By remove_tshirt = By.cssSelector("button#remove-sauce-labs-bolt-t-shirt");

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
