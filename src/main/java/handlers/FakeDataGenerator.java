package handlers;

import com.github.javafaker.Faker;

import java.util.Random;

public class FakeDataGenerator {
    Faker faker;
    Random random;

    public FakeDataGenerator() {
        faker = new Faker();
        random = new Random();
    }

    public Integer getRandomNumberFromRange(int startRange, int endRange) {
        return random.ints(startRange, endRange).findFirst().getAsInt();
    }

    public String getFakeFirstName() {

        return faker.name().firstName();
    }

    public String getFakeLastName() {

        return faker.name().lastName();
    }

    public String getFakePasswordLimit(int minChars, int maxChars) {
        return getValidatedByLimit(getFakePassword(), minChars, maxChars);
    }

    private String getValidatedByLimit(String fakerType, int minChars, int maxChars) {
        String element = "";
        for (int i = 0; i < 1; i++) {
            if ((element.length() > maxChars) || (element.length() < minChars)) {
                element = fakerType;
                i--;
            }
        }
        return element;
    }

    public String getFakeEmail() {
        return faker.internet().emailAddress();
    }

    public String getFakePassword() {
        return faker.internet().password();
    }


}
