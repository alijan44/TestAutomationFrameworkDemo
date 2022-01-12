package pages;

import base.PageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.DriverUtil;

public class HomePage extends PageActions {
    private WebDriver driver = DriverUtil.getDriver();
    private String url = "https://www.saucedemo.com/inventory.html";

    // --- List of Element Locations ---
    private By backpack_atc = By.xpath("/html//button[@id='add-to-cart-sauce-labs-backpack']");
    private By bike_light_atc = By.xpath("/html//button[@id='add-to-cart-sauce-labs-bike-light']");
    private By bolt_tshirt_atc = By.xpath("/html//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']");
    private By shopping_cart_button = By.cssSelector(".shopping_cart_badge");
    private By jacket_atc = By.cssSelector("button#add-to-cart-sauce-labs-fleece-jacket");
    private By remove_backpack = By.xpath("/html//button[@id='remove-sauce-labs-backpack']");


    // --- List of Methods (User Actions) ---
    public void openHomePage(){
        gotoSite(url);
        waitfor(2);
    }

    public void atcBackpack(){
        click(backpack_atc);
    }

    public void atcBikeLight(){
        click(bike_light_atc);
    }

    public void atcBoltTShirt(){
        click(bolt_tshirt_atc);
    }

    public void clickShoppingCart(){
        click(shopping_cart_button);
        waitfor(2);
    }

    public void atcJacket(){
        click(jacket_atc);
    }

    public void removeBackpack(){
        click(remove_backpack);
    }

}
