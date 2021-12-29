package user;

import model.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.user.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends Pages {

    @Test
    @DisplayName("Log in with non-existing user.")
    @Tag("login")
    @Tag("regressionSmall")
    void loginTestNegative() {

        topMenuPage.
                goToLoginPage();
        loginPage.
                logInNonExistingUser();

        assertThat(
                new LoginPage(webdriver).getAlertMessage(), equalTo(System.getProperty("failedLoginMessage")));
    }

    @Test
    @DisplayName("Log in with existing user.")
    @Tag("login")
    @Tag("regressionSmall")
    void loginTestPositive() {

        topMenuPage.
                goToLoginPage();
        loginPage.
                logInExistingUser().
                navigateToTopMenuPage();
        assertThat(topMenuPage.isUserLoggedIn(), equalTo(true));

        topMenuPage.
                clickOnAccountNameButton().
                logOut();

        assertThat(topMenuPage.isUserLoggedIn(), equalTo(false));
    }
}
