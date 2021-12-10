package pages.MainPage;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.topMenuPage.TopMenuPage;
import pages.basePage.BasePage;

public class MainPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        super(driver);
    }
    private final String webUrl = "http://146.59.32.4/";

    public MainPage openWebsite(){
        driver.get(webUrl);
        logger.info("Website {}, opened successfully on the Main Page.", webUrl);
        return new MainPage(driver);
    }

    public TopMenuPage navigateToTopMenuPage() {
        logger.info("Navigating to TopMenuPage");
        return new TopMenuPage(driver);
    }
}
