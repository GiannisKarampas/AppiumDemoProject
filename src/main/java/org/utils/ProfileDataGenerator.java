package org.utils;

import net.datafaker.Faker;

public class ProfileDataGenerator {
    private static final Faker faker = new Faker();

    public static Profile generateRandomProfile() {
        String emailAddress = faker.internet().emailAddress();
        String playerName = faker.internet().username();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String gender = faker.demographic().sex();

        return new Profile("", emailAddress, playerName, firstName, lastName, "", gender);
    }
}
