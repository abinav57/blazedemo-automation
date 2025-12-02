package com.aajil.blazedemo.pages;

import com.aajil.blazedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    private final By fromCity = By.name("fromPort");
    private final By toCity = By.name("toPort");
    private final By findFlightsBtn = By.cssSelector("input[type='submit']");

    public HomePage open(String url) {
        navigateTo(url);
        return this;
    }

    public HomePage selectFromCity(String city) {
        Select sel = new Select(waitForVisible(fromCity));
        sel.selectByVisibleText(city);
        return this;
    }

    public HomePage selectToCity(String city) {
        Select sel = new Select(waitForVisible(toCity));
        sel.selectByVisibleText(city);
        return this;
    }

    public FlightsPage clickFindFlights() {
        click(findFlightsBtn);
        return new FlightsPage();
    }
}
