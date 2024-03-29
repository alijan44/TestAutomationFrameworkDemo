package pages;

import base.PageActions;
import org.openqa.selenium.By;

public class CheckoutPage extends PageActions {
    // --- List of Element Locations ---
    private By first_name = By.xpath("/html//input[@id='first-name']");
    private By last_name = By.xpath("/html//input[@id='last-name']");
    private By zip_code = By.xpath("/html//input[@id='postal-code']");
    private By continue_button = By.xpath("/html//input[@id='continue']");
    private By finish_Button = By.xpath("/html//button[@id='finish']");
    private By thank_you = By.xpath("//div[@id='checkout_complete_container']/img[@alt='Pony Express']");
    private By t_shirt_display = By.xpath("//div[@id='checkout_summary_container']//div[@class='cart_list']//a[@href='./inventory-item.html?id=1']/div[@class='inventory_item_name']");
    private By jacket_display= By.xpath("//a[@id='item_5_title_link']/div[@class='inventory_item_name']");

    // --- List of Methods (User Actions) ---
    public void enterFirstName(String firstName){
        write(first_name,firstName);
    }

    public void enterLastName(String lastName){
        write(last_name, lastName);
    }

    public void enterZipCode(String zipCode){
        write(zip_code, zipCode);
    }

    public void clickContinue(){
        click(continue_button);
    }

    public void clickFinish(){
        click(finish_Button);
    }

    public boolean thankYouIsDisplayed(){
        return isElementDisplayed(thank_you);
    }

    public boolean tShirtDisplayed(){
        return isElementDisplayed(t_shirt_display);
    }

    public boolean jacketDisplayed(){
        return isElementDisplayed(jacket_display);
    }

}
