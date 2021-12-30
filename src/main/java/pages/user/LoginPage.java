package pages.user;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;


public class LoginPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".col-md-6 [name='email']")
    private WebElement emailBox;

    @FindBy(css = "[name='password']")
    private WebElement passwordBox;

    @FindBy(css = "#submit-login")
    private WebElement logInButton;

    @FindBy(css = ".alert")
    private WebElement alertMessage;

    @FindBy(css = ".no-account a")
    private WebElement noAccountRegisterButton;


    public LoginPage logInUser(User user) {
        logIn(user.getUserEmail(), user.getUserPassword());
        return this;
    }

    public LoginPage logIn(String email, String password) {
        sendKeysToObject(emailBox, email);
        sendKeysToObject(passwordBox, password);
        clickOnButton(logInButton);
        return this;
    }

    public String getAlertMessage() {
        return getTextFromObject(alertMessage);
    }

    public RegistrationPage clickOnNoAccountRegister() {
        clickOnButton(noAccountRegisterButton);
        return new RegistrationPage(driver);
    }


}
