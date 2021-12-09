package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='id_gender']")
    private List<WebElement> genderOptions;

    @FindBy(css = "[name='firstname']")
    private WebElement firstNameBox;

    @FindBy(css = "[name='lastname']")
    private WebElement lastNameBox;

    @FindBy(css = ".col-md-6 [name='email']")
    private WebElement emailBox;

    @FindBy(css = "[name='password']")
    private WebElement passwordBox;

    @FindBy(css = "[name='customer_privacy']")
    private WebElement customerPrivacyCheckBox;

    @FindBy(css = "[name='psgdpr']")
    private WebElement acceptTermsCheckBox;

    @FindBy(css = ".form-control-submit")
    private WebElement submitButton;

    public RegistrationPage chooseRandomGender() {
        waitForWebElementToBeClickable(genderOptions.get(0));
        getRandomWebElementFromList(genderOptions).click();
        logger.info("Selected random gender.");
        return this;
    }

    public RegistrationPage fiilInFirstName(String firstName){
        firstNameBox.sendKeys(firstName);
        logger.info("Filled in first name box with: {}",firstName);
        return this;
    }

    public RegistrationPage fiilInLastName(String lastName){
        lastNameBox.sendKeys(lastName);
        logger.info("Filled in last name box with: {}",lastName);
        return this;
    }

    public RegistrationPage fiilInEmail(String email){
        emailBox.sendKeys(email);
        logger.info("Filled in email box with: {}",email);
        return this;
    }

    public RegistrationPage fiilInPassword(String password){
        passwordBox.sendKeys(password);
        logger.info("Filled in password box with: {}",password);
        return this;
    }

    public RegistrationPage clickOnPrivacyCheckBox(){
        customerPrivacyCheckBox.click();
        logger.info("Clicked on agree to privacy policy checkbox");
        return this;
    }

    public RegistrationPage clickOnTermsConditionsCheckBox(){
        acceptTermsCheckBox.click();
        logger.info("Clicked on agree to terms and conditions checkbox");
        return this;
    }
    public TopMenuPage clickOnSubmitButton(){
        submitButton.click();
        logger.info("Clicked on submit button");
        return new TopMenuPage(driver);
    }
}


