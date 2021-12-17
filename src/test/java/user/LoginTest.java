package user;

import handlers.FakeDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mainPage.MainPage;
import pages.user.LoginPage;
import pages.commons.TopMenuPage;
import testBase.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test
    @DisplayName("Log in with non-existing user.")
    @Tag("login")
    @Tag("regression")
    void loginTestNegative() {
        FakeDataGenerator fake = new FakeDataGenerator();
        LoginPage loginPage = new LoginPage(driver);

        new MainPage(driver).
                navigateToTopMenuPage();
        new TopMenuPage(driver).
                goToLoginPage();
        loginPage.
                logIn(fake.getFakeEmail(), fake.getFakePasswordLimit(5, 99));

        assertThat(
                loginPage.getAlertMessage(), equalTo(System.getProperty("failedLoginMessage")));
        logger.info("Alert message as expected: {}", System.getProperty("failedLoginMessage"));
    }

    @Test
    @DisplayName("Log in with existing user.")
    void loginTestPositive() {
        TopMenuPage topMenuPage = new TopMenuPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        new MainPage(driver).
                navigateToTopMenuPage();
        topMenuPage.
                goToLoginPage();
        loginPage.
                logIn(System.getProperty("existingEmail"), System.getProperty("existingPassword")).
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
