package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class AccountPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(AccountPage.class);
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = ".page-footer a")
    private WebElement logOutButton;

    public AccountPage logOut(){
        waitForWebElementToBeClickable(logOutButton);
        logOutButton.click();
        logger.info("Clicked on log out button");
        return this;
    }
}
