package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.registrationPage.RegistrationPage;
import pages.commons.TopMenuPage;


public class LoginPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static String expectedMessage = "Authentication failed.";



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

    public LoginPage logIn(String email, String password) {
        sendKeysToObject(emailBox, email);
        sendKeysToObject(passwordBox, password);
        //loginAction(emailBox,passwordBox,myUser);
        clickObject(logInButton);
        return this;
    }
    /*public LoginPage loginAction(WebElement emailBox, WebElement passwordBox, User user){
        sendKeysToObject(emailBox, user.getEmail());
        sendKeysToObject(passwordBox, user.getPassword());
    }*/

    public String getAlertMessage() {
        String message = alertMessage.getText();
        logger.info("Displayed alert message: {}", message);
        return message;
    }

    public TopMenuPage navigateToTopMenuPage() {
        logger.info("Navigating to TopMenuPage");
        return new TopMenuPage(driver);
    }

    public RegistrationPage clickOnNoAccountRegister() {
        noAccountRegisterButton.click();
        logger.info("Clicked on 'No account? Create one here' button");
        return new RegistrationPage(driver);
    }


}
