package user;

import handlers.UserFactory;
import model.Pages;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends Pages {

    @Test
    @DisplayName("Log in with non-existing user.")
    @Tag("login")
    @Tag("regressionSmall")
    void loginTestNegative() {
        User nonExistingUser = new UserFactory().getRandomUser();

        topMenuPage.
                goToLoginPage();
        loginPage.
                logInUser(nonExistingUser);

        assertThat(loginPage.getAlertMessage(), equalTo(System.getProperty("failedLoginMessage")));
    }

    @Test
    @DisplayName("Log in with existing user.")
    @Tag("login")
    @Tag("regressionSmall")
    void loginTestPositive() {
        User existingUser = new UserFactory().getExistingUser();

        topMenuPage.
                goToLoginPage();
        loginPage.
                logInUser(existingUser).

                navigateToTopMenuPage();
        assertThat(topMenuPage.isUserLoggedIn(), equalTo(true));

        topMenuPage.
                clickOnAccountNameButton().
                logOut();

        assertThat(topMenuPage.isUserLoggedIn(), equalTo(false));
    }
}
