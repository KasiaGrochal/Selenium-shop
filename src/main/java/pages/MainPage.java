package pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

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
