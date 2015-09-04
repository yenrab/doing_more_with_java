package com.doing.more.java.example;

public class ClothingCustomer extends Customer{
    double inseam;

    public ClothingCustomer(String aName, int anAge, double someYears, double inseam) {
        super(aName, anAge, someYears);
        this.inseam = inseam;
    }
}
