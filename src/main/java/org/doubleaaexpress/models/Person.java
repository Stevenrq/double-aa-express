package org.doubleaaexpress.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
}
