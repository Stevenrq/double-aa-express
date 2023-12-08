package org.doubleaaexpress.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    @Override
    public String toString() {
        return getName();
    }
}
