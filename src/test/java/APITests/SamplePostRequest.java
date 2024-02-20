package APITests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SamplePostRequest {

    @Test
    public void samplePostRequestCall() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();

        Map<String, String> payload = new HashMap<>();
        payload.put("name","morpheus");
        payload.put("job", "leader");

        APIResponse apiResponse = apiRequestContext.post("https://reqres.in/api/users", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(payload));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonNode.toPrettyString());
    }
}
