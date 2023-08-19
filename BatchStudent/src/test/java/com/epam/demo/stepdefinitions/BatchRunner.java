package com.epam.demo.stepdefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/resources", glue = "com.epam.demo.stepdefinitions")
public class BatchRunner {
}
