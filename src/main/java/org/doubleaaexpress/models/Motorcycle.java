package org.doubleaaexpress.models;

public class Motorcycle extends Product {

    private String model;
    private Short year;
    private String plateNumber;
    private String status;

    public Motorcycle() {
        super();
    }

    public Motorcycle(Long id, String name, Double price, String model, Short year, String plateNumber, String status) {
        super(id, name, price);
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
