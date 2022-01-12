package model;

import configuration.AppProperties;

import configuration.browser.BrowserProperties;
import configuration.browser.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestBase {
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;
    private static DriverFactory browserFactory;
    private static AppProperties appProperties;



    @BeforeAll
    static void setDriver() {
        appProperties = new AppProperties();
        logger.info("Initialized environment properties");
        browserFactory = new DriverFactory();
        logger.info("Initialized browser environment");
    }

    @BeforeEach
    void setup() {
        driver = browserFactory.getDriver(new BrowserProperties().getActiveBrowser());
        logger.info("configuration.browser.Browser initialized successfully");
        driver.get(System.getProperty("webUrl"));
        logger.info("Website opened at: {}", System.getProperty("webUrl"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Browser closed properly");
    }
}
