package pages;

import base.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.DriverUtil;

public class HomePage extends UIActions {
    private WebDriver driver = DriverUtil.getDriver();
    private String url = "https://www.saucedemo.com/inventory.html";

    // --- List of Element Locations ---
    private By backpack_atc = By.xpath("/html//div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[1]/div[@class='pricebar']/button[.='ADD TO CART']");
    private By bike_light_atc = By.xpath("/html//div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[2]/div[@class='pricebar']/button[.='ADD TO CART']");
    private By bolt_tshirt_atc = By.xpath("/html//div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[3]/div[@class='pricebar']/button[.='ADD TO CART']");
    private By shopping_cart_button = By.cssSelector("path");
    private By jacket_atc = By.cssSelector("div:nth-of-type(4) > .pricebar > .btn_inventory.btn_primary");
    private By remove_backpack = By.xpath("/html//div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[1]/div[@class='pricebar']/button[.='REMOVE']");


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
