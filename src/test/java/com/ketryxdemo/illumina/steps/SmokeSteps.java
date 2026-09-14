package com.ketryxdemo.illumina.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmokeSteps {
    private int result;

    @Given("the system is up")
    public void the_system_is_up() {
        result = 0;
    }

    @When("I add {int} and {int}")
    public void i_add(int a, int b) {
        result = a + b;
    }

    @Then("the result is {int}")
    public void the_result_is(int expected) {
        assertEquals(expected, result);
    }
}
