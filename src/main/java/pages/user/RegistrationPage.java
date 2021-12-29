package pages.user;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.commons.TopMenuPage;


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


    //STEP:
    public RegistrationPage registerNewUser(User randomUser){
        new TopMenuPage(driver).
                goToLoginPage().
                clickOnNoAccountRegister();
        new RegistrationPage(driver).
                fillInTheFormWithRandomData(randomUser).
                clickOnPrivacyCheckBox().
                clickOnTermsConditionsCheckBox().
                clickOnSubmitButton();
        return this;
    }

    public RegistrationPage fillInTheFormWithRandomData(User randomUser){
        chooseRandomGender();
        fiilInFirstName(randomUser.getFirstName());
        fiilInLastName(randomUser.getLastName());
        fiilInEmail(randomUser.getUserEmail());
        fiilInPassword(randomUser.getUserPassword());
        return this;
    }

    public RegistrationPage chooseRandomGender() {
        clickOnCheckBox(getRandomWebElementFromList(genderOptions));
        return this;
    }

    public RegistrationPage fiilInFirstName(String firstName){
        sendKeysToObject(firstNameBox,firstName);
        return this;
    }

    public RegistrationPage fiilInLastName(String lastName){
        sendKeysToObject(lastNameBox,lastName);
        return this;
    }

    public RegistrationPage fiilInEmail(String email){
        sendKeysToObject(emailBox,email);
        return this;
    }

    public RegistrationPage fiilInPassword(String password){
        sendKeysToObject(passwordBox,password);
        return this;
    }

    public RegistrationPage clickOnPrivacyCheckBox(){
        clickOnCheckBox(customerPrivacyCheckBox);
        return this;
    }

    public RegistrationPage clickOnTermsConditionsCheckBox(){
        clickOnCheckBox(acceptTermsCheckBox);
        return this;
    }
    public TopMenuPage clickOnSubmitButton(){
        clickOnButton(submitButton);
        return new TopMenuPage(driver);
    }
}


