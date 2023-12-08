package org.doubleaaexpress.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User extends Person {

    private String email;
    private String password;

    public User() {
        super();
    }

    public User(Long id, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, address, birthDate);
        this.email = email;
        this.password = password;
    }
}
