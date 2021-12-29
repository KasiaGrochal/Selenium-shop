package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebListener extends AbstractWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(WebListener.class);

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.info("***********************EVENTLISTENER BEFORECLICK************************");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.info("***********************EVENTLISTENER AFTERCLICK************************");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver){
        logger.info("***********************EVENTLISTENER BEFORE NAVIGATE TO ************************");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver){
        logger.info("***********************EVENTLISTENER AFTER NAVIGATE TO************************");
    }
}
