package base;

import com.github.javafaker.Faker;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.DriverUtil;
import utility.TestListener;

public abstract class UITestBase {
    private Faker faker;

    @BeforeMethod
    protected void setUp(){
        DriverUtil.openBrowser();
    }


    @AfterMethod
    protected void cleanUp(){
        DriverUtil.closeBrowser();
    }



    protected Faker faker(){
        if(faker == null){
            faker = new Faker();
            return faker;
        }
        return faker;
    }

    protected String getRandomUsername(){
        return faker().name().username();
    }

    protected void LOG(String steps) {
        TestListener.getTestCaseSection().info(steps);
    }

    protected void IMG(String title) {
        // Take a base64 screenshot
        String screenshot = ((TakesScreenshot)DriverUtil.getDriver()).getScreenshotAs(OutputType.BASE64);
        // Attaching the captured screenshot into the report
        TestListener.getTestCaseSection().addScreenCaptureFromBase64String(screenshot, title);
    }
}
