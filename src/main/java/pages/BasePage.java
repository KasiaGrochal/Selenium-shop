package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public abstract class BasePage {
    Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected Random rnd;
    protected WebDriverWait webDriverwait;
    protected Actions action;
    protected JavascriptExecutor jsExecutor;
    protected TakesScreenshot screenshot;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.rnd = new Random();
        this.webDriverwait = new WebDriverWait(driver, Integer.parseInt(System.getProperty("wait")));
        this.action = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
        this.screenshot = (TakesScreenshot) driver;
        PageFactory.initElements(driver, this);
    }


    public void waitForElementToBeVisibleFluent(WebElement webElement) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.pollingEvery(Duration.ofSeconds(1));
            wait.withTimeout(Duration.ofSeconds(Integer.parseInt(System.getProperty("wait"))));
            wait.ignoring(NoSuchElementException.class);
            wait.until(x -> webElement.isDisplayed());
        } catch (NoSuchElementException e) {
            logger.error("Timeout exception webelement: {}, doesn't exist", webElement.getText());
        }
    }

    public void waitForElementToBeClickableFluent(WebElement webElement) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.pollingEvery(Duration.ofSeconds(1));
            wait.withTimeout(Duration.ofSeconds(Integer.parseInt(System.getProperty("wait"))));
            wait.ignoring(NoSuchElementException.class, ElementClickInterceptedException.class);
            wait.until(x -> ExpectedConditions.elementToBeClickable(webElement));
        } catch (NoSuchElementException e) {
            logger.error("Timeout exception webelement: {}, doesn't exist", webElement.getText());
        } catch (ElementClickInterceptedException p) {
            logger.info(">>>>>>>>>>>>>>>>>ElementClickInterceptedException<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    public void waitForAttributeToBe(WebElement webElement, String attribute, String attributeValue) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.pollingEvery(Duration.ofSeconds(1));
            wait.withTimeout(Duration.ofSeconds(Integer.parseInt(System.getProperty("wait"))));
            wait.ignoring(NoSuchElementException.class);
            wait.until(x -> ExpectedConditions.attributeToBe(webElement, attribute, attributeValue));
        } catch (NoSuchElementException e) {
            logger.error("Timeout exception webelement: {}, doesn't exist", webElement.getText());
        }
    }

    public void noStaleClick(WebElement webElement) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                webElement.click();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public void waitForWebElementToBeClickable(WebElement webElement) {
        logger.info("Start waiting for WebElement to be clickable- Timeout set to {} seconds", System.getProperty("wait"));
        webDriverwait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForWebElementToBeVisable(WebElement webElement) {
        logger.info("Start waiting for WebElement to be visible- Timeout set to {} seconds", System.getProperty("wait"));
        webDriverwait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement getRandomWebElementFromList(List<WebElement> list) {
        int randomNumber = new Random().nextInt(list.size());
        return list.get(randomNumber);
    }

    public void click(WebElement webElement) {
        waitForWebElementToBeClickable(webElement);
        String webElementText = getText(webElement);
        webElement.click();
        logger.info("Clicked on webelement: {}", webElementText);
    }

    public void send(WebElement webElement, String text) {
        waitForWebElementToBeVisable(webElement);
        webElement.clear();
        webElement.sendKeys(text);
        logger.info("Typed text '{}' to webelement: {}", text, webElement.getAttribute("class"));
    }

    public String getText(WebElement webElement) {
        String result = "";
        int attempts = 0;
        while (attempts < 2) {
            try {
                result = webElement.getText();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        logger.info("Displayed text from webelement: {}", result);
        return result;

    }

    public void clickOnCheckBox(WebElement webElement) {
        String webElementText = webElement.getText();
        webElement.click();
        logger.info("Clicked on webelement: {}", webElementText);
    }


    public String selectRandomOption(WebElement element, int startIndex) {
        Select select = new Select(element);
        int random = rnd.ints(startIndex, select.getOptions().size()).findFirst().getAsInt();
        String selectedElement = select.getOptions().get(random).getText();
        select.selectByIndex(random);
        return selectedElement;
    }


}
