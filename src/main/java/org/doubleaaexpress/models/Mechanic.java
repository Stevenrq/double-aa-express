package org.doubleaaexpress.models;

import java.time.LocalDate;

public class Mechanic extends User {

    public Mechanic() {
        super();
    }

    public Mechanic(Long id, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate,
            String email, String password) {
        super(id, firstName, lastName, phoneNumber, address, birthDate, email, password);
    }
}
