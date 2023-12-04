package com.cermati.module.ui.util;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utility {
    public static void waitAbit(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElement(WebElement element, int seconds) {
        if (seconds > 5)
            seconds = 5;
        final int MAX_ATTEMPT = 3;
        int attempt = 0;
        boolean success = false;
        while (!success && attempt < MAX_ATTEMPT) {
            try {
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(),seconds);
                wait.until(ExpectedConditions.visibilityOf(element));
                success = true;
            } catch (WebDriverException e) {
                e.printStackTrace();
                attempt++;
            }
        }

    }
}
