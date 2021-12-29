package handlers;

import com.github.javafaker.Faker;
import models.Address;

public class AddressFactory {

    private Faker faker;
    private FakeDataGenerator faker2;

    public AddressFactory() {
        faker = new Faker();
        faker2=new FakeDataGenerator();
    }

    public Address getRandomAddress() {
        return new Address.Builder().
                city(faker.address().city()).
                street(faker.address().streetAddress()).
                zipCode(faker2.getRandomPLZipCode()).
                country(faker.address().country()).
                build();
    }

    public Address getRandomAddressPoland() {
        return new Address.Builder().
                city(faker.address().city()).
                street(faker.address().streetAddress()).
                zipCode(faker2.getRandomPLZipCode()).
                country("Poland").
                build();
    }


}
