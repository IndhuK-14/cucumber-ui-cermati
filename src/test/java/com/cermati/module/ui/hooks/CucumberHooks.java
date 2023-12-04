package com.cermati.module.ui.hooks;


import com.cermati.module.ui.util.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {

    @Before
    public void launchBrowser() {
        Driver.initializeDriver();
    }

    @After
    public void afterRun() {
        //it will executed after scenario run
        Driver.getDriver().quit();
    }


}
