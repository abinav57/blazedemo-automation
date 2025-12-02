package com.aajil.blazedemo.tests;

import com.aajil.blazedemo.base.BaseTest;
import com.aajil.blazedemo.base.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void openHomePage() {
        DriverManager.getDriver().get(baseUrl);
        // check page title contains "BlazeDemo" or header
        String title = DriverManager.getDriver().getTitle();
        // There may be different titles; assert that page loaded by verifying presence of "home" heading or flights selector.
        boolean hasChooseFlights = DriverManager.getDriver().findElements(By.cssSelector("select[name='fromPort']")).size() > 0;
        Assert.assertTrue(hasChooseFlights, "Home page did not load - departure select missing");
    }
}
