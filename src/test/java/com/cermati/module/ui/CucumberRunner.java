package com.cermati.module.ui;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        plugin = {"json:target/destination/cucumber.json","pretty", "html:target/report.html"},
        glue = {"com.cermati.module.ui.hooks","com.cermati.module.ui.steps"},
        stepNotifications = true,
        tags = " ")
public class CucumberRunner {

}
