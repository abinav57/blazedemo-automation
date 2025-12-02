package com.aajil.blazedemo.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    // you can parametrize browser or baseUrl via system properties later
    protected final String baseUrl = "https://blazedemo.com";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
        // navigate will be done in tests/pages
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
