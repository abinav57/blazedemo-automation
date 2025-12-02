package com.aajil.blazedemo.tests;

import com.aajil.blazedemo.base.BaseTest;
import com.aajil.blazedemo.listeners.NoFlightsListener;
import com.aajil.blazedemo.pages.ConfirmationPage;
import com.aajil.blazedemo.pages.FlightsPage;
import com.aajil.blazedemo.pages.HomePage;
import com.aajil.blazedemo.pages.PurchasePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NoFlightsListener.class) // registers listener via annotation (optional if registered in testng.xml)
public class PurchaseFlowTest extends BaseTest {

    @DataProvider(name = "cities")
    public Object[][] cityData() {
        return new Object[][]{
                {"Boston", "New York"},
                {"Philadelphia", "London"},
                {"San Diego", "New York"},
                {"Portland", "Berlin"},
                {"Mexico City", "Cairo"}
        };
    }

    @Test(dataProvider = "cities")
    public void purchaseFlowTest(String from, String to) {
        HomePage home = new HomePage()
                .open(baseUrl)
                .selectFromCity(from)
                .selectToCity(to);

        FlightsPage flights = home.clickFindFlights();

        // Defensive check: if zero flights, throw a message the listener recognizes
        int available = flights.getAvailableFlightsCount();
        if (available == 0) {
            throw new RuntimeException("No flights available for " + from + " -> " + to);
        }

        // Choose first flight (chooseFlight itself also waits & throws controlled message on no flights)
        PurchasePage purchase = flights.chooseFlight(0);
        ConfirmationPage confirm = purchase.fillDetails().clickPurchase();

        String confirmationId = confirm.getConfirmationId();

        System.out.println("==== RESULT for " + from + " → " + to + " ====");
        System.out.println("Confirmation ID: " + confirmationId);
        System.out.println("=======================================");

        Assert.assertNotNull(confirmationId, "Confirmation ID is null");
        Assert.assertFalse(confirmationId.isEmpty(), "Confirmation ID is empty");
    }
}
