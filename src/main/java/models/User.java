package models;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPassword;


    public User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userEmail = builder.userEmail;
        this.userPassword = builder.userPassword;
    }

    public String getFirstAndLastName() {
        return firstName + " " + lastName;
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String userEmail;
        private String userPassword;


        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder userPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
