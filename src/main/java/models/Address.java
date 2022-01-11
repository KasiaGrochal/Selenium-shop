package models;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String city;
    private String zipCode;
    private String country;

    public Address(Builder builder) {
        this.street = builder.street;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
        this.country = builder.country;
    }

    @Override
    public String toString(){
        return getStreet()+
                "\n"+ getZipCode() +" " +getCity()+
                "\n"+ getCountry();
    }
    public static final class Builder {
        private String street;
        private String city;
        private String zipCode;
        private String country;



        public Address.Builder street(String street){
            this.street=street;
            return this;
        }

        public Address.Builder city(String city){
            this.city=city;
            return this;
        }
        public Address.Builder zipCode(String zipCode){
            this.zipCode=zipCode;
            return this;
        }
        public Address.Builder country(String country){
            this.country=country;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
}
