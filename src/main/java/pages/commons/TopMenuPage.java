package pages.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.user.AccountPage;
import pages.BasePage;
import pages.user.LoginPage;

import java.util.List;

public class TopMenuPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(TopMenuPage.class);

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = ".user-info span")
    private WebElement loginButton;

    @FindBy (css = ".account span")
    private WebElement accountLoggedInFullName;

    @FindBy (css = ".user-info a")
    private List<WebElement> userInfo;

    public LoginPage goToLoginPage(){
        clickObject(loginButton);
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn(){
        return userInfo.size() > 1;
    }

    public String getDisplayedUserName(){
        String displayed = accountLoggedInFullName.getText();
        logger.info("Displayed username is: {}", displayed);
        return displayed;
    }

    public AccountPage clickOnAccountNameButton(){
        accountLoggedInFullName.click();
        logger.info("Clicked on account name button. Navigating to Account Page");
        return new AccountPage(driver);
    }



}
