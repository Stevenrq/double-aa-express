package org.doubleaaexpress.models;

import java.time.LocalDate;

public class Buyer extends User {

    public Buyer() {
        super();
    }

    public Buyer(Long id, String firstName, String lastName, String phoneNumber, LocalDate birthDate,
                         String email, String password) {
        super(id, firstName, lastName, phoneNumber, birthDate, email, password);
    }
}
