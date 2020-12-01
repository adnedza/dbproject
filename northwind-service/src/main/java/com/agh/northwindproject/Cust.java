package com.agh.northwindproject;
import org.springframework.data.annotation.Id;


public class Cust {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Cust() {}

    public Cust(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
