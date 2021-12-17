package user;

import handlers.FakeDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.commons.TopMenuPage;
import pages.mainPage.MainPage;
import pages.registrationPage.RegistrationPage;
import pages.user.LoginPage;
import testBase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class UserRegistrationTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(UserRegistrationTest.class);

    @Test
    @DisplayName("Register new user test")
    @Tag("userRgistration")
    @Tag("regression")
    void registerNewUser() {
        FakeDataGenerator fake = new FakeDataGenerator();
        TopMenuPage topMenuPage = new TopMenuPage(driver);

        String firstName = fake.getFakeFirstName();
        String lastName = fake.getFakeLastName();
        String username = firstName + " " + lastName;

        new MainPage(driver).
                openWebsite().
                navigateToTopMenuPage();
        topMenuPage.
                goToLoginPage();
        new LoginPage(driver).
                clickOnNoAccountRegister();
        new RegistrationPage(driver).
                chooseRandomGender().
                fiilInFirstName(firstName).
                fiilInLastName(lastName).
                fiilInEmail(fake.getFakeEmail()).
                fiilInPassword(fake.getFakePasswordLimit(5, 20)).
                clickOnPrivacyCheckBox().
                clickOnTermsConditionsCheckBox().
                clickOnSubmitButton();

        assertThat(username, equalTo(topMenuPage.getDisplayedUserName()));
        logger.info("Displayed user name as added: {}", username);

    }
}
