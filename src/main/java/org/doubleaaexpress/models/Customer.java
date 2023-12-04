package org.doubleaaexpress.models;

import java.time.LocalDate;

public class Customer extends User {

    public Customer() {
        super();
    }

    public Customer(Long id, String firstName, String lastName, String phoneNumber, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, birthDate, email, password);
    }
}
