package com.cermati.module.ui.pages;


import com.cermati.module.ui.util.Driver;
import com.cermati.module.ui.util.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class EbayHomePage {

    String shopByCategory = "gh-shop-a";
    String category = "//div[@id='gh-sbc-o']//a[contains(text(),'Cell phones & accessories')]";
    String verifyMainCategory = "(//a[@class='seo-breadcrumb-text'])[2]//span";
    String verifySubCategory = "(//a[@class='seo-breadcrumb-text'])[3]//span";
    String subCategory = "//div[@id='leftnav']//a[contains(text(),'Cell Phones & Smartphones')]";
    String allFilter = "//section[@class='brm b-refine-menu']//button[@class='brm__all-filters fake-link']";
    String conditionFilter = "//div[@id='c3-mainPanel-condition']";
    String priceFilter = "//div[@id='c3-mainPanel-price']";
    String itemLocationFilter = "//div[@id='c3-mainPanel-location']";
    String selectCondition = "//div[@class='x-overlay__sub-panel']//span[contains(text(),'Open box')]";
    String minPrice = "//div[@class='x-overlay__sub-panel']//input[@class='x-textrange__input x-textrange__input--from']";
    String maxPrice = "//div[@class='x-overlay__sub-panel']//input[@class='x-textrange__input x-textrange__input--to']";
    String location = "//div[@class='x-overlay__sub-panel']//span[contains(text(),'US Only')]";
    String applyButton = "//div[@class='x-overlay-footer__apply']//button";
    String verifyPriceInHeader = "//h1";
    String priceList="//span[@class='s-item__price']";


    public void openEbayHomePage() {
        Driver.getDriver().get("https://www.ebay.com/");
    }

    public void clickOnShopByCategory() {
        Driver.getDriver().findElement(By.id(shopByCategory)).click();
    }

    public void selectCategory() {
        Driver.getDriver().findElement(By.xpath(category)).click();
    }

    public String verifyCategoryFilter() {
        String category = Driver.getDriver().findElement(By.xpath(verifyMainCategory)).getText() + " > " +
                Driver.getDriver().findElement(By.xpath(verifySubCategory)).getText();
        return category;
    }

    public void selectSubCategory() {
        Driver.getDriver().findElement(By.xpath(subCategory)).click();
    }

    public void clickOnAllFilter() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0,700)", "");
        Utility.waitAbit(5000);
        Driver.getDriver().findElement(By.xpath(allFilter)).click();
    }

    public void clickOnConditionFilter(String condition) {
        Utility.waitAbit(2000);
        Driver.getDriver().findElement(By.xpath(conditionFilter)).click();

        Utility.waitAbit(2000);
        Driver.getDriver().findElement(By.xpath(selectCondition)).click();
    }

    public void clickOnPriceFilter(String minPrice, String maxPrice) {
        Driver.getDriver().findElement(By.xpath(priceFilter)).click();
        Utility.waitAbit(2000);

        Driver.getDriver().findElement(By.xpath(this.minPrice)).sendKeys(minPrice);
        Driver.getDriver().findElement(By.xpath(this.maxPrice)).sendKeys(maxPrice);
    }

    public void clickOnItemLocationFilter(String itemLocation) {
        Driver.getDriver().findElement(By.xpath(itemLocationFilter)).click();

        Utility.waitAbit(2000);
        Driver.getDriver().findElement(By.xpath(location)).click();
    }

    public void clickOnApplyButton() {
        Driver.getDriver().findElement(By.xpath(applyButton)).click();
    }

    public String verifyPriceFilterApplied() {
        return  Driver.getDriver().findElement(By.xpath(verifyPriceInHeader)).getText();
    }

    public List<Boolean> verifyProductDisplayedAreInGivenRange() {
        List<Boolean> verify=new ArrayList<>();
        int minimumPrice=Integer.parseInt("100");
        int maximumPrice=Integer.parseInt("150");
        List<WebElement> price = Driver.getDriver().findElements(By.xpath(priceList));
        for(int i=1;i<=price.size();i++){
            String verifyPriceInList="(//span[@class='s-item__price'])["+i+"]";
            System.out.println(Driver.getDriver().findElement(By.xpath(verifyPriceInList)).getText());
            String splitPrice[]=Driver.getDriver().findElement(By.xpath(verifyPriceInList)).getText().split("\\.");
            System.out.println(splitPrice[0]);
            int convertPrice=Integer.parseInt(splitPrice[0].substring(1));
            if((convertPrice>=minimumPrice && convertPrice<=maximumPrice))
                verify.add(true);
            else if(splitPrice.length>1){
                int index=splitPrice[1].lastIndexOf("$");
                convertPrice=Integer.parseInt(splitPrice[1].substring(index+1));
                if((convertPrice>=minimumPrice && convertPrice<=maximumPrice))
                    verify.add(true);
            }
            else
                verify.add(false);
        }

        return verify;
    }
}
