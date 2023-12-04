package com.cermati.module.ui.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    public static WebDriver driver;

    public static WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
