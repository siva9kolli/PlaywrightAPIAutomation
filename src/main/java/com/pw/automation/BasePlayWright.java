package com.pw.automation;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeSuite;

public class BasePlayWright {
    public static Playwright playwright;
    public static APIRequest apiRequest;
    public static APIRequestContext apiRequestContext;

    @BeforeSuite
    public void initialize(){
        playwright = Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();

        /*
         //Set Base URL
            apiRequestContext = apiRequest.newContext(new APIRequest.NewContextOptions().setBaseURL(""));
         */
    }
}
