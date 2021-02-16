package pages;

import base.UIActions;
import org.openqa.selenium.By;

public class CheckoutPage extends UIActions {
    // --- List of Element Locations ---
    private By first_name = By.xpath("/html//input[@id='first-name']");
    private By last_name = By.xpath("/html//input[@id='last-name']");
    private By zip_code = By.xpath("/html//input[@id='postal-code']");
    private By continue_button = By.xpath("//div[@id='checkout_info_container']//form//input[@value='CONTINUE']");
    private By finish_Button = By.xpath("//div[@id='checkout_summary_container']//div[@class='summary_info']//a[@href='./checkout-complete.html']");
    private By thank_you = By.xpath("//div[@id='checkout_complete_container']/h2[@class='complete-header']");

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

}
