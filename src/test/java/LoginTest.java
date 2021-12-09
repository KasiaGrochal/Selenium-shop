import handlers.FakeDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.LoginPage;
import pages.MainPage;
import pages.TopMenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends TestBase {

    @Test
    @DisplayName("Log in with non-existing user.")
    void loginTestNegative() {
        FakeDataGenerator fake = new FakeDataGenerator();
        LoginPage loginPage = new LoginPage(driver);

        new MainPage(driver).
                openWebsite().
                navigateToTopMenuPage();
        new TopMenuPage(driver).
                goToLoginPage();
        loginPage.
                logIn(fake.getFakeEmail(), fake.getFakePasswordLimit(5, 99));

        assertThat(
                loginPage.getAlertMessage(), equalTo(LoginPage.expectedMessage));
        logger.info("Alert message as expected: {}", LoginPage.expectedMessage);
    }

    @Test
    @DisplayName("Log in with existing user.")
    void loginTestPositive() {
        TopMenuPage topMenuPage = new TopMenuPage(driver);

        new MainPage(driver).
                openWebsite().
                navigateToTopMenuPage();
        topMenuPage.
                goToLoginPage();
        new LoginPage(driver).
                logIn(LoginPage.existingEmail, LoginPage.existingPassword).
                navigateToTopMenuPage();

        assertThat(topMenuPage.isUserLoggedIn(), equalTo(true));
        logger.info("User is logged in as expected.");

        topMenuPage.
                clickOnAccountNameButton().
                logOut();

        assertThat(topMenuPage.isUserLoggedIn(), equalTo(false));
        logger.info("User is logged out as expected.");

    }
}
