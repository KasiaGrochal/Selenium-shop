package handlers;

import com.github.javafaker.Faker;
import models.Address;

import java.util.Locale;

public class AddressFactory {

    private Faker faker;

    public AddressFactory() {
        faker = new Faker(new Locale("PL"));
    }

    public Address getRandomAddress() {
        return new Address.Builder().
                city(faker.address().city()).
                street(faker.address().streetAddress()).
                zipCode(faker.address().zipCode()).
                country(System.getProperty("country")).
                build();
    }


}
