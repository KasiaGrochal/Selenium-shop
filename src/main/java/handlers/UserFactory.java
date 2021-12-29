package handlers;

import models.User;

public class UserFactory {
    private FakeDataGenerator faker;

    public UserFactory() {
        faker = new FakeDataGenerator();
    }

    public User getRandomUser() {
        return new User.Builder().
                firstName(faker.getFakeFirstName()).
                lastName(faker.getFakeLastName()).
                userEmail(faker.getFakeEmail()).
                userPassword(faker.getFakePasswordLimit(5,20)).
                build();
    }

}
