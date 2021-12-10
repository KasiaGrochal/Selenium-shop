import handlers.FakeDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.loginPage.LoginPage;
import pages.MainPage.MainPage;
import pages.registrationPage.RegistrationPage;
import pages.topMenuPage.TopMenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class UserRegistrationTest extends TestBase {

    @RepeatedTest(5)
    @DisplayName("Register new user")
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
