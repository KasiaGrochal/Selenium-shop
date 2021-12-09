package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        this.webDriverwait = new WebDriverWait(driver,10);
        this.action = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
        this.screenshot =(TakesScreenshot) driver;
        PageFactory.initElements(driver, this);   }


    public void waitForWebElementToBeClickable(WebElement webElement) {
        logger.info("Start waiting for WebElement to be clickable- Timeout set to 10 seconds");
        webDriverwait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
    public void waitForWebElementToBeVisable(WebElement webElement){
        logger.info("Start waiting for Webelement to be visible- Timeout set to 10 seconds");
        webDriverwait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement getRandomWebElementFromList(List<WebElement> list) {
        int randomNumber = new Random().nextInt(list.size());
        return list.get(randomNumber);
    }



}
