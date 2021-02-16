package base;

import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.DriverUtil;

public abstract class UITestBase {
    private Faker faker;

    @BeforeMethod
    public void setUp(){
        DriverUtil.openBrowser();
    }


    @AfterMethod
    public void cleanUp(){
        DriverUtil.closeBrowser();
    }



    public Faker faker(){
        if(faker == null){
            faker = new Faker();
            return faker;
        }
        return faker;
    }
    public String getRandomUsername(){
        return faker().name().username();
    }


}
