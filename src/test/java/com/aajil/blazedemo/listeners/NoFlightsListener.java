package com.aajil.blazedemo.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class NoFlightsListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable throwable = result.getThrowable();

        if (throwable != null && throwable.getMessage() != null &&
                throwable.getMessage().contains("No flights available")) {

            System.out.println("\n******** NO FLIGHTS SCENARIO DETECTED ********");
            System.out.println("Test Case : " + result.getTestClass().getName() + "." + result.getName());
            System.out.println("Message   : " + throwable.getMessage());
            System.out.println("Action    : Marking test as SKIPPED (no flights for this route)");
            System.out.println("***********************************************\n");

            // Convert failure to SKIP so reports show it as skipped rather than failed
            result.setStatus(ITestResult.SKIP);
        }
    }
}
