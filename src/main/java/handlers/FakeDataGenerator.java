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

    public String getRandomPLZipCode(){
        return getRandomNumberFromRange(10,99)+"-"+getRandomNumberFromRange(100,999);
    }

    public Integer getRandomNumberFromRange(int startRange, int endRange){
        return random.ints(startRange, endRange).findFirst().getAsInt();
    }

    public String getFakeTitle(){
        return faker.name().prefix();
    }

    public String getFakeFirstName() {

        return faker.name().firstName();
    }

    public String getFakeLastName() {

        return faker.name().lastName();
    }

    public String getFakeFullName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public String getFakeFullNameLimit(int minChars, int maxChars) {
        return getValidatedByLimit(getFakeFullName(), minChars, maxChars);
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

    public String getFakeLogin() {
        return faker.name().username();
    }

    public String getFakePassword() {
        return faker.internet().password();
    }

    public String getFakeAdultAge() {
        return String.valueOf(random.ints(18, 100).findFirst().getAsInt());
    }

    public String getFakeBirthday(){
        return DateHandler.formatDateToddMMyyyy(faker.date().birthday());
    }

}
