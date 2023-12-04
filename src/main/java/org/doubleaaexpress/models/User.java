package org.doubleaaexpress.models;

import java.time.LocalDate;

public class User extends Person {

    private String email;
    private String password;

    public User() {
        super();
    }

    public User(Long id, String firstName, String lastName, String phoneNumber, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, birthDate);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
