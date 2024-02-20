package APITests;

import DataManager.ReadJsonData;
import DataManager.WriteToProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SampleGetRequest {

    @Test
    public void getRequestCall() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();
        APIResponse apiResponse = apiRequestContext.get("https://reqres.in/api/users?page=2");
        Map<String, String> map = apiResponse.headers();
        System.out.println("Header values :: "+  map.get("content-type"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode body = objectMapper.readTree(apiResponse.body());
        JsonNode statusText = body.get("support").get("text");
        System.out.println(statusText);

        WriteToProperties writeToProperties = new WriteToProperties();
        writeToProperties.writeDataToProperties("supportText", body.get("support").get("text").asText());
       // writeToProperties.writeDataToProperties("supportUrl", body.get("support").get("url").asText());

        System.out.println("Body :: " + body.toPrettyString());
        System.out.println(apiResponse.status());
        int expectedCode = Integer.valueOf(ReadJsonData.readCapabilities("./testData/statusCodes.json","codes","validCode"));
        Assert.assertEquals(apiResponse.status(), expectedCode);

    }
}
