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

    public User getExistingUser() {
        return new User.Builder().
                firstName(System.getProperty("existingName")).
                lastName(System.getProperty("existingLastName")).
                userEmail(System.getProperty("existingEmail")).
                userPassword(System.getProperty("existingPassword")).
                build();
    }

}
