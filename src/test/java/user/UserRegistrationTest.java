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
public class UserRegistrationTest extends Pages {

    @Test
    @DisplayName("Register new user test")
    @Tag("userRgistration")
    @Tag("regression")
    void registerNewUser() {
        User randomUser = new UserFactory().getRandomUser();

        topMenuPage.
                goToLoginPage().
                clickOnNoAccountRegister();
        registrationPage.
                fillInTheFormWithRandomData(randomUser).
                clickOnPrivacyCheckBox().
                clickOnTermsConditionsCheckBox().
                clickOnSubmitButton();

        assertThat(topMenuPage.getDisplayedUserName(), equalTo(randomUser.getFirstAndLastName()));

    }
}
