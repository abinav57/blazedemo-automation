package com.aajil.blazedemo.pages;

import com.aajil.blazedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FlightsPage extends BasePage {

    private final By flightRows = By.cssSelector("table tbody tr");
    private static final int LOCAL_WAIT_SECONDS = 10;

    /**
     * Returns number of available flights currently visible on the page.
     */
    public int getAvailableFlightsCount() {
        return driver.findElements(flightRows).size();
    }

    /**
     * Choose flight by zero-based index (0 => first flight).
     * Waits up to LOCAL_WAIT_SECONDS for rows to appear.
     * Throws RuntimeException with message containing "No flights available" if none found.
     */
    public PurchasePage chooseFlight(int index) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(LOCAL_WAIT_SECONDS));
        // wait until at least 1 row exists or timeout
        try {
            w.until(ExpectedConditions.numberOfElementsToBeMoreThan(flightRows, 0));
        } catch (Exception e) {
            // timeout waiting for rows -> throw a controlled message for the listener
            throw new RuntimeException("No flights available for the selected route");
        }

        List<WebElement> rows = driver.findElements(flightRows);
        if (rows.isEmpty()) {
            throw new RuntimeException("No flights available for the selected route");
        }

        if (index < 0 || index >= rows.size()) {
            throw new RuntimeException("Invalid flight index: " + index + " (available: " + rows.size() + ")");
        }

        WebElement row = rows.get(index);
        row.findElement(By.cssSelector("input[type='submit']")).click();

        return new PurchasePage();
    }
}
