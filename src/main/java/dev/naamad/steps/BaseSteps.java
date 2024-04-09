package dev.naamad.steps;

import dev.naamad.enums.Environment;
import dev.naamad.enums.Page;
import dev.naamad.factories.WebDriverFactory;
import dev.naamad.pages.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.qameta.allure.Attachment;
import java.net.MalformedURLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public abstract class BaseSteps {
    private static final Logger LOGGER = LogManager.getLogger(BaseSteps.class);
    private final Environment environment;
    private WebDriver driver;
    private BasePage expectedPage;


    protected BaseSteps(Environment environment) {
        this.environment = environment;
    }

    protected WebDriver createWebDriver(Environment environment, MutableCapabilities options) throws MalformedURLException {
        if (environment == Environment.REMOTE) {
            return WebDriverFactory.getRemoteDriver(System.getProperty("hub"), options);
        } else {
            return WebDriverFactory.getDriver(environment, options);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void startSession() throws MalformedURLException {
        MutableCapabilities options = initCapabilities();
        driver = createWebDriver(environment, options);
    }

    public void iShouldBeOnPage(Page page){
        expectedPage = page.getPage(this);
        logToConsoleAndReporter(String.format("page URL is: %s", driver.getCurrentUrl()));
        boolean isExpectedPage = expectedPage.verifyPage();
        Assert.assertTrue(isExpectedPage, "actual page is not " + page);
        logToConsoleAndReporter(String.format("we are on page: %s as expected", page));
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePage> T getExpectedPage(){
        return (T) expectedPage;
    }

    @Step("navigating to URL: {0}")
    public void navigateToUrl(String url) {
        driver.get(url);
        logToConsoleAndReporter(String.format("Navigated to URL: %s", url));
    }

    public void setText(WebElement element, String text){
        boolean isDisplayed = isElementDisplayed(element);
        if(isDisplayed){
            element.clear();
            element.sendKeys(text);
            logToConsoleAndReporter(String.format("entered the text: %s in the text box", text));
        }
    }

    public String getText(WebElement element){
        boolean isDisplayed = isElementDisplayed(element);
        return isDisplayed ? element.getText() : " ";
    }

    @Step("checking if element is displayed. element is: {0}")
    public boolean isElementDisplayed(WebElement element){
        try{
            waitFor(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    @Step("clicked on element: {0}")
    public void clickOnElement(WebElement element){
        boolean isClickable = isElementDisplayed(element);
        if(isClickable){
            element.click();
            logToConsoleAndReporter("clicked on element " + element);
        } else {
            logToConsoleAndReporter("wasn't able to click on element " + element);
        }
    }

    public WebElement findElementBy(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementDisabled(WebElement element){
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].hasAttribute('disabled)';", element);
    }

    public void tearDown(){
        if(driver != null) driver.quit();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public ChromeDriver getChromeDriver(){
        return (ChromeDriver) driver;
    }

    public void sleep(int timeInSeconds){
        try {
            TimeUnit.SECONDS.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void waitFor(ExpectedCondition<WebElement> webElementExpectedCondition){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webElementExpectedCondition);
    }

    private void logToConsoleAndReporter(String message) {
        String formattedMessage = String.format("[%s] %s", LocalDateTime.now(), message);
        LOGGER.info(formattedMessage);
        saveTextLog(formattedMessage);
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String saveTextLog(String text) {
        return text;
    }


    protected abstract MutableCapabilities initCapabilities();

}
