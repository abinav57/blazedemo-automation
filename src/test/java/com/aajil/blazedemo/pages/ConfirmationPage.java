package com.aajil.blazedemo.pages;

import com.aajil.blazedemo.base.BasePage;
import org.openqa.selenium.By;

public class ConfirmationPage extends BasePage {

    // Try robust selectors; adjust if your BlazeDemo variant differs
    private final By idCellCandidate1 = By.xpath("//table//tr[th/text()='Id' or td/text()='Id']/td[2]");
    private final By idCellCandidate2 = By.cssSelector("table tbody tr td");

    public String getConfirmationId() {
        try {
            String id = waitForVisible(idCellCandidate1).getText().trim();
            if (id != null && !id.isEmpty()) return id;
        } catch (Exception ignored) { }

        // fallback: first visible cell
        return waitForVisible(idCellCandidate2).getText().trim();
    }
}
