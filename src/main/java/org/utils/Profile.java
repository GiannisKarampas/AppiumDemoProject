package org.utils;

public class Profile {
    private String fullName;
    private String emailAddress;
    private String playerName;
    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;

    public Profile(String fullName, String emailAddress, String playerName, String firstName, String lastName, String birthday, String gender) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.playerName = playerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "emailAddress='" + emailAddress + '\'' +
                ", playerName='" + playerName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
