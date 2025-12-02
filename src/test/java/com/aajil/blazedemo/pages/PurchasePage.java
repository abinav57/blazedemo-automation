package com.aajil.blazedemo.pages;

import com.aajil.blazedemo.base.BasePage;
import org.openqa.selenium.By;

public class PurchasePage extends BasePage {

    private final By name = By.id("inputName");
    private final By address = By.id("address");
    private final By city = By.id("city");
    private final By state = By.id("state");
    private final By zipCode = By.id("zipCode");
    private final By creditCard = By.id("creditCardNumber"); // may vary site-to-site
    private final By purchaseBtn = By.cssSelector("input[type='submit']");

    public PurchasePage fillDetails() {
        type(name, "Test User");
        type(address, "123 Main Street");
        type(city, "TestCity");
        type(state, "TS");
        type(zipCode, "12345");
        type(creditCard, "4111111111111111");
        return this;
    }

    public ConfirmationPage clickPurchase() {
        click(purchaseBtn);
        return new ConfirmationPage();
    }
}
