package pages;

import base.PageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.DriverUtil;

public class LoginPage extends PageActions {
    private WebDriver driver = DriverUtil.getDriver();
    private String url = "https://www.saucedemo.com/inventory.html";

    // --- List of Element Locations ---
    private By username_box = By.xpath("/html//input[@id='user-name']");
    private By password_box = By.xpath("/html//input[@id='password']");
    private By login_button = By.xpath("/html//input[@id='login-button']");

    // --- List of Methods (User Actions) ---
    public void openLoginPage(){
        gotoSite(url);
        waitfor(2);
    }
    public void login(){
        click(username_box);
        write(username_box, "standard_user");
        waitfor(2);
        write(password_box, "secret_sauce");
        waitfor(2);
        click(login_button);
        waitfor(2);
    }

}
