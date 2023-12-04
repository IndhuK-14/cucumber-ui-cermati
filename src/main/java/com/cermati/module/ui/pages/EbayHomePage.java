package com.cermati.module.ui.pages;


import com.cermati.module.ui.util.Driver;
import com.cermati.module.ui.util.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class EbayHomePage {

    //Locators
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
    String productList="//li[@class='s-item s-item--large']//a";
    String verifyCondition="(//div[@class='x-item-condition-text']//span[@class='ux-textspans'])[1]";
    String verifyLocation = "(//div[@class='ux-labels-values__values-content']//span[@class='ux-textspans ux-textspans--SECONDARY'])[1]";
    String search = "//td[@class='gh-td-s']//input[@class='gh-tb ui-autocomplete-input']";
    String computerCategory = "//div[@id='gh-sbc-o']//a[contains(text(),'Computers & tablets')]";
    String verifyComputerCategory = "(//ul[@class='srp-refine__category__list']//li[@data-state='selected']//span)[1]";
    String productNameList="//div[@class='s-item__title']";

    List<String> condition;
    List<String> itemLocation;
    List<String> productName;
    String minPriceValue;
    String maxPriceValue;


    public void openEbayHomePage() {
        Driver.getDriver().get("https://www.ebay.com/");
    }

    public void clickOnShopByCategory() {
        Driver.getDriver().findElement(By.id(shopByCategory)).click();
    }

    public void selectCategory() {
        Driver.getDriver().findElement(By.xpath(this.category)).click();
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

    public void clickOnConditionFilter() {
        Utility.waitAbit(2000);
        Driver.getDriver().findElement(By.xpath(conditionFilter)).click();

        Utility.waitAbit(2000);
        Driver.getDriver().findElement(By.xpath(selectCondition)).click();
    }

    public void clickOnPriceFilter(String minPrice, String maxPrice) {
        minPriceValue=minPrice;
        maxPriceValue=maxPrice;

        Driver.getDriver().findElement(By.xpath(priceFilter)).click();
        Utility.waitAbit(2000);

        Driver.getDriver().findElement(By.xpath(this.minPrice)).sendKeys(minPrice);
        Driver.getDriver().findElement(By.xpath(this.maxPrice)).sendKeys(maxPrice);
    }

    public void clickOnItemLocationFilter() {
        Driver.getDriver().findElement(By.xpath(itemLocationFilter)).click();

        Utility.waitAbit(4000);
        Driver.getDriver().findElement(By.xpath(location)).click();
    }

    public void clickOnApplyButton() {
        Driver.getDriver().findElement(By.xpath(applyButton)).click();
    }

    public String verifyPriceFilterApplied() {
        return  Driver.getDriver().findElement(By.xpath(verifyPriceInHeader)).getText();
    }

    public List<Boolean> verifyProductDisplayedAreInGivenRange() {
        //iterate over filter results and compare the price range
        List<Boolean> verify=new ArrayList<>();
        System.out.println(minPriceValue);
        int minimumPrice=Integer.parseInt(minPriceValue);
        int maximumPrice=Integer.parseInt(maxPriceValue);
        List<WebElement> price = Driver.getDriver().findElements(By.xpath(priceList));
        for(int i=1;i<=price.size();i++){
            String verifyPriceInList="("+priceList+")["+i+"]";
            String splitPrice[]=Driver.getDriver().findElement(By.xpath(verifyPriceInList)).getText().split("\\.");
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

    public List<String> verifyConditionFilterApplied() {
        //In new tab,open first 5 product url from the filter results and store condition and item location
        condition=new ArrayList<>();
        itemLocation=new ArrayList<>();

        for(int i=1;i<=2;i++){
            String prodLink="("+productList+")["+i+"]";
            prodLink=Driver.getDriver().findElement(By.xpath(prodLink)).getAttribute("href");
            ((JavascriptExecutor)Driver.getDriver()).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(Driver.getDriver().getWindowHandles());
            Driver.getDriver().switchTo().window(tabs.get(1));
            Driver.getDriver().get(prodLink);

            condition.add(Driver.getDriver().findElement(By.xpath(verifyCondition)).getText());
            itemLocation.add(Driver.getDriver().findElement(By.xpath(verifyLocation)).getText());

            Driver.getDriver().close();
            Driver.getDriver().switchTo().window(tabs.get(0));
        }
        return condition;
    }

    public List<String> verifyLocationFilterApplied() {
        return itemLocation;
    }


    public void searchByKeyword(String keyword) {
        Driver.getDriver().findElement(By.xpath(search)).sendKeys(keyword);
        Utility.waitAbit(2000);
        Driver.getDriver().findElement(By.xpath(search)).sendKeys(Keys.RETURN);
        Utility.waitAbit(2000);
    }

    public void applyComputerCategoryFilter() {
        Driver.getDriver().findElement(By.xpath(computerCategory)).click();
    }

    public String verifyComputerCategoryFilterApplied() {
        return Driver.getDriver().findElement(By.xpath(verifyComputerCategory)).getText();
    }

    public List<String> verifySearchFilterApplied() {
        //iterate over search results and store product name
        productName = new ArrayList<>();
        List<WebElement> product = Driver.getDriver().findElements(By.xpath(productNameList));
        for(int i=2;i<=product.size();i++){
            String name="("+productNameList+")["+i+"]";
            productName.add(Driver.getDriver().findElement(By.xpath(name)).getText().toLowerCase());
        }
        return productName;
    }
}
