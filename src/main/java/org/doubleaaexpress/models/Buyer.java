package org.doubleaaexpress.models;

import java.time.LocalDate;

public class Buyer extends User {

    public Buyer() {
        super();
    }

    public Buyer(Long id, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, address, birthDate, email, password);
    }
}
