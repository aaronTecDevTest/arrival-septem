package com.arrival.cucumber.definitions;

import cucumber.api.java.en.Given;

/**
 * Created by Aaaron Pichou Kutekidila on 05.07.2016.
 */

public class MyStepdefs {
    @Given("I have (\\d+) cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        System.out.format("Cukes: %n\n", cukes);
    }


}
