package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {

    private static WebDriver driver;
    private static String CHOSEN_BROWSER         = "";
    private static final String CHROME           = "chrome";
    private static final String CHROME_PRIVATE   = "chrome_private";
    private static final String CHROME_HEADLESS  = "chrome_headless";
    private static final String FIREFOX          = "firefox";
    private static final String FIREFOX_HEADLESS = "firefox_headless";
    private static final String FIREFOX_PRIVATE  = "firefox_private";
    private static final String EDGE             = "ms_edge";
    private static final String EDGE_PRIVATE     = "ms_edge_private";
    private static final String IE               = "ms_ie";

    // region Browser Actions

    public static void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // === Google Chrome Browsers === //
        CHOSEN_BROWSER = CHROME;
        driver.manage().window().maximize();
    }

    public static void openBrowser(String browserType) {
        if(browserType.equalsIgnoreCase(CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            CHOSEN_BROWSER = CHROME;
        }
        else if(browserType.equalsIgnoreCase(EDGE)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            CHOSEN_BROWSER = EDGE;
        }
        else if(browserType.equalsIgnoreCase(FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            CHOSEN_BROWSER = FIREFOX;
        }
    }

    public static WebDriver getDriver() {
        if(driver == null) {
            throw new NullPointerException("WebDriver instance is null");
        }
        return driver;
    }

    public static String getChosenBrowser() {
        return CHOSEN_BROWSER;
    }

    public static void closeBrowser() {
        if(CHOSEN_BROWSER.equals(FIREFOX)) {
            if(driver != null) {
                driver.quit();
                return;
            }
            return;
        }
        if(driver != null){
            driver.close();
            driver.quit();

        }


    }


}