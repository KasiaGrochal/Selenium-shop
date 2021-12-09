package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
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
