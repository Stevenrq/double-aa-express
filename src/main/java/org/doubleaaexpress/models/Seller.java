package org.doubleaaexpress.models;

import java.time.LocalDate;

public class Seller extends User{

    public Seller() {
        super();
    }

    public Seller(Long id, String firstName, String lastName, String phoneNumber, LocalDate birthDate,
                    String email, String password) {
        super(id, firstName, lastName, phoneNumber, birthDate, email, password);
    }
}
