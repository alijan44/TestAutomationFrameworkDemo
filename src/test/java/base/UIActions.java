package base;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DriverUtil;
import utility.StandByUntil;

import java.util.ArrayList;
import java.util.List;

public abstract class UIActions {

    private static final Integer WAIT_TIME = 30;
    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static ArrayList<String> tabs;

    private static StandByUntil standByUntil;

    public UIActions() {
        driver = DriverUtil.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIME);
        standByUntil = new StandByUntil(wait);
    }

    /**
     * Calling this method will result in the browser switching to full screen mode.
     */
    protected void fullScreen() {
        driver.manage().window().fullscreen();
    }

    /**
     * Calling this method will result in browser maximizing the current window.
     */
    protected void maximize() {
        driver.manage().window().maximize();
    }

    /**
     * Call this method to switch to the default iframe.
     */
    protected void switchToIFrame() {
        WebElement iframe = waitUntilElementVisible(By.tagName("iframe"));
        driver = driver.switchTo().frame(iframe);
    }

    /**
     * Call this method to switch to the iframe specified by the locator.
     * @param locator Location of the element.
     */
    protected void switchToIFrame(By locator) {
        WebElement iframe = waitUntilElementVisible(locator);
        driver = driver.switchTo().frame(iframe);
    }

    /**
     * Call this method to switch back from the default iframe.
     */
    protected void switchBackFromIframe() {
        driver = driver.switchTo().defaultContent();
    }

    /**
     * Call this method to open new tabs.
     */
    protected void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * Switch to the tab identified by the tab number.
     * @param tabNumber Non-negative integer that represents the tab number.
     */
    protected void switchToTab(int tabNumber) {
        driver.switchTo().window(tabs.get(tabNumber));
    }

    /**
     * Call this method to close the tab.
     */
    protected void closeTab() {
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * Call this method to load specific cookies.
     * @param name Name of cookie.
     * @param token Token of cookie.
     */
    protected void loadCookie(String name, String token) {
        Cookie cookie = new Cookie(name, token);
        driver.manage().addCookie(cookie);
    }

    /**
     * Call this method to delete specific cookies.
     * @param name Name of cookie.
     */
    protected void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    /**
     * Call this method to delete all cookies.
     */
    protected void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
    //endregion

    /**
     * Call this method to go to a specified web page.
     * @param url URL of desired web page.
     */
    protected void gotoSite(String url) {
        driver.get(url);
    }

    /**
     * Call this method to refresh the page.
     */
    protected void reload() {
        driver.navigate().refresh();
    }

    /**
     * Call this method to go back to the previous page.
     */
    protected void goBack() {
        driver.navigate().back();
    }

    /**
     * Call this method to go forward to the previous page.
     */
    protected void goFoward() {
        driver.navigate().forward();
    }

    /**
     * Call this method to return the web page URL.
     * @return Web page URL.
     */
    protected String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Call this method to return the title of the web page.
     * @return Title of web page.
     */
    protected String title() {
        return driver.getTitle();
    }

    /**
     * Call this method to click using a locator.
     * @param locator Specified locator.
     */
    protected void click(By locator) {
        try {
            highlight(locator);
            WebElement element = standByUntil.elementIsClickable(locator);
            element.click();
        } catch (Exception ex) {
            System.out.println("Element was not clickable. Check its locators logic ( Ex: css, xpath .etc");
        }
    }

    /**
     * Call this method to click using a string of text.
     * @param text String desired to be clicked.
     */
    protected void click(String text) {
        String expression = "//*[text()='" + text + "']";
        By selector = By.xpath(expression);
        waitUntilElementVisible(selector);
        List<WebElement> foundElems = driver.findElements(selector);
        if (foundElems.size() == 0) {
            throw new NotFoundException("Element with given text could not be found.");
        } else {
            for (WebElement elem : foundElems) {
                elem.click();
                break;
            }
        }
    }

    /**
     * Call this method to double-click using a locator.
     * @param locator Specified locator.
     */
    protected void doubleClick(By locator) {
        highlight(locator);
        new Actions(driver)
                .doubleClick(standByUntil.elementIsClickable(locator))
                .build()
                .perform();
    }

    /**
     * Call this method to right click using a locator.
     * @param locator Specified locator.
     */
    protected void rightClick(By locator) {
        new Actions(driver)
                .contextClick(standByUntil.elementIsClickable(locator))
                .build()
                .perform();
    }

    /**
     * Call this method to hover over a specific locator.
     * @param locator Specified locator.
     */
    protected void hover(By locator) {
        WebElement elem = waitUntilElementVisible(locator);
        new Actions(driver).moveToElement(elem).build().perform();
    }

    /**
     * Call this method to set focus to a locator.
     * @param locator Specified locator.
     */
    protected void focus(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        if ("input".equals(element.getTagName())) {
            element.sendKeys("");
        } else {
            new Actions(driver).moveToElement(element).perform();
        }
    }

    /**
     * Call this method to move mouse down on an element.
     * @param element Specified web element.
     */
    protected void mouseDownOn(By element) {
        new Actions(driver)
                .moveToElement(waitUntilElementVisible(element))
                .clickAndHold().perform();
    }

    /**
     * Call this method to move mouse to a specific element.
     * @param element Specified web element.
     */
    protected void moveTo(By element) {
        new Actions(driver).moveToElement(waitUntilElementVisible(element))
                .build()
                .perform();
    }

    /**
     * Call this method to move mouse up on an element.
     * @param element Specified web element.
     */
    protected void mouseUpOn(By element) {
        new Actions(driver).moveToElement(waitUntilElementVisible(element))
                .release()
                .perform();
    }

    /**
     * Call this method to drag base to a target location.
     * @param base  What is being dragged to the target.
     * @param target Where the base is being dropped to.
     */
    protected void dragAndDrop(By base, By target) {
        mouseDownOn(base);
        moveTo(target);
        mouseUpOn(target);
    }

    /**
     * Call this method to move view to an element.
     * @param selector Specified selector.
     */
    protected void moveViewToElement(By selector) {
        WebElement where;
        Actions builder = new Actions(driver);
        where = waitUntilElementVisible(selector);
        builder.moveToElement(where).perform();
    }

    /**
     * Call this method to scroll to the bottom of a page.
     */
    protected void scrollToBottom() {
        String jscode = "window.scrollTo(0, document.body.scrollHeight)";
        ((JavascriptExecutor) driver).executeScript(jscode);
    }

    /**
     * Call this method to scroll to the top of a page.
     */
    protected void scrollToTop() {
        String jsCode = "window.scrollTo(0, 0)";
        ((JavascriptExecutor) driver).executeScript(jsCode);
    }

    /**
     * Call this method to scroll down by a specified number of pixels.
     * @param pixelnum Number of pixels.
     */
    protected void scrollDownByPixel(int pixelnum) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixelnum + ")");
    }

    /**
     * Call this method to scroll up by a specified number of pixels.
     * @param pixelnum Number of pixels.
     */
    protected void scrollUpByPixel(int pixelnum) {
        pixelnum = pixelnum - (pixelnum * 2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixelnum + ")");
    }

    /**
     * Call this method to highlight based on a locator.
     * @param locator Specified locator.
     */
    protected void highlight(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

    /**
     * Call this method to flash a specified locator.
     * @param locator Specified locator.
     */
    protected void flash(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid white;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid white;');", element);
        waitforMili(2);
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid blue;');", element);
    }

    /**
     * Call this method to highlight text by a locator.
     * @param locator Specified locator.
     */
    protected void textHighlight(By locator) {
        WebElement element = waitUntilElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCode = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
        js.executeScript(jsCode, element);
    }

    /**
     * Call this method to clear by a locator.
     * @param locator Specified locator.
     */
    protected void clear(By locator) {
        waitUntilElementVisible(locator).clear();
    }

    /**
     * Call this method to write a string of text at a locator.
     * @param locator Specified locator, where text will be written.
     * @param text String of text to be written at locator.
     */
    protected void write(By locator, String text) {
        highlight(locator);
        standByUntil.elementIsThereAndVisibleToUser(locator).sendKeys(text);
    }

    /**
     * Call this method to clear text at a locator and write a string of text in its place.
     * @param locator Specified locator, where the text will be cleared and rewritten.
     * @param text String of text to overwrite previous text at locator.
     */
    protected void clearThenWrite(By locator, String text) {
        WebElement inputElem = waitUntilElementVisible(locator);
        inputElem.clear();
        inputElem.sendKeys(text);
    }

    /**
     * Call this method to wait for a specified amount of seconds.
     * @param second Amount of seconds to wait for.
     */
    protected void waitfor(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (Exception ignored) {
            // DO NOTHING
        }
    }

    /**
     * Call this method to wait for a specified amount of milliseconds.
     * @param second Amount of milliseconds to wait for.
     */
    protected void waitforMili(int second) {
        try {
            Thread.sleep(second * 100);
        } catch (Exception ignored) {
            // DO NOTHING
        }
    }

    /**
     * Call this method to submit by an element.
     * @param element Specified element being submitted.
     */
    protected void submit(By element) {
        waitUntilElementVisible(element).submit();
    }

    /**
     * Call this method to return a desired a web element.
     * @param locator Specified locator of element.
     * @return The desired element.
     */
    protected WebElement element(By locator) {
        return standByUntil.elementIsThereAndVisibleToUser(locator);
    }

    /**
     * Call this method to return a list elements based on a locator.
     * @param locator Specified locator.
     * @return List of elements.
     */
    protected List<WebElement> listOfElements(By locator) {
        waitUntilElementVisible(locator);
        return driver.findElements(locator);
    }
    //endregion


    //region Selectors

    /**
     * Call this method to select a region by CSS.
     * @param expression Specified CSS.
     * @return Return desired selector.
     */
    protected By css(String expression) {
        return By.cssSelector(expression);
    }

    /**
     * Call this method to select a region by ID.
     * @param expression Specified ID.
     * @return Return desired selector.
     */
    protected By id(String expression) {
        return By.id(expression);
    }

    /**
     * Call this method to select a region by Xpath.
     * @param expression Specified Xpath.
     * @return Return desired selector.
     */
    protected By xpath(String expression) {
        return By.xpath(expression);
    }

    /**
     * Call this method to select a region by link text.
     * @param expression Specified link.
     * @return Return desired selector.
     */
    protected By link(String expression) {
        return By.linkText(expression);
    }

    /**
     * Call this method to select a region based on link text containing
     * a certain expression.
     * @param expression Specified expression within link text.
     * @return Return desired selector.
     */
    protected By linktextContains(String expression) {
        return By.partialLinkText(expression);
    }

    /**
     * Call this method to select a region based on named attributes.
     * @param expression Specified attributes.
     * @return Return desired selector.
     */
    protected By nameAttribute(String expression) {
        return By.name(expression);
    }

    /**
     * Call this method to select a region based on tags.
     * @param expression Specified tag.
     * @return Return desired selector.
     */
    protected By withTag(String expression) {
        return By.tagName(expression);
    }
    //endregion


    //region Waiters

    /**
     * Call this method to wait until an element is visible.
     * @param locator Specified locator.
     * @return Return if element is now visible.
     */
    private WebElement waitUntilElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Call this method to wait until an element is no longer visible.
     * @param locator Specified locator.
     * @return Return if element is no longer visible.
     */
    private boolean waitUntilElementInvisible(By locator) {
        return false;
    }

    /**
     * Call this method to see if there is an element is displayed.
     * @return Return whether an element is visible or not.
     */
    protected boolean isElementDisplayed(By locator) {
        return false;
    }
    //endregion


    //region Private Helper Methods

    /**
     * Call this method to click the Nth element on a page.
     * @param element Desired starting element.
     * @param index Index of what element you want to click, chronologically.
     */
    private void click_NthElement(By element, int index) {
        waitUntilElementVisible(element);
        List<WebElement> elementAllElements = driver.findElements(element);
        WebElement elementElementByIdx = elementAllElements.get(index);
        elementElementByIdx.click();
    }
    //endregion
}