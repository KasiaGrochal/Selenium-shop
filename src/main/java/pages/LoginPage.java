package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static String expectedMessage = "Authentication failed.";
    public static String existingEmail = "existingemail@email.com";
    public static String existingPassword = "haslo5";


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
        waitForWebElementToBeClickable(emailBox);
        emailBox.sendKeys(email);
        logger.info("Filled email box with: {}", email);
        passwordBox.sendKeys(password);
        logger.info("Filled password box with: {}", password);
        logInButton.click();
        logger.info("Clicked on logIn button");
        return this;
    }

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
